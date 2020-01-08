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
	if(command.equals("list")){
		//1.x //2.리스트를 가져옮
		//3.boardlist.jsp로 간다.
		List<MVCDto> list = biz.selectList();
		request.setAttribute("list", list);
		pageContext.forward("boardlist.jsp");
	} else if (command.equals("detail")){
		int seq = Integer.parseInt(request.getParameter("seq"));
		MVCDto dto = biz.selectOne(seq);
		request.setAttribute("dto", dto);
		pageContext.forward("detail.jsp");
	} else if (command.equals("update")){
		int seq = Integer.parseInt(request.getParameter("seq"));
		MVCDto dto = biz.selectOne(seq);
		int res = biz.update(dto);
		request.setAttribute("dto", dto);
		pageContext.forward("update.jsp");
	} else if (command.equals("updateres")){	
		int seq = Integer.parseInt(request.getParameter("seq"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		MVCDto dto = new MVCDto();
		dto.setSeq(seq);
		dto.setTitle(title);
		dto.setContent(content);
		int res = biz.update(dto);
		if(res>0){
%>
		<script type="text/javascript">
			alert("수정 완료");
			location.href="controller.jsp?command=list";
		</script>	
<% 
		} else {
%>			
		<script type="text/javascript">
			alert("수정 실패");
			location.href="controller.jsp?command=update&seq=<%=dto.getSeq()%>";
		</script>
<%
		}
	} else if (command.equals("writeform")){
		//1.x 2.x 3.boardwrite.jsp
		response.sendRedirect("boardwrite.jsp");
	} else if (command.equals("writeres")){
		//1.
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		MVCDto dto = new MVCDto();
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		//2.
		int res = biz.insert(dto);
		//3.
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
	} else if(command.equals("muldel")) {
		//1.chk
		String [] seqs = request.getParameterValues("chk");
		//2.
		boolean res = biz.MDelte(seqs);
		//3.
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