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
	// insert에서 받은 myname, mytitle, mycontent
	// request를 사용해 변수에 담는다.
	// 변수를 dto로 세팅해준다.
	// dao의 insert(dto)기능으로 결과를 받는다. (res)
	// res>0 일 경우.. 성공.
	String myname = request.getParameter("myname"); //request 객체
	String mytitle = request.getParameter("mytitle");
	String mycontent = request.getParameter("mycontent");
	MyDto dto = new MyDto();
	dto.setMyname(myname);
	dto.setMytitle(mytitle);
	dto.setMycontent(mycontent);
	MyDao dao = new MyDao();
	int res = dao.insert(dto);
	if(res>0){
%>
	<script type="text/javascript">
		alert("글 작성 성공")
		location.href="mylist.jsp";
	</script>
<%
	}else{
%>
	<script type="text/javascript">
		alert("글 작성 실패")
		location.href="myinsert.jsp";
	</script>
<%
	}
%>
</body>
</html>