package com.test.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dto.Score;

@WebServlet("/controller.do")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public test() {
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
		System.out.println("["+command+"]");
		
		if(command.equals("el")) {
			response.sendRedirect("basic-arithmetic.jsp");
		} else if(command.equals("eltest")) {
			Score score = new Score("홍길동", 100, 100, 98);
			//request에 score 객체를 "hong"라는 이름으로 담자.
			request.setAttribute("hong", score);
			
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("eltest.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("jstltest")) {
			List<Score> list = new ArrayList<Score>();
			
			for(int i=10; i<50; i+=10) {
				Score sc = new Score("이름"+i, 50+i, 50+i, 50+i);
				list.add(sc);
			}
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jstltest.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("usebean")) {
			response.sendRedirect("usebeantest.jsp");
		}
		
		
	}

}
