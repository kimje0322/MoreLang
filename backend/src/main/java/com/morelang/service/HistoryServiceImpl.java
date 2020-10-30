package com.morelang.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morelang.dto.History;
import com.morelang.dto.HistoryVideo;
import com.morelang.dto.Member;
import com.morelang.repository.HistoryRepository;
import com.morelang.repository.HistoryVideoRepository;
import com.morelang.repository.MemberRepository;

@Service
public class HistoryServiceImpl implements HistoryService{
	@Autowired
	private HistoryRepository historyRepository;

	@Autowired
	private HistoryVideoRepository historyVideoRepository;

	@Autowired
	private MemberRepository memberRepository;

	private static int POINT_VALUE = 100;

	@Override
	public String watchVideo(String accessToken, HistoryVideo watched) {
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		if(m.isPresent()) {
			Optional<HistoryVideo> video = historyVideoRepository.findByYoutubeUrl(watched.getYoutubeUrl());
			boolean is_watch = false;
			HistoryVideo info;
			if(video.isPresent()) {
				Optional<History> history = historyRepository.findByMember_useridAndVideo_videoId(m.get().getUserid(),video.get().getVideoId());
				if(history.isPresent()) {
					is_watch = true;
					info = video.get();
					info.setCount(info.getCount()+1);
					historyVideoRepository.save(info);
					return "success";
				}
			}
			if(!is_watch && m.get().getPoint()>=POINT_VALUE) {
				if(video.isPresent()) {
					info = video.get();
					info.setCount(info.getCount()+1);
					historyVideoRepository.save(info);
				}else {
					info = watched;
					info.setCount(1);
					historyVideoRepository.save(info);
				}
				History h = new History();
				h.setMember(m.get());
				h.setVideo(info);
				historyRepository.save(h);
				return "success";
			}else {
				return "잔액부족";
			}
		}else {
			return "fail";
		}
	}

}
