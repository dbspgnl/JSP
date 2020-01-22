package com.bike.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bike.biz.BikeBiz;
import com.bike.biz.BikeBizImpl;
import com.bike.dto.BikeDto;

@WebServlet("/BikeServlet")
public class BikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BikeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		
		String command = request.getParameter("command");
		BikeBiz biz = new BikeBizImpl();
		
//		String addr_gu = request.getParameter("str.addr_gu");
//		int content_id = Integer.parseInt(request.getParameter("str.content_id"));
//		String content_num = request.getParameter("str.content_num");
//		String new_addr = request.getParameter("str.new_addr");
//		int cradle_count = Integer.parseInt(request.getParameter("str.cradle_count"));
//		double longitude = Double.parseDouble(request.getParameter("str.longitude"));
//		double latitude = Double.parseDouble(request.getParameter("str.latitude"));
		
		
		if(command.equals("first")) {
			response.sendRedirect("bike01.jsp");
		}
		else if(command.equals("firstdb")) {
			
			String[]bike = request.getParameterValues("bike");
			List<BikeDto> bikes = new ArrayList<BikeDto>();
			for(int i=0; i<bike.length; i++) {
				String[]tmp = bike[i].split("/");
				//System.out.println(tmp[0]+" "+tmp[5]+", "+tmp[6]);
				
				
//			BikeDto dto = new BikeDto(
//				Integer.parseInt(tmp[0]), tmp[1], tmp[2], tmp[3], tmp[4], tmp[5], tmp[6]);
				
				
				//1.dto에 담자
				
				
				
				
				
				//2.dto를 list(bikes)에 담자
			}
			//3. dao에 list를 보내서 insert하자.
			
			
			//4. 저장 완료되면 index.html로 보내자.
					
//			int res = biz.insert(dto);
//			if(res>0) {
//				jsResponse("추가 성공", "bike.do?command=list", response);
//			} else {
//				jsResponse("추가 실패", "bike.do?command=list", response);
//			}
			
		}
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"')");
		out.println("location.href='"+url+"'");
		out.println("</script>");
	}

}
