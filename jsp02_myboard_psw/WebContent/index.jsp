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

	<a href="./mylist.jsp">list</a><br/>
	<a href="mylist.jsp">해당파일</a><br/>
	<a href="/jsp02_myboard_psw/mylist.jsp">전체경로로 진입</a>
<%-- 	<jsp:forward page="./mylist.jsp"></jsp:forward> --%>
	
</body>
</html>