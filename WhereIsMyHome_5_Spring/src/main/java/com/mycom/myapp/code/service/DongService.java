package com.mycom.myapp.code.service;

import java.util.List;

import com.mycom.myapp.code.dto.DongDto;

public interface DongService {
	List<DongDto> dongList(String gugunCode);
}
