package com.morelang.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.morelang.dto.PageRequest;
import com.morelang.dto.Voca;
import com.morelang.dto.VocaSub;
import com.morelang.service.VocaService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class VocaController {
	@Autowired
	VocaService vocaService;
	
	@PostMapping("/regist-voca")
	@ApiOperation(value = "[단어등록]단어장에 단어 등록하기")
	public ResponseEntity<?> registVoca(HttpServletResponse response, @RequestBody Voca voca) {
		String accessToken = response.getHeader("accessToken");
		vocaService.registVoca(accessToken, voca);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@GetMapping("/myvoca")
	@ApiOperation(value = "[내 단어장 보기- 페이징] 내 단어장 확인하기")
	public ResponseEntity<?> MyVoca(HttpServletResponse response,PageRequest pageable) {
		String accessToken = response.getHeader("accessToken");
		return new ResponseEntity<Page<VocaSub>>(vocaService.MyVoca(accessToken,pageable.vocaof()),HttpStatus.OK);
	}
	@PutMapping("/update-voca")
	@ApiOperation(value = "[단어 갱신] 해당 단어 업데이트하기")
	public ResponseEntity<?> updateVoca(HttpServletResponse response, @RequestBody Voca voca) {
		String accessToken = response.getHeader("accessToken");
		vocaService.updateVoca(accessToken, voca);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@DeleteMapping("/delete-voca")
	@ApiOperation(value = "[단어장에서 단어 제거] 단어장에서 해당 단어 제거")
	public ResponseEntity<?> deleteVoca(HttpServletResponse response, @RequestParam("vocaId") Long vocaId) {
		String accessToken = response.getHeader("accessToken");
		vocaService.DeleteVoca(accessToken, vocaId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@PutMapping("/islearn")
	@ApiOperation(value = "[단어 학습 완료 on/off] 해당 단어 학습했는지 안했는지 on/off하기")
	public ResponseEntity<?> isLearn(HttpServletResponse response, @RequestParam("vocaId") Long vocaId) {
		String accessToken = response.getHeader("accessToken");
		vocaService.makeLearn(accessToken, vocaId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@GetMapping("/vocaquize")
	@ApiOperation(value = "[단어 퀴즈 만들기] 내 단어장에서 학습 안한 걸로 단어퀴즈 만들기, 해당 나라를 주면 해당 나라거만 가져옴")
	public ResponseEntity<?> vocaQuize(HttpServletResponse response, @RequestParam(required = false) String country) {
		String accessToken = response.getHeader("accessToken");
		return new ResponseEntity<List<String>>(vocaService.vocaQuize(accessToken, country),HttpStatus.OK);
	}
	@GetMapping("/vocamean")
	@ApiOperation(value = "[단어 의미 보기] 단어에 대한 의미와 에문을 확인할 수 있습니다.")
	public ResponseEntity<?> vocaQuize(@RequestParam("voca") String voca) throws IOException {
		vocaService.vocaMean(voca);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
