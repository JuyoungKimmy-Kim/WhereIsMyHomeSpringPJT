package com.mycom.myapp.house.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myapp.house.dao.HouseDao;
import com.mycom.myapp.house.dto.HouseDto;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	HouseDao dao;
	
	@Override
	public List<HouseDto> getSearchByDong(String dongName) {
		System.out.println("House Service Impl - getSearchByDong");
		List<HouseDto> list=null;
		
		try {
			list=dao.getSearchByDong(dongName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<HouseDto> getSearchByApt(String aptName) {
		System.out.println("House Service Impl - getSearchByApt");
		List<HouseDto> list=null;
		
		try {
			list=dao.getSearchByApt(aptName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}