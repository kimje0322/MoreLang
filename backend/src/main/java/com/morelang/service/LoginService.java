package com.morelang.service;

import java.io.IOException;
import java.util.Map;

public interface LoginService {
	public Map<String,Object> login(String authCode, String redirect) throws IOException;
	public Map<String,Object> refresh(String refreshToken);
}
