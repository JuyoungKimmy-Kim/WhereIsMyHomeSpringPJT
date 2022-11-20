package com.mycom.myhome.user;

import lombok.Data;

@Data
public class UserParamDto {
	private String userName;
	private String userEmail;
	private String userPassword;
	private String userClsf;
	
	public User toEntity() {
		return new User(-1, userName, userPassword, userEmail, null, null, userClsf);
	}
}
