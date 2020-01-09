<%@page import="com.login.dto.MyMemberDto"%>
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
<%
	MyMemberDto dto = (MyMemberDto)session.getAttribute("dto");
%>
<body>
	<h1><%=dto.getMyname() %>님 환영합니다.</h1>
	<input type="button" value="조회" onclick="location.href='logincontroller.jsp?command=selectenabled'" />
	<input type="button" value="수정" onclick="" />
	<input type="button" value="탈퇴" onclick="" />
	<!-- 탈퇴는 delete가 아니라 update (enabled:"N")-->
	
</body>
</html>