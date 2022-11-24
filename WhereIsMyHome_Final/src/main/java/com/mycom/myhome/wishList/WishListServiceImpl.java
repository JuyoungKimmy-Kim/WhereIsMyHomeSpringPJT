package com.mycom.myhome.wishList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.myhome.common.Status;
import com.mycom.myhome.wishList.WishListDto.Request;
import com.mycom.myhome.wishList.WishListDto.Response;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService{

	private final WishListDao dao;
	
	@Override
	public List<Response> getWishList(int userSeq) {
		List<Integer> houseNoList = dao.selectListBySeq(userSeq);
		List<Response> list = new ArrayList<Response>();
		houseNoList.forEach(el->{
			WishResponse item = dao.selectByHouseWithNo(el);
			
			if(item != null) {
				list.add(WishListDto.Response.builder()
						.aptname(item.getAptname())
						.buildyear(item.getBuildyear())
						.code(el.toString())
						.dealAmount(item.getDealAmount())
						.dong(item.getDong())
						.build());
			}
		});
		
		return list;
	}

	@Override
	public Response addFavorite(Request reqDto) {
		dao.insertWishList(reqDto.toEntity(reqDto.getHouseNo(), reqDto.getUserSeq()));
		return Response.builder().result(Status.SUCCESS).build();
	}

	@Override
	public Response removeFavorite(Request reqDto) {
		System.out.println(reqDto.toString());
		dao.deleteWishList(reqDto.toEntity(reqDto.getHouseNo(), reqDto.getUserSeq()));
		return Response.builder().result(Status.SUCCESS).build();
	}

}
