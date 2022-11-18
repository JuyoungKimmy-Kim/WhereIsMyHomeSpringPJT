package com.mycom.myhome.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
	
	int signup(User user);
	User findByEmail(String email);
	void updateRefreshToken(@Param("userEmail") String userEmail, @Param("token") String token);
	String getRefreshToken(String userEmail);
}
 