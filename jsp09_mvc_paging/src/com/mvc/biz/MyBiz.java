package com.mvc.biz;

import java.util.List;

import com.mvc.dto.MyDto;
import com.mvc.dto.PagingDto;

public interface MyBiz {

	public List<MyDto> selectList();
	public MyDto selectOne(int seq);
	public int insert(MyDto dto);
	public int update(MyDto dto);
	public int delete(int seq);
	
	public List<MyDto> selectAll(PagingDto dto);	//한페이지에 표시될 list 가져오기
	public int totalPage(int totalrows);	//현재 가지고 있는 총 게시글의 수
	
}
