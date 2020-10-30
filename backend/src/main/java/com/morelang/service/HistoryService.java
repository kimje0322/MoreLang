package com.morelang.service;

import com.morelang.dto.HistoryVideo;

public interface HistoryService {
	public String watchVideo(String accessToken, HistoryVideo watched);
}
