package com.mycom.myhome.board;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardFile {
	private int fileId;
	private String boardId;
	private String fileName;
	private long fileSize;
	private String fileContentType;
	private String fileUrl;
	private LocalDateTime regDt;
	
	public void setRegDt(Date regDt) {
		this.regDt = LocalDateTime.ofInstant(regDt.toInstant(), ZoneId.systemDefault());
	}
}
