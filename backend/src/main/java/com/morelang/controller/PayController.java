package com.morelang.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.morelang.dto.PayApprove;
import com.morelang.dto.PayReady;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class PayController {

	@GetMapping("/pay/ready")
	public ResponseEntity<PayReady> ready(@RequestParam("item_name") String item_name,
			@RequestParam("total_amount") String total_amount, HttpServletRequest request) {

		PayReady payReady;
		try {
			RestTemplate template = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "KakaoAK fef33ec5acdbb638e3f4f80ad3f5a006");

			MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
			body.add("cid", "TC0ONETIME");
			body.add("partner_order_id", "order");
			body.add("partner_user_id", "user");
			body.add("item_name", item_name);
			body.add("quantity", "1");
			body.add("total_amount", total_amount);
			body.add("tax_free_amount", "0");
			body.add("approval_url", "http://localhost:8081/pay/approve");
			body.add("cancel_url", "http://localhost:8081/pay/fail");
			body.add("fail_url", "http://localhost:8081/pay/fail");

			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);
			payReady = template.postForObject("https://kapi.kakao.com/v1/payment/ready", entity, PayReady.class);
			payReady.setTotal_amount(total_amount);

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
			RestTemplate template = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "KakaoAK fef33ec5acdbb638e3f4f80ad3f5a006");

			MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
			body.add("cid", "TC0ONETIME");
			body.add("tid", tid);
			body.add("partner_order_id", "order");
			body.add("partner_user_id", "user");
			body.add("pg_token", pg_token);
			body.add("total_amount", total_amount);

			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);
			payApprove = template.postForObject("https://kapi.kakao.com/v1/payment/approve", entity, PayApprove.class);

			// 여기서 DB에 포인트 추가 및 결제내역 추가좀 @성오
			System.out.println(payApprove);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(payApprove, HttpStatus.OK);
	}

}
