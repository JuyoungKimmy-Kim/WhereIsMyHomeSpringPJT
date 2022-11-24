package com.mycom.myhome.wishList;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface WishListDao {
	public List<Integer> selectListBySeq(int userSeq);
	public WishResponse selectByHouseWithNo(int houseNo);
	public int insertWishList(WishList reqDto);
	public int deleteWishList(WishList reqDto);
}
