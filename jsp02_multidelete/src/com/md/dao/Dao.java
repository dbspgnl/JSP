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
		
		String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE FROM MDBOARD ORDER BY SEQ DESC ";
		List<Dto> list = new ArrayList<Dto>();
		
		try {
			stmt = con.createStatement();
			System.out.println("sql");
			rs = stmt.executeQuery(sql);
			System.out.println("execute");
			while(rs.next()) {
				Dto dto = new Dto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("ERROR 3,4");
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}
		return list;
	}
	
	public int multiDelete(String[] seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM MDBOARD WHERE SEQ = ? ";
		
		int[] cnt = null;
		try {
			pstm = con.prepareStatement(sql);
			
			for(int i =0; i<seq.length; i++) {
				pstm.setString(1, seq[i]);
				pstm.addBatch(); // 메모리에 적재 후, executeBatch()가 호출될 때 한번에 실행
				System.out.println("삭제할 번호: "+ seq[i]);
			}
			System.out.println("3 query set");
			cnt = pstm.executeBatch(); //메모리에 있던 query를 한번에 실행, int[]로 리턴
			System.out.println("execute");
			
			//[-2,-2,-3, ...] 성공 실패를 배열로 줌
			for(int i=0; i<cnt.length; i++) {
				// -2 : 성공
				// -3 : 실패
				if(cnt[i]==-2) {
					res++;
				}
			}
			if(res == seq.length) {
				commit(con);
			}
			
		} catch (SQLException e) {
			System.out.println("ERROR 3,4");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("db close");
		}
		
		return res;
	}
	
	public Dto selectOne(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE "
				+ "FROM MDBOARD WHERE SEQ=? ";
		Dto dto = new Dto();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			rs = pstm.executeQuery();
			while(rs.next()) {
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
			}
			
		} catch (SQLException e) {
			System.out.println("ERROR 3,4");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return dto;
	}
	
	public int insert(Dto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO MDBOARD VALUES(MDBOARDSEQ.NEXTVAL,?,?,?,SYSDATE) ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
						
			res = pstm.executeUpdate();
			
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			System.out.println("ERROR 3,4");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		return res;
	}
	
	public int update(Dto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE MDBOARD SET TITLE=?, CONTENT=? "
				+ " WHERE SEQ=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			
			res = pstm.executeUpdate();
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		return res;
	}
}
