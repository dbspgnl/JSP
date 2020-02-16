package com.mvc.biz;

import java.util.List;

import com.mvc.dao.MyDao;
import com.mvc.dao.MyDaoImpl;
import com.mvc.dto.MyDto;
import com.mvc.dto.PagingDto;

public class MyBizImpl implements MyBiz {

	MyDao dao = new MyDaoImpl();
	
	@Override
	public List<MyDto> selectList() {
		return dao.selectList();
	}

	@Override
	public MyDto selectOne(int seq) {
		return dao.selectOne(seq);
	}

	@Override
	public int insert(MyDto dto) {
		return dao.insert(dto);
	}

	@Override
	public int update(MyDto dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(int seq) {
		return dao.delete(seq);
	}

	public List<MyDto> selectAll(PagingDto dto){ //한페이지에 표시될 list 가져오기
		int currentpage = dto.getCurrentpage();
		int totalrows = dto.getTotalrows();
		
		int to = totalrows * (currentpage-1) +1;
		// 현재 페이지가 1이면 0+1 이므로 1부터
		// 현재 페이지가 2이면 10+1 이므로 11부터
		// 현재 페이지가 3이면 20+1 이므로 21부터
		int from = totalrows * currentpage;
		// 현재 페이지가 1이면 10*1 이므로 10까지
		// 현재 페이지가 2이면 10*2 이므로 20까지
		// 현재 페이지가 3이면 10*3 이므로 30까지
		
		return dao.selectAll(to, from);
	}	
	
	public int totalPage(int totalrows) {	//현재 가지고 있는 총 게시글의 수
		int totalpage = (int)Math.ceil((double)dao.totalPage()/totalrows); // 1
		return totalpage;
	}
	
}
