package com.bike.biz;

import java.util.List;

import com.bike.dao.BikeDao;
import com.bike.dto.BikeDto;

public class BikeBiz {

	BikeDao dao = new BikeDao();
	
	public int insert(List<BikeDto> list) {
		return dao.insert(list);
	}

}
