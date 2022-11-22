package com.mycom.myhome.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
	
	// 회원가입
	int insert(User user);
	
	// 회원정보 수정
	int updateByEmail(User user);
	
	// 전체 회원 수
	int selectTotalCount();
	
	// 전체 회원 목록
	List<User> selectAll(@Param("limit") int limit, @Param("offset") int offset);
	
	// 이메일로 찾기 
	User selectByEmail(String email);
	
	// seq로 찾기
	User selectBySeq(int userSeq);
	
	// refresh토큰 등록 or 삭제
	int updateToken(@Param("refreshToken") String refreshToken, @Param("email") String email);
	
}
 