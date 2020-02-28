<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성적처리 프로그램</title>
<style type="text/css">
	fieldset{width:300px;}
	legend{font-size: 20px;}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("process.jq").click(function() {
			$.ajax({
				url:"ajax.do?"+getParameterValues(),
				data:"",
				dataType:"json",
				success:function(msg) {
					$("#result_js").html(
						decodeURIComponent(msg.name)+"님의 총점은 "
						+msg.sum+"이고, 평균은 "
						+msg.avg+"입니다."
					);	
				},
				error:function () {
					alter("통신 실패");
				}
			});
		});
	});
	function getParameterValues() {
		var name="name="+encodeURIComponent($("#name_js").val());
		var kor="kor="+$("#kor_js").val();
		var eng="eng="+$("#eng_js").val();
		var math="math="+$("#math_js").val();
		var query=name+"&"kor+"&"+eng+"&"+math;
		return query;
	}
	
</script>
</head>
<body>
	<fieldset>
	<legend>성적처리 프로그램 JS</legend>
	이름: <input type="text" id="name_js" /><br/>
	국어: <input type="text" id="kor_js" /><br/>
	영어: <input type="text" id="eng_js" /><br/>
	수학: <input type="text" id="math_js" /><br/>
	<input type="button" id="process_js" value="성적 처리" /><br/>
	
	<div id="result_js"></div>
	</fieldset>
	<br/>
	<fieldset>
	<legend>성적처리 프로그램 JQ</legend>
	이름: <input type="text" id="name_jq" /><br/>
	국어: <input type="text" id="kor_jq" /><br/>
	영어: <input type="text" id="eng_jq" /><br/>
	수학: <input type="text" id="math_jq" /><br/>
	<input type="button" id="process_jq" value="성적 처리" /><br/>
	
	<div id="result_jq"></div>
	</fieldset>
</body>
</html>