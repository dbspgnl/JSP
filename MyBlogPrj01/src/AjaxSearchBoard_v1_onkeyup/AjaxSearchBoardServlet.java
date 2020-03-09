package AjaxSearchBoard_v1_onkeyup;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import AjaxSearchBoard_v1_onkeyup.AjaxSearchBoardDao;
import AjaxSearchBoard_v1_onkeyup.AjaxSearchBoardDto;


@WebServlet("/abs1.do")
public class AjaxSearchBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("servlet connection");
		String userName = request.getParameter("userName");
		System.out.println(userName);
		String res = getJSON(userName);
		System.out.println(res);
		response.getWriter().write(res);
	}
	public String getJSON(String userName) {
		if(userName==null) userName = "";
		System.out.println("getJSON 진입");
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		AjaxSearchBoardDao asbDao = new AjaxSearchBoardDao();
		List<AjaxSearchBoardDto> list = asbDao.search(userName);
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
