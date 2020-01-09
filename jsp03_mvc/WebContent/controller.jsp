<%@page import="com.mvc.dao.MVCDaoImpl"%>
<%@page import="com.mvc.dao.MVCDao"%>
<%@page import="com.mvc.dto.MVCDto"%>
<%@page import="java.util.List"%>
<%@page import="com.mvc.biz.MVCBizImpl"%>
<%@page import="com.mvc.biz.MVCBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String command = request.getParameter("command");
	System.out.println("<"+command+">");
	
	MVCBiz biz = new MVCBizImpl();
		//1. 받을 데이터가 있는지?
		//2. db에서 가져올 데이터가 있는지?
		//3. 어디로 갈건지?
	if(command.equals("list")){ //------- 메인 글 목록 -------
		List<MVCDto> list = biz.selectList();
		request.setAttribute("list", list);
		pageContext.forward("boardlist.jsp");
	} else if (command.equals("detail")){	//------- 내용 보기 -------
		int seq = Integer.parseInt(request.getParameter("seq"));
		MVCDto dto = biz.selectOne(seq);
		request.setAttribute("dto", dto);
		pageContext.forward("detail.jsp");
	} else if (command.equals("update")){	//------- 수정 -------
		int seq = Integer.parseInt(request.getParameter("seq"));
		MVCDto dto = biz.selectOne(seq);
		request.setAttribute("dto", dto);
		pageContext.forward("update.jsp");
	} else if (command.equals("updateres")){	//------- 수정 결과 -------
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int seq = Integer.parseInt(request.getParameter("seq"));
		MVCDto dto = new MVCDto();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setSeq(seq);
		int res = biz.update(dto);
		if(res>0){
%>
		<script type="text/javascript">
			alert("수정 완료");
			location.href="controller.jsp?command=detail&seq=<%=seq%>";
		</script>	
<% 
		} else {
%>			
		<script type="text/javascript">
			alert("수정 실패");
			location.href="controller.jsp?command=update&seq=<%=seq%>";
		</script>
<%
		}
	} else if (command.equals("writeform")){ //------- 글쓰기 -------
		response.sendRedirect("boardwrite.jsp");
	} else if (command.equals("writeres")){ //------- 글쓰기 결과 -------
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		MVCDto dto = new MVCDto();
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		int res = biz.insert(dto);
		if(res>0){
%>
		<script type="text/javascript">
			alert("새로운 글 등록 완료");
			location.href="controller.jsp?command=list";
		</script>	
<% 
		} else {
%>			
		<script type="text/javascript">
			alert("글 등록 실패");
			location.href="controller.jsp?command=writeform";
		</script>
<%
		}
	} else if(command.equals("muldel")) { //------- 삭제 (submit) -------
		String [] seqs = request.getParameterValues("chk");
		boolean res = biz.MDelte(seqs);
		if(res){
%>			
		<script type="text/javascript">
			alert("삭제 완료");
			location.href="controller.jsp?command=list";
		</script>
<%
		} else{
%>			
		<script type="text/javascript">
			alert("삭제 실패");
			location.href="controller.jsp?command=list";
		</script>
<%
		}
	}
%>			
	<h1>잘못왔다(command 확인)!</h1> 
</body>
</html>