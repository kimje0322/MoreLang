package com.morelang.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.morelang.dto.recommendChannel;

public interface RecommendRepository extends JpaRepository<recommendChannel, Long>{
	Page<recommendChannel> findByCountry(String country,Pageable pageable);
}
