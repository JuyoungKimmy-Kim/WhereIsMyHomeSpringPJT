package com.mycom.myhome.user;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
	private int seq;
	private String name;
	private String password;
	private String email;
	private String profileImageUrl;
	private LocalDateTime regDt;
	private String code;
	private String token;
	private String gugunCode;
	
	public void setRegDt(Date regDt) {
		// Mybatis가 LocalDateTime을 지원하지 않기 때문에 Date로 먼저 받아서 처리한다.( Mybatis Date Mapping )
		this.regDt = LocalDateTime.ofInstant(regDt.toInstant(), ZoneId.systemDefault());
	}
	
	@Builder
	public User(String name, String password, String email, String profileImageUrl, String code, String gugunCode) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.profileImageUrl = profileImageUrl;
		this.code = code;
		this.gugunCode = gugunCode;
	}
}
