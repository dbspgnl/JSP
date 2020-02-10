package com.chat.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chat.dto.Chat;

public class ChatDao {

	private Connection con;
	
	public ChatDao() {
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "kh";
			String password ="kh";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("ERROR Account con");
			e.printStackTrace();
		}
	}
	
	public ArrayList<Chat> getChatList(String nowTime){
		ArrayList<Chat> chatList = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM CHAT WHERE CHATTIME > ? ORDER BY CHATTIME ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, nowTime);
			rs = pstm.executeQuery();
			chatList = new ArrayList<Chat>();
			while(rs.next()) {
				Chat chat = new Chat();
				chat.setChatName(rs.getString("chatName"));
				chat.setChatContent(rs.getString("chatContent")
						.replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
				String timeType = "오전";
				if(Integer.parseInt(rs.getString("chatTime").substring(11,13)) >= 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0,11)
						+" "+timeType+" "+chatTime+":"+rs.getString("chatTime")
						.substring(14,16)+"");
				chatList.add(chat);
			}
		} catch (SQLException e) {
			System.out.println("ERROR sql exe");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
			} catch (SQLException e) {
				System.out.println("ERROR close");
				e.printStackTrace();
			}
		}
		return chatList;
	}
	
	public ArrayList<Chat> getChatListByRecent(int number){
		ArrayList<Chat> chatList = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM CHAT WHERE CHATID > (SELECT MAX(CHATID)-? FROM CHAT)"
				+ " ORDER BY CHATTIME ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, number);
			rs = pstm.executeQuery();
			chatList = new ArrayList<Chat>();
			while(rs.next()) {
				Chat chat = new Chat();
				chat.setChatID(rs.getInt("chatID"));
				chat.setChatName(rs.getString("chatName"));
				chat.setChatContent(rs.getString("chatContent")
						.replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
				String timeType = "오전";
				if(Integer.parseInt(rs.getString("chatTime").substring(11,13)) >= 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0,11)
						+" "+timeType+" "+chatTime+":"+rs.getString("chatTime")
						.substring(14,16)+"");
				chatList.add(chat);
			}
		} catch (SQLException e) {
			System.out.println("ERROR sql exe");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
			} catch (SQLException e) {
				System.out.println("ERROR close");
				e.printStackTrace();
			}
		}
		return chatList;
	}
	
	public ArrayList<Chat> getChatListByRecent(String chatID){
		ArrayList<Chat> chatList = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM CHAT WHERE CHATID > ? ORDER BY CHATTIME ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, Integer.parseInt(chatID));
			rs = pstm.executeQuery();
			chatList = new ArrayList<Chat>();
			while(rs.next()) {
				Chat chat = new Chat();
				chat.setChatID(rs.getInt("chatID"));
				chat.setChatName(rs.getString("chatName"));
				chat.setChatContent(rs.getString("chatContent")
						.replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
				String timeType = "오전";
				if(Integer.parseInt(rs.getString("chatTime").substring(11,13)) >= 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0,11)
						+" "+timeType+" "+chatTime+":"+rs.getString("chatTime")
						.substring(14,16)+"");
				chatList.add(chat);
			}
		} catch (SQLException e) {
			System.out.println("ERROR sql exe");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
			} catch (SQLException e) {
				System.out.println("ERROR close");
				e.printStackTrace();
			}
		}
		return chatList;
	}
	
	public int submit(String chatName, String chatContent) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " INSERT INTO CHAT VALUES(CHATSEQ.NEXTVAL,?,?, SYSDATE) ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, chatName);
			pstm.setString(2, chatContent);
			return pstm.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("ERROR sql exe");
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
			} catch (SQLException e) {
				System.out.println("ERROR close");
				e.printStackTrace();
			}
		}
		return -1;
	}
	
}
