package com.mycom.myhome.wishList;

import java.util.List;

import com.mycom.myhome.common.Status;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class WishListDto {
	@Setter
	@Getter
	@Builder
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Request{

		private int houseNo;
		private int userSeq;
		
		@Builder
		public WishList toEntity(int houseNo, int userSeq) {
			return WishList.builder()
					.houseNo(houseNo)
					.userSeq(userSeq)
					.build();
		}
	}
	
	private List<Response> list;
	
	@Getter
	@Builder
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		private Status result;
		
		private String dong;
		private String aptname;
		private String buildyear;
		private String code;
		private int dealAmount;
		
	}
}
