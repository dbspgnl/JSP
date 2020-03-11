<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>  

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/JavaScript" src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script> <!-- 도로명 API -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<%	
	String authNum = (String)session.getAttribute("authNum");  //인증번호
	String mem_email = (String)session.getAttribute("mem_email");
	String emailNotTobe = request.getParameter("emailNotTobe");
%>	
<script type="text/javascript">

// 이메일 인증
function echeckFun(){
	if($("#mem_email").val()==""){
		alert("이메일을 입력해주세요");
		return false;
	}else{
		$("input[name='command']").val("emailChk");
		$("form").submit();
	}
}

//인증 번호 확인 클릭시 css속성 변경
function authNumcheck(){
	var inputauthNum = document.getElementById("InputauthNum").value;
	if("<%=authNum%>"==inputauthNum){
	$("#emailconfirmmsg").css("display", "block"); //화면에 보이게 
	$("#eCheck_Display").css("display", "none"); //화면에 안보이게 
	$("#emailchk").css("display", "none"); //이메일 인증버튼 안보이게 
	
	document.getElementById("InputauthNum").title = 'y';  //인증번호 입력태그의 title('n'->'y')로 변경

	}else{
		alert("인증메일에서 버튼클릭후 진행해주세요.");
	}	
}

//처음 로드 시 값 유지시키기 위한 함수
$(function(){

	if("<%=emailNotTobe%>"=="false"){
		$("#eJung_Display").css("display", "block"); //이메일중복인지
	}else if("<%=emailNotTobe%>"=="true"){
		$("#eCheck_Display").css("display", "block"); //인증번호입력란 화면에 보이게 
		alert("이메일에서 인증번호를 확인해 주세요");
	}
	//자동으로 인증번호 입력되게 하는 조건문
	if("<%=authNum%>"!="null"){
		
		$("#eCheck_Display").show();
		$("#InputauthNum").val("<%=authNum%>");
		$("#mem_email").val("<%=mem_email%>");
	}
});

</script>
</head>


<body>
<br>
<section>
<div id="loginform">
  <form action="../signup.do" method="post"">
  <input type="hidden" name="command" value="signupres"/>
  	 <a class="atag">Email: </a><input type="email" value="" name="mem_email" id="mem_email" required="required"/>
  	 <!-- 이메일 인증버튼 -->
  	 <input type="button" id="emailchk" onclick="echeckFun();" value="이메일인증" style="background:black; width:200px; border-radius: 10px; color:white; font-weight:bold;"/>
  	 <!-- 이메일 인증버튼 -->
  	 <!-- 1.이메일 인증 버튼 클릭시  -->
			<a id="eJung_Display" style="color:red; display:none;">이미 존재하는 이메일입니다</a>
			<div id="eCheck_Display" style="display:none;">
				<!-- 인증번호 -->
			
				<span>인증번호 입력:</span> <input type="text" id="InputauthNum" title="n" value="" style="width: 230px;"/>
				<input type="button" id="btn" onclick="authNumcheck();" value="확인" style="background:black; width:100px; border-radius: 10px; color:white; font-weight:bold;"/>
				<input type="button" id="ReSend" onclick="echeckFun();" value="재전송" style="background:black; width:100px; border-radius: 10px; color:white; font-weight:bold;"/> 
			
			</div>
				<!--  2. 이메일 인증 완료시 보일부분 -->
			<a id="emailconfirmmsg" style="color:blue; display:none; margin-left: -20px; font-size: 20px;font-weight: bold;">이메일 인증 완료</a>
			<!--  2. 이메일 인증 완료시 보일부분 끝     -->
			<!-- 1.이메일 인증 버튼 클릭시  끝   -->
</form>
</div>
</section>

</body>
</html>