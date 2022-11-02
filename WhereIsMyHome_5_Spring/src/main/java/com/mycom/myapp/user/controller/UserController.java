package com.mycom.myapp.user.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.user.dto.CodeDto;
import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.service.UserService;

@RestController
public class UserController{
	
	private static final int SUCCESS = 1;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<Map<String, String>> join(UserDto userDto){
	
		Map<String, String> map = new HashMap<>();
		int result = userService.joinUser(userDto);
		if(result == SUCCESS) {
			map.put("result", "success");
			return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
		}
		
		map.put("result", "fail");
		return new ResponseEntity<Map<String,String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("/users/{userSeq}")
	public ResponseEntity<Map<String, String>> update(UserDto dto, HttpSession session){
		
		int result = userService.updateUser(dto);
		Map<String, String> map = new HashMap<String, String>();
		if(result == SUCCESS) {
			map.put("result", "success");
			return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
		}

		map.put("result", "fail");
		return new ResponseEntity<Map<String,String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/users/{userSeq}")
	public ResponseEntity<Map<String, String>> delete(@PathVariable int userSeq, HttpSession session){
		int result = userService.deleteUser(userSeq);
		

		Map<String, String> map = new HashMap<String, String>();
		if(result == SUCCESS) {
			map.put("result", "success");
			session.invalidate();
			return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
		}

		map.put("result", "fail");
		return new ResponseEntity<Map<String,String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(UserDto userDto, HttpSession session){
		
		UserDto user = userService.login(userDto);
		System.out.println(user);
		Map<String, Object> map = new HashMap<>();
		if(user != null) {
			map.put("result", "success");
			map.put("userClsf", user.getUserClsf());
			map.put("userSeq", user.getUserSeq());
			
			
			session.setAttribute("userDto", user);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("result", "fail");
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/logout")
	public void logout(HttpSession session) {
		System.out.println("session 종료");
		session.invalidate();
	}
	
	@GetMapping("/codes")
	public ResponseEntity<List<CodeDto>> getCodeclsf(){
		List<CodeDto> codeList = userService.getCodeclsf();
		return new ResponseEntity<List<CodeDto>>(codeList, HttpStatus.OK);
	}
}