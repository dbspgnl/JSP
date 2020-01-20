package com.answer.dao;

import java.util.List;

import com.answer.dto.AnswerDto;

public interface AnswerDao {
	
	public List<AnswerDto> selectList();
	public AnswerDto selectOne(int boardno);
	public int insert(AnswerDto dto);
	public int update(AnswerDto dto);
	public int delete(int boardno);
	
}
