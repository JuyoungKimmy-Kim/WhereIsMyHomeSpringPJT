package com.mycom.myhome.user;

import java.util.List;

public interface UserService {
	
	public UserDto.Response getTotalCount();
	public List<UserDto.Response> getUserList(int limit, int offset);
	public UserDto.Response loginProcess(UserDto.Request paramDto);
	public UserDto.Response processNewUser(UserDto.Request paramDto);
	public UserDto.Response modifyProcess(UserDto.Request paramDto);
	public UserDto.Response tokenValidationProcess(String email, String accessToken);
	public UserDto.Response getRefreshToken(UserDto.Request paramDto, String token);
	public UserDto.Response logoutProcess(String userEmail);
}
