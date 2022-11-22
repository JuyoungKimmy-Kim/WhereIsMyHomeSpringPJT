package com.mycom.myhome.comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentDao {
	
	List<Comment> selectByboardId(int boardId);
	
	int insertComment(Comment comment);
	
	int deleteComment(int no);
	
	int updateHeartByNo(Comment comment);
	
	int selectByInfo(Comment comment);
	
	int insertByInfo(Comment comment);
}
