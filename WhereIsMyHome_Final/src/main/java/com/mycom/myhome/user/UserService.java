package com.mycom.myhome.user;

public interface UserService {
	
	public UserResultDto login(UserParamDto paramDto);
	public UserResultDto signup(UserParamDto paramDto);
	
}
