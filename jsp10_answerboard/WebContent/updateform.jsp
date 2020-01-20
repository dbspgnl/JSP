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
	<h1>글수정</h1>
	<form action="answer.do" method="post">
	<input type="hidden" name="command" value="updateres">
	<table border="1">
	<tr>
		<th>작성자</th>
		<td><input type="text" name="writer" value="${dto.writer }"/></td>
	</tr>
	<tr>
		<th>제목</th>
		<td><input type="text" name="title" value="${dto.title }"/></td>
	</tr>
	<tr>
		<th>내용</th>
		<td>
			<textarea rows="10" cols="60" name="content" >${dto.content }</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="hidden" name="boardno" value="${dto.boardno }">
			<input type="submit" value="수정">
			<input type="button" value="목록" onclick="location.href='answer.do?command=list'">
		</td>
	</tr>
	</table>
	</form>
</body>
</html>