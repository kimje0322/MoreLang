package com.morelang.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class Loginfilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("dd?도착");
		String requestTokenHeader = request.getHeader("Authorization");
		String refreshToken = request.getHeader("refreshToken");
		System.out.println("why? " + requestTokenHeader);
		String token = null;
		if(refreshToken != null && refreshToken.startsWith("Bearer ")) {
			refreshToken = refreshToken.substring(7);	
			response.setHeader("refreshToken", refreshToken);
			filterChain.doFilter(request, response);
		}else {
			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				token = requestTokenHeader.substring(7);
				response.setHeader("accessToken", token);
			}
		}
		filterChain.doFilter(request, response);
	}

}
