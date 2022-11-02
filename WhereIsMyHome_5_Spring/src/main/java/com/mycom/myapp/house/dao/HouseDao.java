package com.mycom.myapp.house.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.house.dto.HouseDto;

@Mapper
public interface HouseDao {
	public List<HouseDto> getSearchByDong(String dongName);
	public List<HouseDto> getSearchByApt(String aptName);
}