package com.mycom.myhome.wishList;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myhome.wishList.WishListDto.Request;
import com.mycom.myhome.wishList.WishListDto.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(
		// localhost:5500 과 127.0.0.1 구분
		origins = "http://localhost:5500", 
		allowCredentials = "true", 
		allowedHeaders = "*", 
		exposedHeaders = {"Content-Disposition"},
		methods = { RequestMethod.GET, RequestMethod.POST,
				RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.HEAD, RequestMethod.OPTIONS })
public class WishListController {
	
	private static final Logger logger = LoggerFactory.getLogger(WishListController.class);
	
	private final WishListService service;
	
	
	@GetMapping("/wish/{userSeq}")
	public ResponseEntity<List<Response>> getWishList(@PathVariable int userSeq) {
		List<WishListDto.Response> result = service.getWishList(userSeq);
		
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/wish")
	public ResponseEntity<Response> addFavorite(@RequestBody Request paramDto){
		
		WishListDto.Response result = service.addFavorite(paramDto);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/wish")
	public ResponseEntity<Response> removeFavorite(@RequestBody Request paramDto){
		
		Response result = service.removeFavorite(paramDto);
		return ResponseEntity.ok(result);
	}
}
