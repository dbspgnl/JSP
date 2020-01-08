<%@page import="com.mvc.dao.MVCDaoImpl"%>
<%@page import="com.mvc.dao.MVCDao"%>
<%@page import="com.mvc.dto.MVCDto"%>
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
	MVCDto dto = (MVCDto)request.getAttribute("dto");
%>
	<h1>글 내용</h1>
	<table border="1">
		<tr>
			<th>작성자</th>
			<td><%=dto.getWriter() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=dto.getTitle() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getTitle() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="button" value="수정" onclick="location.href='controller.jsp?command=update&seq=<%= dto.getSeq()%>'"/>
				<input type="button" value="삭제" onclick="location.href='controller.jsp?command=muldel&chk=<%= dto.getSeq()%>'"/>
				<input type="button" value="취소" onclick="location.href='controller.jsp?command=list'"/>
			</td>
		</tr>
	</table>
</body>
</html>