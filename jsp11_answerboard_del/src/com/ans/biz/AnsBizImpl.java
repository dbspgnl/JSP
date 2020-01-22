package com.ans.biz;

import java.util.List;

import com.ans.dao.AnsDao;
import com.ans.dao.AnsDaoImpl;
import com.ans.dto.AnsDto;

public class AnsBizImpl implements AnsBiz {

	AnsDao dao = new AnsDaoImpl();
	
	@Override
	public List<AnsDto> selectList() {
		return dao.selectList();
	}

	@Override
	public AnsDto selectOne(int boardno) {
		return dao.selectOne(boardno);
	}

	@Override
	public int insert(AnsDto dto) {
		return dao.insert(dto);
	}

	@Override
	public int update(AnsDto dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(int boardno) {
		return dao.delete(boardno);
	}

	@Override
	public int ansProc(AnsDto dto) {
		int updateRes = dao.ansUpdate(dto.getBoardno());
		int insertRes = dao.ansInsert(dto);
		return updateRes+insertRes;
	}

}
