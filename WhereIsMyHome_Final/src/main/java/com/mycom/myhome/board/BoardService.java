package com.mycom.myhome.board;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myhome.board.file.BoardFileResultDto;
import com.mycom.myhome.property.PropertyDetailDto;

public interface BoardService {
	
	public BoardResultDto list(int limit, int offset);
	public BoardResultDto detail(int boardId, String userEmail);
	public BoardResultDto listCount(String boardClsf);
	
	public BoardResultDto insert(BoardParamDto paramDto);
	public BoardResultDto fileUpload(int boardId, MultipartHttpServletRequest request);
	public BoardFileResultDto fileDownload(int fileId);
	
	public BoardResultDto update(BoardParamDto paramDto);
	
	public BoardResultDto delete(int boardId);
}
