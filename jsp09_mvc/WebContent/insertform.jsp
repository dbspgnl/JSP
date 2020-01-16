<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>글쓰기</h1>
	<form action="#">
	<table border="1">
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer"/></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60"></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="작성">
				<input type="button" value="목록" onclick="con.do?command=list"/>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>