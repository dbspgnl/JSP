<%@page import="com.my.dto.MyDto"%>
<%@page import="java.util.List"%>
<%@page import="com.my.dao.MyDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <% %> 안은 자바다. -->
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
	MyDao dao = new MyDao();
	List<MyDto> list = dao.selectList();
%>
	<h1>List</h1>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
<%
		for(int i=0; i<list.size(); i++){
%>
		<tr>
			<td><%= list.get(i).getMyno() %></td>
			<td><%= list.get(i).getMyname() %></td>
			<td><a href="mydetail.jsp?myno=<%=list.get(i).getMyno()%>"><%= list.get(i).getMytitle() %></a></td>
			<td><%= list.get(i).getMydate() %></td>
		</tr> 
<%	
		}
%>
		<tr>
			<td colspan="4" align="right">
				<input type="button" onclick="location.href = 'myinsert.jsp'" value="글쓰기" />
			<!-- <input type="button" onclick="./myinsert.jsp" value="글쓰기" /> -->
			</td>
		</tr>
	</table>
</body>
</html>