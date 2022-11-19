package com.mycom.myhome.board;

import lombok.Data;

@Data
public class BoardParamDto {
	
	private String boardId;
	private String title;
	private String content;
	private String userEmail;
	
	public Board toEntity(String userSeq) {
		return new Board(boardId, userSeq, title, content, null, 0, "001");
	}
	
}
