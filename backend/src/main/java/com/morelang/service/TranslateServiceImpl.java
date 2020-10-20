package com.morelang.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class TranslateServiceImpl implements TranslateService {

	final static String REST_API_KEY = "e852b5b6b1d40103d1bc8dfa69a0bd6c";

	@Override
	public String translate(String src_lang, String target_lang, String query) throws Exception {
		String s = "";

		JSONObject json = null;
		URL url = new URL("https://dapi.kakao.com/v2/translation/translate?" + "src_lang=" + src_lang + "&target_lang="
				+ target_lang + "&query=" + URLEncoder.encode(query, "utf-8"));
		System.out.println(url);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		con.setRequestProperty("Authorization", "KakaoAK " + REST_API_KEY);

		if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();

			json = new JSONObject(sb.toString());
			s = json.toString();
			s = s.substring(22, s.length() - 4);

		} else {
			System.out.println(con.getResponseMessage());
		}

		return s;
	}

}
