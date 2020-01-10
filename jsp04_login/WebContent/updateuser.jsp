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
<body>
<%
	MyMemberDto dto = (MyMemberDto)request.getAttribute("dto");
%>
	<form action="logincontroller.jsp" method="post">
	<input type="hidden" name="command" value="updateres" />
	<h1>정보 수정</h1>
	<table border="1">
		<tr>
			<th>이름</th>
			<td><input type="text" name="myname" value="<%=dto.getMyname() %>"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="mypw" value="<%=dto.getMypw() %>"></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="myaddr" value="<%=dto.getMyaddr() %>"></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="hidden" name="myno" value="<%=dto.getMyno() %>"/>
				<input type="submit" value="수정" />
				<input type="button" value="목록" onclick="history.back();"/>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>