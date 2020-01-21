package com.ans.dao;

import java.util.List;

import com.ans.dto.AnsDto;

public interface AnsDao {

	public List<AnsDto> selectList();
	public AnsDto selectOne(int boardno);
	public int insert(AnsDto dto);
	public int update(AnsDto dto);
	public int delete(int boardno);
	public int ansUpdate(int boardno);
	public int ansInsert(AnsDto dto);
	
}
