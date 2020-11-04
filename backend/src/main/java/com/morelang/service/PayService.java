package com.morelang.service;

import com.morelang.dto.PayApprove;
import com.morelang.dto.PayReady;

public interface PayService {
	public PayReady ready(String item_name, String total_amount) throws Exception;

	public PayApprove approve(String aceessToken,String tid, String pg_token, String total_amount) throws Exception;
}
