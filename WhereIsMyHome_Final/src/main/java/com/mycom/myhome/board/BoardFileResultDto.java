package com.mycom.myhome.board;

import com.mycom.myhome.Status;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName="of")
public class BoardFileResultDto {

	private final Status result;
	private final String message;
	
	private final BoardFile file;
	
	public static BoardFileResultDto ofSuccess(String message, BoardFile file) {
		return new BoardFileResultDto(Status.SUCCESS, message, file);
	}
	
	public static BoardFileResultDto ofFail(String message) {
		return new BoardFileResultDto(Status.FAIL, message, null);
	}
}
