package com.ans.biz;

import java.util.List;

import com.ans.dto.AnsDto;

public interface AnsBiz {

	public List<AnsDto> selectList();
	public AnsDto selectOne(int boardno);
	public int insert(AnsDto dto);
	public int update(AnsDto dto);
	public int delete(int boardno);
	public int ansProc(AnsDto dto);
	
}
