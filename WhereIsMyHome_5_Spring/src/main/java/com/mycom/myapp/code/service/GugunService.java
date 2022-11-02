package com.mycom.myapp.code.service;

import java.util.List;

import com.mycom.myapp.code.dto.GugunDto;

public interface GugunService {
	List<GugunDto> gugunList(String sidoCode);
}
