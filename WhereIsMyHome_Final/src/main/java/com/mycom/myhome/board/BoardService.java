package com.mycom.myhome.board;

import org.springframework.web.multipart.MultipartHttpServletRequest;

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
