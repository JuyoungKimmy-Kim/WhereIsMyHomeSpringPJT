package com.mycom.myapp.house.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HouseDto {
	// 동별, 아파트 검색용 
	
	private String no;
	private String dong;
	private String aptName;
	private String code;
	
	private String buildYear;
	private String jibun;
	private String lat;
	private String lng;
	private String img;
	
	private String dealAmount;
	private String dealYear;
	private String dealMonth;
	private String dealDay;
	private String area;
	private String floor;
	private String type;
	private String rentMoney;
	private String houseNo;
}

