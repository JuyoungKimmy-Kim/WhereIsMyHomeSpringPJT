package com.mycom.myhome.user;


import java.util.List;

import com.mycom.myhome.Status;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName="of")
public class UserResultDto {
	private final Status result;
	private final String message;
	private final User user;
	private final List<User> userList;
	private final String accessToken;
	private final String refreshToken;
	
	public static UserResultDto ofSuccess(String message, User user, List<User> userList, String accessToken, String refreshToken) {
		return new UserResultDto(Status.SUCCESS, message, user, userList, accessToken, refreshToken);
	}
	
	public static UserResultDto ofFail(String message) {
		return new UserResultDto(Status.FAIL, message, null, null, null, null);
	}
	
	public static UserResultDto ofUnauthorized(String message) {
		return new UserResultDto(Status.UNAUTHORIZED, message, null, null, null, null);
	}
	
}
