package com.mycom.myapp.code.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.code.dto.DongDto;

@Mapper
public interface DongDao {
	List<DongDto> dongList(String gugunCode);
}
