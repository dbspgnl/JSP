package com.mvc.dao;

import static com.mvc.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mvc.dto.EmpDto;

public class EmpDaoImpl implements EmpDao {

	@Override
	public List<EmpDto> selectList() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<EmpDto> list = new ArrayList<EmpDto>();
		String sql = " SELECT * FROM EMP ORDER BY EMPNO DESC ";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3.query");
			rs = pstm.executeQuery();
			System.out.println("4.execute");
			while(rs.next()) {
				EmpDto dto = 
					new EmpDto(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getDate(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getInt(8));
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
	public EmpDto selectOne(int empno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		EmpDto dto = null;
		String sql = " SELECT * FROM EMP WHERE EMPNO=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, empno);
			System.out.println("3.query");
			rs = pstm.executeQuery();
			System.out.println("4.execute");
			while(rs.next()) {
				dto = 
					new EmpDto(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getDate(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getInt(8));
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
	public int insert(EmpDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO EMP "
				+ "VALUES(?,?,?,?,SYSDATE,?,?,?) ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, dto.getEmpno());
			pstm.setString(2, dto.getEname());
			pstm.setString(3, dto.getJob());
			pstm.setInt(4, dto.getMgr());
			pstm.setInt(5, dto.getSal());
			pstm.setInt(6, dto.getComm());
			pstm.setInt(7, dto.getDeptno());
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
	public int update(EmpDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE EMP SET ENAME=?, JOB=? WHERE EMPNO=? ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getEname());
			pstm.setString(2, dto.getJob());
			pstm.setInt(3, dto.getEmpno());
			
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
	public int delete(int empno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM EMP WHERE EMPNO=?";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3. query");
			pstm.setInt(1, empno);
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
				pstm.addBatch();
				System.out.println("삭제할 번호: "+ seq[i]);
			}
			System.out.println("3 query set");
			cnt = pstm.executeBatch();
			System.out.println("execute");
			
			for(int i=0; i<cnt.length; i++) {
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
	
}
