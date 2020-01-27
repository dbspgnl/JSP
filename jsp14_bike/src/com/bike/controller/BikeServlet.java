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
import com.bike.dao.BikeDao;
import com.bike.dto.BikeDto;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
		BikeDao dao = new BikeDao();
		BikeBiz biz = new BikeBiz();
		dao.delete(); // 프라이머리 키 때문에 지우고 시작.
		
		if(command.equals("first")) {
			response.sendRedirect("bike01.jsp");
		}
		else if(command.equals("firstdb")) {
			
			String[]bike = request.getParameterValues("bike");
			List<BikeDto> bikes = new ArrayList<BikeDto>();
			for(int i=0; i<bike.length; i++) {
				String[]tmp = bike[i].split("/");
				//System.out.println(tmp[0]+" "+tmp[5]+", "+tmp[6]);
				
				//1.dto에 담자
				BikeDto dto = new BikeDto(
					tmp[0], Integer.parseInt(tmp[1]), tmp[2], tmp[3], 
					Integer.parseInt(tmp[4]), Double.parseDouble(tmp[5]), 
					Double.parseDouble(tmp[6]));
				//2.dto를 list(bikes)에 담자
				bikes.add(dto);
			}
			//3. dao에 list를 보내서 insert하자.
			int res = biz.insert(bikes);
			if(res==bikes.size()) {
				System.out.println("insert 성공");
			} else {
				System.out.println("insert 실패");
			}
			
			//4. 저장 완료되면 index.html로 보내자.
			response.sendRedirect("index.html");	
		}
		else if (command.equals("second")) {
			response.sendRedirect("bike02.jsp");
		} 
		else if(command.equals("second_db")) {
			
			String txt = request.getParameter("obj");
			//System.out.println(txt);
			
			JsonElement element = JsonParser.parseString(txt);
			//System.out.println(element.getAsJsonObject().get("DESCRIPTION"));
			
			List<BikeDto> list = new ArrayList<BikeDto>();
			
			for(int i=0; i<element.getAsJsonObject().get("DATA").getAsJsonArray().size(); i++) {
				JsonObject tmp = element.getAsJsonObject().get("DATA").getAsJsonArray().get(i).getAsJsonObject();
				// json bike 전체가 element 이다. getASJsonObject 객체를 가져온다. Data 라는 놈.
				// 여기까지가 Json이다. Json 엘리먼트가 array라고 알려주고 i번째 애를 가져온다.
				// i번째 가져온 json엘리먼트를 받아온다.
				
				// System.out.println(tmp.get("addr_gu").getAsString()); // 강남구~중랑구
				JsonElement addr_gu_je = tmp.get("addr_gu");
				JsonElement content_id_je = tmp.get("content_id");
				JsonElement content_nm_je = tmp.get("content_nm");
				JsonElement new_addr_je = tmp.get("new_addr");
				JsonElement cradle_count_je = tmp.get("cradle_count");
				JsonElement longitude_je = tmp.get("longitude");
				JsonElement latitude_je = tmp.get("latitude");
				
				String addr_gu = addr_gu_je.getAsString();
				int content_id = content_id_je.getAsInt();
				String content_nm = content_nm_je.getAsString();
				String new_addr = new_addr_je.getAsString();
				int cradle_count = cradle_count_je.getAsInt();
				double longitude = longitude_je.getAsDouble();
				double latitude = latitude_je.getAsDouble();
				
				BikeDto dto = 
					new BikeDto(addr_gu, content_id, content_nm, new_addr, cradle_count, longitude, latitude);
				list.add(dto);
			}
			
			int res = dao.insert(list);
			if(res == 1163) {
				System.out.println("insert 성공");
			} else {
				System.out.println("insert 실패");
			}
			
			response.getWriter().append(res+"");
			
		}
	}
	
}
