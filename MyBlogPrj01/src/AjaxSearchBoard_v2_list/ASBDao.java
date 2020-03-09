package AjaxSearchBoard_v2_list;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ASBDao {
	private Connection con;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	public ASBDao() {
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
	
	public List<ASBDto> search(String username){
		String sql = "SELECT * FROM ASB WHERE USERNAME LIKE ? ";
		List<ASBDto> list = new ArrayList<ASBDto>();
		System.out.println("dao 진입");
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, "%"+username+"%");
			rs = pstm.executeQuery();
			while(rs.next()) {
				ASBDto dto = new ASBDto();
				dto.setUsername(rs.getString(1));
				dto.setUserage(rs.getInt(2));
				dto.setUsergender(rs.getString(3));
				dto.setUsermail(rs.getString(4));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("ERROR ASBDao search()");
			e.printStackTrace();
		}
		
		return list;
	}
	
	
}
