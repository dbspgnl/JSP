package com.chat.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chat.dao.ChatDao;

@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChatServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; chartset=UTF-8");
		String chatName = URLDecoder.decode(request.getParameter("chatName"), "UTF-8"); 
		String chatContent = URLDecoder.decode(request.getParameter("chatContent"), "UTF-8"); 
		if(chatName==null || chatName.equals("") || chatContent==null || chatContent.equals("")) {
			response.getWriter().write("0"); // 비어있는 경우 오류로써 0 리턴
		} else {
			response.getWriter().write(new ChatDao().submit(chatName, chatContent)+"");
			// 반드시 문자로 출력하기 위해 +""
		}
	}

}
