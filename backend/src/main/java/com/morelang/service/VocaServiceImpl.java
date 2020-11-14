package com.morelang.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.AccessToken;
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
	public String registVoca(String accessToken,Voca input) {
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		if(m.isPresent()) {
			Member member = m.get();
			Voca s = input;
			s.setMember(member);
			vocaRepository.save(s);
			return "success";
		}
		return "fail";
	}
	
	@Override
	public String DeleteVoca(String accessToken, Long vocaId) {
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		if(m.isPresent()) {
			Optional<Voca> voca = vocaRepository.findByMember_idAndVocaId(m.get().getId(), vocaId);
			if(voca.isPresent()) {
				vocaRepository.delete(voca.get());
				return "success";
			}
		}
		return "fail";
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
	public String updateVoca(String accessToken, Voca updateVoca) {
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		if(m.isPresent()) {
			Optional<Voca> voca = vocaRepository.findByMember_idAndVocaId(m.get().getId(), (long)(updateVoca.getVocaId()));
			if(voca.isPresent()) {
				vocaRepository.save(updateVoca);
				return "success";
			}
		}
		return "fail";
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
	public String makeLearn(String accessToken, Long VocaId) {
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		if(m.isPresent()) {
			Optional<Voca> voca = vocaRepository.findByMember_idAndVocaId(m.get().getId(), Long.valueOf(VocaId));
			if(voca.isPresent()) {
				Voca v1 = voca.get();
				if(v1.isLearn()) {
					v1.setLearn(false);
				}else {
					v1.setLearn(true);
				}
				vocaRepository.save(v1);
				return "success";
			}
		}
		return "fail";
	}
	@Override
	public Map<String,Object> vocaQuize(String accessToken, String country, Integer idx) throws IOException {
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		Map<String, Object> map = new HashMap<>();
		List<Voca> vocaList = null;
		List<Voca> meanList = null;
		if(m.isPresent()) {
			if(idx == 0) {
				vocaRepository.updateStatus(m.get().getId());
			}
			if(country == null) {
				vocaList = vocaRepository.findByMember_idAndUsed(m.get().getId(), false);
			}else {
				vocaList = vocaRepository.findByMember_idAndUsedAndCountry(m.get().getId(), false, country);
			}
			Integer maxIdx = vocaList.size() >=10?10:vocaList.size();
			if(vocaList.size() == 0) {
				map.put("result", "not exist");
				return map;
			}else {
				Map<String, Object> ma = new HashMap<>();
				List<String> answer_list = new ArrayList<>();
				ma.put("num", idx);
				ma.put("maxIdx", maxIdx);
				Collections.shuffle(vocaList);
				Voca v1 = vocaList.get(0);
				ma.put("problem", v1.getEachVoca());
				String answer = vocaMean(v1.getEachMean());
				answer_list.add(answer);
				meanList = vocaRepository.distinctMean(v1.getEachMean());
				Collections.shuffle(meanList);
				for(int i=0; i<3;i++) {
					answer_list.add(meanList.get(i).getEachMean());
				}
				Collections.shuffle(answer_list);
				ma.put("answer", answer_list.indexOf(answer));
				ma.put("answer_list", answer_list);
				map.put("result", ma);
				if(idx == maxIdx) {
					vocaRepository.updateStatus(m.get().getId());
				}
				return map;
			}
		}else {
			map.put("result", "fail");
			return map;
		}
		
	}
	
	public String vocaMean(String voca) throws IOException {
		String url = "https://dic.daum.net/search.do?q="+voca;
		Connection conn = Jsoup.connect(url);
		Document html = conn.get();
		Element element = html.body();
		Elements el = element.select(".txt_search");
		String element1 = el.get(0).text();
		System.out.println(element1);
		return element1;
	}

	@Override
	public String initQuiz(String accessToken) {
		Optional<Member> m = memberRepository.findByAccessToken(accessToken);
		if(m.isPresent()) {
			vocaRepository.updateStatus(m.get().getId());
			return "success";
		}else {
			return "fail";
		}
	}
}
