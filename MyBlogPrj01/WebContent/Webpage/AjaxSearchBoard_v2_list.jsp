<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet"  href="../resources/css/custom.css"> 
<title>MyBlog Project</title>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="../resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function search() {
			$("#ajaxTable").html("");
			var getpara = "userName="+encodeURIComponent($("#userName").val()); 
			$.ajax({
				type: "GET",
				url:"../asb2.do?"+getpara, 
				success:function(msg){
		           	$(".container").html(msg);
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
<body>
<%-- <%@include file="header.jsp" %> --%>

	<br>
	<div class="container">
		<div class="row">
			<div class="form-group row pull-right">
				<div class="col-xs-8">
					<input class="form-control" id="userName" onkeyup="enterkey();" type="text" size="20">
					<!-- <input class="form-control" id="userName" type="text" size="20"> -->
					<!-- <input class="form-control" id="userName" onkeyup="search();" type="text" size="20"> -->
				</div>
				<div class="col-xs-2">
					<button class="btn btn-primary" onclick="search();" type="button">검색</button>
				</div>
			</div>
			<div class="col-xs-1 pull-right" style="min-width: 200px;">
				<select class="form-control">
					<option>이름</option>
					<option>나이</option>
					<option>이메일</option>
				</select>
			</div>
		</div>
		<div id="ajaxTable"> 
		<table id="admin_table" class="table table-striped table-bordered">
			<tr>
				<th>이름</th>
				<th>나이</th>
				<th>성별</th>
				<th>이메일</th>
			</tr>
			<c:choose>
				<c:when test="${empty ASB_list }">
					<tr>
						<td colspan="4">회원 목록이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${ASB_list }" var="dto">
						<tr>
							<td>${dto.username }</td>
							<td>${dto.userage }</td>
							<td>${dto.usergender }</td>
							<td>${dto.usermail }</td>
						</tr>								
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		</div>
	</div>
	

</body>
</html>