package com.mycom.myhome.address;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StationDto {
	String line;
	String name;
	String code;
	String lat;
	String lng;
}
