package com.mycom.myhome.user;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.myhome.code.Code;
import com.mycom.myhome.code.CodeDao;
import com.mycom.myhome.common.Status;
import com.mycom.myhome.user.UserDto.Request;
import com.mycom.myhome.user.UserDto.Response;
import com.mycom.myhome.user.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final UserDao dao;
	private final CodeDao codeDao;
	
	private final JwtService jwtService;
	
	private final String groupCode = "001";
	
	
	@Override
	public UserDto.Response getTotalCount() {
		int totalCount = dao.selectTotalCount();
		return UserDto.Response.builder().count(totalCount).build();
	}
	
	@Override
	public List<UserDto.Response> getUserList(int limit, int offset) {
		List<User> userList = dao.selectAll(limit, offset);
		
		List<UserDto.Response> list = new ArrayList<>();
		userList.forEach(item->{
			String userCode = item.getCode();
			Code code = codeDao.selectByCode(groupCode, userCode);
			
			String role = code.getDescription();
			list.add(
					UserDto.Response.builder()
						.name(item.getName())
						.email(item.getEmail())
						.profileImageUrl(item.getProfileImageUrl())
						.regDt(item.getRegDt())
						.role(role)
						.gugunCode(item.getGugunCode())
						.build()
					);
		});
		
		return list;
	}
	
	//로그인
	@Override
	public UserDto.Response loginProcess(UserDto.Request dto) {
		String email = dto.getEmail();
		User user = dao.selectByEmail(email);
		
		if(user != null) {
			String accessToken = jwtService.createAccessToken("email", email);
			String refreshToken = jwtService.createRefreshToken("email", email);
				
			dao.updateToken(refreshToken, email);
			
			Code code = codeDao.selectByCode(groupCode, user.getCode());
			String role = code.getDescription();
			
			return UserDto.Response.builder()
					.result(Status.SUCCESS)
					.seq(user.getSeq())
					.name(user.getName())
					.email(user.getEmail())
					.profileImageUrl(user.getProfileImageUrl())
					.regDt(user.getRegDt())
					.role(role)
					.gugunCode(user.getGugunCode())
					.accessToken(accessToken)
					.refreshToken(refreshToken)
					.build();
		}
			
		return null;
	}
	
	// 회원가입
	@Override
	public UserDto.Response processNewUser(UserDto.Request dto) {
		if(dto.getProfileImageUrl() == null) 
			dto.setProfileImageUrl("/no_img.png");
		
		User entity = dto.toEntity();
		String email = entity.getEmail();
		
		
		logger.info(dto.toString());
		// insert 실패해도 seq오르는거 방지
		User user = dao.selectByEmail(email);
		if(user == null) {
			logger.info(entity.toString());
			dao.insert(entity);
			return UserDto.Response.builder()
					.result(Status.SUCCESS).build();
		}

		// 중복 유저
		return UserDto.Response.builder().result(Status.FAIL).build();
	}
	
	@Override
	public UserDto.Response modifyProcess(UserDto.Request dto) {
		String email = dto.getEmail();
		User user = dao.selectByEmail(email);
		
		if(user != null) {
			String role = dto.getRole();
			if(role == null) {
				String code = user.getCode();
				dto.setCode(code);
			}else {
				Code findCode = codeDao.selectByDescription(role);
				String code= findCode.getCode();
				dto.setCode(code);
			}
			
			User entity = dto.toEntity();
			dao.updateByEmail(entity);
			
			return UserDto.Response.builder()
					.result(Status.SUCCESS).build();
		}
		
		// 존재하지 않는 유저
		return UserDto.Response.builder().result(Status.FAIL).build();
	}
	
	// 현재 가지고있는 토큰이 유효한지 확인
	public UserDto.Response tokenValidationProcess(String email, String accessToken) {
		if(jwtService.checkToken(accessToken)) {
			User user = dao.selectByEmail(email);
			if(user != null) {

				Code code = codeDao.selectByCode(groupCode, user.getCode());
				String role = code.getDescription();
				
				return UserDto.Response.builder()
						.result(Status.SUCCESS)
						.seq(user.getSeq())
						.name(user.getName())
						.email(user.getEmail())
						.profileImageUrl(user.getProfileImageUrl())
						.regDt(user.getRegDt())
						.role(role)
						.gugunCode(user.getGugunCode())
						.accessToken(accessToken)
						.build();
			}
		}
		
		// exception handler 생기면 없어질 코드
		return UserDto.Response.builder().result(Status.FAIL).build();
	}
	
	// refresh 토큰 다시 받기
	public UserDto.Response getRefreshToken(UserDto.Request dto, String token) {
		if(jwtService.checkToken(token)) {
			
			String email = dto.getEmail();
			User user = dao.selectByEmail(email);
			
			String dbToken = user.getToken();
			if(token.contentEquals(dbToken)) {
				String accessToken = jwtService.createAccessToken("email", email);
				// accessToken 반환
				return UserDto.Response.builder()
						.result(Status.SUCCESS)
						.accessToken(accessToken)
						.build();
			}
		}

		// exception handler 생기면 없어질 코드
		return UserDto.Response.builder().result(Status.UNAUTHORIZED).build();
	}
	
	// refresh 토큰 삭제
	public UserDto.Response logoutProcess(String userEmail) {
		User user = dao.selectByEmail(userEmail);
		user.setToken(null);
		
		dao.updateByEmail(user);
		
		return UserDto.Response.builder().result(Status.SUCCESS).build();
	}

	
	@Override
	public UserDto.Response validateUser(UserDto.Request paramDto){
		String email = paramDto.getEmail();
		User user = dao.selectByEmail(email);
		if(user == null) {
			return UserDto.Response.builder()
					.result(Status.FAIL)
					.email(paramDto.getEmail())
					.name(paramDto.getName())
					.profileImageUrl(paramDto.getProfileImageUrl())
					.build();
		}
		

		String accessToken = jwtService.createAccessToken("email", email);
		String refreshToken = jwtService.createRefreshToken("email", email);
		
		dao.updateToken(refreshToken, email);
		
		Code code = codeDao.selectByCode(groupCode, user.getCode());
		String role = code.getDescription();
		
		return UserDto.Response.builder()
				.result(Status.SUCCESS)
				.seq(user.getSeq())
				.name(user.getName())
				.email(user.getEmail())
				.profileImageUrl(user.getProfileImageUrl())
				.regDt(user.getRegDt())
				.role(role)
				.gugunCode(user.getGugunCode())
				.accessToken(accessToken)
				.refreshToken(refreshToken)
				.build();
	}
	
	
}
