package MyBlogProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import AjaxSearchBoard_v1_onkeyup.AjaxSearchBoardDao;
import AjaxSearchBoard_v1_onkeyup.AjaxSearchBoardDto;
import user.UserDAO;
import user.UserDTO;
import util.SHA256;


@WebServlet("/myblog.do")
public class MyBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String command = request.getParameter("command");
		System.out.println("<"+command+">");
		if(command.equals("main")) {
			response.sendRedirect("index.jsp");
		}
		else if(!command.equals("main")) {
			response.sendRedirect("AjaxSearchBoard/AjaxSearchBoard_v1_onkeyup.jsp");
		}
		else if(command.equals("AjaxSearchBoard_v1_onkeyup")) {
			response.sendRedirect("AjaxSearchBoard/AjaxSearchBoard_v1_onkeyup.jsp");
		}
		else if(command.equals("AjaxSearchBoard_v2_list")) {
			response.sendRedirect("AjaxSearchBoard/AjaxSearchBoard_v2_list.jsp");
		}
		
	}

}
