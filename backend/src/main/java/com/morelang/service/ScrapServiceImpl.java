package com.morelang.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.morelang.dto.Member;
import com.morelang.dto.Scrap;
import com.morelang.dto.ScrapSub;
import com.morelang.repository.MemberRepository;
import com.morelang.repository.ScrapRepository;

@Service
public class ScrapServiceImpl implements ScrapService{
	@Autowired
	ScrapRepository scrapRepository;
	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public void DoScrap(String accessToken,Scrap input) {
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		if(m.isPresent()) {
			Member member = m.get();
			Scrap s = input;
			s.setMember(member);
			scrapRepository.save(s);
		}
	}
	
	@Override
	public void DeleteScrap(String accessToken, Long scrapId) {
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		if(m.isPresent()) {
			Optional<Scrap> s = scrapRepository.findByMember_useridAndScrapId(m.get().getUserid(), scrapId);
			if(s.isPresent()) {
				scrapRepository.delete(s.get());
			}
		}
	}
	
	@Override
	public Page<ScrapSub> MyScrap(String accessToken,Pageable pageable) {
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		if(m.isPresent()) {
			Page<ScrapSub> s = scrapRepository.findByMember_userid(m.get().getUserid(),pageable);
			return s;
		}else {
			return null;
		}
	}
	
	@Override
	public void updateScrap(String accessToken, Scrap updateScrap) {
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		if(m.isPresent()) {
			Optional<Scrap> s = scrapRepository.findByMember_useridAndScrapId(m.get().getUserid(), (long)(updateScrap.getScrapId()));
			if(s.isPresent()) {
				scrapRepository.save(updateScrap);
			}
		}
	}
}
