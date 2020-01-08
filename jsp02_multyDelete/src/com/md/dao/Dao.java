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
	
	public int insert(Dto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		String sql = " INSERT INTO MDT VALUES(MDTSEQ.NEXTVAL,?,?,?,SYSDATE) ";
		int res = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getName());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("query");
			res = pstm.executeUpdate();
			System.out.println("execute");
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR 3,4");
		} finally {
			close(pstm, con);
		}
		return res;
	}

	public int update() {
		return 0;
	}
	
	public int delete(String[]num) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		String sql = " DELETE FROM MDT WHERE NUM =? ";
		int res = 0;
		int[]cnt=null;
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("query");
			for(int i=0; i<num.length; i++) {
				pstm.setString(1, num[i]);
				pstm.addBatch();
			}
			cnt = pstm.executeBatch();
			
			for(int i=0; i<cnt.length; i++) {
				if(cnt[i]==-2) { // 성공일때
					res++; 		// 하나라도 있으면 
				} 				//(res>0)으로 성공
			}
			if(res==num.length) { //들어온 배열 길이와
				commit(con); 	// 성공한 수가 같으면
			}					// 모두 성공이므로
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm, con);
		}
		
		return res;
	}
	
}
