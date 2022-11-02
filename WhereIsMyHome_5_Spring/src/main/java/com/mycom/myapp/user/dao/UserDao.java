package com.mycom.myapp.user.dao;

import com.mycom.myapp.user.dto.UserDto;

public interface UserDao {
	int userRegister(UserDto userDto);
	public UserDto login(String userEmail);
	int userUpdate(UserDto userDto, int userSeq);
	int userDelete(int userSeq);
}
