package com.mycom.myapp.house.service;

import java.util.List;

import com.mycom.myapp.code.dto.DongDto;
import com.mycom.myapp.code.dto.GugunDto;
import com.mycom.myapp.code.dto.SidoDto;
import com.mycom.myapp.house.dto.HouseDto;

public interface HouseService {
	public List<SidoDto> getSido();

	public List<GugunDto> getGugun(String sido_code);

	public List<DongDto> getDong(String gugun_code);

	public List<HouseDto> getSearchByDong(String dong_name);

	public List<HouseDto> getSearchByApt(String apt_name);
}