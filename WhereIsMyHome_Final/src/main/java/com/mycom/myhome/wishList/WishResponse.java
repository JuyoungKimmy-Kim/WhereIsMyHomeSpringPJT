package com.mycom.myhome.wishList;

import com.mycom.myhome.common.Status;

import lombok.Data;

@Data
public class WishResponse {
	private Status result;
	
	private String dong;
	private String aptname;
	private String buildyear;
	private String code;
	private int dealAmount;
}
