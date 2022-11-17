package com.mycom.myapp.code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myapp.code.dao.DongDao;
import com.mycom.myapp.code.dto.DongDto;

@Service
public class DongServiceImpl implements DongService{

	@Autowired
	DongDao dao;
	
	@Override
	public List<DongDto> dongList(String gugunCode) {
		System.out.println("Dong Service Impl - Dong List");
		List<DongDto> list=null;
		
		try {
			list=dao.dongList(gugunCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
