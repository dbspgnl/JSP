package com.ef.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public int multiDelete(String[] seq) {
		int res = 0;
		SqlSession session = null;
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("seqs", seq);
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete(namespace+"muldel",map);
			if(res==seq.length) {
				session.commit();
			}
		} catch (Exception e) {
			System.out.println("ERROR : muldel");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
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
