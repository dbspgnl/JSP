<%@page import="com.my.dto.MyDto"%>
<%@page import="com.my.dao.MyDao"%>
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
	int myno = Integer.parseInt(request.getParameter("myno"));
	MyDao dao = new MyDao();
	MyDto dto = dao.selectOne(myno);
%>
	<h1></h1>
	<form>
		<table>
			<tr>
				<th>이름</th>
				<td></td>
			</tr>
			<tr>
				<th>제목</th>
				<td></td>
			</tr>
			<tr>
				<th>내용</th>
				<td></td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="수정" />
					<input type="reset" value="취소" />
				</td>
			</tr>
		</table>
	</form>


</body>
</html>