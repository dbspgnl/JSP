package com.ans.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.ans.db.JDBCTemplate.*;
import com.ans.dto.AnsDto;

public class AnsDaoImpl implements AnsDao {

	@Override
	public List<AnsDto> selectList() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<AnsDto> list = new ArrayList<AnsDto>();
		String sql = "SELECT * FROM ANSWERBOARD "
				+ " ORDER BY GROUPNO DESC, GROUPSEQ ";
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3. query" + sql);
			rs = pstm.executeQuery();
			System.out.println("4. execute"+rs);
			while(rs.next()) {
				AnsDto dto 
					= new AnsDto(
						rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getDate(8),
						rs.getString(9));
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("4. execute");
			e.printStackTrace();
		} finally {
			close(pstm, con);
		}
		return list;
	}

	@Override
	public AnsDto selectOne(int boardno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		AnsDto dto = null;
		String sql = " SELECT * FROM ANSWERBOARD "
				+ " WHERE BOARDNO=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3. query");
			pstm.setInt(1, boardno);
			rs = pstm.executeQuery();
			System.out.println("4. execute");
			while(rs.next()) {
				dto = 
					new AnsDto(
						rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getDate(8),
						rs.getString(9));
			}
			
		} catch (SQLException e) {
			System.out.println("ERROR 3,4");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm, con);
		}
		
		return dto;
	}

	@Override
	public int insert(AnsDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO ANSWERBOARD "
				+ " VALUES(BOARDNOSEQ.NEXTVAL, GROUPNOSEQ.NEXTVAL, 1, 0, "
				+ " ?,?,?,SYSDATE,'N' ) ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setString(3, dto.getWriter());
			System.out.println("3.query" + sql);
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
	public int update(AnsDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE ANSWERBOARD "
				+ " SET WRITER=?, TITLE=?, CONTENT=? "
				+ " WHERE BOARDNO=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3. query");
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			pstm.setInt(4, dto.getBoardno());
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
	public int delete(int boardno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE ANSWERBOARD "
				+ " SET DELCHECK='Y' WHERE BOARDNO=?";
				
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3. query");
			pstm.setInt(1, boardno);
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
	public int ansUpdate(int boardno) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int ansInsert(AnsDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

}
