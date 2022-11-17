//package com.mycom.myapp.code.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.mycom.myapp.code.dto.DongDto;
//import com.mycom.myapp.code.service.DongService;
//
//@RestController
//public class DongController {
//	
//	@Autowired
//	DongService service;
//	
//	@GetMapping ("/house/{gugunCode}")
//	private List<DongDto> gugunList (@PathVariable String gugunCode) {
//		System.out.println("DongController - Dong List");
//		
//		return service.dongList(gugunCode);
//	}
//}
