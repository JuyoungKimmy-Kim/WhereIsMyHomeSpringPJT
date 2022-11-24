package com.mycom.myhome.station;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StationDao {
	public List<StationDto> stationList ();
}
