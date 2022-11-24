package com.mycom.myhome.wishList;

import java.util.List;

import com.mycom.myhome.wishList.WishListDto.Request;
import com.mycom.myhome.wishList.WishListDto.Response;

public interface WishListService {
	public List<Response> getWishList(int userSeq);
	public Response addFavorite(Request reqDto);
	public Response removeFavorite(Request reqDto);
}
