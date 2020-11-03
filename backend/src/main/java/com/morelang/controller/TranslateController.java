package com.morelang.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.morelang.config.Webhook;
import com.morelang.service.TranslateService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class TranslateController {
	@Autowired
	TranslateService translateService;

	@GetMapping("/translate/test")
	public ResponseEntity<String> translate(@RequestParam("query") String query, HttpServletRequest request) {

		String s = null;
		try {
			s = translateService.translate(query);
		} catch (Exception e) {
			e.printStackTrace();
			Webhook.send(request, this.getClass().toString(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(s, HttpStatus.OK);
	}

}
