package com.kgl.KglServices.controller;

import java.util.Base64;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kgl.KglServices.model.CampaignPojo;
import com.kgl.KglServices.model.PhonepeResponseObj;
import com.kgl.KglServices.services.PhonePeServcies;

@RestController
@RequestMapping({ "/call" })
public class CallrecordingApiController {

	private static final Logger logger = LoggerFactory.getLogger(CallrecordingApiController.class);

	@Autowired
	private PhonePeServcies phpeServices;

	@Value("${AP_SMS_CAMPAIGN_JSON_RESPONSE}")
	private String ap_sms_googlesheet_data_restapi;

	@GetMapping("/testApi")
	public String test() {
		return "hi";
	}

	@PostMapping("/ExotelSmsAP")
	public ResponseEntity<String> ExotelSmsAP() throws JsonMappingException, JsonProcessingException  {
		String AP_URL = ap_sms_googlesheet_data_restapi;
		List<CampaignPojo> artist = getCampDataAPAndROAP(AP_URL);
		int recordsSize = artist.size();
		logger.info("AP SMS google sheet api records coutnt:::" + recordsSize);
		List<CampaignPojo> sublist1 = null;
		List<CampaignPojo> sublist2 = null;
		List<CampaignPojo> sublist3 = null;
		List<CampaignPojo> sublist4 = null;
		List<CampaignPojo> sublist5 = null;
		List<CampaignPojo> sublist6 = null;
		List<CampaignPojo> sublist7 = null;
		List<CampaignPojo> sublist8 = null;
		List<CampaignPojo> sublist9 = null;
		List<CampaignPojo> sublist10 = null;
		int rowSize = 2000;
		try {
			if (artist.size() > 0 && artist.size() > 2000) {

				if (recordsSize >= 0 && recordsSize <= 2000) {
					sublist1 = artist.subList(0, rowSize * 1);
					CompletableFuture<List<CampaignPojo>> cars1 = phpeServices.startSmsCampaignList(sublist1);
					CompletableFuture.allOf(cars1).join();
				}
				if (recordsSize >= 2001 && recordsSize <= 4000) {
					sublist1 = artist.subList(0, rowSize * 1);
					sublist2 = artist.subList((rowSize + 1), artist.size());
					CompletableFuture<List<CampaignPojo>> cars1 = phpeServices.startSmsCampaignList(sublist1);
					CompletableFuture<List<CampaignPojo>> cars2 = phpeServices.startSmsCampaignList(sublist2);
					CompletableFuture.allOf(cars1, cars2).join();
				} else if (recordsSize >= 4001 && recordsSize <= 6000) {
					sublist1 = artist.subList(0, rowSize * 1);
					sublist2 = artist.subList((rowSize + 1), rowSize * 2);
					sublist3 = artist.subList((rowSize * 2) + 1, artist.size());
					CompletableFuture<List<CampaignPojo>> cars1 = phpeServices.startSmsCampaignList(sublist1);
					CompletableFuture<List<CampaignPojo>> cars2 = phpeServices.startSmsCampaignList(sublist2);
					CompletableFuture<List<CampaignPojo>> cars3 = phpeServices.startSmsCampaignList(sublist3);
					CompletableFuture.allOf(cars1, cars2, cars3).join();
				} else if (recordsSize >= 6001 && recordsSize <= 8000) {
					sublist1 = artist.subList(0, rowSize * 1);
					sublist2 = artist.subList((rowSize + 1), rowSize * 2);
					sublist3 = artist.subList((rowSize * 2) + 1, rowSize * 3);
					sublist4 = artist.subList((rowSize * 3) + 1, artist.size());
					CompletableFuture<List<CampaignPojo>> cars1 = phpeServices.startSmsCampaignList(sublist1);
					CompletableFuture<List<CampaignPojo>> cars2 = phpeServices.startSmsCampaignList(sublist2);
					CompletableFuture<List<CampaignPojo>> cars3 = phpeServices.startSmsCampaignList(sublist3);
					CompletableFuture<List<CampaignPojo>> cars4 = phpeServices.startSmsCampaignList(sublist4);
					CompletableFuture.allOf(cars1, cars2, cars3, cars4).join();
				} else if (recordsSize >= 8001 && recordsSize <= 10000) {
					sublist1 = artist.subList(0, rowSize * 1);
					sublist2 = artist.subList((rowSize + 1), rowSize * 2);
					sublist3 = artist.subList((rowSize * 2) + 1, rowSize * 3);
					sublist4 = artist.subList((rowSize * 3) + 1, rowSize * 4);
					sublist5 = artist.subList((rowSize * 4) + 1, artist.size());
					CompletableFuture<List<CampaignPojo>> cars1 = phpeServices.startSmsCampaignList(sublist1);
					CompletableFuture<List<CampaignPojo>> cars2 = phpeServices.startSmsCampaignList(sublist2);
					CompletableFuture<List<CampaignPojo>> cars3 = phpeServices.startSmsCampaignList(sublist3);
					CompletableFuture<List<CampaignPojo>> cars4 = phpeServices.startSmsCampaignList(sublist4);
					CompletableFuture<List<CampaignPojo>> cars5 = phpeServices.startSmsCampaignList(sublist5);
					CompletableFuture.allOf(cars1, cars2, cars3, cars4, cars5).join();
				} else if (recordsSize >= 10001 && recordsSize <= 12000) {
					sublist1 = artist.subList(0, rowSize * 1);
					sublist2 = artist.subList((rowSize + 1), rowSize * 2);
					sublist3 = artist.subList((rowSize * 2) + 1, rowSize * 3);
					sublist4 = artist.subList((rowSize * 3) + 1, rowSize * 4);
					sublist5 = artist.subList((rowSize * 4) + 1, rowSize * 5);
					sublist6 = artist.subList((rowSize * 5) + 1, artist.size());
					CompletableFuture<List<CampaignPojo>> cars1 = phpeServices.startSmsCampaignList(sublist1);
					CompletableFuture<List<CampaignPojo>> cars2 = phpeServices.startSmsCampaignList(sublist2);
					CompletableFuture<List<CampaignPojo>> cars3 = phpeServices.startSmsCampaignList(sublist3);
					CompletableFuture<List<CampaignPojo>> cars4 = phpeServices.startSmsCampaignList(sublist4);
					CompletableFuture<List<CampaignPojo>> cars5 = phpeServices.startSmsCampaignList(sublist5);
					CompletableFuture<List<CampaignPojo>> cars6 = phpeServices.startSmsCampaignList(sublist6);
					CompletableFuture.allOf(cars1, cars2, cars3, cars4, cars5, cars6).join();
				} else if (recordsSize >= 12001 && recordsSize <= 14000) {
					sublist1 = artist.subList(0, rowSize * 1);
					sublist2 = artist.subList((rowSize + 1), rowSize * 2);
					sublist3 = artist.subList((rowSize * 2) + 1, rowSize * 3);
					sublist4 = artist.subList((rowSize * 3) + 1, rowSize * 4);
					sublist5 = artist.subList((rowSize * 4) + 1, rowSize * 5);
					sublist6 = artist.subList((rowSize * 5) + 1, rowSize * 6);
					sublist7 = artist.subList((rowSize * 6) + 1, artist.size());
					CompletableFuture<List<CampaignPojo>> cars1 = phpeServices.startSmsCampaignList(sublist1);
					CompletableFuture<List<CampaignPojo>> cars2 = phpeServices.startSmsCampaignList(sublist2);
					CompletableFuture<List<CampaignPojo>> cars3 = phpeServices.startSmsCampaignList(sublist3);
					CompletableFuture<List<CampaignPojo>> cars4 = phpeServices.startSmsCampaignList(sublist4);
					CompletableFuture<List<CampaignPojo>> cars5 = phpeServices.startSmsCampaignList(sublist5);
					CompletableFuture<List<CampaignPojo>> cars6 = phpeServices.startSmsCampaignList(sublist6);
					CompletableFuture<List<CampaignPojo>> cars7 = phpeServices.startSmsCampaignList(sublist7);
					CompletableFuture.allOf(cars1, cars2, cars3, cars4, cars5, cars6, cars7).join();
				} else if (recordsSize >= 14001 && recordsSize <= 16000) {
					sublist1 = artist.subList(0, rowSize * 1);
					sublist2 = artist.subList((rowSize + 1), rowSize * 2);
					sublist3 = artist.subList((rowSize * 2) + 1, rowSize * 3);
					sublist4 = artist.subList((rowSize * 3) + 1, rowSize * 4);
					sublist5 = artist.subList((rowSize * 4) + 1, rowSize * 5);
					sublist6 = artist.subList((rowSize * 5) + 1, rowSize * 6);
					sublist7 = artist.subList((rowSize * 6) + 1, rowSize * 7);
					sublist8 = artist.subList((rowSize * 7) + 1, artist.size());
					CompletableFuture<List<CampaignPojo>> cars1 = phpeServices.startSmsCampaignList(sublist1);
					CompletableFuture<List<CampaignPojo>> cars2 = phpeServices.startSmsCampaignList(sublist2);
					CompletableFuture<List<CampaignPojo>> cars3 = phpeServices.startSmsCampaignList(sublist3);
					CompletableFuture<List<CampaignPojo>> cars4 = phpeServices.startSmsCampaignList(sublist4);
					CompletableFuture<List<CampaignPojo>> cars5 = phpeServices.startSmsCampaignList(sublist5);
					CompletableFuture<List<CampaignPojo>> cars6 = phpeServices.startSmsCampaignList(sublist6);
					CompletableFuture<List<CampaignPojo>> cars7 = phpeServices.startSmsCampaignList(sublist7);
					CompletableFuture<List<CampaignPojo>> cars8 = phpeServices.startSmsCampaignList(sublist8);
					CompletableFuture.allOf(cars1, cars2, cars3, cars4, cars5, cars6, cars7, cars8).join();
				} else if (recordsSize >= 16001 && recordsSize <= 18000) {
					sublist1 = artist.subList(0, rowSize * 1);
					sublist2 = artist.subList((rowSize + 1), rowSize * 2);
					sublist3 = artist.subList((rowSize * 2) + 1, rowSize * 3);
					sublist4 = artist.subList((rowSize * 3) + 1, rowSize * 4);
					sublist5 = artist.subList((rowSize * 4) + 1, rowSize * 5);
					sublist6 = artist.subList((rowSize * 5) + 1, rowSize * 6);
					sublist7 = artist.subList((rowSize * 6) + 1, rowSize * 7);
					sublist8 = artist.subList((rowSize * 7) + 1, rowSize * 8);
					sublist9 = artist.subList((rowSize * 8) + 1, artist.size());
					CompletableFuture<List<CampaignPojo>> cars1 = phpeServices.startSmsCampaignList(sublist1);
					CompletableFuture<List<CampaignPojo>> cars2 = phpeServices.startSmsCampaignList(sublist2);
					CompletableFuture<List<CampaignPojo>> cars3 = phpeServices.startSmsCampaignList(sublist3);
					CompletableFuture<List<CampaignPojo>> cars4 = phpeServices.startSmsCampaignList(sublist4);
					CompletableFuture<List<CampaignPojo>> cars5 = phpeServices.startSmsCampaignList(sublist5);
					CompletableFuture<List<CampaignPojo>> cars6 = phpeServices.startSmsCampaignList(sublist6);
					CompletableFuture<List<CampaignPojo>> cars7 = phpeServices.startSmsCampaignList(sublist7);
					CompletableFuture<List<CampaignPojo>> cars8 = phpeServices.startSmsCampaignList(sublist8);
					CompletableFuture<List<CampaignPojo>> cars9 = phpeServices.startSmsCampaignList(sublist9);
					CompletableFuture.allOf(cars1, cars2, cars3, cars4, cars5, cars6, cars7, cars8, cars9).join();
				} else if (recordsSize >= 18001 && recordsSize <= 20000) {
					sublist1 = artist.subList(0, rowSize * 1);
					sublist2 = artist.subList((rowSize + 1), rowSize * 2);
					sublist3 = artist.subList((rowSize * 2) + 1, rowSize * 3);
					sublist4 = artist.subList((rowSize * 3) + 1, rowSize * 4);
					sublist5 = artist.subList((rowSize * 4) + 1, rowSize * 5);
					sublist6 = artist.subList((rowSize * 5) + 1, rowSize * 6);
					sublist7 = artist.subList((rowSize * 6) + 1, rowSize * 7);
					sublist8 = artist.subList((rowSize * 7) + 1, rowSize * 8);
					sublist9 = artist.subList((rowSize * 8) + 1, rowSize * 9);
					sublist10 = artist.subList((rowSize * 9) + 1, artist.size());
					CompletableFuture<List<CampaignPojo>> cars1 = phpeServices.startSmsCampaignList(sublist1);
					CompletableFuture<List<CampaignPojo>> cars2 = phpeServices.startSmsCampaignList(sublist2);
					CompletableFuture<List<CampaignPojo>> cars3 = phpeServices.startSmsCampaignList(sublist3);
					CompletableFuture<List<CampaignPojo>> cars4 = phpeServices.startSmsCampaignList(sublist4);
					CompletableFuture<List<CampaignPojo>> cars5 = phpeServices.startSmsCampaignList(sublist5);
					CompletableFuture<List<CampaignPojo>> cars6 = phpeServices.startSmsCampaignList(sublist6);
					CompletableFuture<List<CampaignPojo>> cars7 = phpeServices.startSmsCampaignList(sublist7);
					CompletableFuture<List<CampaignPojo>> cars8 = phpeServices.startSmsCampaignList(sublist8);
					CompletableFuture<List<CampaignPojo>> cars9 = phpeServices.startSmsCampaignList(sublist9);
					CompletableFuture<List<CampaignPojo>> cars10 = phpeServices.startSmsCampaignList(sublist10);
					CompletableFuture.allOf(cars1, cars2, cars3, cars4, cars5, cars6, cars7, cars8, cars9, cars10)
							.join();
				}
			} else {
				if (recordsSize >= 0 && recordsSize <= 2000)
					sublist1 = artist.subList(0, artist.size());
				CompletableFuture<List<CampaignPojo>> cars1 = phpeServices.startSmsCampaignList(sublist1);
				CompletableFuture.allOf(cars1).join();
			}
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			logger.info("google SMS sheet api response No records found:::" + e);
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	private List<CampaignPojo> getCampDataAPAndROAP(String campaign_ListUrls)
			throws JsonMappingException, JsonProcessingException {
		List<CampaignPojo> responseData = null;
		try {
			responseData = getDataFromApi(campaign_ListUrls);
		} catch (Exception e) {
			logger.info("google sheet api response No records found:::" + e);
		}
		return responseData;
	}

	private List<CampaignPojo> getDataFromApi(String c) throws JsonMappingException, JsonProcessingException {
		ResponseEntity<String> response = new RestTemplate().getForEntity(c, String.class);
		ObjectMapper objectMapper;
		List<CampaignPojo> responseDataObj = null;
		if (response != null) {
			String stringArry = response.getBody().toString();
			objectMapper = new ObjectMapper();
			responseDataObj = objectMapper.readValue(stringArry, new TypeReference<List<CampaignPojo>>() {
			});
		}
		return responseDataObj;
	}

	@RequestMapping(path = "/phpeCallStatusApi/{id}", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> phpeCallStatusApi(@PathVariable String id,
			@RequestBody String callBackResponse) throws JsonMappingException, JsonProcessingException, ParseException {
		ResponseEntity<String> respStatus = null;
		logger.info("Method:phpeCallStatusApi ID ::" + id);
		try {
			JSONObject jobj = jsonParsing(callBackResponse);
			String resp = (String) jobj.get("response");
			System.out.println(resp.toString());
			byte[] decodedBytes = Base64.getDecoder().decode(resp);
			String decodedString = new String(decodedBytes);
			JSONObject st = jsonParsing(decodedString);
			boolean status = (boolean) st.get("success");
			String code = (String) st.get("code");
			String message = (String) st.get("message");
			JSONObject jobj2 = (JSONObject) st.get("data");
			String merchantId = (String) jobj2.get("merchantId");
			String transactionId = (String) jobj2.get("transactionId");
			String providerReferenceId = (String) jobj2.get("providerReferenceId");
			String paymentState = (String) jobj2.get("paymentState");
			String payResponseCode = (String) jobj2.get("payResponseCode");
			String mode = null, ifsc = null, utr = null, upiTransactionId = null;
			JSONArray jarry = (JSONArray) jobj2.get("paymentModes");
			for (int i = 0; i < jarry.size(); i++) {
				JSONObject jobj3 = (JSONObject) jarry.get(0);
				mode = (String) jobj3.get("mode");
				ifsc = (String) jobj3.get("ifsc");
				utr = (String) jobj3.get("utr");
				upiTransactionId = (String) jobj3.get("upiTransactionId");
			}
			PhonepeResponseObj phpeResobj = new PhonepeResponseObj();
			phpeResobj.setId(id);
			phpeResobj.setSuccess(status);
			phpeResobj.setCode(code);
			phpeResobj.setMessage(message);
			phpeResobj.setMerchantId(merchantId);
			phpeResobj.setTransactionId(transactionId);
			phpeResobj.setProviderReferenceId(providerReferenceId);
			phpeResobj.setPaymentState(paymentState);
			phpeResobj.setPayResponseCode(payResponseCode);
			phpeResobj.setMode(mode);
			phpeResobj.setIfsc(ifsc);
			phpeResobj.setUtr(utr);
			phpeResobj.setUpiTransactionId(upiTransactionId);
			try {
				respStatus = phpeServices.updatePhpeCallbackResponseIntoGoogleSheet(phpeResobj);
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		} catch (Exception e) {
			logger.info("Call back Response Error::" + e);
		}
		return respStatus;
	}

	@RequestMapping(path = "/exotelSmsCallBack/{id}", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public void exotelSmsCallBack(@PathVariable String id,
			@RequestBody String callBackResponse) throws ParseException {
		
		JSONObject jobj = jsonParsing(callBackResponse);
		String smsCampaignSid = (String) jobj.get("sid");
		logger.info("Method:ap exotelSmsCallBack smsCampaignSid ::" + smsCampaignSid);
		String created_time = (String) jobj.get("created_time");
		String last_viewed = (String) jobj.get("last_viewed");
		long total_clicks = (Long) jobj.get("total_clicks");
		String total_clk = String.valueOf(total_clicks);
		JSONObject jobj2 = (JSONObject) jobj.get("device_info");
		String device = (String) jobj2.get("device_name");
		String region = (String) jobj2.get("region");
		String city = (String) jobj2.get("city");
		long accuracy_radius = (long) jobj2.get("accuracy_radius");
		String ac_radius = String.valueOf(accuracy_radius);
		try {
			phpeServices.updateSmsCallBackApiIntoAppSheet(id, smsCampaignSid, created_time, last_viewed, total_clk,
					device, region, city, ac_radius, "SmsCallBack");
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	private JSONObject jsonParsing(String stringToParse) throws ParseException {
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(stringToParse);
		return jobj;
	}

	@RequestMapping(path = "/cashFree", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public void sendSMS(@RequestParam String cf_event, 
			@RequestParam String cf_subReferenceId,
			@RequestParam(required = false) String cf_status,
			@RequestParam(required = false) String cf_lastStatus,
			@RequestParam(required = false) String cf_eventTime,
			@RequestParam(required = false) String signature) {
		logger.info("cashFree Call back URL started....rowid:: " + cf_subReferenceId);
		try {
			phpeServices.updateCahsFreeSmsCallBackApiIntoAppSheet(cf_event,cf_subReferenceId,
					cf_status,cf_lastStatus,cf_eventTime,signature);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		logger.info("cashFree Call back URL ended....rowid:: " + cf_subReferenceId);
	}
}
