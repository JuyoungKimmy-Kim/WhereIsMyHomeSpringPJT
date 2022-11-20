package com.mycom.myhome.address;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AddressDao {
	List<SidoDto> sidoList();
	List<GugunDto> gugunList(String sidoCode);
	List<DongDto> dongList(String gugunCode);
}
