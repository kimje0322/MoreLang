package com.morelang.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.morelang.service.LoginService;



//import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
//@RequestMapping("/login")
public class LoginController {
	@Autowired
	LoginService loginService;
	
	
	@PostMapping("/guest/login")
	//@ApiOperation(value = "[구글 로그인] accessToken을 전달해 로그인한다, 만약 사용자 정보가 제대로 넘어오면, Member Dto, 잘못된 access면 responseCode return, 실패하면 fail을 return")
	public ResponseEntity<?> login(@RequestParam("code") String code, @RequestParam("redirect") String redirect) throws IOException {
		return new ResponseEntity<Map<String,Object>>(loginService.login(code, redirect),HttpStatus.OK);
	}
	
	@PostMapping("/guest/refresh")
	public ResponseEntity<?> refresh(HttpServletResponse response) throws IOException {
		System.out.println(response.getHeader("refreshToken"));
		return new ResponseEntity<Map<String,Object>>(loginService.refresh(response.getHeader("refreshToken")),HttpStatus.OK);
	}
}
