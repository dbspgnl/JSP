function load() {
	var url = getParameterValues();
	// var 입력을 안하면 변수의 호이스팅이 이뤄진다.
	httpRequest=new XMLHttpRequest();			// 서버와 (비)통신
	httpRequest.onreadystatechange=callback;	// 처리할 함수
	httpRequest.open("GET",url,true);			// true : 비동기 / false 동기
	httpRequest.send();							// "GET" : send() / "POST" : send(String)
	//* httpRequest.onreadystatechange=callback; callback()이면 바로 실행된다.
	// 위에 뜻은 onreadystatechange가 callback 일때 실행된다는 의미.
}
/*
 	XMLHttpRequest : 서버와 통신을 도와주는 객체. http를 통한 데이터 송수신 지원.
 					(javascript object) 자바스크립트 객체
 	
-- readyState
	0: uninitialized -  실행(load)되지 않음
	1: loading - 로드 중
	2: loaded - 로드 됨
	3: interactive - 통신 됨
	4: complete - 통신 완료
	
-- status
	200: 성공
	400: bad request (잘못된 요청)
	401: unauthorized (권한 없다)
	403: forbidden (인증)
	404: not found (찾을 수 없다)
	500: internal server error (null point error)

--	encodeURIComponent() : 모든 문자 인코딩
	encodeURI() : 주소줄에 사용되는 특수문자는 제외하고 인코딩
	//결국 한글 잘 입력받기 위함
	
 */

function callback() {
	alert("readyState : "+httpRequest.readyState);
	
	if(httpRequest.readyState==4){
		alert("status : "+httpRequest.status);
		if(httpRequest.status==200){
			var obj = JSON.parse(httpRequest.responseText);
			
			document.getElementById("result").innerHTML=decodeURIComponent(obj.name)
			+ "님의 총점은 " + obj.sum + "이고, 평균은 " + obj.avg+"입니다.";
		} else {
			alert("통신 실패");
		}
	}
}

function getParameterValues() {
	var name="name="+encodeURIComponent(document.getElementById("name").value);
	var kor="kor="+document.getElementById("kor").value;
	var eng="eng="+document.getElementById("eng").value;
	var math="math="+document.getElementById("math").value;
	
	var url = "score.cal?"+name+"&"+kor+"&"+eng+"&"+math;
	console.log(url);
	
	return url;
}