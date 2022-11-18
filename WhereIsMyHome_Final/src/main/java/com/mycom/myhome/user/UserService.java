package com.mycom.myhome.user;

public interface UserService {
	
	public UserResultDto login(UserParamDto paramDto);
	public UserResultDto signup(UserParamDto paramDto);
	public UserResultDto info(String email, String accessToken);
	public UserResultDto deleteRefreshToken(String userEmail);
	public UserResultDto getRefreshToken(UserParamDto paramDto, String token);
}
