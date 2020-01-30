package com.md.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.md.dto.Dto;

public class Dao extends SqlMapConfig{
	
	private String namespace="muldel.";
	
	public List<Dto> selectList(){
		List<Dto> list = null;
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession();
			list = session.selectList(namespace+"selectList");
			
		} catch (Exception e) {
			System.out.println("ERROR : selectList");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	
	public Dto selectOne(int seq) {
		Dto dto = null;
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession();
			dto = session.selectOne(namespace+"selectOne", seq);
		} catch (Exception e) {
			System.out.println("ERROR : selectOne");
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
	
	public int insert(Dto dto) {
		int res = 0;
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert(namespace+"insert", dto);
			if(res>0) {
				session.commit();
			}
		} catch (Exception e) {
			System.out.println("ERROR : insert");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}
	
	public int update(Dto dto) {
		int res = 0;
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession(true);
			res = session.update(namespace+"update", dto);
		} catch (Exception e) {
			System.out.println("ERROR : update");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}
}
