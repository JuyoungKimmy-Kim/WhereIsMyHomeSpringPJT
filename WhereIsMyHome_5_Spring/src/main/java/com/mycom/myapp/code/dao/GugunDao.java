package com.mycom.myapp.code.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.code.dto.GugunDto;

@Mapper
public interface GugunDao {
	List<GugunDto> gugunList(String sidoCode);
}
