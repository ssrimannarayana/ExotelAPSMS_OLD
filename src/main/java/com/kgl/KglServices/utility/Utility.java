package com.kgl.KglServices.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Utility {
	
	@Value("${SALTKEY}")
	private String salt_key;

	public JSONObject jsonParsing(String stringToParse) throws ParseException {
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(stringToParse);
		return jobj;
	}
	
	public String getReqString(String jsonStr) {
		// TODO Auto-generated method stub
		String base64payloadconversionString = Base64.getEncoder().encodeToString(jsonStr.getBytes());
		return base64payloadconversionString;
	}

	public String getReqStringDe(String jsonStr) {
		// TODO Auto-generated method stub
		byte[] decoded = Base64.getDecoder().decode(jsonStr);
		String decodedStr = new String(decoded, StandardCharsets.UTF_8);
		return decodedStr;
	}
	
	public String getXverifyCode(String reqString,String pathUrl) {
		String longString = reqString+pathUrl+salt_key;
		
		String shar256ConvertedString = sha256(longString);
		
		String finalString = shar256ConvertedString + "###" + 1;
		
		return finalString;
	}

	public static String sha256(final String base) {
		try {
			final MessageDigest digest = MessageDigest.getInstance("SHA-256");
			final byte[] hash = digest.digest(base.getBytes("UTF-8"));
			final StringBuilder hexString = new StringBuilder();
			for (int i = 0; i < hash.length; i++) {
				final String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public long getCampaign(String s) {
		s = s.substring(s.indexOf("/") + 1);
		s = s.substring(0, s.indexOf("/"));
		return Long.valueOf(s);
	}

}
