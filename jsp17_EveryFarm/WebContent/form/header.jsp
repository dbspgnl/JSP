<%@page import="com.ef.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	UserDto user = null;
	if(session.getAttribute("user")!=null){
		user = (UserDto)session.getAttribute("user");
		System.out.println(user.getId());
	}
%>
<header>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-exple-nabvar-collaps-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">EveryFarm</a>
		</div>
		<div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">메인</a></li>
				<li><a href="#">게시판</a></li>
			</ul>
<%
			if(user == null){
%>			
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="ef.do?command=login">로그인</a></li>
						<li><a href="#">회원가입</a></li>
					</ul>
				</li>
			</ul>
<%
			} else {
%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">회원관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="ef.do?command=logout">로그아웃</a></li>
					</ul>
				</li>
			</ul>
<%
			}
%>
		</div>
	</nav>
</header>	
</body>
</html>