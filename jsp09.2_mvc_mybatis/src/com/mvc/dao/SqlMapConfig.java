package com.mvc.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {

	private SqlSessionFactory sessionFactory;
		
	public SqlSessionFactory getsqlsSessionFactory() {
		String resource = "com/mvc/db/config.xml";
		Reader reader = null;

		try {
			reader = Resources.getResourceAsReader(resource);
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
		} catch (IOException e) {
			System.out.println("ERROR: getResourceAsReader");
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				System.out.println("ERROR: reader close");
				e.printStackTrace();
			}
		}
		
		return sessionFactory;
	}
	
}
