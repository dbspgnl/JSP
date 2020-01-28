package com.cal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	
}
