package com.mycom.myhome.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@PostMapping("/join")
	public void join() {
		
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<Object>> users(){
		
		return null;
	}
	
}
