package com.mvc.biz;

import java.util.List;

import com.mvc.dao.EmpDao;
import com.mvc.dao.EmpDaoImpl;
import com.mvc.dto.EmpDto;

public class EmpBizImpl implements EmpBiz {

	EmpDao dao = new EmpDaoImpl();
	
	@Override
	public List<EmpDto> selectList() {
		return dao.selectList();
	}

	@Override
	public EmpDto selectOne(int empno) {
		return dao.selectOne(empno);
	}

	@Override
	public int insert(EmpDto dto) {
		return dao.insert(dto);
	}

	@Override
	public int update(EmpDto dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(int empno) {
		return dao.delete(empno);
	}
	
	@Override
	public int multiDelete(String[] seq) {
		return dao.multiDelete(seq);
	}

}
