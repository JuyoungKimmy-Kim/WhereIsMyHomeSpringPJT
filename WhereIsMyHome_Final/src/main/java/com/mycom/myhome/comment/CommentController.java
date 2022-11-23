package com.mycom.myhome.comment;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(
		// localhost:5500 과 127.0.0.1 구분
		origins = "http://localhost:5500", 
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = { RequestMethod.GET, RequestMethod.POST,
				RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.HEAD, RequestMethod.OPTIONS })
public class CommentController {
	
	private final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	private final CommentService service;
	
	
	@GetMapping("/comments/{boardId}")
	public ResponseEntity<List<CommentDto.Response>> getComments(@PathVariable int boardId){
		List<CommentDto.Response> list = service.getComments(boardId);
		
		logger.info("get List => " + list.toString());
		
		return ResponseEntity.ok(list);
	}
	
	@PostMapping("/comments")
	public ResponseEntity<CommentDto.Response> submitComment(@RequestBody CommentDto.Request requestDto){

		logger.info("submit Comment => " + requestDto.toString());
		
		CommentDto.Response responseDto = service.processNewComment(requestDto);
		return ResponseEntity.ok(responseDto);
	}
	
	@DeleteMapping("/comments/{no}")
	public ResponseEntity<CommentDto.Response> removeComment(@PathVariable int no){
		CommentDto.Response responseDto = service.processRemoveComment(no);
		return ResponseEntity.ok(responseDto);
	}
	
	@PutMapping("/comments")
	public ResponseEntity<CommentDto.Response> plusHeart(@RequestBody CommentDto.Request requestDto){
		CommentDto.Response responseDto = service.processPlusHeart(requestDto);
		return ResponseEntity.ok(responseDto);
	}
	
}
