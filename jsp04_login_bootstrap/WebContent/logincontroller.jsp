<%@page import="java.util.List"%>
<%@page import="com.login.dto.MyMemberDto"%>
<%@page import="com.login.biz.MyMemberBizImpl"%>
<%@page import="com.login.biz.MyMemberBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String command = request.getParameter("command");
	System.out.println("["+command+"]");
	
	MyMemberBiz biz = new MyMemberBizImpl();
	
	//------------------로그인 ------------------
	if(command.equals("login")){
		String myid = request.getParameter("id");
		String mypw = request.getParameter("pw");
		
		MyMemberDto dto = biz.login(myid, mypw);
		
		//if(dto != null){ //login 정보가 있으면
		if(dto.getMyid() != null){
			// session: 만료되기 전까지 어플리케이션 전체에서 사용 가능
			// 스코프(범위 객체) 중에 하나다.
			session.setAttribute("dto", dto);
			//setMaxInactiveInterval(second): 초 만큼 활동이 없으면 
			// session을 invalidate한다. (default: 30분 / 음수: 무제한) 
			session.setMaxInactiveInterval(10*60);
			
			if(dto.getMyrole().equals("ADMIN")){
				response.sendRedirect("adminmain.jsp");
			} else if (dto.getMyrole().equals("USER")){
				response.sendRedirect("usermain.jsp");
			}
		} else {
%>
		<script type="text/javascript">
			alert("id와 pw를 다시 한 번 확인해 주세요!");
			location.href="index.jsp";
		</script>
<% 			
		}
	//------------------로그아웃 ------------------	
	} else if (command.equals("logout")){
		// 만료
		session.invalidate();
		response.sendRedirect("index.jsp");
	//------------------전체 출력(all) ------------------
	} else if (command.equals("selectlist")){
		List<MyMemberDto> list = biz.selectList();
		request.setAttribute("list", list);
		pageContext.forward("userselectlist.jsp");
	//------------------전체 출력(en) ------------------
	} else if (command.equals("selectenabled")){
		List<MyMemberDto> list = biz.selectEnabled();
		request.setAttribute("list", list);
		pageContext.forward("userselectenabled.jsp");
	//------------------등급 수정 ------------------
	} else if (command.equals("updateroleform")){
		int myno = Integer.parseInt(request.getParameter("myno"));
		MyMemberDto dto = biz.selectUser(myno);
		request.setAttribute("select", dto);
		pageContext.forward("updaterole.jsp");
	//------------------등급 수정 결과 ------------------	
	} else if (command.equals("updateroleres")){
		int myno = Integer.parseInt(request.getParameter("myno"));
		String role = request.getParameter("role");
		int res = biz.updateRole(myno, role);
		if(res>0){
%>
		<script type="text/javascript">
			alert("등급 조정 성공");
			location.href="adminmain.jsp";
		</script>
<% 
		} else {
%>
		<script type="text/javascript">
			alert("등급 조정 실패");
			location.href="controller.jsp?command=updateroleform&myno=<%=myno%>";
		</script>
<%
	}
	//------------------계정 수정 ------------------
	} else if (command.equals("updateuser")){
		int myno = Integer.parseInt(request.getParameter("myno"));
		MyMemberDto dto = biz.selectUser(myno);
		request.setAttribute("dto", dto);
		pageContext.forward("updateuser.jsp");
	//------------------수정 결과------------------	
	} else if (command.equals("updateres")){
		String mypw = request.getParameter("mypw");
		String myname = request.getParameter("myname");
		String myaddr = request.getParameter("myaddr");
		int myno = Integer.parseInt(request.getParameter("myno"));
		MyMemberDto dto = new MyMemberDto();
		dto.setMypw(mypw);
		dto.setMyname(myname);
		dto.setMyaddr(myaddr);
		dto.setMyno(myno);
		int res = biz.updateUser(dto);
		if(res>0){
%>			
		<script type="text/javascript">
			alert("정보 수정 성공");
			location.href="usermain.jsp";
		</script>
<%
		} else {
%>			
		<script type="text/javascript">
			alert("정보 수정 실패");
			location.href="usermain.jsp";
		</script>
<%
		}
	} else if (command.equals("deleteuser")){
	int myno = Integer.parseInt(request.getParameter("myno"));
	int res = biz.deleteUser(myno);
	if(res>0){
%>
		<script type="text/javascript">
			alert("탈퇴 성공");
			location.href="index.jsp";
		</script>
<% 		
	} else {
%>
		<script type="text/javascript">
			alert("탈퇴 실패");
			location.href="history.back()";
		</script>
<%
	}
	
	//------------------회원가입------------------
	} else if (command.equals("registform")){
		response.sendRedirect("registform.jsp");
	//------------------아이디 체크------------------	
	} else if (command.equals("idchk")){
		String myid = request.getParameter("id");
		MyMemberDto dto = biz.idChk(myid);
		
		boolean idnotused = true;
		if(dto!=null){ // if(dto.getMyid() != null) {
			idnotused = false;
		}
		response.sendRedirect("idchk.jsp?idnotused="+idnotused);
	//------------------가입 결과 ------------------
	} else if (command.equals("registres")){
		String myid = request.getParameter("myid");
		String mypw = request.getParameter("mypw");
		String myname = request.getParameter("myname");
		String myaddr = request.getParameter("myaddr");
		String myphone = request.getParameter("myphone");
		String myemail = request.getParameter("myemail");
		MyMemberDto dto = new MyMemberDto();
		dto.setMyid(myid);
		dto.setMypw(mypw);
		dto.setMyname(myname);
		dto.setMyaddr(myaddr);
		dto.setMyphone(myphone);
		dto.setMyemail(myemail);
		int res = biz.insertUser(dto);
		if(res>0){
%>			
		<script type="text/javascript">
			alert("가입 성공");
			location.href="index.jsp";
		</script>
<%
		} else {
%>			
		<script type="text/javascript">
			alert("가입실패");
			location.href="history.back()";
		</script>
<%
		}
	}
%>			
		
		
			
				
		
	<h1 style="color:red;">잘못왔다.</h1>

</body>
</html>