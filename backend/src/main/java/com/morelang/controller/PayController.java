package com.morelang.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.morelang.dto.PayApprove;
import com.morelang.dto.PayReady;
import com.morelang.service.PayService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class PayController {
	@Autowired
	PayService payService;

	@GetMapping("/pay/ready")
	public ResponseEntity<PayReady> ready(@RequestParam("item_name") String item_name,
			@RequestParam("total_amount") String total_amount, HttpServletRequest request) {

		PayReady payReady = null;
		try {
			payReady = payService.ready(item_name, total_amount);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(payReady, HttpStatus.OK);
	}

	@GetMapping("/pay/approve")
	public ResponseEntity<PayApprove> approve(@RequestParam("tid") String tid,
			@RequestParam("pg_token") String pg_token, @RequestParam("total_amount") String total_amount,
			HttpServletRequest request) {

		PayApprove payApprove = null;
		try {
			payApprove = payService.approve(tid, pg_token, total_amount);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(payApprove, HttpStatus.OK);
	}

}
