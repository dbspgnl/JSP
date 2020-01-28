package com.cal.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cal.dao.CalDao;
import com.cal.dao.Util;
import com.cal.dto.CalDto;

@WebServlet("/calendar.do")
public class CalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CalController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String command = request.getParameter("command");
		System.out.println(command);
		
		if(command.equals("calendar")) {
			response.sendRedirect("calendar.jsp");
		} else if(command.equals("insertcal")) {
			String year = request.getParameter("year");
			String month = Util.isTwo(request.getParameter("month"));
			String date = Util.isTwo(request.getParameter("date"));
			String hour = Util.isTwo(request.getParameter("hour"));
			String min = Util.isTwo(request.getParameter("min"));
			String mDate = year + month + date + hour + min;
			
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			CalDao dao = new CalDao();
			CalDto dto = new CalDto(id, title, content, mDate); 
			int res = dao.insertCalBoard(dto);
			if(res>0) {
				jsResponse("일정 추가 성공", "index.html", response);
			} else {
				jsResponse("일정 추가 실패", "history.back()", response);
			}
		}
		
	}

	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String res = "<script type=\"text/javascript\">"
				+ " alert('"+msg+"'); "
				+ " location.href='"+url+"';"
				+ "</script>";
		PrintWriter out = response.getWriter();
		out.print(res);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		doGet(request, response);
	}

}
