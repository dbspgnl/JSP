package com.mvc.dao;

import java.util.List;

import com.mvc.dto.EmpDto;

public interface EmpDao {
	
	public List<EmpDto> selectList();
	public EmpDto selectOne(int empno);
	public int insert(EmpDto dto);
	public int update(EmpDto dto);
	public int delete(int empno);
	public int multiDelete(String[] seq);
	
}
