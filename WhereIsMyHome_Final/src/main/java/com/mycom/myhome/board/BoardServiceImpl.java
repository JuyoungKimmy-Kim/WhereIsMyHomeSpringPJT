package com.mycom.myhome.board;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myhome.board.file.BoardFile;
import com.mycom.myhome.board.file.BoardFileResultDto;
import com.mycom.myhome.comment.CommentDao;
import com.mycom.myhome.property.PropertyDetailDto;
import com.mycom.myhome.user.User;
import com.mycom.myhome.user.UserDao;

import ch.qos.logback.classic.Logger;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final UserDao userDao;
	
	private final CommentDao commentDao;
	
	private final BoardDao dao;
	
	
	@Value("${app.fileupload.uploadDir}")
	private String uploadFolder;
	 
	@Override
	public BoardResultDto list(int limit, int offset) {
		List<Board> boardList = dao.list(limit, offset, "001");
		if(!boardList.isEmpty()) {
			return BoardResultDto.ofSuccess("목록을 불러왔습니다.", null, boardList, 0, null);
		}
		return BoardResultDto.ofFail("목록을 불러오는데 실패하였습니다.");
	}

	@Override
	public BoardResultDto detail(int boardId, String userEmail) {
		Board board = dao.detail(boardId);
		
		User findUser = userDao.selectByEmail(userEmail);
		if(findUser != null) {
			int sameUser = dao.ReadCount(boardId,findUser.getSeq());
			if(sameUser != 1) {
				dao.insertReadCount(boardId, findUser.getSeq());
				dao.addReadCount(boardId);
			}
			
			board = dao.detail(boardId);
		}

		System.out.println("detail" + board.toString());
		List<BoardFile> fileList = dao.getFileList(boardId);
		return BoardResultDto.ofSuccess("게시글을 불러왔습니다.", board, null, 0, fileList);
	}

	@Override
	public BoardResultDto listCount(String boardClsf) {
		int count = dao.listCount(boardClsf);
		if(count >= 0) {
			return BoardResultDto.ofSuccess("전체 목록 수를 불러오는데 성공하였습니다.", null, null, count, null);
		}
		return BoardResultDto.ofFail("전체 목록 수 조회과정에서 오류가 발생하였습니다.");
	}
	
	@Override
	public BoardResultDto insert(BoardParamDto paramDto) {
		String userEmail = paramDto.getUserEmail();
		System.out.println(userEmail);
		User findUser = userDao.selectByEmail(userEmail);	
		
		if(findUser != null) {
			int result = dao.insert(paramDto.toEntity(findUser.getName()));
			System.out.println(result);
			if(result == 1) {
				Board board = dao.list(1,0,"001").get(0);
				return BoardResultDto.ofSuccess("게시물이 등록되었습니다.", board, null, 0, null);
			}
		}
		
		return BoardResultDto.ofFail("게시물 등록이 실패하였습니다.");
	}
	
	@Override
	public BoardResultDto fileUpload(int boardId, MultipartHttpServletRequest request) {
		try {
			File uploadDir = new File(uploadFolder);
			if(!uploadDir.exists()) uploadDir.mkdir();
			
			List<MultipartFile> fileList = request.getFiles("file");
			for (MultipartFile partFile : fileList) {
				String fileName = partFile.getOriginalFilename();
				
				UUID uuid = UUID.randomUUID();
				
				String extension = FilenameUtils.getExtension(fileName);
				
				String savingFileName = uuid+"."+extension;
				File destFile = new File(uploadFolder + savingFileName);
				partFile.transferTo(destFile);
				 
				BoardFile files =new BoardFile(0, boardId, fileName, partFile.getSize(), partFile.getContentType(), uploadFolder + savingFileName, null);
				dao.uploadFile(files);
			}
			return BoardResultDto.ofSuccess("파일 업로드 완료.", null, null, 0, null);
		}catch(Exception e) {
			e.printStackTrace();
		}

		return BoardResultDto.ofFail("파일 업로드 실패.");
	}
	
	@Override
	public BoardFileResultDto fileDownload(int fileId) {
		
		BoardFile file = dao.downloadFile(fileId);
			
		if(file != null) {
			return BoardFileResultDto.ofSuccess("파일 다운로드 성공", file);
		}
		
		return BoardFileResultDto.ofFail("파일 다운로드 실패");
	}

	@Override
	public BoardResultDto update(BoardParamDto paramDto) {
		Board post = paramDto.toEntity(null);
		int result = dao.update(post);
		if(result == 1) {
			return BoardResultDto.ofSuccess("게시물이 수정되었습니다.", post, null, 0, null);
		}
		
		return BoardResultDto.ofFail("게시물 수정에 실패하였습니다.");
	}

	@Override
	public BoardResultDto delete(int boardId) {
		List<String> fileUrlList = dao.deleteFileUrl(boardId);
        
		for(String fileUrl : fileUrlList) {
            File file = new File(uploadFolder + fileUrl);
            if(file.exists()) {
                file.delete();
            }
        }

        System.out.println("ERROR");
        
        dao.deleteFile(boardId);
        dao.deleteReadCount(boardId);
        commentDao.deleteHeartByBoardId(boardId);
        commentDao.deleteCommentByBoardId(boardId);
        
        System.out.println("ERROR");
		int result = dao.delete(boardId);
		if(result == 1) {
			return BoardResultDto.ofSuccess("게시물이 삭제되었습니다.", null, null, 0, null);
		}
		
		return BoardResultDto.ofFail("게시물 삭제에 실패하였습니다.");
	}
}
