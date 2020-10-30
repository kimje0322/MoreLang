package com.morelang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.morelang.dto.History;

public interface HistoryRepository extends JpaRepository<History, Long>{
	public Optional<History> findByMember_useridAndVideo_videoId(String userid, int videoId);
	public List<History> findByMember_userid(String userid, Pageable pageable);
}
