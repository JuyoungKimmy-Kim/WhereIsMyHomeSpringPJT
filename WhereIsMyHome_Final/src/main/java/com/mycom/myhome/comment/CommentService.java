package com.mycom.myhome.comment;

import java.util.List;

public interface CommentService {
	
	public List<CommentDto.Response> getComments(int boardId);
	public CommentDto.Response processNewComment(CommentDto.Request dto);
	public CommentDto.Response processRemoveComment(int no);
	public CommentDto.Response processPlusHeart(CommentDto.Request dto);
}
