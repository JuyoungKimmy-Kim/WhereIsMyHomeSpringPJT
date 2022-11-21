package com.mycom.myhome.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
	
	// 회원가입
	int signup(User user);
	
	// 회원정보 수정
	int updateByEmail(User user);
	
	// 전체 회원 목록
	List<User> getTotalList();
	
	// 회원 목록
	List<User> getList(@Param("limit") int limit, @Param("offset") int offset);
	
	// 이메일로 찾기 
	User findByEmail(String email);
	
	// 이메일과 비밀번호가 맞는 유저찾기
	User findByMatch(User user);
	
	// refresh토큰 등록 or 삭제
	int updateRefreshToken(@Param("userEmail") String userEmail, @Param("token") String token);
	
	// refresh토큰 얻기
	String getRefreshToken(String userEmail);
}
 