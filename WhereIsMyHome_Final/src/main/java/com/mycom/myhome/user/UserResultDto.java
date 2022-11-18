package com.mycom.myhome.user;

import com.mycom.myhome.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName="of")
public class UserResultDto {
	private final Status result;
	private final String message;
	private final String userEmail;
	private final String accessToken;
	private final String refreshToken;
	
	
	public static UserResultDto ofSuccess(String message, String userEmail, String accessToken, String refreshToken) {
		return new UserResultDto(Status.SUCCESS, message, userEmail, accessToken, refreshToken);
	}
	
	public static UserResultDto ofFail(String message) {
		return new UserResultDto(Status.FAIL, message, null, null, null);
	}
	
//	@RequiredArgsConstructor(staticName="of") 이 아래를 뜻함
//	private UserResultDto(Status result, String message) {
//		this.result = result;
//		this.message = message;
//	}
//	
//	static UserResultDto of(Status result, String message) {
//		return new UserResultDto(result, message);
//	}
}
