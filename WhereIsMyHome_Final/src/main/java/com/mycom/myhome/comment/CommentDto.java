package com.mycom.myhome.comment;

import java.time.LocalDateTime;
import java.util.List;

import com.mycom.myhome.common.Status;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class CommentDto {
	
	@Getter
	@Builder
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Request{
		private int no;
		private String email;
		private int boardId;
		private String content;
		
		public Comment toEntity(int seq) {
			return Comment.builder()
					.no(no)
					.userSeq(seq)
					.boardId(boardId)
					.content(content)
					.build();
		}
	}
	
	private List<Response> list;
	
	@Getter
	@Builder
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		private Status result;
		
		private int no;
		private String name;
		private String email;
		private String profileImageUrl;
		
		private String content;
		private int heart;
		private LocalDateTime regDt;
		
	}

}
