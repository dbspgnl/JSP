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
	<form action="con.do" method="get">
	<input type="hidden" name="command" value="insertres"/>
	<table border="1">
	<!-- 7934	MILLER	CLERK	7782	1982-01-23	1300	0	10 -->
		<tr>
			<th>empno</th>
			<td><input type="text" name="empno"/></td>
		</tr>
		<tr>
			<th>ename</th>
			<td><input type="text" name="ename"/></td>
		</tr>
		<tr>
			<th>job</th>
			<td><input type="text" name="job"/></td>
		</tr>
		<tr>
			<th>mgr</th>
			<td><input type="text" name="mgr"/></td>
		</tr>
		<tr>
			<th>sal</th>
			<td><input type="text" name="sal"/></td>
		</tr>
		<tr>
			<th>comm</th>
			<td><input type="text" name="comm"/></td>
		</tr>
		<tr>
			<th>deptno</th>
			<td><input type="text" name="deptno"/></td>
		</tr>

		<tr>
			<td colspan="2">
				<input type="submit" value="작성">
				<input type="button" value="목록" onclick="location.href='con.do?command=list'"/>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>