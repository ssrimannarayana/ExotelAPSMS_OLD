package com.kgl.KglServices.services;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kgl.KglServices.model.CampaignPojo;
import com.kgl.KglServices.model.PhonepeObj;
import com.kgl.KglServices.model.PhonepeResponseObj;
import com.kgl.KglServices.model.ScriptObj;
import com.kgl.KglServices.utility.Utility;

@Service
public class PhonePeServcies {

	@Value("${END_TIME}")
	private String end_time;

	@Value("${Exotel_Campaign_Url}")
	private String exotel_Campaign_Url;

	@Value("${SMS_API_URL}")
	private String Sms_Api_url;

	@Value("${Exotel_auth_token}")
	private String Exotel_auth_token;

	@Value("${ServerUrl}")
	private String serverUrl;

	@Value("${AP_SMS_UPDATE_DATA_INTO_GOOGLESHEET}")
	private String AP_SMS_UPDATE_GOOGLESHEET_URL;

	@Value("${AP_PHPE_CALLBACK_UPDATE_DATA_INTO_GOOGLESHEET}")
	private String AP_PHPE_CALLBACK_UPDATE_GOOGLESHEET_URL;

	@Value("${CASHFREE_CALLBACK_UPDATE_GOOGLESHEET_URL}")
	private String CASHFREE_CALLBACK_UPDATE_GOOGLESHEET_URL;

	@Autowired
	private Utility utility;

	private static final Logger logger = LoggerFactory.getLogger(PhonePeServcies.class);

	@Async
	public CompletableFuture<List<CampaignPojo>> startSmsCampaignList(List<CampaignPojo> campList)
			throws JsonProcessingException, ParseException, java.text.ParseException {
		String dateFormat = "HH:mm:ss";
		for (CampaignPojo campaignPojo : campList) {
			if(campaignPojo.getMOBILE_NUMBER().length()<10)
			{
				logger.info("AP SMS service failed due to wrong phno::"+ campaignPojo.getMOBILE_NUMBER());
				continue;
			}
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
			LocalDateTime now = LocalDateTime.now();
			Date startTime = new SimpleDateFormat(dateFormat).parse(dtf.format(now));
			Date endTime = new SimpleDateFormat(dateFormat).parse(end_time);
			long campaign = utility.getCampaign(campaignPojo.getDUE_DATE());
			campaignPojo.setCAMPAIGN(campaign);
			if (startTime.before(endTime)) {
				startExotelSmsCampaign(campaignPojo);
			} else {
				logger.info("AP SMS service stopped due to time out");
				break;
			}
		}
		return CompletableFuture.completedFuture(campList);
	}

	private String startExotelSmsCampaign(CampaignPojo camp) throws ParseException, JsonProcessingException {
		logger.info("Method: APstartExotelSmsCampaign:: " + camp.getMOBILE_NUMBER());
		String id = camp.getID();
		ScriptObj scobj = new ScriptObj();
		scobj.setSno(camp.getID());
		scobj.setLoanamount(String.valueOf(camp.getDUE_AMOUNT()).replaceAll(",", ""));
		scobj.setPhone(String.valueOf(camp.getMOBILE_NUMBER()));
		scobj.setLoanacno(camp.getACCOUNT_NUMBER());
		scobj.setState(camp.getSTATE());
		scobj.setDuedate(camp.getDUE_DATE());
		scobj.setCampaignSheet(camp.getCAMPAIGN_SHEET());
		scobj.setName(camp.getNAME());
		String payLink = phonepeLinkScriptApi(scobj);
		scobj.setPaylink(payLink);
		Map<String, String> smsRespData = sendSMSByScript(scobj);
		String smsSid = smsRespData.get("smsSid");
		String smsStatus = smsRespData.get("smsStatus");
		String detailedStatusCode = smsRespData.get("DetailedStatusCode");
		String phpeLink = smsRespData.get("phpeLink");
		updateSmsApiIntoAppSheet(id, smsSid, smsStatus, detailedStatusCode, phpeLink, "SmsApi",
				camp.getCAMPAIGN_SHEET());
		logger.info("ID:: " + id + " STATUS::" + smsStatus);
		return smsStatus;
	}

	public String phonepeLinkScriptApi(ScriptObj emi) throws JsonProcessingException {
		String phpeCallbackUrl = serverUrl + "/call/phpeCallStatusApi/" + emi.getSno();
		PhonepeObj phpe = new PhonepeObj();
		phpe.setMerchantId("APKANAKDURGAFINANCE");
		phpe.setTransactionId(
				"KFL" + emi.getState().toUpperCase() + randomNumber() + emi.getLoanacno().replace("/", ""));
		phpe.setMerchantOrderId("KFL" + emi.getState().toUpperCase() + emi.getLoanacno().replace("/", ""));
		phpe.setAmount(Long.valueOf(emi.getLoanamount()) * 100);
		phpe.setMessage("collect for 1 order");
		phpe.setExpiresIn(604800);
		ObjectMapper mapper = new ObjectMapper();
		String jobj = mapper.writeValueAsString(phpe);
		String url = "https://mercury-t2.phonepe.com/v3/payLink/init";
		String pathUrl = url.substring(30);
		String reqString = utility.getReqString(jobj);
		String x_verify = utility.getXverifyCode(reqString, pathUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.add("X-VERIFY", x_verify);
		headers.add("X-CALLBACK-URL", phpeCallbackUrl);
		Map<String, String> map = new HashMap<>();
		map.put("request", reqString);
		HttpEntity<Map<String, String>> entity = new HttpEntity<>(map, headers);
		String json = null, message = "NA", code = "NA";
		String transactionId = "NA", merchantId = "NA", payLink = "NA";
		long amount;
		boolean status = false;
		try {
			ResponseEntity<String> response = new RestTemplate().postForEntity(url, entity, String.class);
			json = response.getBody();
			JSONObject jsonObject = new JSONObject(json);
			status = jsonObject.getBoolean("success");
			code = jsonObject.getString("code");
			message = jsonObject.getString("message");
			JSONObject jsonObject2 = new JSONObject(json).getJSONObject("data");
			if (code.equalsIgnoreCase("SUCCESS")) {
				transactionId = jsonObject2.getString("transactionId");
				amount = jsonObject2.getLong("amount");
				merchantId = jsonObject2.getString("merchantId");
				payLink = jsonObject2.getString("payLink");
			} else {
				payLink = "NA";
			}
		} catch (Exception e) {
			logger.info("error:" + e);
		}
		Map<String, String> js = new HashMap<String, String>();
		js.put("status", String.valueOf(status));
		js.put("code", code);
		js.put("message", message);
		js.put("merchantOrderId", merchantId);
		js.put("transactionId", transactionId);
		js.put("payLink", payLink);
		js.put("METHOD", "PHPE_LINK");
		return payLink;
	}

	// sending sms api//
	public Map<String, String> sendSMSByScript(ScriptObj product) throws ParseException {
		String phpeLink = product.getPaylink();
		String StatusCallback = serverUrl + "/call/exotelSmsCallBack/" + product.getSno();
		String btext = getSms(product);
		String from = "KKDFIN";
		String toPerson = product.getPhone();
		String body = btext;
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("From", from);
		map.add("To", toPerson);
		map.add("Body", body);
		// map.add("StatusCallback", StatusCallback);
		map.add("CustomField", "venkey");
		map.add("ShortenUrl", "true");
		map.add("ShortenUrlParams[Tracking]", "true");
		map.add("ShortenUrlParams[ClickTrackingCallbackUrl]", StatusCallback);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set("Authorization", Exotel_auth_token);
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> restTemplate = new RestTemplate().exchange(Sms_Api_url, HttpMethod.POST, entity,
				String.class);
		String resp = restTemplate.getBody();
		JSONObject jobject = new JSONObject(resp);
		JSONObject jobj = jobject.getJSONObject("SMSMessage");
		String smsSid = jobj.getString("Sid");
		String smsStatus = jobj.getString("Status");
		long DetailedStatusCode = jobj.getLong("DetailedStatusCode");
		String detailedCode = String.valueOf(DetailedStatusCode);
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("smsSid", smsSid);
		map2.put("smsStatus", smsStatus);
		map2.put("DetailedStatusCode", detailedCode);
		map2.put("phpeLink", phpeLink);
		return map2;
	}

	// message mapping api//
	private String getSms(ScriptObj sc) {
		String sms = null;
		if (sc.getState().equalsIgnoreCase("ap") || sc.getState().equalsIgnoreCase("ts")) {

			sms = "ప్రియమైన " + sc.getName()
					+ ",మీ వాహనము తాలుకు నెలవారి వాయిదా చెల్లించవలసివున్నది,కావున వెంటనే చెల్లించగలరు.చెల్లించుటకు ఈ లింక్ ఉపయోగించగలరు. "
					+ sc.getPaylink() + " -కనకదుర్గ ఫైనాన్స్.టోల్ఫ్రీ నెం:04045207945";

		} else if (sc.getState().equalsIgnoreCase("ka")) {

			sms = "ಆತ್ಮೀಯ " + sc.getName()
					+ ",ನಿಮ್ಮ ವಾಹನದ ಮಾಸಿಕ ಕಂತು ಬಾಕಿಯಿದೆ. ಆದ್ದರಿಂದ ನೀವು ತಕ್ಷಣ ಪಾವತಿಸಬಹುದು. ಪಾವತಿಸಲು ಈ ಲಿಂಕ್ ಬಳಸಿ. "
					+ sc.getPaylink() + " -Kanakadurga Finance.Tollfree No:08069458347";

		} else if (sc.getState().equalsIgnoreCase("gj")) {
			sms = "પ્રિય " + sc.getName()
					+ ",તમારું વાહન માસિક હપ્તા ભરવાનું બાકી છે. તેથી તમે તરત જ ચૂકવણી કરી શકો છો. ચૂકવણી કરવા માટે આ લિંકનો ઉપયોગ કરો. "
					+ sc.getPaylink() + " -Kanakadurga Finance.Tollfree No:07948222437";

		} else if (sc.getState().equalsIgnoreCase("tn")) {
			sms = "அன்புள்ள sindu,உங்கள் வாகனம் மாத தவணை செலுத்த வேண்டியுள்ளது. எனவே நீங்கள் உடனடியாக பணம் செலுத்தலாம். பணம் செலுத்த இந்த இணைப்பைப் பயன்படுத்தவும். https://phon.pe/v28wicb8-Kanakadurga Finance.Tollfree No:04440114693";

		}
		return sms;
	}

	private int randomNumber() {
		int min = 9999;
		int max = 1000;
		int random_int = (int) (Math.random() * (max - min + 1) + min);
		return random_int;
	}

	public void updateSmsApiIntoAppSheet(String id, String smsSid, String smsStatus, String detailedStatusCode,
			String phpeLink, String method, String campaignSheet) {
		String url = AP_SMS_UPDATE_GOOGLESHEET_URL;
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("id", id);
		map.add("smsSid", smsSid);
		map.add("smsStatus", smsStatus);
		map.add("detailedStatusCode", detailedStatusCode);
		map.add("phpeLink", phpeLink);
		map.add("method", method);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> restTemplate = new RestTemplate().exchange(url, HttpMethod.POST, entity, String.class);
	}

	public void updateSmsCallBackApiIntoAppSheet(String id, String smsCampaignSid, String created_time,
			String last_viewed, String total_clicks, String device, String region, String city, String accuracy_radius,
			String method) {
		String url = AP_SMS_UPDATE_GOOGLESHEET_URL;
		// TODO Auto-generated method stub
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("id", id);
		map.add("smsSid", smsCampaignSid);
		map.add("created_time", created_time);
		map.add("last_viewed", last_viewed);
		map.add("total_clicks", total_clicks);
		map.add("device", device);
		map.add("region", region);
		map.add("city", city);
		map.add("accuracy_radius", accuracy_radius);
		map.add("method", method);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> restTemplate = new RestTemplate().exchange(url, HttpMethod.POST, entity, String.class);
	}

	public String updatePhpeCallbackResponseIntoGoogleSheet(PhonepeResponseObj phpeResobj) {
		// TODO Auto-generated method stub
		String url = AP_PHPE_CALLBACK_UPDATE_GOOGLESHEET_URL;
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("id", phpeResobj.getId());
		// map.add("campaignSheet",phpeResobj.getCampaignSheet() );
		map.add("status", String.valueOf(phpeResobj.isSuccess()));
		map.add("code", phpeResobj.getCode());
		map.add("message", phpeResobj.getMessage());
		// map.add("merchantId",phpeResobj.getMerchantId() );
		map.add("transactionId", phpeResobj.getTransactionId());
		map.add("providerReferenceId", phpeResobj.getProviderReferenceId());
		map.add("paymentState", phpeResobj.getPaymentState());
		// map.add("payResponseCode",phpeResobj.getPayResponseCode() );
		// map.add("mode",phpeResobj.getMode() );
		map.add("ifsc", phpeResobj.getIfsc());
		map.add("utr", phpeResobj.getUtr());
		map.add("upiTransactionId", phpeResobj.getTransactionId());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> restTemplate = new RestTemplate().exchange(url, HttpMethod.POST, entity, String.class);
		return "success";
	}

	public String updateCahsFreeSmsCallBackApiIntoAppSheet(String event_type, String cf_subReferenceId,
			String cf_status, String cf_lastStatus, String cf_eventTime, String cf_subscriptionId, String signature) {
		String url = CASHFREE_CALLBACK_UPDATE_GOOGLESHEET_URL;
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("cf_subscriptionId", cf_subscriptionId);
		map.add("event_type", event_type);
		map.add("cf_subReferenceId", cf_subReferenceId);
		map.add("cf_status", cf_status);
		map.add("cf_lastStatus", cf_lastStatus);
		map.add("cf_eventTime", cf_eventTime);
		map.add("signature", signature);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> restTemplate = new RestTemplate().exchange(url, HttpMethod.POST, entity, String.class);
		return "success";

	}
}
