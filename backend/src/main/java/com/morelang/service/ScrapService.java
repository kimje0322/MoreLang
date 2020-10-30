package com.morelang.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.morelang.dto.Scrap;
import com.morelang.dto.ScrapSub;

public interface ScrapService {
	public void DoScrap(String accessToken,Scrap input);
	public void DeleteScrap(String accessToken, Long scrapId);
	public Page<ScrapSub> MyScrap(String accessToken,Pageable pageable);
	public void updateScrap(String accessToken, Scrap updateScrap);
}
