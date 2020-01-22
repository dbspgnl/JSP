package com.bike.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import static com.bike.db.JDBCTemplate.*;

import com.bike.dto.BikeDto;

public class BikeDaoImpl implements BikeDao {

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
	public int insert(BikeDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		int num = dto.getContent_id();
		String sql = " INSERT ";
		

		
		return res;
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
