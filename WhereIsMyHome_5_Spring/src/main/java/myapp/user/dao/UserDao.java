package com.mycom.myapp.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.user.dto.CodeDto;
import com.mycom.myapp.user.dto.UserDto;

@Mapper
public interface UserDao {
	
	// 회원가입 
	int joinUser(UserDto userDto);
	List<CodeDto> getCodeclsf();
	
	
	// 로그인
	UserDto login(String userEmail);
	
	// 회원정보 수정
	int updateUser(UserDto userDto);
	
	// 회원 탈퇴
	int deleteUser(int userSeq);
}
