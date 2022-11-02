package com.mycom.myapp.house.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.house.dto.HouseDto;
import com.mycom.myapp.house.service.HouseService;

@RestController
public class HouseController {
	@Autowired
	HouseService houseService;
	
	@GetMapping ("/houses/{dongName}")
	private List<HouseDto> houseListSearchByDong(@PathVariable String dongName) {
		System.out.println("HouseController - House List - Search By Dong");
		
		return houseService.getSearchByDong(dongName);
	}
	
	// 꼼수 썼습니다..
	@GetMapping ("/house/{aptName}")
	private List<HouseDto> houseListSearchByAptName(@PathVariable String aptName) {
		System.out.println("HouseController - House List - Search By AptName");
		
		return houseService.getSearchByApt(aptName);
	}
	
}