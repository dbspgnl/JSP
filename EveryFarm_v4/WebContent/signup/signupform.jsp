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
<style type="text/css">
*{margin:0px; padding:0px;}
body, html{margin:0;padding:0;}
body{
  background-color:#cd3333;
  font-family:sans-serif;
}
#loginform{
      width: 40%;
    height: 90%;
  background-color:#fff;
  padding:5px;
}
h2{
  text-align:center;
  font-size:42px;
  color:#333;
}
input{display:block;}
input{
  -webkit-transition:border 0.2s ease-in-out;
  -moz-transition:border 0.2s ease-in-out;
  -ms-transition:border 0.2s ease-in-out;
  -o-transition:border 0.2s ease-in-out;
            margin-left: -100px;    
  border:2px solid #EEE;
  border-radius:5px;
  background-color:#EEE;
  padding:10px;
 margin-left:-30px;
  margin-top:5px;
  font-size:18px;
  font-family:sans-serif;
  width:65%;
  outline:none;
}
.atag{
	margin-left:-20px;
}
input[type="submit"]{
  border:0;
  margin:0 auto;
  margin-top:15px;
  background:#CD3333;
  color:#FFF;
  font-size:18px;
  padding:10px;
  border-radius:5px;
  width:270px;
  outline:none;
  cursor:pointer;
}
input:focus{
  border:2px solid #cd3333;
}
.warning{
  padding:10px;
  margin:10px;
  text-align:center;
  color:#666;
  display:none;
}
</style>
 <script type="text/javascript" src="../resources/js/signup/signupform.js"></script>
<script type="text/JavaScript" src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script> <!-- 도로명 API -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<%
	
	String authNum = (String)session.getAttribute("authNum");  //인증번호
	String mem_name = (String)session.getAttribute("mem_name");   //새창열려도 기존 값 유지
	String mem_email = (String)session.getAttribute("mem_email");
	String mem_id = (String)session.getAttribute("mem_id");
	String mem_pw = (String)session.getAttribute("mem_pw");
	
	String emailnotused = request.getParameter("emailnotused");

%>	
<script type="text/javascript">

//////////이메일체크
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
	$("#mem_phone").focus();
	$("#emailchk").css("display", "none"); //이메일 인증버튼 안보이게 
	
	document.getElementById("InputauthNum").title = 'y';  //인증번호 입력태그의 title('n'->'y')로 변경

	}else{
		alert("인증메일에서 버튼클릭후 진행해주세요.");
	}	
}
//////////이메일체크

//처음 로드 시 값 유지시키기 위한 함수
$(function(){
	
	if("<%=mem_name%>"!="null"){

		$("#mem_id").val("<%=mem_id%>");
		$("#mem_pw").val("<%=mem_pw%>");
		$("#mem_name").val("<%=mem_name%>");
		$("#mem_email").val("<%=mem_email%>");
	}
	if("<%=emailnotused%>"=="false"){
		$("#eJung_Display").css("display", "block"); //이메일중복인지
	}else if("<%=emailnotused%>"=="true"){
		$("#eCheck_Display").css("display", "block"); //인증번호입력란 화면에 보이게 
		alert("이메일에서 인증번호를 확인해 주세요");
	}
	//자동으로 인증번호 입력되게 하는 조건문
	if("<%=authNum%>"!="null"){
		
		$("#eCheck_Display").show();
		$("#InputauthNum").val("<%=authNum%>");
	}
});

//아이디 중복체크 버튼 클릭 시
function idChk(){
	var doc = document.getElementsByName("mem_id")[0];
	
	if(doc.value.trim()==""||doc.value==null){
		alert("아이디를 입력해주세요");
	}else{
		open("../signup.do?command=idchk&mem_id="+doc.value,"",
			 "width=400,height=250");
	}
}

function submitinfo(){    //회원가입 버튼 클릭 시 유효성 검사
	var InputauthNum = document.getElementById("InputauthNum").title;  //인증번호 입력태그 "y"를 변수에 대입
	
	if($("#mem_id").val()==""){
		alert("아이디를 입력해 주세요");
		$(this).focus();
		return false;
	}else if($("#mem_pw").val()==""){
		alert("비밀번호를 입력해 주세요");
		$(this).focus();
		return false;
	}else if($("#mem_name").val()==""){
		alert("이름을 입력해 주세요");
		$(this).focus();
		return false;
	}else if(InputauthNum!="y"){   //'y'와 다르면 return false
		alert("이메일을 확인해 주세요");
		$(this).focus();
		return false;
	}else if($("#mem_phone").val()==""){
		alert("전화번호를 입력해 주세요");
		$(this).focus();
		return false;
	}else{

		$("form").submit();
	}
}

$(document).ready(function(){            //도로명주소 클릭시 함수 실행
	$("#zonecode").click(function(){

		openDaumZipAddress();
	
	});
	
});
function openDaumZipAddress() {   //클릭시 실행되는 함수
	
	new daum.Postcode({

		oncomplete:function(data) {

			jQuery("#postcode1").val(data.postcode1);

			jQuery("#postcode2").val(data.postcode2);

			jQuery("#zonecode").val(data.zonecode);

			jQuery("#address").val(data.address);

			jQuery("#address_etc").focus();

			console.log(data);

		}

	}).open();
	$("#detail01").show();
	$("#detail02").show();
}
</script>
</head>


<body>

<section>

<div id="loginform">
  <h2 style="margin-left:70px;">Signup</h2>
  <form action="../signup.do" method="post" style="margin-left: 13%;">
  <input type="hidden" name="command" value="signupres"/>
     
  	 <a class="atag">Email:</a><input type="email" value="" name="mem_email" id="mem_email" required="required"/>
  	 <!-- 이메일 인증버튼 -->
  	 <input type="button" id="emailchk" onclick="echeckFun();" value="이메일인증" style="background:black; width:200px; border-radius: 10px; color:white; font-weight:bold;  margin: -8% 0% 0% 62%;"/>
  	 <!-- 이메일 인증버튼 -->
  	 <!-- 1.이메일 인증 버튼 클릭시  -->
			<a id="eJung_Display" style="color:red; display:none;">이미 존재하는 이메일입니다</a>
			<div id="eCheck_Display" style="display:none;">
				<!-- 인증번호 -->
			
				<span>인증번호 입력:</span> <input type="text" id="InputauthNum" title="n" value="" style="width: 230px;"/>
				<input type="button" id="btn" onclick="authNumcheck();" value="확인" style="background:black; width:100px; border-radius: 10px; color:white; font-weight:bold; margin: -48px 0px 0px 210px;"/>
				<input type="button" id="ReSend" onclick="echeckFun();" value="재전송" style="background:black; width:100px; border-radius: 10px; color:white; font-weight:bold; margin: -48px 0px 0px 310px;"/> 
			
			</div>
				<!--  2. 이메일 인증 완료시 보일부분 -->
			<a id="emailconfirmmsg" style="color:blue; display:none; margin-left: -20px; font-size: 20px;font-weight: bold;">이메일 인증 완료</a>
			<!--  2. 이메일 인증 완료시 보일부분 끝     -->
			<!-- 1.이메일 인증 버튼 클릭시  끝   -->
			
  	
  	<input type="button" onclick="submitinfo();" value="SignUp" style="width: 60%;
    height: 5%; background:red; border-radius:10px; color:white; font-weight:bold; margin-top:1%; margin-left:5%;"/>
  
</div>
</form>
</section>

</body>
</html>