package com.mycom.myhome.station;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl implements StationService{
	@Autowired
	StationDao dao;

	@Override
	public List<StationDto> stationList() {
		List<StationDto> list=null;
		try {
			list=dao.stationList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}	
}
