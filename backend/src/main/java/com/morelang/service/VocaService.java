package com.morelang.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.morelang.dto.Voca;
import com.morelang.dto.VocaSub;

public interface VocaService {
	public void registVoca(String accessToken,Voca input);
	public void DeleteVoca(String accessToken, Long vocaId);
	public Page<VocaSub> MyVoca(String accessToken,Pageable pageable);
	public void updateVoca(String accessToken, Voca updateVoca);
	public void makeLearn(String accessToken, Long VocaId);
	public List<String> vocaQuize(String accessToken,String country);
	public void vocaMean(String voca) throws IOException;
}
