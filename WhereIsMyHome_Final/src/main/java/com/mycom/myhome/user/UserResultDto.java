package com.mycom.myhome.user;

import java.time.LocalDateTime;

import com.mycom.myhome.Status;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName="of")
public class UserResultDto {
	private final Status result;
	private final String message;
	private final UserInfo userInfo;
	private final String accessToken;
	private final String refreshToken;
	
	public static UserResultDto ofSuccess(String message, UserInfo userInfo, String accessToken, String refreshToken) {
		return new UserResultDto(Status.SUCCESS, message, userInfo, accessToken, refreshToken);
	}
	
	public static UserResultDto ofFail(String message) {
		return new UserResultDto(Status.FAIL, message, null, null, null);
	}
	
	public static UserResultDto ofUnauthorized(String message) {
		return new UserResultDto(Status.UNAUTHORIZED, message, null, null, null);
	}
	
	public static UserInfo setInfo(User user) {
		return UserInfo.of(user.getUserSeq(), user.getUserEmail(), user.getUserName(), user.getUserProfileImageUrl(), user.getRegDt(), user.getUserClsf());
	}
	
	@Data
	@RequiredArgsConstructor(staticName = "of")
	private static class UserInfo{
		private final String userSeq;
		private final String userEmail;
		private final String userName;
		private final String userProfileImgUrl;
		private final LocalDateTime regDt;
		private final String userClsf;
	}
}
