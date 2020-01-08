package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.mvc.db.JDBCTemplate.*;

import com.mvc.dto.MVCDto;

public class MVCDaoImpl implements MVCDao {

	@Override
	public List<MVCDto> selectList() {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<MVCDto> list = new ArrayList<MVCDto>();
		try {
			stmt = con.createStatement();
			System.out.println("query");
			rs = stmt.executeQuery(SELECT_LIST_SQL);
			System.out.println("execute");
			while(rs.next()) {
				MVCDto dto = new MVCDto();
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
			close(stmt, con);
		}
		return list;
	}

	@Override
	public MVCDto selectOne(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MVCDto dto = new MVCDto();
		try {
			pstm = con.prepareStatement(SELECT_ONE_SQL);
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
			close(pstm, con);
		}
		return dto;
	}

	@Override
	public int insert(MVCDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
				
		try {
			pstm = con.prepareStatement(INSERT_SQL);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("query : " + INSERT_SQL);
			res = pstm.executeUpdate();
			System.out.println("execute");
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm, con);
		}
		return res;
	}

	@Override
	public int update(MVCDto dto) {

		return 0;		
	}

	@Override
	public int delete(int seq) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean MDelte(String[] seqs) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		int[]cnt = null;
		
		try {
			pstm = con.prepareStatement(DELETE_SQL);
			for(int i=0; i<seqs.length; i++) {
				pstm.setString(1, seqs[i]);
				pstm.addBatch();
			}
			System.out.println("query : " + DELETE_SQL);
			cnt = pstm.executeBatch();
			System.out.println("execute");
			for(int i=0; i<cnt.length; i++) {
				if(cnt[i]==-2) {
					res++;
				}
			}
			if(res == seqs.length) {
				commit(con);
			} else {
				rollback(con);
				res = 0;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm, con);
		}
		
		return (res==seqs.length)?true:false;
	}

}
