package com.mycom.myapp.code.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.code.dto.DongDto;
import com.mycom.myapp.code.dto.GugunDto;
import com.mycom.myapp.code.dto.SidoDto;
import com.mycom.myapp.code.service.DongService;
import com.mycom.myapp.code.service.GugunService;
import com.mycom.myapp.code.service.SidoService;

@RestController
public class CodeController {
	@Autowired
	SidoService sidoService;
	
	@Autowired
	GugunService gugunService;
	
	@Autowired
	DongService dongService;
	
	@GetMapping ("/address")
	private List<SidoDto> sidoList () {
		System.out.println("SidoController - Sido List");
		
		return sidoService.sidoList();
	}
	
	@GetMapping ("/address/{sidoCode}")
	private List<GugunDto> gugunList (@PathVariable String sidoCode) {
		System.out.println("GugunController - Gugun List");
		
		return gugunService.gugunList(sidoCode);
	}
	
	@GetMapping ("/address/{sidoCode}/{gugunCode}")
	private List<DongDto> dongList (@PathVariable String gugunCode) {
		System.out.println("DongController - Dong List");
		
		return dongService.dongList(gugunCode);
	}
}
