package com.morelang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.morelang.dto.Voca;
import com.morelang.dto.VocaSub;

public interface VocaRepository extends JpaRepository<Voca, Long>{
	public Optional<Voca> findByMember_useridAndVocaId(String userid, Long vocaid);
	public List<VocaSub> findByMember_userid(String userid);
	public Page<VocaSub> findByMember_userid(String userid,Pageable pageable);
	public List<VocaSub> findByMember_useridAndIsLearnAndCountry(String userid,Boolean isLearn,String country);
	public List<VocaSub> findByMember_useridAndIsLearn(String userid,Boolean isLearn);
}
