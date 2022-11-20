package com.mycom.myhome.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(
		origins = {"http://localhost:5500", "http://localhost:5501"},
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class PropertyController {
	@Autowired
	PropertyService service;
	
	@GetMapping("/property/{dongName}/{gugunCode}")
	private List<PropertyDto> searchByDong(@PathVariable String dongName, @PathVariable String gugunCode) {
		System.out.println("PropertyController - House List - Search By Dong");
		System.out.println(dongName + " " +gugunCode);
		return service.searchByDong(dongName, gugunCode);
	}
}

