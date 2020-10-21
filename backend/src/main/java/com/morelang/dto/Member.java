package com.morelang.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "members")
public class Member {

	@Id
	@Column
	private String userid;

	@Column
	private String name;

	@Column
	private String profileImg;

	@Column
	private String providerName;

	@Column
	private String email;

	@Column
	private String accessToken;
	
	public Member(String userid, String name, String profileImg, String providerName, String email,
			String accessToken) {
		super();
		this.userid = userid;
		this.name = name;
		this.profileImg = profileImg;
		this.providerName = providerName;
		this.email = email;
		this.accessToken = accessToken;
	}
}
