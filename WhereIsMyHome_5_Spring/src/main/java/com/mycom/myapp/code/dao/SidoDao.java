package com.mycom.myapp.code.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.code.dto.SidoDto;

@Mapper
public interface SidoDao {
	List<SidoDto> sidoList();
}
