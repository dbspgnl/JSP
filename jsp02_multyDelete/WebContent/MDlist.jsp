<%@page import="java.util.List"%>
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
<script type="text/javascript">
	function allChk(click) {
		var chks = document.getElementsByName("chk");
		for(var i=0; i<chks.length;	i++){
			chks[i].checked = click;
		}
	}
</script>
</head>
<body>
<%@ include file="./form/header.jsp" %>
<%
	Dao dao = new Dao();
	List<Dto> list = dao.selectList();
%>
	<h2>글 목록</h2>
	<form action="./MDelete.jsp" method="post" id="mdform">
	<table border="1">
	<col width ="30"/>
	<col width ="50"/>
	<col width ="100"/>
	<col width ="300"/>
	<col width ="100"/>
		<tr>
			<th><input type="checkbox" name="all" onclick="allChk(this.checked);" /></th>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>등록일</th>
		</tr>
<%
		if(list.size()==0){
%>
		<tr>
			<td colspan="5">작성한 글이 없습니다.</td>
		</tr>
<%
		}
		for(Dto dto : list){
%>	
		<tr>
			<td style="text-align: center;"><input type="checkbox" name="chk" value="<%=dto.getNum()%>"/></td>
			<td style="text-align: center;"><%=dto.getNum() %></td>
			<td style="text-align: center;"><%=dto.getName() %></td>
			<td><a href="#" style="text-decoration:none;"><%=dto.getTitle() %></a></td>
			<td style="text-align: center;"><%=dto.getDate() %></td>
		</tr>
<%
		}
%>		
		<tr>
			<td colspan="5" align="right">
				<input type="button" value="글쓰기" onclick="location.href='insert.jsp'"/>
				<input type="submit" value="선택삭제"/>
			</td>
		</tr>
	</table>
	</form>
<%@ include file="./form/footer.jsp" %>
</body>
</html>