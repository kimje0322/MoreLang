package com.morelang.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "videos")
public class HistoryVideo {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int videoId;
	
	@Column
	private String title;
	
	@Column
	private String thumbnail;
	
	@Column
	private String youtubeUrl;
	
	@Column
	private String defaultLanguage;
	
	@Column
	private int count;

	public HistoryVideo(int videoId, String title, String thumbnail, String youtubeUrl, String defaultLanguage,
			int count) {
		super();
		this.videoId = videoId;
		this.title = title;
		this.thumbnail = thumbnail;
		this.youtubeUrl = youtubeUrl;
		this.defaultLanguage = defaultLanguage;
		this.count = count;
	}
	
	
}
