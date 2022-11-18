package com.mycom.myhome.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userProfileImageUrl;
	private Date regDt;
	private String userClsf;
}
