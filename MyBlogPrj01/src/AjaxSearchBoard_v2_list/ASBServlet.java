package AjaxSearchBoard_v2_list;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/asb2.do")
public class ASBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("<ASBServlet>");
		String userName = request.getParameter("userName");
		System.out.println(userName);
//		String res = getJSON(userName);
//		System.out.println(res);
//		response.getWriter().write(res);
		
		ASBDao asbDao = new ASBDao();
		List<ASBDto> list = asbDao.search(userName);
		System.out.println("dao 완료");
		System.out.println(list);
		HttpSession session = request.getSession();
		session.setAttribute("ASB_list", list);
		//response.sendRedirect("AjaxSearchBoard_v2_list2.jsp");
		response.sendRedirect("Webpage/AjaxSearchBoard_v2_list.jsp");
		
//		JSONArray jsonArray = JSONArray.fromObject(list);
//		System.out.println("mybeanList - " + jsonArray);
//		 
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("result", jsonArray);
//		
//		JSONObject jsonObject = JSONObject.fromObject(map);
//		System.out.println("json - " + jsonObject);
//		
//		String jout = org.json.simple.JSONArray.toJSONString(jsonArray);
//		System.out.println(jout);
//		
//		response.getWriter().write(jout);
	}
	
	public String getJSON(String userName) {
		if(userName==null) userName = "";
		System.out.println("getJSON 진입");
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ASBDao asbDao = new ASBDao();
		List<ASBDto> list = asbDao.search(userName);
		for(int i=0; i<list.size(); i++) {
			result.append("{\"name\":\""+list.get(i).getUsername()+"\",");
			result.append("\"age\":\""+list.get(i).getUserage()+"\",");
			result.append("\"gender\":\""+list.get(i).getUsergender()+"\",");
			result.append("\"email\":\""+list.get(i).getUsermail()+"\"}");
			if(i!=list.size()-1) {
				result.append(",");
			}
		}
		result.append("]}");
		return result.toString();
	}
	

}
