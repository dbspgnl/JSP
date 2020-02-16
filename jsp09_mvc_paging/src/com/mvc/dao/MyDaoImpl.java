package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.mvc.db.JDBCTemplate.*;
import com.mvc.dto.MyDto;
import com.mvc.dto.PagingDto;


public class MyDaoImpl implements MyDao {
	
	public List<MyDto> selectAll(int to, int from){
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MyDto> list = new ArrayList<MyDto>();
		String sql = " SELECT B.* " + 
				" FROM (SELECT A.*, ROWNUM AS RNUM " + 
				" FROM (SELECT * FROM MVCBOARD ORDER BY SEQ DESC)A )B " + 
				" WHERE RNUM BETWEEN ? AND ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, to);
			pstm.setInt(2, from);
			System.out.println("selectAll : 3.query");
			rs = pstm.executeQuery();
			System.out.println("selectAll : 4.execute");
			while(rs.next()) {
				MyDto dto = new MyDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm, con);
		}
		return list;
	}
	
	public int totalPage() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		//String sql = " SELECT COUNT(*) AS CNT FROM MVCBOARD ";
		String sql = " SELECT * FROM MVCBOARD ";
		int cnt = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("totalPage : 3. query");
			rs = pstm.executeQuery();
			while(rs.next()) {
				cnt++;
			}
			//cnt = pstm.executeUpdate();
			System.out.println("totalpage: "+cnt);
			System.out.println("totalPage : 4. execute");
			if(cnt>0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("ERROR 3,4");
			e.printStackTrace();
		} finally {
			close(pstm, con);
		}
		return cnt;
	}
	
	@Override
	public List<MyDto> selectList() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MyDto> list = new ArrayList<MyDto>();
		
		try {
			pstm = con.prepareStatement(SELECT_LIST_SQL);
			System.out.println("3.query");
			rs = pstm.executeQuery();
			System.out.println("4.execute");
			while(rs.next()) {
				MyDto dto = new MyDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm, con);
		}
		return list;
	}

	@Override
	public MyDto selectOne(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MyDto dto = null;
		
		try {
			pstm = con.prepareStatement(SELECT_ONE_SQL);
			pstm.setInt(1, seq);
			System.out.println("3.query");
			rs = pstm.executeQuery();
			System.out.println("4.execute");
			while(rs.next()) {
				dto = new MyDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm, con);
		}
		return dto;
	}

	@Override
	public int insert(MyDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(INSERT_SQL);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("3. query");
			res = pstm.executeUpdate();
			System.out.println("4. execute");
			if(res>0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("ERROR 3,4");
			e.printStackTrace();
		} finally {
			close(pstm, con);
		}
		return res;
	}

	@Override
	public int update(MyDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(UPDATE_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			System.out.println("3. query");
			res = pstm.executeUpdate();
			System.out.println("4.excute");
			if(res>0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("ERROR 3,4");
			e.printStackTrace();
		} finally {
			close(pstm, con);
		}
		return res;
	}

	@Override
	public int delete(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(DELETE_SQL);
			System.out.println("3. query");
			pstm.setInt(1, seq);
			res = pstm.executeUpdate();
			System.out.println("4. execute");
			if(res>0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("ERROR 3,4");
			e.printStackTrace();
		} finally {
			close(pstm, con);
		}
		return res;
	}

}
