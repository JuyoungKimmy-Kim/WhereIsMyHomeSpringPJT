//package com.mycom.myapp.code.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.mycom.myapp.code.dto.SidoDto;
//import com.mycom.myapp.code.service.SidoService;
//
//@RestController
//public class SidoController {
//	
//	@Autowired
//	SidoService service;
//	
//	@GetMapping ("/houses")
//	private List<SidoDto> sidoList () {
//		System.out.println("SidoController - Sido List");
//		
//		return service.sidoList();
//	}
//}
