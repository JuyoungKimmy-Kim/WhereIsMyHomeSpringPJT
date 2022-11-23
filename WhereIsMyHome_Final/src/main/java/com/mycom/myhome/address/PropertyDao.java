package com.mycom.myhome.address;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PropertyDao {
	public List<PropertyDto> searchByDong(String dongName);
	public List<PropertyDto> searchByGugun(String gugunName);
	public List<PropertyDto> searchByApt(String aptName);
}
