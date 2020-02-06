package com.ef.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ef.dao.BoardDao;
import com.ef.dao.UserDao;
import com.ef.dto.UserDto;

@WebServlet("/ef.do")
public class EfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EfServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("<"+command+">");
		UserDao userDao = new UserDao();
		BoardDao boaDao = new BoardDao();
		
		if(command.equals("main")) {
			response.sendRedirect("main.jsp");
		} else if(command.equals("login")) {
			response.sendRedirect("login.jsp");
		} else if(command.equals("loginres")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			UserDto userDto = userDao.login(id, pw);
			if(userDto.getId() != null) {
				HttpSession session = request.getSession();
				session.setAttribute("userID", userDto.getId());
				session.setMaxInactiveInterval(10*60); // 해당 초만큼 활동이 없으면 만료
				dispatch("main.jsp", request, response);
			} else {
				jsResponse("아이디와 비밀번호를 확인하세요.", "ef.do?command=main", response);
			}
		} 
	
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String res = "<script type='text/javascript'>"
				+ "alert('"+msg+"');"
				+ "location.href='"+url+"';"
				+ "</script>";
		PrintWriter out = response.getWriter();
		out.print(res);
	}
	
	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
}
