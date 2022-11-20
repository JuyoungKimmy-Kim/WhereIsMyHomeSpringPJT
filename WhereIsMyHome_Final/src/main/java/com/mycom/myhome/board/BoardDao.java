package com.mycom.myhome.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardDao {
	
	List<Board> list(@Param("limit") int limit, @Param("offset") int offset, @Param("boardClsf") String boardClsf);
	int listCount(String boardClsf);
	
	Board detail(String boardId);
	List<BoardFile> getFileList(String boardId);
	int ReadCount(@Param("boardId") String boardId, @Param("userSeq")  String userSeq);
	int insertReadCount(@Param("boardId") String boardId, @Param("userSeq")  String userSeq);
	int addReadCount(String boardId);
	
	int insert(Board board);
	int uploadFile(BoardFile files);
	
	int update(Board board);
	
	List<String> deleteFileUrl(String boardId);
	int deleteReadCount(String boardId);
	int deleteFile(String boardId);
	int delete(String boardId);
}
