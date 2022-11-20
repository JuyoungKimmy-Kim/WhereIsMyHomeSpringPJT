package com.mycom.myhome.board;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mycom.myhome.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName="of")
public class BoardResultDto {
	private final Status result;
	private final String message;
	
	private final Board post;
	
	private final List<BoardInfo> list;
	
	private final int listCount;
	
	private final List<BoardFile> fileList;
	
	private static List<BoardInfo> infoList(List<Board> boardList) {
		if(boardList != null) {
			List<BoardInfo> resultList = new ArrayList<>();
			boardList.forEach(board->resultList.add(toBoardInfo(board)));
			return resultList;
		}
		return null;
	}
	
	private static BoardInfo toBoardInfo(Board board) {
		if(board == null) return null;
		BoardInfo info = new BoardInfo();
		info.setBoardId(board.getBoardId());
		info.setTitle(board.getTitle());
		info.setUserName(board.getWriter());
		info.setRegDt(board.getRegDt());
		info.setReadCount(board.getReadCount());
		return info;
	}
	
	public static BoardResultDto ofSuccess(String message, Board post, List<Board> list, int listCount, List<BoardFile> fileList) {
		return new BoardResultDto(Status.SUCCESS, message, post, infoList(list), listCount, fileList);
	}
	
	public static BoardResultDto ofFail(String message) {
		return new BoardResultDto(Status.FAIL, message, null, null, 0, null);
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	private static class BoardInfo {
		private String boardId;
		private String title;
		private String userName;
		private LocalDateTime regDt;
		private int readCount;
	}
}
