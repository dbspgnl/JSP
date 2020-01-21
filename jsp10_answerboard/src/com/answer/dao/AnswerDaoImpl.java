package com.answer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.ans.db.JDBCTemplate.*; 
import com.answer.dto.AnswerDto;

public class AnswerDaoImpl implements AnswerDao {

	String SELECT_LIST_SQL = " SELECT BOARDNO, GROUPNO, GROUPSEQ, TITLETAB, "
			+ " TITLE, CONTENT, WRITER, REGDATE "
			+ " FROM ANSWERBOARD ORDER BY GROUPNO DESC, GROUPSEQ ";
	String SELECT_ONE_SQL = " SELECT BOARDNO, GROUPNO, GROUPSEQ, TITLETAB, "
			+ " TITLE, CONTENT, WRITER, REGDATE "
			+ " FROM ANSWERBOARD WHERE BOARDNO=? ";
	String INSERT_SQL = " INSERT INTO ANSWERBOARD "
			+ " VALUES(BOARDNOSEQ.NEXTVAL, GROUPNOSEQ.NEXTVAL, 1, 0, "
			+ " ?,?,?,SYSDATE ) ";
	String UPDATE_SQL = " UPDATE ANSWERBOARD SET WRITER=?, TITLE=?, CONTENT=? WHERE BOARDNO=? ";
	String DELETE_SQL = " DELETE FROM ANSWERBOARD WHERE BOARDNO=? ";
	
	@Override
	public List<AnswerDto> selectList() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<AnswerDto> list = new ArrayList<AnswerDto>();
		
		try {
			pstm = con.prepareStatement(SELECT_LIST_SQL);
			System.out.println("3. query");
			rs = pstm.executeQuery();
			System.out.println("4. execute");
			while(rs.next()) {
				AnswerDto dto = 
					new AnswerDto(
						rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getDate(8));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("ERROR 3,4");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm, con);
		}
		return list;
	}

	@Override
	public AnswerDto selectOne(int boardno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		AnswerDto dto = null;
		
		try {
			pstm = con.prepareStatement(SELECT_ONE_SQL);
			System.out.println("3. query");
			pstm.setInt(1, boardno);
			rs = pstm.executeQuery();
			System.out.println("4. execute");
			while(rs.next()) {
				dto = 
					new AnswerDto(
						rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getDate(8));
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
	public int insert(AnswerDto dto) {
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
	public int update(AnswerDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(UPDATE_SQL);
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
				
		try {
			pstm = con.prepareStatement(DELETE_SQL);
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
	
	public int answerUpdate(int parentboardno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String ANSWER_UPDATE_SQL = "UPDATE ANSWERBOARD " + 
				" SET GROUPSEQ = GROUPSEQ+1 " + 
				" WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO=?) " +  
				" AND GROUPSEQ > (SELECT GROUPSEQ FROM ANSWERBOARD WHERE BOARDNO=?) " ;
		try {
			pstm = con.prepareStatement(ANSWER_UPDATE_SQL);
			pstm.setInt(1, parentboardno);
			pstm.setInt(2, parentboardno);
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
	
	public int answerInsert(AnswerDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String ANSWER_INSERT_SQL = " INSERT INTO ANSWERBOARD " + 
				" VALUES( " + 
				"	BOARDNOSEQ.NEXTVAL, " + 
				"	(SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO=?), " + 
				"	(SELECT GROUPSEQ FROM ANSWERBOARD WHERE BOARDNO=?)+1, " + 
				"	(SELECT TITLETAB FROM ANSWERBOARD WHERE BOARDNO=?)+1, " + 
				"	?, ?, ?, SYSDATE ) ";  
		
		try {
			pstm = con.prepareStatement(ANSWER_INSERT_SQL);
			pstm.setInt(1, dto.getBoardno());
			pstm.setInt(2, dto.getBoardno());
			pstm.setInt(3, dto.getBoardno());
			pstm.setString(4, dto.getTitle());
			pstm.setString(5, dto.getContent());
			pstm.setString(6, dto.getWriter());
			
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
		}
		
		return res;
	}

}
