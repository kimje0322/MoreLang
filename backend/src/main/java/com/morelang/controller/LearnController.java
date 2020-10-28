package com.morelang.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.morelang.dto.Learn;
import com.morelang.service.LearnService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class LearnController {
	@Autowired
	LearnService learnService;
	
	@PostMapping("/guest/puzzle")
	public ResponseEntity<?> WorkPuzzle(@RequestParam("inputText") String input) throws IOException {
		return new ResponseEntity<Map<String,Object>>(learnService.WordPuzzle(input),HttpStatus.OK);
	}
	
	@PostMapping("/guest/puzzletest")
	public ResponseEntity<?> Puzzletest(@RequestParam("inputText") String input) throws IOException {
		return new ResponseEntity<Map<String,Object>>(learnService.puzzeltest(input),HttpStatus.OK);
	}
}
