package com.morelang.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.morelang.dto.Member;
import com.morelang.repository.MemberRepository;

@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public Map<String,Object> login(String authCode, String redirect) throws IOException {
		GoogleTokenResponse tokenResponse =
				new GoogleAuthorizationCodeTokenRequest(
						new NetHttpTransport(),
						JacksonFactory.getDefaultInstance(),
						"https://oauth2.googleapis.com/token",
						"258439612277-a2k3f6ro1jvdkbois85pt4cngrs6hctk.apps.googleusercontent.com",
						"H56LQIIC1wfc6_8x7WWhrXV3",
						authCode,
						redirect.substring(0,redirect.length()-1))    // requesturl, front 페이지임.  
				.execute();

		String accessToken = tokenResponse.getAccessToken();
		String refreshToken = tokenResponse.getRefreshToken();
		System.out.println("ㅇㅇ? " + tokenResponse);
		System.out.println(accessToken);
		// Get profile info from ID token
		GoogleIdToken idToken = tokenResponse.parseIdToken();
		GoogleIdToken.Payload payload = idToken.getPayload();
		String userId = payload.getSubject();  // Use this value as a key to identify a user.
		String email = payload.getEmail();
//		boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
//		String locale = (String) payload.get("locale");
//		String familyName = (String) payload.get("family_name");
//		String givenName = (String) payload.get("given_name");
		String name = (String) payload.get("name");
		String pictureUrl = (String) payload.get("picture");

		Member m = new Member(userId,name,pictureUrl,"Google",email,accessToken);
		memberRepository.save(m);
		Map<String,Object> map = new HashMap<>();
		map.put("member", m);
		map.put("refreshToken", refreshToken);
		return map;
	}
}
