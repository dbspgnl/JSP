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
	//Dto dto = dao.selectOne(no);
%>
<%@ include file="./form/header.jsp" %>
	<h1>글 내용</h1>
	<table>
		<tr>
			<th>작성자</th>
			<td><%= %></td>
		</tr>
	
	</table>
<%@ include file="./form/footer.jsp" %>
</body>
</html>