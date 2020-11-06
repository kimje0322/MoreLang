package com.morelang.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.morelang.dto.HistoryVideo;

public interface HistoryService {
	public String watchVideo(String accessToken, HistoryVideo watched);
	public List<HistoryVideo> myVideoList(String accessToken,Pageable pageable);
	public Boolean is_view(String accessToken,HistoryVideo watched);
}
