package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.EmpBiz;
import com.mvc.biz.EmpBizImpl;
import com.mvc.dto.EmpDto;

@WebServlet("/EmpServlet")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmpServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8;");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8;");
		
		String command = request.getParameter("command");
		System.out.println("<"+command+">");
		EmpBiz biz = new EmpBizImpl();
		
		if(command.equals("list")) {
			List<EmpDto> list = new ArrayList<EmpDto>();
			list = biz.selectList();
			request.setAttribute("list", list);
			dispatch("emplist.jsp", request, response);
		}
		else if(command.equals("insertform")) {
			response.sendRedirect("insertform.jsp");
		}
//		else if(command.equals("writeres")) {
//			String writer  = request.getParameter("writer");
//			String title = request.getParameter("title");
//			String content = request.getParameter("content");
//			EmpDto dto = new EmpDto();
//			dto.setWriter(writer);
//			dto.setTitle(title);
//			dto.setContent(content);
//			int res = biz.insert(dto);
//			if(res>0) {
//				jsResponse("글쓰기 성공", "answer.do?command=list", response);
//			} else {
//				jsResponse("글쓰기 실패", "answer.do?command=writeform", response);
//			}
//		}
//		else if(command.equals("delete")) {
//			int boardno = Integer.parseInt(request.getParameter("boardno"));
//			int res = biz.delete(boardno);
//			if(res>0) {
//				jsResponse("글 삭제 성공", "answer.do?command=list", response);
//			} else {
//				jsResponse("글 삭제 실패", "answer.do?command=detail?boardno="+boardno, response);
//			}
//		}
//		else if(command.equals("updateform")) {
//			int boardno = Integer.parseInt(request.getParameter("boardno"));
//			EmpDto dto = biz.selectOne(boardno);
//			request.setAttribute("dto", dto);
//			dispatch("updateform.jsp", request, response);
//		}
//		else if(command.equals("updateres")) {
//			int boardno = Integer.parseInt(request.getParameter("boardno"));
//			String writer  = request.getParameter("writer");
//			String title = request.getParameter("title");
//			String content = request.getParameter("content"); 
//			EmpDto dto = new EmpDto();
//			dto.setWriter(writer);
//			dto.setTitle(title);
//			dto.setContent(content);
//			dto.setBoardno(boardno);
//			int res = biz.update(dto);
//			if(res>0) {
//				jsResponse("글 수정 성공", "answer.do?command=list", response);
//			} else {
//				jsResponse("글 수정 실패", "answer.do?command=detail&boardno="+boardno, response);
//			}
//		}
//		else if(command.equals("answer")) {
//			int boardno = Integer.parseInt(request.getParameter("boardno"));
//			EmpDto dto = biz.selectOne(boardno);
//			request.setAttribute("dto", dto);
//			dispatch("answerform.jsp", request, response);
//		}
		
	}

	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"')");
		out.println("location.href='"+url+"'");
		out.println("</script>");
	}

}
