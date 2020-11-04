package com.morelang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.morelang.dto.pointCharge;

public interface ChargeRepository extends JpaRepository<pointCharge, Long>{
	
}
