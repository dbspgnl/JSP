package Login_Join;

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

@WebServlet("/LoginJoinServlet")
public class LoginJoinServlet extends HttpServlet {
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
			
		}
		else if(command.equals("AjaxSearchBoard_v1_onkeyup")) {
			response.sendRedirect("AjaxSearchBoard_v1_onkeyup.jsp");
		}
		else if(command.equals("searchBoardResult")) {
			String userName = request.getParameter("userName");
			System.out.println(userName);
			String res = getJSON(userName);
			System.out.println(res);
			response.getWriter().write(res);
		}
		else if(command.equals("join")) {
			response.sendRedirect("userJoin.jsp");
		}
		else if(command.equals("userJoin")){
			
			HttpSession session = null;
			String userID = null;
			String userPassword = null;
			String userEmail = null;
			if(request.getParameter("userID")!=null) {
				userID = request.getParameter("userID");
			}
			if(request.getParameter("userPassword")!=null) {
				userPassword = request.getParameter("userPassword");
			}
			if(request.getParameter("userEmail")!=null) {
				userEmail = request.getParameter("userEmail");
			}
			if(userID == null || userPassword ==null || userEmail == null) {
				jsResponse("입력이 안 된 사항이 있습니다.", "history.back();", response);
			}
			UserDAO userDAO = new UserDAO();
			int result = userDAO.join(new UserDTO(userID, userPassword, userEmail, SHA256.getSHA256(userEmail), 0));
			if(result == -1) {
				jsResponse("이미 존재하는 아이디 입니다.", "history.back();", response);
				return;
			} else {
				session.setAttribute("userID", userID);
				response.sendRedirect("emailSendAction.jsp");
				return;
			}
		}
	}

	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"')");
		out.println("location.href='"+url+"'");
		out.println("</script>");
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
