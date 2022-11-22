package com.mycom.myhome.comment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mycom.myhome.common.Status;
import com.mycom.myhome.user.User;
import com.mycom.myhome.user.UserDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	
	private final CommentDao dao;
	private final UserDao userDao;
	
	public List<CommentDto.Response> getComments(int boardId){
		List<Comment> commentList = dao.selectByboardId(boardId);
		
		List<CommentDto.Response> list = new ArrayList<>();
		commentList.forEach(comment->{
			int userSeq = comment.getUserSeq();
			User user = userDao.selectBySeq(userSeq);
			
			System.out.println(comment);
			list.add(CommentDto.Response.builder()
						.no(comment.getNo())
						.name(user.getName())
						.email(user.getEmail())
						.profileImageUrl(user.getProfileImageUrl())
						.content(comment.getContent())
						.heart(comment.getHeart())
						.regDt(comment.getRegDt())
						.build()
					);
			
		});
		
		
		return list;
	}
	
	@Override
	public CommentDto.Response processNewComment(CommentDto.Request dto){
		String email = dto.getEmail();
		User user = userDao.selectByEmail(email);
		
		if(user != null) {
			Comment commentEntity = dto.toEntity(user.getSeq());
			dao.insertComment(commentEntity);
			
			return CommentDto.Response.builder()
					.result(Status.SUCCESS)
					.build();
		}
		
		return CommentDto.Response.builder().result(Status.FAIL).build();
	}
	

	@Override
	public CommentDto.Response processRemoveComment(int no){
		int result = dao.deleteComment(no);
			
		if(result == 1) {
			return CommentDto.Response.builder()
					.result(Status.SUCCESS)
					.build();
		}
		
		return CommentDto.Response.builder().result(Status.FAIL).build();
	}
	
	@Override
	public CommentDto.Response processPlusHeart(CommentDto.Request dto){
		String email = dto.getEmail();
		User user = userDao.selectByEmail(email);
		
		if(user != null) {
			Comment commentEntity = dto.toEntity(user.getSeq());
			
			System.out.println(commentEntity.toString());
			int result = dao.selectByInfo(commentEntity);
			
			System.out.println(result);
			if(result == 0) {
				dao.insertByInfo(commentEntity);
				dao.updateHeartByNo(commentEntity);
				
				
				return CommentDto.Response.builder()
						.result(Status.SUCCESS)
						.build();
			}
		}

		
		return CommentDto.Response.builder().result(Status.FAIL).build();
	}
}
