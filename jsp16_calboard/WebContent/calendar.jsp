<%@page import="com.cal.dto.CalDto"%>
<%@page import="java.util.List"%>
<%@page import="com.cal.dao.CalDao"%>
<%@page import="com.cal.dao.Util"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#calendar{
		border-collapse: collapse;
		border: 1px solid gray;
	}
	#calendar th{
		width: 80px;
		border: 1px solid gray;
	}
	#calendar td{
		width: 80px;
		height: 100px;
		border: 1px solid gray;
		text-align: left;
		vertical-align: top;
		position: relative;
	}
	a{
		text-decoration: none;
	}
	.clist > p {
		font-size: 5px;
		margin: 1px;
		background-color: skyblue;
	}
	.cpreview{
		position: absolute;
		top: -30px;
		left: 10px;
		background-color: skyblue;
		width: 40px;
		height: 40px;
		text-align: center;
		line-height: 40px;
		border-radius: 40px 40px 40px 1px;
	}
	
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function isTwo(n) {
		return (n.length<2)?"0"+n:n;
	}

	$(function() {
		$(".countview").hover(function() {
			var aCountView = $(this); 
			var year = $(".y").text().trim();
			var month = $(".m").text().trim();
			var cDate = aCountView.text().trim();
			var yyyyMMdd = year + isTwo(month) + isTwo(cDate);
			
			$.ajax({
				type:"POST",						// 전송 방식
				url:"calcountajax.do",				// 요청 경로
				data:"id=kh&yyyymmdd="+yyyyMMdd,	// 전송 파라미터
				dataType:"json",					// 받는 데이터의 타입
				async:false,						// 동기
				success:function(msg){
					var count = msg.count;
					aCountView.after("<div class='cpreview'>"+count+"</div>");
				},
				error:function(){
					alert("서버 통신 실패");
				}
			});
		
		}, function() {
			$(".cpreview").remove();	
		
		});	
	});	
</script>

<%
	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH)+1;
	
	String paramYear = request.getParameter("year");
	String paramMonth = request.getParameter("month");
	
	if(paramYear!=null){
		year = Integer.parseInt(paramYear);
	}
	if(paramMonth!=null){
		month = Integer.parseInt(paramMonth);
	}
	
	if(month<1){
		month=12;
		year--;
	}
	
	if(month>12){
		month=1;
		year++;
	}
	
	// 현재년도, 현재월, 해당 월의 1일로 셋팅
	//cal.set(Calendar.YEAR, year);
	//cal.set(Calendar.MONTH-1, month);
	//cal.set(Calendar.DATE, 1);
	cal.set(year,month-1,1);
	
	// 1일의 요일
	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		
	// 현재 월의 마지막 일
	int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	
	// 달력에 일정 표현
	CalDao dao = new CalDao();
	String yyyyMM = year + Util.isTwo(String.valueOf(month));
	List<CalDto> clist = dao.getCalViewList("kh", yyyyMM);
	
%>
</head>
<body>
	<table id="calendar">
		<caption>
			<a href="calendar.jsp?year=<%=year-1 %>&month=<%=month %>">◁</a>		
			<a href="calendar.jsp?year=<%=year%>&month=<%=month-1 %>">◀</a>
			
			<span class="y"><%=year %></span>년		
			<span class="m"><%=month %></span>월
				
			<a href="calendar.jsp?year=<%=year%>&month=<%=month+1 %>">▶</a>
			<a href="calendar.jsp?year=<%=year+1 %>&month=<%=month %>">▷</a>
		</caption>

	<tr>
		<th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>
	</tr>
	<tr>
<%
	for(int i=1; i<dayOfWeek; i++){
		out.print("<td>&nbsp;</td>");
	}
	for(int i=1; i<=lastDay; i++){
%>
	<td>
		<a class="countview" href="calendar.do?command=list&year=<%=year %>&month=<%=month %>&date=<%=i %>" 
			style="color:<%=Util.fontColor(i,dayOfWeek) %>"><%=i %></a>
		<a href="insertcalboard.jsp?year=<%=year%>&month=<%=month%>&date=<%=i%>&lastDay=<%=lastDay%>&">
			<img alt="일정추가" src="img/pen.png" style="width:10px; height:10px;"/>
		</a>
		<div class="clist">
			<%=Util.getCalView(i, clist) %>
		</div>
	</td>
<%
		if((dayOfWeek-1+i)%7==0){
			out.print("</tr><tr>");						
		}
	}
	for(int i=0; i<(7-(dayOfWeek-1+lastDay)%7)%7; i++){
		out.print("<td>&nbsp;</td>");
	}
%>
	</tr>
	</table>

</body>
</html>