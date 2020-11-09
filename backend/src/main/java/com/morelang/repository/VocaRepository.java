package com.morelang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.morelang.dto.Voca;
import com.morelang.dto.VocaSub;

public interface VocaRepository extends JpaRepository<Voca, Long>{
	public Optional<Voca> findByMember_idAndVocaId(Integer userid, Long vocaid);
	public List<VocaSub> findByMember_id(Integer userid);
	public Page<VocaSub> findByMember_id(Integer userid, Pageable pageable);
	public Page<VocaSub> findByMember_idAndCountryIn(Integer userid,String[] Country, Pageable pageable);
	public List<VocaSub> findByMember_idAndIsLearnAndCountry(Integer userid,Boolean isLearn,String country);
	public List<VocaSub> findByMember_idAndIsLearn(Integer userid,Boolean isLearn);
	
	@Query(value = "SELECT DISTINCT country FROM vocas where member_id=:userid",nativeQuery = true)
	public List<String> findDistinctCountry(Integer userid);
}
