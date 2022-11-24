package com.mycom.myhome.property;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PropertyDetailDao {
	public List<PropertyDetailDto> propertyDetail(String houseNo);
	public List<PropertyDetailDto> cheapestProperty(String gugunCode);
}