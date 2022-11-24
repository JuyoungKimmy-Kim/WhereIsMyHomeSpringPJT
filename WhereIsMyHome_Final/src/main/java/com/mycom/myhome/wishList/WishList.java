package com.mycom.myhome.wishList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WishList {
	int houseNo;
	int userSeq;
	
	@Builder
	public WishList(int houseNo, int userSeq) {
		this.houseNo = houseNo;
		this.userSeq = userSeq;
	}
}
