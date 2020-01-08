<%@page import="com.md.dao.Dao"%>
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
	String[]chks = request.getParameterValues("chk");
	Dao dao = new Dao();
	int res = dao.delete(chks);
	if(res>0){
%>
	<script type="text/javascript">
		alert("삭제 성공");
		location.href="./MDlist.jsp";
	</script>	
<%
	} else {
%>
	<script type="text/javascript">
		alert("삭제 실패");
		location.href="./MDlist.jsp";
	</script>	
<%
	}
%>		
</body>
</html>