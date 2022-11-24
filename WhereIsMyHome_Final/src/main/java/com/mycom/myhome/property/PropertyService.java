package com.mycom.myhome.property;

import java.util.List;

public interface PropertyService {
	public List<PropertyDto> searchByDong(String dongName, String gugunCode);
	public List<PropertyDto> searchByRegion(String regionCode);
	public List<PropertyDto> searchByApt(String aptName);
	public List<PropertyDetailDto> propertyDetail(String houseNo);
}