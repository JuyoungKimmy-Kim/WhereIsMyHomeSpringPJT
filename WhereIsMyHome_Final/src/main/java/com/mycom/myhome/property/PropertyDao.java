package com.mycom.myhome.property;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PropertyDao {
	public List<PropertyDto> searchByDong(@Param("dongName") String dongName, @Param("gugunCode")  String gugunCode);
	public List<PropertyDto> searchByGugunCode(String gugunCode);
	public List<PropertyDto> searchBySidoCode(String sidoCode);
	public List<PropertyDto> searchByApt(String aptName);
}