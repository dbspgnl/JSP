package com.login.dao;

import static com.login.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.login.dto.MyMemberDto;

public class MyMemberDaoImpl implements MyMemberDao {

	@Override
	public List<MyMemberDto> selectList() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MyMemberDto> list = new ArrayList<MyMemberDto>();
		String sql = " SELECT * FROM MYMEMBER ORDER BY MYNO DESC ";
		
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				MyMemberDto dto = new MyMemberDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMypw(rs.getString(3));
				dto.setMyname(rs.getString(4));
				dto.setMyaddr(rs.getString(5));
				dto.setMyphone(rs.getString(6));
				dto.setMyemail(rs.getString(7));
				dto.setMyenabled(rs.getString(8));
				dto.setMyrole(rs.getString(9));
				
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
	public List<MyMemberDto> selectEnabled() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MyMemberDto> list = new ArrayList<MyMemberDto>();
		String sql = " SELECT * FROM MYMEMBER "
				+ "WHERE MYENABLED = 'Y' ORDER BY MYNO DESC ";
		
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				MyMemberDto dto = new MyMemberDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMypw(rs.getString(3));
				dto.setMyname(rs.getString(4));
				dto.setMyaddr(rs.getString(5));
				dto.setMyphone(rs.getString(6));
				dto.setMyemail(rs.getString(7));
				dto.setMyenabled(rs.getString(8));
				dto.setMyrole(rs.getString(9));
				
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
	public int updateRole(int myno, String role) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE MYMEMBER SET MYROLE=? WHERE MYNO=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, role);
			pstm.setInt(2, myno);
			res = pstm.executeUpdate();
			
			if(res>0) {
				commit(con);
			} else {
				rollback(con);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm, con);
		}
		return res;
	}

	@Override
	public MyMemberDto login(String myid, String mypw) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		//MyMemberDto dto = null;
		MyMemberDto dto = new MyMemberDto();
		
		String sql = " SELECT * FROM MYMEMBER WHERE "
				+ "MYID=? AND MYPW=? AND MYENABLED=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myid);
			pstm.setString(2, mypw);
			pstm.setString(3, "Y");
			System.out.println("쿼리준비");
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				dto = new MyMemberDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMypw(rs.getString(3));
				dto.setMyname(rs.getString(4));
				dto.setMyaddr(rs.getString(5));
				dto.setMyphone(rs.getString(6));
				dto.setMyemail(rs.getString(7));
				dto.setMyenabled(rs.getString(8));
				dto.setMyrole(rs.getString(9));
			}
		// select는 보기만 하는거라 commit 안함	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm, con);
		}
		return dto;
	}

	@Override
	public MyMemberDto idChk(String myid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertUser(MyMemberDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MyMemberDto selectUser(int myno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT * "
				+ "FROM MYMEMBER WHERE MYNO=? ";
		MyMemberDto dto = new MyMemberDto();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			rs = pstm.executeQuery();
			while(rs.next()) {
				dto = new MyMemberDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMypw(rs.getString(3));
				dto.setMyname(rs.getString(4));
				dto.setMyaddr(rs.getString(5));
				dto.setMyphone(rs.getString(6));
				dto.setMyemail(rs.getString(7));
				dto.setMyenabled(rs.getString(8));
				dto.setMyrole(rs.getString(9));
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
	public int updateUser(MyMemberDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUser(int myno) {
		// TODO Auto-generated method stub
		return 0;
	}

}
