package com.morelang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.morelang.service.TranslateService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class TranslateController {
	@Autowired
	TranslateService translateService;

	@GetMapping("/translate")
	public ResponseEntity<String> translate(@RequestParam("src_lang") String src_lang,
			@RequestParam("target_lang") String target_lang, @RequestParam("query") String query) {

		String s = "";
		try {
			s = translateService.translate(src_lang, target_lang, query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(s, HttpStatus.OK);
	}

}
