package com.mycom.myhome.board;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	private final BoardService service;
	
	@GetMapping("/boards/{limit}/{offset}")
	public ResponseEntity<BoardResultDto> list(@PathVariable int limit,@PathVariable  int offset){
		BoardResultDto resultDto = service.list(limit, offset);
		
		if(resultDto != null) {
			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		}
	
		return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/boards/{boardId}")
	public ResponseEntity<BoardResultDto> detail(@PathVariable String boardId, @RequestParam String userEmail){
		BoardResultDto resultDto = service.detail(boardId, userEmail);
		
		if(resultDto != null) {
			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		}
		return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/boards/list/{boardClsf}")
	public ResponseEntity<BoardResultDto> listCount(@PathVariable String boardClsf){
		BoardResultDto resultDto = service.listCount(boardClsf);
		
		if(resultDto != null) {
			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		}
		return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/boards")
	public ResponseEntity<BoardResultDto> insert(@RequestBody BoardParamDto paramDto){
		logger.info("paramDto >>> " + paramDto.toString());
		BoardResultDto resultDto = service.insert(paramDto);
		
		if(resultDto != null) {
			logger.info("insert resultDto >> " + resultDto);
			return new ResponseEntity<>(resultDto,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/upload/{boardId}")
	public ResponseEntity<BoardResultDto> fileUpload(@PathVariable String boardId, MultipartHttpServletRequest request){
		BoardResultDto resultDto = service.fileUpload(boardId, request);

		if(resultDto != null) {
			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("/boards")
	public ResponseEntity<BoardResultDto> update(@RequestBody BoardParamDto paramDto){
		BoardResultDto resultDto = service.update(paramDto);

		if(resultDto != null) {
			return new ResponseEntity<>(resultDto,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/boards/{boardId}")
	public ResponseEntity<BoardResultDto> delete(@PathVariable String boardId){
		BoardResultDto resultDto = service.delete(boardId);
		
		if(resultDto != null) {
			return new ResponseEntity<>(resultDto,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
