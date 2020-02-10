<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
	<title>JSP AJAX 실시간 익명 채팅 사이트</title>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script type="js/bootstrap.js"></script>
	<script type="text/javascript">
		var lastID = 0;
		function submitFunction() {
			var chatName = $('#chatName').val();
			var chatContent = $('#chatContent').val();
			$.ajax({
				type: "POST",
				url: "./chatSubmitServlet",
				data: {
					chatName: encodeURIComponent(chatName),
					chatContent: encodeURIComponent(chatContent)
				},
				success: function(result){
					if(result ==1){
						alert('전송에 성공했습니다.');
					} else if(result ==0){
						alert('이름과 내용을 정확히 입력하세요.'); 
					} else {
						alert('데이터베이스 오류가 발생했습니다.');
					}
				}
			});
			$('#chatContent').val('');
		}
		function chatListFunction(type) {
			$.ajax({
				type: "POST",
				url: "./chatListServlet",
				data: {
					listType: type,
				},
				success: function(data){
					if(data=="") return; 
					var parsed = JSON.parse(data);
					var result = parsed.result;
					for(var i=0; i<result.length; i++){
						addChat(result[i][0].value, result[i][1].value, result[i][2].value);
					}
					lastID = Number(parsed.last);
				}
			});
		}
		function addChat(chatName, chatContent, chatTime) {
			$('#chatList').append('<div class="row">'+
							'<div class="col-lg-12">'+
							'<div class="media">'+
							'<a class="pull-left" href="#">'+
							'<img class="media-object img-circle" src="images/icon.png" style="width: 50px;">'+
							'</a>'+
							'<div class="media-body">'+
							'<h4 class="media-heading">'+
							chatName +
							'<span class="small pull-right">' +
							chatTime +
							'</span>'+
							'</h4>'+
							'<p>'+
							chatContent +
							'</p>'+
							'</div>'+
							'</div>'+
							'</div>'+
							'</div>'+
							'<hr>');
			$('#chatList').scrollTop($('#chatList')[0].scrollHeight);
		}
		function getInfiniteChat() {
			setInterval(function() {
				chatListFunction(lastID);
			}, 1000);
		}
	</script>
</head>
<body>
	<div class="container">
		<div class="container bootstrap snippet">
		    <div class="row">
		        <div class="col-xs-12">
		            <div class="portlet portlet-default">
		                <div class="portlet-heading">
		                    <div class="portlet-title">
		                    	<h4><i class="fa fa-circle text-green"></i>실시간 채팅방</h4>
							</div>
							<div class="clearfix"></div>
						</div>	
						<div id="chat" class="panel-collapse collapse in">
							<div id="chatList" class="portlet-body chat-widget" style="overflow-y: auto; width: auto; height: 600px;">
							<!-- overflow:y 세로로 늘어날 수 있음 -->		
							</div>	 
								
								<!-- 입력부분 -->
								<div class="portlet-footer">
									<div class="row">
										<div class="form-group col-xs-4">
											<input style="height: 40px;" type="text" id="chatName"
												class="form-control" placeholder="이름" maxlength="20">
										</div>
									</div>
									<div class="row" style="height: 90px">
										<div class="form-group col-xs-10">
											<textarea rows="" cols="" style="height: 80px;" id="chatContent" 
											class="form-control" placeholder="메시지를 입력하세요." maxlength="100"></textarea>
										</div>
										<div class="form-group col-xs-2">
											<button type="button" class="btn btn-default pull-right" onclick="submitFunction();">전송</button>
											<div class="clearfix"></div>
										</div>
									</div>
								</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			chatListFunction('ten');
			getInfiniteChat();
		});
	</script>
</body>
</html>