package com.cal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cal.dto.CalDto;

public class CalDao {

	public int insertCalBoard(CalDto dto) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. driver con");
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR 1");
			e.printStackTrace();
		}
		
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
			System.out.println("2. account con");
		} catch (SQLException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
		
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO CALBOARD "
				+ " VALUES(CALBOARDSEQ.NEXTVAL,?,?,?,?,SYSDATE) ";
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3. query" + sql);
			pstm.setString(1, dto.getId());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			pstm.setString(4, dto.getMdate());
			res = pstm.executeUpdate();
			System.out.println("4. execute");
			if(res>0) {
				con.commit();
			}
		} catch (SQLException e) {
			System.out.println("ERROR 3,4");
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
				con.close();
				System.out.println("5. close");
			} catch (SQLException e) {
				System.out.println("ERROR 5");
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public List<CalDto> selectCalList(String id, String yyyyMMdd){
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. driver con");
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR 1");
			e.printStackTrace();
		}
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
			System.out.println("2. account con");
		} catch (SQLException e) {
			System.out.println("ERROR 2");
			e.printStackTrace();
		}
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM CALBOARD "
				+ " WHERE ID=? AND SUBSTR(MDATE,1,8)=? ";
		List<CalDto> list = new ArrayList<CalDto>();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMdd);
			System.out.println("3. query" + sql);
			rs = pstm.executeQuery();
			System.out.println("4. execute");
			while(rs.next()) {
				CalDto dto = 
				 new CalDto(
						 rs.getInt(1),
						 rs.getString(2),
						 rs.getString(3),
						 rs.getString(4),
						 rs.getString(5),
						 rs.getDate(6)
						 );
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("ERROR 3,4");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstm.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("ERROR 5");
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List<CalDto> getCalViewList(String id, String yyyyMM){
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. driver con");
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR 1");
			e.printStackTrace();
		}
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
			System.out.println("2. account con");
		} catch (SQLException e) {
			System.out.println("ERROR 2");
			e.printStackTrace();
		}
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CalDto> list = new ArrayList<CalDto>();
				
		String sql = " SELECT * " + 
				" FROM( " + 
				"	SELECT (ROW_NUMBER() OVER(PARTITION BY SUBSTR(MDATE,1,8) ORDER BY MDATE)) RN, " + 
				"	SEQ, ID, TITLE, CONTENT, MDATE, REGDATE " + 
				"	FROM CALBOARD " + 
				"	WHERE ID = ? AND SUBSTR(MDATE,1,6)=? " + 
				" ) " + 
				" WHERE RN BETWEEN 1 AND 3 ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMM);
			System.out.println("3. query" + sql);
			rs = pstm.executeQuery();
			System.out.println("4. execute");
			while(rs.next()) {
				CalDto dto = 
				 new CalDto(
						 //(1):RN
						 rs.getInt(2),
						 rs.getString(3),
						 rs.getString(4),
						 rs.getString(5),
						 rs.getString(6),
						 rs.getDate(7)
						 );
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("ERROR 3,4");
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstm.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("ERROR 5");
				e.printStackTrace();
			}
		}
		return list;
	}

	public int getCalCount(String id, String yyyyMMdd) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. driver con");
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR 1");
			e.printStackTrace();
		}
		
		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		
		try {
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
			System.out.println("2. account con");
		} catch (SQLException e) {
			System.out.println("ERROR 2");
			e.printStackTrace();
		}
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		String sql = " SELECT COUNT(*) FROM CALBOARD"
				+ " WHERE ID=? AND SUBSTR(MDATE,1,8)=? ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMdd);
			System.out.println("3.query");
			rs = pstm.executeQuery();
			System.out.println("4.execute");
			while(rs.next()) {
				res = rs.getInt(1);
			}
			if(res>0) {
				con.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR 3,4");
		} finally {
			try {
				rs.close();
				pstm.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("ERROR 5");
				e.printStackTrace();
			}
			
		}
		return res;
	}
	
	
}
