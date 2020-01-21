package com.answer.controller;

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

import com.answer.biz.AnswerBiz;
import com.answer.biz.AnswerBizImpl;
import com.answer.dto.AnswerDto;

@WebServlet("/answer.do")
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
    public AnswerServlet() {
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
		AnswerBiz biz = new AnswerBizImpl();
		
		if(command.equals("list")) {
			List<AnswerDto> list = new ArrayList<AnswerDto>();
			list = biz.selectList();
			request.setAttribute("list", list);
			dispatch("answerlist.jsp", request, response);
		}
		else if(command.equals("detail")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			AnswerDto dto = biz.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch("answerdetail.jsp", request, response);
		}
		else if(command.equals("writeform")) {
			response.sendRedirect("writeform.jsp");
		}
		else if(command.equals("writeres")) {
			String writer  = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			AnswerDto dto = new AnswerDto();
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			int res = biz.insert(dto);
			if(res>0) {
				jsResponse("글쓰기 성공", "answer.do?command=list", response);
			} else {
				jsResponse("글쓰기 실패", "answer.do?command=writeform", response);
			}
		}
		else if(command.equals("delete")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			int res = biz.delete(boardno);
			if(res>0) {
				jsResponse("글 삭제 성공", "answer.do?command=list", response);
			} else {
				jsResponse("글 삭제 실패", "answer.do?command=detail?boardno="+boardno, response);
			}
		}
		else if(command.equals("updateform")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			AnswerDto dto = biz.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch("updateform.jsp", request, response);
		}
		else if(command.equals("updateres")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			String writer  = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content"); 
			AnswerDto dto = new AnswerDto();
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setBoardno(boardno);
			int res = biz.update(dto);
			if(res>0) {
				jsResponse("글 수정 성공", "answer.do?command=list", response);
			} else {
				jsResponse("글 수정 실패", "answer.do?command=detail&boardno="+boardno, response);
			}
		}
		else if(command.equals("answer")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			AnswerDto dto = biz.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch("answerform.jsp", request, response);
		}
		else if(command.equals("answerres")){
			int parentboardno = Integer.parseInt(request.getParameter("parentboardno"));
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			AnswerDto dto = new AnswerDto();
			dto.setBoardno(parentboardno);
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			
			int res = biz.answerProc(dto);
			if(res>0) {
				jsResponse("답변 작성 성공", "answer.do?command=list", response);
			} else {
				jsResponse("답변 작성 실패", "answer.do?command=answer&boardno="+parentboardno, response);
			}
		}
		
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
