<%@page import="com.mvc.dto.PagingDto"%>
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
<%
	PagingDto paging = (PagingDto)request.getAttribute("paging");
	int pagegroup = (int)Math.ceil((double)paging.getCurrentpage()/paging.getPagescale()); 
	//블록번호 = 현재 페이지 / 블록크기 = 올림 (1/5=0.2=1) 1번째 블록
	int startpage = paging.getPagescale()*(pagegroup-1)+1;
	// 블록의 시작 페이지 번호 = (블록크기 * (블록번호-1)) +1 / 5*(1-1)+1= 블록의 시작은 1
	int endpage = paging.getPagescale()*pagegroup;
	int totalpage = paging.getTotalpage();

%>
<body>
	<h1>글목록</h1>
	<table border="1">
	<col width="50">
	<col width="100">
	<col width="300">
	<col width="100">
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
		
		<c:choose>
			<c:when test="${empty list }">
				<tr>
					<td colspan="4">작성된 글이 존재하지 않습니다.</td>
				</tr>			
			</c:when>
			<c:otherwise>
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.seq }</td>
						<td>${dto.writer }</td>
						<td><a href="con.do?command=detail&seq=${dto.seq }">${dto.title }</a></td>
						<td>${dto.regdate }</td>
					</tr>
				</c:forEach>
				
				<!-- 페이징 영역 -->
				<tr>
					<td colspan="4" align="center">
<%
					if(pagegroup>1){
%>											
						<a href="con.do?command=list&page=<%=startpage-1%>">&lt;&lt;prev</a>
<%
					}
					for(int pagenum = startpage; pagenum <= ((endpage<totalpage)?endpage:totalpage); pagenum++){
					// 변수 pagenum 선언하고 startpage로 초기화함
					// pagenum을 하나씩 증가하며 마지막 페이지가 전체 페이지 보다 작으면 반복
%>					
						<a href="con.do?command=list&page=<%=pagenum%>"><%=pagenum %></a>
<%
					}
					if(endpage < paging.getTotalpage()){						
%>
						<a href="con.do?command=list&page=<%=endpage+1%>">next&gt;&gt;</a>
<%
					}
%>
					</td>
				</tr>
				
			</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="4">
				<input type="button" value="글쓰기" onclick="location.href='con.do?command=insert'" />
			</td>
		</tr>
	
	
	</table>
	
</body>
</html>