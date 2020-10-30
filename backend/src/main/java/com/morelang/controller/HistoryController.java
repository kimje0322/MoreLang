package com.morelang.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.morelang.dto.HistoryVideo;
import com.morelang.service.HistoryService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class HistoryController {
	
	@Autowired
	HistoryService historyService;
	
	@GetMapping
	@ApiOperation(value="")
	public ResponseEntity<String> watchVideo(HttpServletResponse response, @RequestBody HistoryVideo video){
		String accessToken = response.getHeader("accessToken");
		return new ResponseEntity<String>(historyService.watchVideo(accessToken, video),HttpStatus.OK);
	}
}
