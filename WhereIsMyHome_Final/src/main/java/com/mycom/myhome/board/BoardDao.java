package com.mycom.myhome.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.myhome.address.PropertyDetailDto;

@Mapper
public interface BoardDao {
	
	List<Board> list(@Param("limit") int limit, @Param("offset") int offset, @Param("boardClsf") String boardClsf);
	int listCount(String boardClsf);
	
	Board detail(int boardId);
	List<BoardFile> getFileList(int boardId);
	int ReadCount(@Param("boardId") int boardId, @Param("userSeq")  int userSeq);
	int insertReadCount(@Param("boardId") int boardId, @Param("userSeq")  int userSeq);
	int addReadCount(int boardId);
	
	int insert(Board board);
	int uploadFile(BoardFile files);
	BoardFile downloadFile(int fileId);
	
	int update(Board board);
	
	List<String> deleteFileUrl(int boardId);
	int deleteReadCount(int boardId);
	int deleteFile(int boardId);
	int delete(int boardId);
	
	List<Integer>  findHouseNoByUserSeq(int userSeq);
	PropertyDetailDto findByHouseNo(int houseNo);
}
