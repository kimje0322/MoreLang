package com.morelang.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.morelang.dto.History;
import com.morelang.dto.HistoryVideo;
import com.morelang.dto.Member;
import com.morelang.dto.pointCharge;
import com.morelang.repository.ChargeRepository;
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

	@Autowired
	private ChargeRepository chargeRepository;

	private static int POINT_VALUE = 100;

	@Override
	public String watchVideo(String accessToken, HistoryVideo watched) {
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		if(m.isPresent()) {
			Optional<HistoryVideo> video = historyVideoRepository.findByYoutubeUrl(watched.getYoutubeUrl());
			boolean is_watch = false;
			HistoryVideo info;
			// 누군가 봐서 비디오에 값이 들어가면?
			if(video.isPresent()) {
				Optional<History> history = historyRepository.findByMember_useridAndVideo_videoId(m.get().getUserid(),video.get().getVideoId());
				//내가 본 기록이 있으면?
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
				pointCharge pc = new pointCharge();
				pc.setCharge(false);
				pc.setChargeAmount(100);
				pc.setMember(m.get());
				History h = new History();
				h.setMember(m.get());
				h.setVideo(info);
				chargeRepository.save(pc);
				historyRepository.save(h);
				return "success";
			}else {
				return "잔액부족";
			}
			

		}else {
			return "fail";
		}
	}

	@Override
	public List<HistoryVideo> myVideoList(String accessToken,Pageable pageable){
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		if(m.isPresent()) {
			List<History> history = historyRepository.findByMember_userid(m.get().getUserid(),pageable);
			List<Long> viewId = new ArrayList<>();
			for(int i=0; i<history.size(); i++) {
				viewId.add(Long.valueOf(history.get(i).getVideo().getVideoId()));
			}
			return historyVideoRepository.findByVideoIdIn(viewId);
		}
		return null;
	}
}
