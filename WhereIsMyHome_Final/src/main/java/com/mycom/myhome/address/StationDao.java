package com.mycom.myhome.address;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StationDao {
	public List<StationDto> stationList ();
}
