package com.mycom.myhome.user;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private String userSeq;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userProfileImageUrl;
	private LocalDateTime regDt;
	private String userClsf;
	
	public void setRegDt(Date regDt) {
		// Mybatis가 LocalDateTime을 지원하지 않기 때문에 Date로 먼저 받아서 처리한다.( Mybatis Date Mapping )
		this.regDt = LocalDateTime.ofInstant(regDt.toInstant(), ZoneId.systemDefault());
	}
}
