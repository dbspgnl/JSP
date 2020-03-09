package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.JDBCTemplate;

public class UserDAO {

	public int login(String userID, String userPassword) {
		String sql = " SELECT userPassword FROM MBP_USER WHERE userID = ? ";
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = JDBCTemplate.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, userID);
			rs = pstm.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; // 로그인 성공
				}
				else {
					return 0; // 비번틀림
				}
			}
			return -1; // 아이디 없음
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstm);
			JDBCTemplate.close(con);
		}
		return -2; //데이터베이스 오류
	}
	public int join(UserDTO user) {
		String sql = " INSERT INTO MBP_USER VALUES(?,?,?,?,0) ";
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = JDBCTemplate.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user.getUserID());
			pstm.setString(2, user.getUserPassword());
			pstm.setString(3, user.getUserEmail());
			pstm.setString(4, user.getUserEmailHash());
			return pstm.executeUpdate(); //1
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstm);
			JDBCTemplate.close(con);
		}
		return -1; //오류
	}
	public String getUserEmail(String userID) {
		String sql = " SELECT userEmail FROM WHERE userID = ? ";
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = JDBCTemplate.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, userID);
			rs = pstm.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstm);
			JDBCTemplate.close(con);
		}
		return null; //오류
	}
	public int getUserEmailChecked(String userID) {
		String sql = " SELECT userEmailChecked FROM WHERE userID = ? ";
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = JDBCTemplate.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, userID);
			rs = pstm.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstm);
			JDBCTemplate.close(con);
		}
		return 0; //오류
	}
	public int setUserEmailChecked(String userID) {
		String sql = " UPDATE MBP_USER SET userEmailChecked= 1 WHERE userID =? ";
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = JDBCTemplate.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, userID);
			pstm.executeUpdate();
			return 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstm);
			JDBCTemplate.close(con);
		}
		return 0; //오류
	}
}
