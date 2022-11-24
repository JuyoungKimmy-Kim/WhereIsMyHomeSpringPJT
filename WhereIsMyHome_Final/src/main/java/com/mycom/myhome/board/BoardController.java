package com.mycom.myhome.board;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.webresources.FileResource;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myhome.board.file.BoardFile;
import com.mycom.myhome.board.file.BoardFileResultDto;
import com.mycom.myhome.common.NaverNewsApi;
import com.mycom.myhome.property.PropertyDetailDto;

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
	public ResponseEntity<BoardResultDto> detail(@PathVariable int boardId, @RequestParam String userEmail){
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
	public ResponseEntity<BoardResultDto> fileUpload(@PathVariable int boardId, MultipartHttpServletRequest request){
		BoardResultDto resultDto = service.fileUpload(boardId, request);

		if(resultDto != null) {
			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@GetMapping("/download/{fileId}")
	public ResponseEntity<Resource> fileDownload(@PathVariable int fileId) throws IOException{
		BoardFileResultDto resultDto = service.fileDownload(fileId);
		System.out.println(resultDto);
		BoardFile bfile = resultDto.getFile();
		
		File file = new File(bfile.getFileUrl());
		
		Resource resource =  new InputStreamResource(new FileInputStream(file));
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + new String(bfile.getFileName().getBytes("UTF-8"),"ISO-8859-1"))
				.body(resource);
	}
	
	@PutMapping("/boards")
	public ResponseEntity<BoardResultDto> update(@RequestBody BoardParamDto paramDto){
		BoardResultDto resultDto = service.update(paramDto);
		System.out.println(resultDto);
		if(resultDto != null) {
			return new ResponseEntity<>(resultDto,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/boards/{boardId}")
	public ResponseEntity<BoardResultDto> delete(@PathVariable int boardId){
		BoardResultDto resultDto = service.delete(boardId);
		
		if(resultDto != null) {
			return new ResponseEntity<>(resultDto,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/boards/wish/{userSeq}")
	public ResponseEntity<List<PropertyDetailDto>> getWishList(@PathVariable int userSeq) {
		List<PropertyDetailDto> result = service.getWishList(userSeq);
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/boards/wish")
	public ResponseEntity<List<PropertyDetailDto>> modifyMyArea(@RequestParam int houseNo, @RequestParam int userSeq){
		List<PropertyDetailDto> result = service.modifyMyArea(houseNo, userSeq);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/boards/news")
	public ResponseEntity<StringBuffer> getNews(){
		StringBuffer news = NaverNewsApi.getNews();
		System.out.println(news);
		return ResponseEntity.ok(news);
	}
}
