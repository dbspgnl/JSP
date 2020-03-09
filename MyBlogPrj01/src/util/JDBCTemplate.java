package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	public static Connection getConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1.diver con");
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR 1");
			e.printStackTrace();
		}
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
			System.out.println("2. account con");
		} catch (SQLException e) {
			System.out.println("ERROR 2");
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void close(Connection con) {
		try {
			con.close();
			System.out.println("5.close");
		} catch (SQLException e) {
			System.out.println("ERROR 5");
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			stmt.close();
			System.out.println("5.close");
		} catch (SQLException e) {
			System.out.println("ERROR 5");
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			rs.close();
			System.out.println("5.close");
		} catch (SQLException e) {
			System.out.println("ERROR 5");
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt, Connection con) {
		try {
			stmt.close();
			con.close();
			System.out.println("5.close");
		} catch (SQLException e) {
			System.out.println("ERROR 5");
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection con) {
		try {
			con.commit();
			System.out.println("4.commit");
		} catch (SQLException e) {
			System.out.println("ERROR 5");
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con) {
		try {
			con.rollback();
			System.out.println("5.rollback");
		} catch (SQLException e) {
			System.out.println("ERROR 5");
			e.printStackTrace();
		}
	}
	
	
}
