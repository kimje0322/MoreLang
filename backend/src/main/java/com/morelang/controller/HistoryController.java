package com.morelang.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.morelang.dto.HistoryVideo;
import com.morelang.dto.PageRequest;
import com.morelang.service.HistoryService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class HistoryController {
	
	@Autowired
	HistoryService historyService;
	
	@PutMapping("/watch-video")
	@ApiOperation(value="[동영상 조회] 사용자가 동영상을 눌렀을 때, 조회수를 증가시키고, 100 포인트를 결제하는 행위 입니다. 이미 본건 결제 안함 [return] 성공하면 success, 사용자 인증에 실패하면 fail, 포인트가 없으면 잔액부족")
	public ResponseEntity<String> watchVideo(HttpServletResponse response, @RequestBody HistoryVideo video){
		String accessToken = response.getHeader("accessToken");
		return new ResponseEntity<String>(historyService.watchVideo(accessToken, video),HttpStatus.OK);
	}
	
	@GetMapping("myvideo")
	@ApiOperation(value="[결제한 동영상- 페이징 처리] 내가 결제한 동영상을 최신 순(DESC)/안본지 오래된(ASC) 순으로 가져옵니다.")
	public ResponseEntity<List<HistoryVideo>> myVideo(HttpServletResponse response, PageRequest pageable){
		String accessToken = response.getHeader("accessToken");
		return new ResponseEntity<List<HistoryVideo>>(historyService.myVideoList(accessToken, pageable.videoTime()),HttpStatus.OK);
	}
}
