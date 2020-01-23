package com.bike.biz;

import java.util.List;

import com.bike.dao.BikeDao;
import com.bike.dao.BikeDaoImpl;
import com.bike.dto.BikeDto;

public class BikeBizImpl implements BikeBiz {

	BikeDao dao = new BikeDaoImpl();
	
	@Override
	public List<BikeDto> selectList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BikeDto selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(List<BikeDto> list) {
		return dao.insert(list);
	}

	@Override
	public int update(BikeDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int num) {
		// TODO Auto-generated method stub
		return 0;
	}

}
