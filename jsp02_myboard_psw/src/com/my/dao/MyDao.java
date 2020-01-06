package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.MyDto;
import static com.my.jdbc.JDBCTemplate.*;

public class MyDao {

	public List<MyDto> selectList(){
		//준비
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<MyDto> list = new ArrayList<MyDto>();
		String sql = " SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, "
				+ " MYDATE FROM MYBOARD ORDER BY MYNO DESC ";
		//실행
		try {
			stmt = con.createStatement();
			System.out.println("3 쿼리준비");
			rs = stmt.executeQuery(sql);
			System.out.println("4 실행리턴");
			while(rs.next()) {
				MyDto dto = new MyDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyname(rs.getString(2));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
				dto.setMydate(rs.getDate(5));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("ERROR 3,4");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
				System.out.println("5 종료");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("ERROR 5");
			}
		}
		return list;
	}
	
	public MyDto selectOne(int myno) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE "
				+ " FROM MYBOARD WHERE MYNO= " + myno;
		MyDto dto = new MyDto();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
			dto = new MyDto(rs.getInt(1), rs.getString(2), 
				rs.getString(3), rs.getString(4), rs.getDate(5));
		}
			System.out.println("3. 쿼리준비: " + sql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR 3,4");
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
				System.out.println("5. db종료");
			} catch (SQLException e) {
				System.out.println("ERROR 5");
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	public int insert(MyDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO MYBOARD VALUES(MYSEQ.NEXTVAL,?,?,?,SYSDATE) ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMyname());
			pstm.setString(2, dto.getMytitle());
			pstm.setString(3, dto.getMycontent());
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public int update(MyDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE MYBOARD SET MYNAME=?, "
				+ " MYTITLE=? MYCONTENT=? WHERE MYNO=? ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMytitle());
			pstm.setString(2, dto.getMycontent());
			pstm.setInt(3, dto.getMyno());
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public int delete(int myno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM MYBOARD WHERE =? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
}
