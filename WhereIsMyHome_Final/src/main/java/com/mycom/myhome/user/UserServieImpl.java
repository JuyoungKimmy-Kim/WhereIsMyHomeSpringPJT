package com.mycom.myhome.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.myhome.Status;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServieImpl implements UserService{

	
	private final UserDao dao;
	
	// 회원가입
	public UserResultDto signup(UserParamDto paramDto) {
		User user = paramDto.toEntity();
		
		// insert 실패해도 seq오르는거 방지
		User findUser = dao.findByEmail(user.getUserEmail());
		if(findUser != null) {
			return new UserResultDto(Status.FAIL, "이미 가입된 회원입니다.");
		}
		
		dao.signup(user);
		return new UserResultDto(Status.SUCCESS, "회원가입에 성공하였습니다.");
	}
	
	
}
