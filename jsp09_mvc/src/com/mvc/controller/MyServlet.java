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

@WebServlet("/con.do")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("html/text; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("<"+command+">");
		MyBiz biz = new MyBizImpl();
		
		if(command.equals("list")) {
			List<MyDto> list = biz.selectList();
			request.setAttribute("list", list);
			dispatch("mylist.jsp", request, response);
		} else if (command.equals("detail")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			MyDto dto = biz.selectOne(seq);
			request.setAttribute("dto", dto);
			dispatch("mydetail.jsp", request, response);
		} else if (command.equals("insert")) {
			response.sendRedirect("insertform.jsp");
		}
		
		response.getWriter().append("<h1><a href='con.do?command=list'>잘못왔다.</a></h1>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("html/text; charset=UTF-8");
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
