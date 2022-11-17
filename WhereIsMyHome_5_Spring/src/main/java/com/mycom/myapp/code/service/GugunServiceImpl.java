package com.mycom.myapp.code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myapp.code.dao.GugunDao;
import com.mycom.myapp.code.dto.GugunDto;

@Service
public class GugunServiceImpl implements GugunService{

	@Autowired
	GugunDao dao;
	
	@Override
	public List<GugunDto> gugunList(String sidoCode) {
		System.out.println("Gugun Service Impl - Gugun List");
		List<GugunDto> list=null;
		
		try {
			list=dao.gugunList(sidoCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
