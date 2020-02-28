package com.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/ajax.do")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8;");
		
		String command = request.getParameter("command");
		System.out.println("<"+command+">");
		
		if(command.equals("score")) {
			response.sendRedirect("score.jsp");
		}
		else if(command.equals("cal_js")) {
			String name = request.getParameter("name");
			int kor = Integer.parseInt(request.getParameter("kor"));
			int eng = Integer.parseInt(request.getParameter("eng"));
			int math = Integer.parseInt(request.getParameter("math"));
			
			int sum = kor + eng + math;
			double avg = sum/3.0;
			
			JSONObject obj = new JSONObject();
			obj.put("name", name);
			obj.put("sum", sum);
			obj.put("avg", avg);
			String res = obj.toJSONString();
			System.out.println("js json : "+res);
			PrintWriter out = response.getWriter();
			out.println(res);
			
		}
		
	}

}
