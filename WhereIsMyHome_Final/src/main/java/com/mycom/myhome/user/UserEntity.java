package com.mycom.myhome.user;

import java.util.Date;

import lombok.Data;

@Data
public class UserEntity {
	private int userSeq;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userProfileImageUrl;
	private Date regDt;
	private String userClsf;
}
