package com.ans.controller;

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

import com.ans.biz.AnsBiz;
import com.ans.biz.AnsBizImpl;
import com.ans.dto.AnsDto;

@WebServlet("/AnsServlet")
public class AnsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AnsServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8;");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8;");
		
		String command = request.getParameter("command");
		System.out.println("<"+command+">");
		
		AnsBiz biz = new AnsBizImpl();
		
		if(command.equals("list")) {
			List<AnsDto> list = new ArrayList<AnsDto>();
			list = biz.selectList();
			request.setAttribute("list", list);
			dispatch("anslist.jsp", request, response);
		}
		else if(command.equals("writeform")) {
			response.sendRedirect("writeform.jsp");
		}
		else if(command.equals("writeres")) {
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			AnsDto dto = new AnsDto();
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			int res = biz.insert(dto);
			if(res>0) {
				jsResponse("글쓰기 성공", "ans.do?command=list", response);
			} else {
				jsResponse("글쓰기 실패", "ans.do?command=writeform", response);
			}
		}
		else if(command.equals("detail")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			AnsDto dto = biz.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch("detail.jsp", request, response);
		}
		else if(command.equals("delete")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			int res = biz.delete(boardno);
			if(res>0) {
				jsResponse("글 삭제 성공", "ans.do?command=list", response);
			} else {
				jsResponse("글 삭제 실패", "ans.do?command=detail?boardno="+boardno, response);
			}
		}
		else if(command.equals("updateform")) {
			//To do updatform
			//To do updateres
			//To do answer
			//To do answerres
		}
		
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
