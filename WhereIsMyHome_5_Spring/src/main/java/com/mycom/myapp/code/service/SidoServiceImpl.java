package com.mycom.myapp.code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myapp.code.dao.SidoDao;
import com.mycom.myapp.code.dto.SidoDto;

@Service
public class SidoServiceImpl implements SidoService{

	@Autowired
	SidoDao dao;
	
	@Override
	public List<SidoDto> sidoList() {
		
		System.out.println("Sido Service Impl - Sido List");
		List<SidoDto> list=null;
		try {
			list=dao.sidoList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
