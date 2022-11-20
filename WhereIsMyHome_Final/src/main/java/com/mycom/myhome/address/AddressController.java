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
public class AddressController {

		@Autowired
		AddressServiceImpl service;
		
		@GetMapping ("/address")
		private List<SidoDto> sidoList () {
			System.out.println("SidoController - Sido List");
			
			return service.sidoList();
		}
		
		@GetMapping ("/address/{sidoCode}")
		private List<GugunDto> gugunList (@PathVariable String sidoCode) {
			System.out.println("GugunController - Gugun List");
			
			return service.gugunList(sidoCode);
		}
		
		@GetMapping ("/address/{sidoCode}/{gugunCode}")
		private List<DongDto> dongList (@PathVariable String gugunCode) {
			System.out.println("DongController - Dong List");
			
			return service.dongList(gugunCode);
		}
		
		
}
