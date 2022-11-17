package com.mycom.myhome.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
	@PostMapping("/signup")
	public ResponseEntity<UserResultDto> signup(@RequestBody UserParamDto paramDto) {
		UserResultDto resultDto = service.signup(paramDto);
		
		if(resultDto != null) return new ResponseEntity<UserResultDto>(resultDto, HttpStatus.OK);
		return new ResponseEntity<UserResultDto>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<Object>> users(){
		
		return null;
	}
	
}
