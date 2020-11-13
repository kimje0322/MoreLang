package com.morelang.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.morelang.dto.Voca;
import com.morelang.dto.VocaSub;

public interface VocaService {
	public String registVoca(String accessToken,Voca input);
	public String DeleteVoca(String accessToken, Long vocaId);
	public Page<VocaSub> MyVoca(String accessToken,Pageable pageable,String[] country);
	public String updateVoca(String accessToken, Voca updateVoca);
	public String makeLearn(String accessToken, Long VocaId);
	public List<String> myVocaCountry(String accessToken);
	public String changeMean(String accessToken, Integer vocaId, String mean);
}
