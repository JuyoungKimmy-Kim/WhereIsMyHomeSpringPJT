package com.mycom.myhome.property;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	PropertyDao dao;
	@Autowired
	PropertyDetailDao detailDao;
	
	@Override
	public List<PropertyDto> searchByDong(String dongName, String gugunCode) {
		List<PropertyDto> list=null;
		try {
			list=dao.searchByDong(dongName, gugunCode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<PropertyDto> searchByRegion(String regionCode) {
		List<PropertyDto> list=null;
		
		try {
			if(regionCode.length() == 2) {
				list=dao.searchBySidoCode(regionCode);
			}else {
				list=dao.searchByGugunCode(regionCode);
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

	@Override
	public List<PropertyDetailDto> propertyDetail(String houseNo) {
		List<PropertyDetailDto> list=null;
		try {
			list=detailDao.propertyDetail(houseNo);
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		return list;
	}

}