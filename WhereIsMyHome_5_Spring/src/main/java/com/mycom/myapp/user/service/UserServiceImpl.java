package com.mycom.myapp.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myapp.user.dao.UserDao;
import com.mycom.myapp.user.dto.CodeDto;
import com.mycom.myapp.user.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao dao;
	
	@Override
	public int joinUser(UserDto userDto) {
		return dao.joinUser(userDto);
	}

	@Override
	public List<CodeDto> getCodeclsf() {
		return dao.getCodeclsf();
	}
	
	@Override
	public UserDto login(UserDto userDto) {
		UserDto user = dao.login(userDto.getUserEmail());
		return user != null && user.getUserPassword().equals(userDto.getUserPassword()) ? user : null;
	}

	@Override
	public int updateUser(UserDto userDto) {
		return dao.updateUser(userDto);
	}

	@Override
	public int deleteUser(int userSeq) {
		return dao.deleteUser(userSeq);
	}

}
