package com.bike.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import static com.bike.db.JDBCTemplate.*;

import com.bike.dto.BikeDto;

public class BikeDao {

	public int insert(List<BikeDto> list) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO BIKE_TB "
				+ " VALUES(?,?,?,?,?,?,?) ";
		try {
			pstm = con.prepareStatement(sql);
			for(int i=0; i<list.size(); i++) {
				BikeDto dto = list.get(i);
				// list[0], list[1]... Dto 객체를 만듬
				pstm.setString(1, dto.getAddr_gu());
				pstm.setInt(2, dto.getContent_id());
				pstm.setString(3, dto.getContent_num());
				pstm.setString(4, dto.getNew_addr());
				pstm.setInt(5, dto.getCradle_count());
				pstm.setDouble(6, dto.getLongitude());
				pstm.setDouble(7, dto.getLatitude());
				
				pstm.addBatch(); // 객체를 담아둠
			}
			System.out.println("3.query" + sql);
			
			int[]result = pstm.executeBatch();
			// 담아둔 객체를 실행 후 결과를 새로운 배열에 담음
			System.out.println("4.execute");
			
			for(int i=0; i<result.length; i++) {
				if(result[i]==-2) {
					res++;
				}
			}
			if(res == list.size()) { // 같으면 모두 성공
				commit(con); 
			} else {
				rollback(con);
			} //auto commit 아니라 알아서 된다.
			
		} catch (SQLException e) {
			System.out.println("ERROR 3,4");
			e.printStackTrace();
		} finally {
			close(pstm, con);
		}
		return res;
	}

	public int delete() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM BIKE_TB ";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3. queury " + sql);
			res = pstm.executeUpdate();
			System.out.println("4. execute");
			if(res>0) {
				commit(con);
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
