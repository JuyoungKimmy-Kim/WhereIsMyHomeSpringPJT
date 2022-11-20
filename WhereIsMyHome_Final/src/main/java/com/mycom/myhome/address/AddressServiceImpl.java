package com.mycom.myhome.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	AddressDao dao;
	
	@Override
	public List<SidoDto> sidoList() {
		List<SidoDto> list=null;
		
		try {
			list=dao.sidoList();
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<GugunDto> gugunList(String sidoCode) {
		List<GugunDto> list=null;
		
		try {
			list=dao.gugunList(sidoCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<DongDto> dongList(String gugunCode) {
		List<DongDto> list=null;
		
		try {
			list=dao.dongList(gugunCode);
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
