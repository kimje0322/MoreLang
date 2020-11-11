package com.morelang.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.morelang.dto.Member;
import com.morelang.dto.Voca;
import com.morelang.dto.VocaSub;
import com.morelang.repository.MemberRepository;
import com.morelang.repository.VocaRepository;

@Service
public class VocaServiceImpl implements VocaService{
	@Autowired
	VocaRepository vocaRepository;
	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public void registVoca(String accessToken,Voca input) {
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		if(m.isPresent()) {
			Member member = m.get();
			Voca s = input;
			s.setMember(member);
			vocaRepository.save(s);
		}
	}
	
	@Override
	public void DeleteVoca(String accessToken, Long vocaId) {
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		if(m.isPresent()) {
			Optional<Voca> voca = vocaRepository.findByMember_idAndVocaId(m.get().getId(), vocaId);
			if(voca.isPresent()) {
				vocaRepository.delete(voca.get());
			}
		}
	}
	
	@Override
	public Page<VocaSub> MyVoca(String accessToken,Pageable pageable,String[] country) {
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		if(m.isPresent()) {
			Page<VocaSub> voca;
			if(country == null || country.length == 0) {
				voca = vocaRepository.findByMember_id(m.get().getId(), pageable);
			}else {
				voca = vocaRepository.findByMember_idAndCountryIn(m.get().getId(),country, pageable);
			}
			return voca;
		}else {
			return null;
		}
	}
	
	@Override
	public void updateVoca(String accessToken, Voca updateVoca) {
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		if(m.isPresent()) {
			Optional<Voca> voca = vocaRepository.findByMember_idAndVocaId(m.get().getId(), (long)(updateVoca.getVocaId()));
			if(voca.isPresent()) {
				vocaRepository.save(updateVoca);
			}
		}
	}
	@Override
	public String changeMean(String accessToken, Integer vocaId, String mean) {
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		if(m.isPresent()) {
			Optional<Voca> voca = vocaRepository.findByMember_idAndVocaId(m.get().getId(), Long.valueOf(vocaId));
			if(voca.isPresent()) {
				voca.get().setEachMean(mean);
				vocaRepository.save(voca.get());
				return "success";
			}
		}
		return "fail";
	}
	@Override
	public List<String> myVocaCountry(String accessToken) {
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		List<String> country = new ArrayList<>();
		if(m.isPresent()) {
			country = vocaRepository.findDistinctCountry(m.get().getId());
			return country;
		}else {
			return null;
		}
	}
	@Override
	public void makeLearn(String accessToken, Long VocaId) {
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		if(m.isPresent()) {
			Optional<Voca> voca = vocaRepository.findByMember_idAndVocaId(m.get().getId(), VocaId);
			if(voca.isPresent()) {
				Voca v1 = voca.get();
				if(v1.isLearn()) {
					v1.setLearn(false);
				}else {
					v1.setLearn(true);
				}
				vocaRepository.save(v1);
			}
		}
	}
	@Override
	public List<String> vocaQuize(String accessToken,String country) {
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		if(m.isPresent()) {
			List<VocaSub> voca;
			if(country == null) {
				voca = vocaRepository.findByMember_idAndIsLearnAndCountry(m.get().getId(),false,country);
			}else {
				voca = vocaRepository.findByMember_idAndIsLearn(m.get().getId(), false);
			}
			Collections.shuffle(voca);
			List<String> result = new ArrayList<>();
			int size = result.size();
			if(size >10){ size = 10;}
			for(int i=0; i<size; i++) {
				result.add(voca.get(i).getEachVoca());
			}
			return result;
		}
		return null;
	}
	public void vocaMean(String voca) throws IOException {
		String url = "https://en.dict.naver.com/#/search?range=word&query="+voca;
		Connection conn = Jsoup.connect(url);
		Document html = conn.get();
		System.out.println(html);
	}
}
