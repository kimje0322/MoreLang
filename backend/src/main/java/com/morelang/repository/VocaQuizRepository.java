package com.morelang.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.morelang.dto.VocaQuizLog;
import com.morelang.dto.VocaQuizLogSub;

public interface VocaQuizRepository extends JpaRepository<VocaQuizLog, Long>{
	List<VocaQuizLogSub> findByCountry(String country,Sort sort);
}
