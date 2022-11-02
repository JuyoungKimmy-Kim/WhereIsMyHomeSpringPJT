package com.mycom.myapp.user.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserDto {
	
	private int userSeq;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userProfileImageUrl;
    private Date userRegisterDate;
    private String userClsf;

    public void setUserProfileImageUrl(String userProfileImageUrl) {
        if( userProfileImageUrl == null || "null".equals(userProfileImageUrl) || "".equals(userProfileImageUrl)) {
            this.userProfileImageUrl = "/img/noProfile.png";
        }else {
            this.userProfileImageUrl = userProfileImageUrl;
        }
    }

  
	
    
    
}