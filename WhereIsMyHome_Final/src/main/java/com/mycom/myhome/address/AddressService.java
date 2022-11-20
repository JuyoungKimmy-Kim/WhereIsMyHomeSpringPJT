package com.mycom.myhome.address;

import java.util.List;

public interface AddressService {
	List<SidoDto> sidoList();
	List<GugunDto> gugunList(String sidoCode);
	List<DongDto> dongList(String gugunCode);
}
