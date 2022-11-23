package com.mycom.myhome.comment;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment {
	private int no;
	private int userSeq;
	private int boardId;
	private String content;
	private LocalDateTime regDt;
	private int heart;
	
	public void setRegDt(Date regDt) {
		// Mybatis가 LocalDateTime을 지원하지 않기 때문에 Date로 먼저 받아서 처리한다.( Mybatis Date Mapping )
		this.regDt = LocalDateTime.ofInstant(regDt.toInstant(), ZoneId.systemDefault());
	}
	
	@Builder
	public Comment(int no, int userSeq, int boardId, String content) {
		this.no = no;
		this.userSeq = userSeq;
		this.boardId = boardId;
		this.content = content;
	}
}
