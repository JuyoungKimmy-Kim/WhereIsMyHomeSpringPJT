package com.mycom.myhome.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.myhome.Status;
import com.mycom.myhome.user.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final UserDao dao;
	
	private final JwtService jwtService;
	
	//로그인
	public UserResultDto login(UserParamDto paramDto) {
		User findUser = dao.findByEmail(paramDto.getUserEmail());
		if(findUser != null) {
			logger.info("login 성공!! >> " + findUser.toString());
			
			String accessToken = jwtService.createAccessToken("userEmail", findUser.getUserEmail());
			String refreshToken = jwtService.createRefreshToken("userEmail", findUser.getUserEmail());
			
			dao.updateRefreshToken(findUser.getUserEmail(), refreshToken);
			
			return UserResultDto.ofSuccess("로그인이 완료되었습니다.", findUser.getUserEmail(), accessToken, refreshToken);
		}
		return UserResultDto.ofFail("아이디 또는 비밀번호를 다시 확인해주세요.");
	}
	
	public void getRefreshToken(String userEmail) {
		String refreshToken = dao.getRefreshToken(userEmail);
	}
	
	// 회원가입
	public UserResultDto signup(UserParamDto paramDto) {
		User user = paramDto.toEntity();
		
		// insert 실패해도 seq오르는거 방지
		User findUser = dao.findByEmail(user.getUserEmail());
		if(findUser != null) {
			return UserResultDto.ofFail("이미 가입된 회원입니다.");
		}
		
		dao.signup(user);
		return UserResultDto.ofSuccess("회원가입에 성공하였습니다.", null, null, null);
	}
	
	
}
