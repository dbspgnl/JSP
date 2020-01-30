package com.mvc.dao;

import java.util.List;
import com.mvc.dao.SqlMapConfig.*;
import com.mvc.dto.MyDto;

public class MyDaoImpl implements MyDao{

	@Override
	public List<MyDto> selectList() {
		List<MyDto>list = null;
		
		return list;
	}

	@Override
	public MyDto selectOne(int seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(MyDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(MyDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int seq) {
		// TODO Auto-generated method stub
		return 0;
	}

}
