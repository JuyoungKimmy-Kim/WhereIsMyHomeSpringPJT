package com.mycom.myapp.house.service;

import java.util.List;

import com.mycom.myapp.code.dto.DongDto;
import com.mycom.myapp.code.dto.GugunDto;
import com.mycom.myapp.code.dto.SidoDto;
import com.mycom.myapp.house.dto.HouseDto;

public interface HouseService {

	public List<HouseDto> getSearchByDong(String dongName);
	public List<HouseDto> getSearchByApt(String aptName);
}