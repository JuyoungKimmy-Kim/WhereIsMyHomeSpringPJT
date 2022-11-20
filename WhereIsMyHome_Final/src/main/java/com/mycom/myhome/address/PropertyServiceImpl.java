package com.mycom.myhome.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	PropertyDao dao;
	
	@Override
	public List<PropertyDto> searchByDong(String dongName, String gugunCode) {
		List<PropertyDto> list=null;
		try {
			list=dao.searchByDong(dongName);
			if (list.size()==0) {
				list=dao.searchByGugunCode(gugunCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public List<PropertyDto> searchByApt(String aptName) {
		List<PropertyDto> list=null;
		
		try {
			list=dao.searchByApt(aptName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
