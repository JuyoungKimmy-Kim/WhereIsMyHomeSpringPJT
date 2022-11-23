package com.mycom.myhome.user;

import java.time.LocalDateTime;
import java.util.List;

import com.mycom.myhome.common.Status;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class UserDto {
	
	@Setter
	@Getter
	@Builder
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Request{
		private String name;
		private String email;
		private String password;
		private String code;
		private String role;
		private String profileImageUrl;
		private String gugunCode;
		
		public User toEntity() {
			return User.builder()
					.name(name)
					.email(email)
					.password(password)
					.profileImageUrl(profileImageUrl)
					.code(code)
					.gugunCode(gugunCode)
					.build();
		}
	}
	
	private List<Response> list;
	
	@Getter
	@Builder
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		private Status result;
		
		private int userSeq;
		private String name;
		private String email;
		private String profileImageUrl;
		private LocalDateTime regDt;
		private String role;
		private String accessToken;
		private String refreshToken;
		private String gugunCode;
		
		
		private long count;
	}

}
