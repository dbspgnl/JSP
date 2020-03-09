<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../resources/css/bootstrap.css">
<link rel="stylesheet"  href="../resources/css/custom.css">
<title>MyBlog Project</title>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="../resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function search() {
			$("#ajaxTable").html("");
			var getpara = "userName="+encodeURIComponent($("#userName").val());  
			$.ajax({
				url:"../abs1.do?"+getpara,
				data:"", 
				dataType:"json", 
				success:function(msg){
						$.each(msg, function(key, val) {
							for(var i=0; i<val.length; i++){
								var $tr = $("<tr>");
								$("#ajaxTable").append("<tr>");
								for(var j in val[i]){
									var $td = $("<td>");
									$("#ajaxTable tr").eq(i).append($td.html(val[i][j]));
								}
							}
					});
				}, 
				error:function(){
					alert("통신 실패");
				}
			});
		}; 	
		window.onload = function() { 
			search();
		}
	</script>
</head>
<body>

	<br>
	<div class="container">
		<div class="form-group row pull-right">
			<div class="col-xs-8">
				<input class="form-control" id="userName" onkeyup="search();" type="text" size="20">
			</div>
			<div class="col-xs-2">
				<button class="btn btn-primary" onclick="search();" type="button">검색</button>
			</div>
		</div>
		<table class="table" style="text-align: center; border: 1px solid #dddddd">
		<col width="20%">
		<col width="20%">
		<col width="20%">
		<col width="40%">
			<thead>
				<tr>
					<th style="background-color: #fafafa; text-align: center;">이름</th>
					<th style="background-color: #fafafa; text-align: center;">나이</th>
					<th style="background-color: #fafafa; text-align: center;">성별</th>
					<th style="background-color: #fafafa; text-align: center;">이메일</th>
				</tr>
			</thead>
			<tbody id="ajaxTable">
			</tbody>
		</table>
	</div>

</body>
</html>