package com.cal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
		CalDao dao = new CalDao();
		
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
			
			CalDto dto = new CalDto(id, title, content, mDate); 
			int res = dao.insertCalBoard(dto);
			if(res>0) {
				jsResponse("일정 추가 성공", "calendar.do?command=calendar", response);
			} else {
				request.setAttribute("msg", "일정 등록 실패");
				dispatch("error.jsp", request, response);
			}
		} else if(command.equals("list")) {
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String date = request.getParameter("date");
			String yyyyMMdd = year + Util.isTwo(month) + Util.isTwo(date);
			
			List<CalDto> list = dao.selectCalList("kh", yyyyMMdd);
			request.setAttribute("list", list);
			dispatch("calendarlist.jsp", request, response);
			
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
	
	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		doGet(request, response);
	}

}
