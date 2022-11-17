package com.mycom.myhome;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@PostMapping("/login")
	public void login() {
		
	}
	
	@GetMapping("/logout")
	public void logout() {
		
	}
}
