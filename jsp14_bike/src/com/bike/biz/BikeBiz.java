package com.bike.biz;

import java.util.List;

import com.bike.dto.BikeDto;

public interface BikeBiz {

	public List<BikeDto> selectList();
	public BikeDto selectOne();
	public int insert(BikeDto dto);
	public int update(BikeDto dto);
	public int delete(int num);
	
}
