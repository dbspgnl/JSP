<%@page import="com.md.dto.Dto"%>
<%@page import="com.md.dao.Dao"%>
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
<%@ include file="./form/header.jsp" %>
<%
	int seq = Integer.parseInt(request.getParameter("seq"));
	Dao dao = new Dao();
	Dto dto = dao.selectOne(seq);
%>
	<h1>글 수정</h1>
	
	<form action="updateres.jsp" method="post">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><%=dto.getWriter() %></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="<%=dto.getWriter() %>"/></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><textarea rows="10" cols="60" name="content"><%=dto.getWriter() %></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="hidden" name="seq" value="<%=dto.getSeq()%>"/>
					<!-- 숫자 안보내면 모든 테이블이 수정된다. -->
					<input type="submit" value="수정"/>
					<input type="button" value="취소" onclick=""/>
				</td>
			</tr>
		</table>
	</form>
	
<%@ include file="./form/footer.jsp" %>
</body>
</html>