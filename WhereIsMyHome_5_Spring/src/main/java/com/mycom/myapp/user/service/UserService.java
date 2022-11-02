package com.mycom.myapp.user.service;

import com.mycom.myapp.user.dto.UserDto;

public interface UserService {
	int userRegister(UserDto userDto);
	public UserDto login(String userEmail, String userPassword);
	int userUpdate(UserDto userDto, int userSeq);
	int userDelete(int userSeq);

}
