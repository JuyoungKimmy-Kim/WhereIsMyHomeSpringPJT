package com.mycom.myhome.address;

import java.util.List;

public interface PropertyService {
	public List<PropertyDto> searchByDong(String dongName, String gugunCode);
	public List<PropertyDto> searchByApt(String aptName);
}
