package com.md.dao;

import static com.md.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.md.dto.Dto;

public class Dao {

	public List<Dto> selectList(){
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT NUM, NAME, TITLE, CONTENT, "
				+ "REGDATE FROM MDT ORDER BY NUM DESC ";
		List<Dto> list = new ArrayList<Dto>();
		try {
			stmt = con.createStatement();
			System.out.println("query ready");
			rs = stmt.executeQuery(sql);
			System.out.println("execute");
			while(rs.next()) {
				Dto dto = new Dto();
				dto.setNum(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setDate(rs.getDate(5));
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("ERROR 3,4");
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt, con);
		}
		return list;
	}
	
	public Dto selectOne() {
	
		return null;
	}
	
	public int insert() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		String sql = " INSERT MDT ";
		return 0;
	}

	public int update() {
		return 0;
	}
	
	public int delete() {
		return 0;
	}
	
}
