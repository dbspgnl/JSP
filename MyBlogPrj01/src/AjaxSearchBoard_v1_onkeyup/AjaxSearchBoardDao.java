package AjaxSearchBoard_v1_onkeyup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AjaxSearchBoardDao {
	private Connection con;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	public AjaxSearchBoardDao() {
		try {
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="kh";
			String password="kh";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("connection error");
			e.printStackTrace();
		}
	}
	
	public List<AjaxSearchBoardDto> search(String username){
		String sql = "SELECT * FROM ASB WHERE USERNAME LIKE ? ";
		List<AjaxSearchBoardDto> list = new ArrayList<AjaxSearchBoardDto>();
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, "%"+username+"%");
			rs = pstm.executeQuery();
			while(rs.next()) {
				AjaxSearchBoardDto dto = new AjaxSearchBoardDto();
				dto.setUsername(rs.getString(1));
				dto.setUserage(rs.getInt(2));
				dto.setUsergender(rs.getString(3));
				dto.setUsermail(rs.getString(4));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("ERROR AjaxSearchBoardDao search()");
			e.printStackTrace();
		}
		
		return list;
	}
}
