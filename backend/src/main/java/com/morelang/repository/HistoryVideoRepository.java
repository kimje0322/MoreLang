package com.morelang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.morelang.dto.HistoryVideo;

public interface HistoryVideoRepository extends JpaRepository<HistoryVideo,Long>{
	public Optional<HistoryVideo> findByYoutubeUrl(String url);
	public List<HistoryVideo> findByVideoIdIn(List<Long> video);
	
}
