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
	<h1>목록</h1>
	<table border="1">
		<tr>
			<th>EMPNO</th>
			<th>ENAME</th>
			<th>JOB</th>
			<th>MGR</th>
			<th>HIREDATE</th>
			<th>SAL</th>
			<th>COMM</th>
			<th>DEPTNO</th>
		</tr>
		
		<c:choose>
			<c:when test="${empty list }">
				<tr>
					<td colspan="8">작성된 글이 존재하지 않습니다.</td>
				</tr>			
			</c:when>
			<c:otherwise>
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.empno }</td>
						<td>${dto.ename }</td>
						<td>${dto.job }</td>
						<td>${dto.mgr }</td>
						<td>${dto.hiredate }</td>
						<td>${dto.sal }</td>
						<td>${dto.comm }</td>
						<td>${dto.deptno }</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="8">
				<input type="button" value="추가" onclick="location.href='con.do?command=insertform'" />
				<input type="button" value="추가" onclick="location.href='con.do?command=insertform'" />
			</td>
		</tr>
	
	
	</table>
	
</body>
</html>