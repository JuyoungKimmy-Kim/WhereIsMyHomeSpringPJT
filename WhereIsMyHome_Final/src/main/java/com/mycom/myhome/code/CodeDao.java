package com.mycom.myhome.code;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CodeDao {
	Code selectByCode(@Param("groupCode") String groupCode, @Param("code") String code);
}
