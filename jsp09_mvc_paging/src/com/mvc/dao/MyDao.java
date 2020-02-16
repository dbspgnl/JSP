package com.mvc.dao;

import java.util.List;

import com.mvc.dto.MyDto;
import com.mvc.dto.PagingDto;

public interface MyDao {

	String SELECT_LIST_SQL = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE "
			+ " FROM MVCBOARD ORDER BY SEQ DESC ";
	String SELECT_ONE_SQL = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE "
			+ " FROM MVCBOARD WHERE SEQ =? ";
	String INSERT_SQL = " INSERT INTO MVCBOARD VALUES(MVCSEQ.NEXTVAL, ?,?,?, SYSDATE ) ";
	String UPDATE_SQL = " UPDATE MVCBOARD SET TITLE=?, CONTENT=? WHERE SEQ=? ";
	String DELETE_SQL = " DELETE FROM MVCBOARD WHERE SEQ=? ";
	
	public List<MyDto> selectList();
	public List<MyDto> selectAll(int to, int from);	// 페이징으로 리스트 불러오기
	public int totalPage();							// 전체 페이지 구하기 = 마지막 페이지
	
	public MyDto selectOne(int seq);
	public int insert(MyDto dto);
	public int update(MyDto dto);
	public int delete(int seq);
	
	
}
