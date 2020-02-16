package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.MyBiz;
import com.mvc.biz.MyBizImpl;
import com.mvc.dto.MyDto;
import com.mvc.dto.PagingDto;

@WebServlet("/con.do")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("<"+command+">");
		MyBiz biz = new MyBizImpl();
		
		if(command.equals("list")) {
			int currentpage = Integer.parseInt(request.getParameter("page"));
			System.out.println("초기 페이지 번호 : "+currentpage+" //1");
			PagingDto paging = new PagingDto();
			paging.setCurrentpage(currentpage);
			paging.setTotalrows(10);	// 한 페이지 당 10글
			paging.setPagescale(5);		// 블록크기 5
			paging.setTotalpage(biz.totalPage(paging.getTotalrows()));
			request.setAttribute("paging", paging);
			
			List<MyDto> list = biz.selectAll(paging);
			request.setAttribute("list", list);
			dispatch("mylist.jsp", request, response);
		} 
		else if (command.equals("detail")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			MyDto dto = biz.selectOne(seq);
			request.setAttribute("dto", dto);
			dispatch("mydetail.jsp", request, response);
		} 
		else if (command.equals("insert")) {
			response.sendRedirect("insertform.jsp");
		} 
		else if (command.equals("insertres")) {
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			MyDto dto = new MyDto();
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			int res = biz.insert(dto);
			if(res>0) {
				jsResponse("글 등록 성공", "con.do?command=list", response);
			} else {
				jsResponse("글 등록 실패", "con.do?command=insertform", response);
			}
		} 
		else if(command.equals("updateform")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			MyDto dto = biz.selectOne(seq);
			request.setAttribute("dto", dto);
			dispatch("updateform.jsp", request, response);
		}
		else if(command.equals("updateres")) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int seq = Integer.parseInt(request.getParameter("seq"));
			MyDto dto = new MyDto();
			dto.setTitle(title);
			dto.setContent(content);
			dto.setSeq(seq);
			int res = biz.update(dto);
			if(res>0) {
				jsResponse("글 수정 성공", "mvc.do?command=list", response);
			} else {
				jsResponse("글 수정 실패", "mvc.do?command=detail&seq="+seq, response);
			}
		}
		else if(command.equals("delete")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			int res = biz.delete(seq);
			if(res>0) {
				jsResponse("삭제 성공", "mvc.do?command=list", response);
			} else {
				jsResponse("삭제 실패", "mvc.do?command=detail&seq="+seq, response);
			}
		}
		
		response.getWriter().append("<h1><a href='con.do?command=list'>잘못왔다.</a></h1>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		doGet(request, response);
	}

	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"')");
		out.println("location.href='"+url+"'");
		out.println("</script>");
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
}
