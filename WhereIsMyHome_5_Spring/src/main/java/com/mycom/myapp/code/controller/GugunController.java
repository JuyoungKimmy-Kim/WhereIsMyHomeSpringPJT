//package com.mycom.myapp.code.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.mycom.myapp.code.dto.GugunDto;
//import com.mycom.myapp.code.dto.SidoDto;
//import com.mycom.myapp.code.service.GugunService;
//
//@RestController
//public class GugunController {
//	
//	@Autowired
//	GugunService service;
//	
//	@GetMapping ("/houses/{sidoCode}")
//	private List<GugunDto> gugunList (@PathVariable String sidoCode) {
//		System.out.println("GugunController - Gugun List");
//		
//		return service.gugunList(sidoCode);
//	}
//}
