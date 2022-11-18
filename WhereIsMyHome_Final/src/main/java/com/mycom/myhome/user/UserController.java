package com.mycom.myhome.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myhome.Status;

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
	
	private final UserService service;
	
	@PostMapping("/login")
	public ResponseEntity<UserResultDto> login(@RequestBody UserParamDto paramDto) {
		UserResultDto resultDto = service.login(paramDto);
		
		System.out.println(paramDto);
		System.out.println(resultDto);
		if(resultDto != null) {
			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		}
		return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/info/{email}")
	public ResponseEntity<UserResultDto> info(@PathVariable String email, HttpServletRequest request){
		UserResultDto resultDto = service.info(email, request.getHeader("access-token"));
		
		HttpStatus status = null;
		switch(resultDto.getResult()) {
			case SUCCESS: status = HttpStatus.OK; break;
			case FAIL: status = HttpStatus.INTERNAL_SERVER_ERROR; break;
			case UNAUTHORIZED: status = HttpStatus.UNAUTHORIZED; break;
		}
		
		return new ResponseEntity<>(resultDto, status);
	}
	
	@GetMapping("/logout/{email}")
	public ResponseEntity<UserResultDto> logout(@PathVariable String email) {
		UserResultDto resultDto = service.deleteRefreshToken(email);
		if(resultDto != null) {
			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		}
		return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<UserResultDto> signup(@RequestBody UserParamDto paramDto) {
		UserResultDto resultDto = service.signup(paramDto);
		
		if(resultDto != null) return new ResponseEntity<>(resultDto, HttpStatus.OK);
		return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/regen")
	public ResponseEntity<UserResultDto> accessRegen(@RequestBody UserParamDto paramDto, HttpServletRequest request){
		String token = request.getHeader("refresh-token");
		UserResultDto resultDto = service.getRefreshToken(paramDto, token);
		
		HttpStatus status = null;
		switch(resultDto.getResult()) {
			case SUCCESS: status = HttpStatus.OK; break;
			case FAIL: status = HttpStatus.INTERNAL_SERVER_ERROR; break;
			case UNAUTHORIZED: status = HttpStatus.UNAUTHORIZED; break;
		}
		
		return new ResponseEntity<>(resultDto, status);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<Object>> users(){
		
		return null;
	}
	
}
