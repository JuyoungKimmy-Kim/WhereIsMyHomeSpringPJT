package com.mycom.myhome.board;

import lombok.Data;

@Data
public class BoardParamDto {
	
	private int boardId;
	private int userSeq;
	private String title;
	private String content;
	private String userEmail;
	
	public Board toEntity(String writer) {
		return new Board(boardId, userSeq, writer, title, content, null, 0, "001");
	}
	
}
