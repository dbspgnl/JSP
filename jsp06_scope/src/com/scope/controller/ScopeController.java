package com.scope.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ScopeController")
public class ScopeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ScopeController() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("Controller 도착!!");
		
		String requestId = (String)request.getAttribute("requestId");
		HttpSession session = request.getSession();
		String sessionId = (String)session.getAttribute("sessionId");
		ServletContext application = getServletContext();
		String applicationId = (String)application.getAttribute("applicationId");
						
		System.out.println("request: "+requestId);
		System.out.println("session: "+sessionId);
		System.out.println("application: "+applicationId);
		
		String param = request.getParameter("req");
		System.out.println("request parameter : "+param);
		
//		PrintWriter out = response.getWriter();
//		out.println("<h1>응답</h1>");
//		out.println("<table border='1'>");
//		out.println("<tr>");
//		out.println("<th>scope</th>");
//		out.println("<td>값</td>");
//		out.println("</tr>");
//		out.println("<tr>");
//		out.println("<td>page</td>");
//		out.println("<td>"+null+"</td>");
//		out.println("</tr>");
//		out.println("<tr>");
//		out.println("<td>request</td>");
//		out.println("<td>"+requestId+"</td>");
//		out.println("</tr>");
//		out.println("");
//		out.println("");
//		out.println("");
//		out.println("</table>");

		RequestDispatcher dispatch = request.getRequestDispatcher("result.jsp");
		request.setAttribute("requestId", "servlet에서 보내준 request");
		dispatch.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		doGet(request, response);
	}

}
