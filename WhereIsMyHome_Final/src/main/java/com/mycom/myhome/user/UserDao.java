package com.mycom.myhome.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
	
	int signup(User user);
	
	User findByEmail(String email);
	
}
 