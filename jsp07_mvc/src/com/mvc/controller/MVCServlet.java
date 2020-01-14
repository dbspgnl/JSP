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

import com.mvc.biz.MVCBiz;
import com.mvc.biz.MVCBizImpl;
import com.mvc.dto.MVCDto;

@WebServlet("/MVCServlet")
public class MVCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MVCServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.printf("<%s>\n", command);
		
		MVCBiz biz = new MVCBizImpl();
		
		if(command.equals("list")) {
			List<MVCDto> list = biz.selectList();
			request.setAttribute("list", list);
			dispatch("mvclist.jsp", request, response);
		} 
		else if(command.equals("insertform")) {
			response.sendRedirect("insertform.jsp");
		}
		else if(command.equals("insertres")) {
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			MVCDto dto = new MVCDto();
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			int res = biz.insert(dto);
			if(res>0) {
				//dispatch("mvc.do?command=list", request, response);
				//response.sendRedirect("mvc.do?command=list");
				jsResponse("글 등록 성공!", "mvc.do?command=list", response);
			} else {
				//dispatch("mvc.do?command=insertform", request, response);
				jsResponse("글 등록 실패", "mvc.do?command=insertform", response);
			}
		}
		else if(command.equals("detail")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			MVCDto dto = biz.selectOne(seq);
			request.setAttribute("dto", dto);
			dispatch("detail.jsp", request, response);
		}
		else if(command.equals("updateform")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			MVCDto dto = biz.selectOne(seq);
			request.setAttribute("dto", dto);
			dispatch("updateform.jsp", request, response);
		}
		else if(command.equals("updateres")) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int seq = Integer.parseInt(request.getParameter("seq"));
			MVCDto dto = new MVCDto();
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
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String res = "<script type=\"text/javascript\">"
				+ " alert('"+msg+"'); "
				+ " location.href='"+url+"';"
				+ "</script>";
		PrintWriter out = response.getWriter();
		out.println(res);
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		doGet(request, response);
	}

}
