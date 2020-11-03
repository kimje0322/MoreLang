package com.morelang.service;

import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.morelang.util.Https;

@Service
public class TranslateServiceImpl implements TranslateService {

	final static String REST_API_KEY = "e852b5b6b1d40103d1bc8dfa69a0bd6c";

	@Override
	public String translate(String query) throws Exception {
		String s = null;

		if (query.equals("")) {
			return s;
		}

		URL url = new URL(
				"https://dapi.kakao.com/v3/translation/language/detect?query=" + URLEncoder.encode(query, "utf-8"));
		Map<String, String> props = new HashMap<>();
		props.put("Authorization", "KakaoAK " + REST_API_KEY);
		JSONObject obj = new JSONObject(Https.get(url, props)).getJSONArray("language_info").getJSONObject(0);
		String code = obj.getString("code");
		double confidence = obj.getDouble("confidence");

		if (confidence >= 0.99) {
			url = new URL("https://dapi.kakao.com/v2/translation/translate?" + "src_lang=" + code
					+ "&target_lang=kr&query=" + URLEncoder.encode(query, "utf-8"));
			props = new HashMap<>();
			props.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			props.put("Authorization", "KakaoAK " + REST_API_KEY);
			obj = new JSONObject(Https.get(url, props));

			s = obj.toString().replace("\"", "");
			s = s.substring(19, s.length() - 3);
		}

		return s;
	}

}
