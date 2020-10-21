package com.morelang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.morelang.dto.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

}
