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
		System.out.println("PropertyController - Search By Dong");
		return service.searchByDong(dongName, gugunCode);
	}
	
	@GetMapping("/property/{regionCode}")
	private List<PropertyDto> searchByRegion(@PathVariable String regionCode) {
		System.out.println("PropertyController - Search By Gugun or Sido");
		List<PropertyDto> result = service.searchByRegion(regionCode);
		System.out.println(result.toString());
		return result;
	}
	
	
	@GetMapping("/apt/{aptName}")
	private List<PropertyDto> searchByApt(@PathVariable String aptName) {
		System.out.println("PropertyController - Search By AptName");
		return service.searchByApt(aptName);
	}
	
	@GetMapping ("/propertyDetail/{houseNo}")
	private List<PropertyDetailDto> propertyDetail (@PathVariable String houseNo) {
		System.out.println("PropertyController - Property Detail");
		return service.propertyDetail(houseNo);
	}
}