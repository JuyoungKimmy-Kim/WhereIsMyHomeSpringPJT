package com.mycom.myhome.board;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mycom.myhome.common.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName="of")
public class BoardResultDto {
	private final Status result;
	private final String message;
	
	private final Board board;
	
	private final List<Board> list;
	
	private final int listCount;
	
	private final List<BoardFile> fileList;
	

	public static BoardResultDto ofSuccess(String message, Board post, List<Board> list, int listCount, List<BoardFile> fileList) {
		return new BoardResultDto(Status.SUCCESS, message, post, list, listCount, fileList);
	}
	
	public static BoardResultDto ofFail(String message) {
		return new BoardResultDto(Status.FAIL, message, null, null, 0, null);
	}
}
