package com.mvc.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import static com.mvc.dao.SqlMapConfig.*;
import com.mvc.dto.MyDto;

public class MyDaoImpl implements MyDao{

	private String namespace = "mvc.";
	
	@Override
	public List<MyDto> selectList() {
		List<MyDto>list = null;
		SqlSession session = null;
		
		try {
			session = getSqlsSessionFactory().openSession();
			list = session.selectList(namespace+"selectList");
		} catch (Exception e) {
			System.out.println("ERROR : selectList");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}

	@Override
	public MyDto selectOne(int seq) {
		MyDto dto = null;
		SqlSession session = null;
		
		try {
			session = getSqlsSessionFactory().openSession(false);
			dto = session.selectOne(namespace+"selectOne", seq);
		} catch (Exception e) {
			System.out.println("ERROR : selectOne");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dto;
	}

	@Override
	public int insert(MyDto dto) {
		int res = 0;
		SqlSession session = null;
		
		try {
			session = getSqlsSessionFactory().openSession(false);
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

	@Override
	public int update(MyDto dto) {
		int res = 0;
		SqlSession session = null;
		
		try {
			session = getSqlsSessionFactory().openSession(false);
			res = session.update(namespace+"update",dto);
			if(res>0) {
				session.commit();
			}
		} catch (Exception e) {
			System.out.println("ERROR : update");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return res;
	}

	@Override
	public int delete(int seq) {
		int res = 0;
		SqlSession session = null;
		
		try {
			session = getSqlsSessionFactory().openSession(false);
			res = session.delete(namespace+"delete",seq);
			if(res>0) {
				session.commit();
			}
		} catch (Exception e) {
			System.out.println("ERROR : delete");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}

}
