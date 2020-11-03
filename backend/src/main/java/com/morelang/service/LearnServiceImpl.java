package com.morelang.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.cloud.language.v1beta2.PartOfSpeech.Tag;
import com.google.cloud.language.v1beta2.Token;
import com.morelang.dto.Learn;
import com.morelang.repository.LearnRepository;
import com.morelang.util.NLAnalyze;
@Service
public class LearnServiceImpl implements LearnService{
	
	@Autowired
	LearnRepository learnRepository;
	private String text = "Google, headquartered in Mountain View, unveiled the new Android phone at the Consumer Electronic Show. Sundar Pichai said in his keynote that users love their new Android phones.";
	private String text2 = "だから みなさんに 僕の経験を共有したい";
	@Override
	public Map<String,Object> WordPuzzle(String input) {
		List<Token> LearnList =  NLAnalyze.getInstance().analyzeSyntax(input);//learnRepository.findAll();
		Map<Integer,String> complete = new HashMap<>();
		Map<Integer,String> keyword = new HashMap<>();
		List<Token> subList = new ArrayList<>();
		int k = 1;
		for(Token learn : LearnList) {
			complete.put(k++,learn.getLemma());
			if(learn.getPartOfSpeech().getTag().equals(Tag.NOUN) || learn.getPartOfSpeech().getTag().equals(Tag.VERB) || learn.getPartOfSpeech().getTag().equals(Tag.ADJ)) {
	        	subList.add(learn);
	        }
		}
		Collections.shuffle(subList);
		int num = (int)Math.ceil((double)subList.size()/2);
		String result = input;
		int p=0;
		for(int i=0; i<num; i++) {
			if(result.indexOf(subList.get(i).getLemma())!=-1) {
				p++;
				result = result.replaceFirst(subList.get(i).getLemma(), "______");
				keyword.put(i+1,subList.get(i).getLemma());
			}
		}
		System.out.println(result);
		Map<String,Object> m2 = new HashMap<>();
		m2.put("inputText",input);
		m2.put("original",complete);
		m2.put("quizeText", result);
		m2.put("keyword", keyword);
		m2.put("count", num);
		return m2;
	}
	
	@Override
	public Map<String,Object> puzzeltest(String input) {
		List<Learn> LearnList =  learnRepository.findAll();
		Map<Integer,String> complete = new HashMap<>();
		Map<Integer,String> keyword = new HashMap<>();
		List<Learn> subList = new ArrayList<>();
		int k = 1;
		String result = text;
		for(Learn learn : LearnList) {
			complete.put(k++,learn.getLemma());
			if(learn.getTag().equals("NOUN") || learn.getTag().equals("VERB") || learn.getTag().equals("ADJ")) {
	        	if(result.indexOf(learn.getLemma())!= -1) {
	        		subList.add(learn);
	        	}
	        }
		}
		Collections.shuffle(subList);
		int num = (int)Math.ceil((double)subList.size()/2);
		for(int i=0; i<num; i++) {
				result = result.replaceFirst(subList.get(i).getLemma(), "______");
				keyword.put(i+1,subList.get(i).getLemma());
				System.out.printf("단어: %s\n", subList.get(i).getLemma());
				System.out.printf("품사: %s\n", subList.get(i).getTag());
		}
		StringTokenizer st = new StringTokenizer(result, " ", true);
		String[] resultArray = new String[st.countTokens()];
		int pas = 0;
		while(st.hasMoreTokens()) {
			resultArray[pas++] = st.nextToken();
		}
		Map<String,Object> m2 = new HashMap<>();
		m2.put("inputTextArray",resultArray);
		m2.put("inputText",input);
		m2.put("original",complete);
		m2.put("quizeText", result);
		m2.put("keyword", keyword);
		m2.put("count", num);
		return m2;
	}
}