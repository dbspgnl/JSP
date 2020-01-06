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
	// dao 객체를 만들어서 selectone(myno)를 돌릴거다.
	// 그렇다면 myno가 필요하다. myno를 이전 페이지에서 받는다.
	// request로 myno를 받아서 dao.selectone(myno)한다.
	// selectone()한 걸 dto에 담아서 쓸거다. 
	// dto.getMyname() / dto.getMytitle() / dto.getMycontent()
	int myno = Integer.parseInt(request.getParameter("myno"));
	MyDao dao = new MyDao();
	MyDto dto = dao.selectOne(myno);
%>
	<h1>글 보기</h1>
	<table border="1">
		<tr>
			<th>이름</th>
			<td><%=dto.getMyname() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=dto.getMytitle() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getMycontent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="button" value="수정" onclick="location.href='myupdate.jsp?myno=<%=dto.getMyno() %>'"/>
				<input type="button" value="삭제" onclick="location.href='mydelete.jsp?myno=<%=dto.getMyno() %>'"/>
				<input type="button" value="목록" onclick="location.href='mylist.jsp'"/>
			</td>
		</tr>
	
	</table>
</body>
</html>