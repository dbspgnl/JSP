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
<%
	String name = request.getParameter("name");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	Dto dto = new Dto();
	dto.setName(name);
	dto.setTitle(title);
	dto.setContent(content);
	Dao dao = new Dao();
	int res = dao.insert(dto);
	if(res>0){
%>
	<script type="text/javascript">
		alert("작성 성공");
		location.href="./MDlist.jsp";
	</script>
<%
	} else {
%>		
	<script type="text/javascript">
		alert("작성 실패");
		location.href="./insert.jsp";
	</script>
<%
	}
%>		


</body>
</html>