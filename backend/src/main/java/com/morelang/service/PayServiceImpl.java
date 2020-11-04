package com.morelang.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.morelang.dto.PayApprove;
import com.morelang.dto.PayReady;

@Service
public class PayServiceImpl implements PayService {

	@Override
	public PayReady ready(String item_name, String total_amount) throws Exception {
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
		body.add("approval_url", "https://morelang.gq/pay/approve");
		body.add("cancel_url", "https://morelang.gq/pay/fail");
		body.add("fail_url", "https://morelang.gq/pay/fail");

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);
		PayReady payReady = template.postForObject("https://kapi.kakao.com/v1/payment/ready", entity, PayReady.class);
		payReady.setTotal_amount(total_amount);

		return payReady;
	}

	@Override
	public PayApprove approve(String tid, String pg_token, String total_amount) throws Exception {
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
		PayApprove payApprove = template.postForObject("https://kapi.kakao.com/v1/payment/approve", entity,
				PayApprove.class);

		// 여기서 DB에 포인트 추가 및 결제내역 추가좀 @성오
		System.out.println(payApprove);

		return payApprove;
	}

}
