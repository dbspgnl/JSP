package com.ef.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import static com.ef.dao.SqlMapConfig.*;

import com.ef.dto.UserDto;

public class UserDao {

	private String namespace = "ef.";
	
	public List<UserDto> selectList() {

		return null;
	}

	public List<UserDto> selectEnabled() {

		return null;
	}

	public int updateRole(int myno, String role) {

		return 0;
	}

	public UserDto login(String id, String pw) {
		UserDto dto = null;
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne(namespace+"login", id);
		} catch (Exception e) {
			System.out.println("ERROR : login");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dto;
	}

	public UserDto idChk(String myid) {

		return null;
	}

	public int insertUser(UserDto dto) {

		return 0;
	}

	public UserDto selectUser(int myno) {

		return null;
	}

	public int updateUser(UserDto dto) {

		return 0;
	}

	public int deleteUser(int myno) {

		return 0;
	}

}
