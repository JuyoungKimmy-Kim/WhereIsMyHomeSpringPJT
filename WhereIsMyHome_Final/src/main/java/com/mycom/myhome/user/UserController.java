package com.mycom.myhome.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myhome.common.Status;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(
		// localhost:5500 과 127.0.0.1 구분
		origins = "http://localhost:5500", 
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = { RequestMethod.GET, RequestMethod.POST,
				RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.HEAD, RequestMethod.OPTIONS })
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private final UserService service;	
	
	
	// 모든 유저 수
	@GetMapping("/users/count")
	public ResponseEntity<UserDto.Response> totalCount(){
		UserDto.Response responseDto = service.getTotalCount();

		return ResponseEntity.ok(responseDto);
	}
	
	// 유저 목록 ( limit offset )
	@GetMapping("/users/{limit}/{offset}")
	public ResponseEntity<List<UserDto.Response>> userList(@PathVariable int limit, @PathVariable  int offset){
		List<UserDto.Response> list = service.getUserList(limit, offset);
		return ResponseEntity.ok(list);
	}
	
	// 로그인
	@PostMapping("/login")
	public ResponseEntity<UserDto.Response> login(@RequestBody UserDto.Request requestDto) {
		logger.info("Login Process => " + requestDto.toString());
		UserDto.Response responseDto = service.loginProcess(requestDto);
		logger.info("Login Result => " + responseDto.toString());
		
		return ResponseEntity.ok(responseDto);
	}
	
	
	// 회원가입
	@PostMapping("/signup")
	public ResponseEntity<UserDto.Response> signUp(@RequestBody UserDto.Request requestDto) {
		logger.info("signUp Process => " + requestDto.toString());
		
		UserDto.Response responseDto = service.processNewUser(requestDto);
		
		logger.info("signUp Result => " + responseDto.toString());
		return ResponseEntity.ok(responseDto);
	}
	
	// 유저 정보 수정
	@PutMapping("/users")
	public ResponseEntity<UserDto.Response> modifyInfo(@RequestBody UserDto.Request requestDto){
		logger.info("modify Process => " + requestDto.toString());
		
		UserDto.Response responseDto = service.modifyProcess(requestDto);

		logger.info("modify Result => " + responseDto.toString());
		return ResponseEntity.ok(responseDto);
	}
	
	// email인 유저 찾아서 토큰확인
	@GetMapping("/users/token")
	public ResponseEntity<UserDto.Response> validateToken(String email, HttpServletRequest request){
		UserDto.Response responseDto = service.tokenValidationProcess(email, request.getHeader("access-token"));
		return ResponseEntity.ok(responseDto);
	}

	
	// 로그아웃
	@GetMapping("/logout")
	public ResponseEntity<UserDto.Response> logout(String email) {
		UserDto.Response responseDto = service.logoutProcess(email);
		return ResponseEntity.ok(responseDto);
	}
	
	// AccessToken 재생성
	@PostMapping("/users/token")
	public ResponseEntity<UserDto.Response> createAccessToken(@RequestBody UserDto.Request requestDto, HttpServletRequest request){
		logger.info("createAccessToken Process => " + requestDto.toString());
		String token = request.getHeader("refresh-token");
		UserDto.Response responseDto = service.getRefreshToken(requestDto, token);
		logger.info("createAccessToken Process => " + responseDto.toString());
		
		Status result = responseDto.getResult();
		if(result == Status.SUCCESS){
			return ResponseEntity.ok(responseDto);
		}else if(result == Status.UNAUTHORIZED) {
			return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/login/kakao")
	public ResponseEntity<UserDto.Response> kakaoLogin(@RequestBody UserDto.Request requestDto) {
		logger.info("kakao login >> " + requestDto.toString());
		
		UserDto.Response responseDto = service.validateUser(requestDto);
		
		return ResponseEntity.ok(responseDto);
	}
}
