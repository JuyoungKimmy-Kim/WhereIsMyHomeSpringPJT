package com.mycom.myapp.user.service;

import java.util.List;

import com.mycom.myapp.user.dto.CodeDto;
import com.mycom.myapp.user.dto.UserDto;


public interface UserService {
	
	int joinUser(UserDto userDto);
	List<CodeDto> getCodeclsf();
	
	public UserDto login(UserDto userDto);


	int updateUser(UserDto userDto);
	
	
	int deleteUser(int userSeq);
}
