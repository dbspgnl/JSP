<%@page import="com.everyfarm.admin.dto.PagingDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
    <link rel="stylesheet" href="../resources/css/admin/admin.css">   
	<link rel="stylesheet" href="../resources/css/membermypage/buttonstyle.css">
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="../resources/js/admin/bootstrap.js"></script>
	<script type="text/javascript">
		function search() {
			$("#admin_table").html(""); 
			var getpara = "userName="+encodeURIComponent($("#userName").val()); 
			$.ajax({
				type: "GET",
				url:"../admin.do?command=userlist&pageNumber=1&"+getpara, 
				success:function(msg){
					console.log("통신 성공");
		           	$("body").html(msg);
				}, 
				error:function(request,status,error){
					console.log("통신 실패");
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
		};
		$("#userName").keyup(function(e) {
			if(e.keyCode == 13) search();
		});
		function enterkey() {
			if(window.event.keyCode==13){
				search();
			}
		}
		window.onload = function() { 
			search();
		}
	</script> 
</head>
<%
	PagingDto paging = (PagingDto)session.getAttribute("userlist_paging");
	int pagegroup = (int)Math.ceil((double)paging.getCurrentpage()/paging.getPagescale()); 
	int startpage = paging.getPagescale()*(pagegroup-1)+1;
	int endpage = paging.getPagescale()*pagegroup;
	int totalpage = paging.getTotalpage();
	int currentpage = paging.getCurrentpage();
%>
<body>
<%@ include file="../home/header.jsp" %>
<div class="container-fluid">
	<div class="row">
	<%@ include file="admin_sidebar.jsp" %>
		<div class="col" >
			
		<!-- 바디 -->
		<div class="section_content">
			<h2 style="margin: 40px 40px 0px 40px;">회원목록</h2> 
				<div class="form-group row pull-right" style="margin: 0px !important;">
				<div class="col-md-9"></div>
				
					<div class="col-md-2">
						<input class="form-control" id="userName" onkeyup="enterkey();" type="text" 
						style="height: 35px !important;"> 
					</div>
					<div class="col-md-1">
						<button class="btn btn-primary" onclick="search();" type="button"
						style="margin-left: 0px !important;">검색</button>
					</div>
					
				</div>
			<div style="margin: 20px;">
				<table id="admin_table" class="table table-striped table-bordered">
				<col width="12%">
				<col width="10%">
				<col width="15%">
				<col width="22%">
				<col width="15%">
				<col width="8%">
				<col width="10%">
				<col width="8%">
					<tr>
						<th>아이디</th>
						<th>이름</th>
						<th>전화번호</th>
						<th>주소</th>
						<th>이메일</th>
						<th>등급</th>
						<th>가입일</th>
						<th style="font-size: 15px;">탈퇴여부</th>
					</tr>
					<c:choose>
						<c:when test="${empty userlist_admin }">
							<tr>
								<td colspan="8">회원 목록이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${userlist_admin }" var="dto">
								<tr>
									<td>${dto.mem_id }</td>
									<td>${dto.mem_name }</td>
									<td>${dto.mem_phone }</td>
									<td>${dto.mem_addr }</td>
									<td>${dto.mem_email }</td>
									<td>
										<c:choose>
											<c:when test="${dto.mem_grade eq 2}">
												<c:out value="농부"></c:out>
											</c:when>
											<c:when test="${dto.mem_grade eq 3}">
												<c:out value="관리자"></c:out>
											</c:when>
											<c:otherwise>
												<c:out value="회원"></c:out>
											</c:otherwise>											
										</c:choose>
									</td>
									<td>${dto.mem_regdate }</td>
									<td>
										<c:choose>
											<c:when test="${dto.mem_drop eq 'n'}">
												<c:out value="탈퇴"></c:out>
											</c:when>
											<c:when test="${dto.mem_drop eq 'y'}">
												<c:out value="가입"></c:out>
											</c:when>	
											<c:otherwise>
												<c:out value="오류"></c:out>
											</c:otherwise>									
										</c:choose>
									</td>
								</tr>								
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</table>
				
				<!-- 페이징 영역 -->
				<div align="center">
				<ul class="pagination" style="margin: 0 auto; display: inline-block;">
				<%
					if(pagegroup!=1){
				%>							
						<li><a href="../admin.do?command=userlist&pageNumber=<%=startpage-1%>">
						<span>&lt;&lt;</span></a></li>
				<%
					} else if(pagegroup==1){
				%>
						<li><span style="color: gray;">&lt;&lt;</span></li>
				<%	
					}
					for(int pagenum = startpage; pagenum <= ((endpage<totalpage)?endpage:totalpage); pagenum++){
						if(paging.getCurrentpage()==pagenum){					
				%>					
						<li class="active"><a href="../admin.do?command=userlist&pageNumber=<%=pagenum%>"><%=pagenum %></a></li>
				<%
							} else {
				%>
						<li><a href="../admin.do?command=userlist&pageNumber=<%=pagenum%>"><%=pagenum %></a></li>
				<%
							}
						}
					if(endpage < paging.getTotalpage()){				
				%>
						<li><a href="../admin.do?command=userlist&pageNumber=<%=endpage+1%>">
						<span>&gt;&gt;</span></a></li>
				<%
					} else {
				%>
						<li><span style="color: gray;">&gt;&gt;</span></li>
				<%
					}
				%>
				</ul>
				</div>	
			</div>
	    	
	    	</div>
	    
		</div>
	</div>
</div>
	
	
<%@ include file="../home/footer.jsp" %>
</body>
<script src="../resources/js/admin/admin.js"></script>
<script type="text/javascript">
/* 	if(${empty sessionScope.dto.mem_id}){
		alert("로그인이 필요합니다");
		location.href="../home/section.jsp";
	} */
</script>
</html>