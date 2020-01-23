$(function() {
	parseJsonTest();
});
/*
 * JSON.parse() : string -> json (json 형태를 지켜야한다.)
 * JSON.stringify : json -> string 
 */
function parseJsonTest() {
	$.getJSON("resources/json/bike.json", function(data) {
		$.ajax({
			url:"bike.do?command=second_db",
			method:"post",
			data:{"obj":JSON.stringify(data)}, //* 매우 중요함
			success:function(msg){
				if(msg==1163){
					$.each(data, function(key, val){
						if(key=='DESCRIPTION'){
							$("table").attr("border","1");
							var $tr = $("<tr>");
							for(var i in val){ // in: 하나하나 (키)가져옴
								$tr.append($("<th>").html(val[i])); 
								// val[i]: 키의 i번째 value를 가져옴
							}
							$("thead").append($tr);
							
//							$("thead").append(
//								"<tr>"+
//									"<th>"+val.ADDR_GU+"</th>"+
//									"<th>"+val.CONTENT_ID+"</th>"+
//									"<th>"+val.CONTENT_NM+"</th>"+
//									"<th>"+val.NEW_ADDR+"</th>"+
//									"<th>"+val.CRADLE_COUNT+"</th>"+
//									"<th>"+val.LONGITUDE+"</th>"+
//									"<th>"+val.LATITUDE+"</th>"+
//								"</tr>"	
//							);
						} else if (key=="DATA"){
							
							for(var i=0; i<val.length; i++){
								var $tr = $("<tr>");
								for(var j in val[i]){ 
									$tr.append($("<td>").html(val[i][j]));
								}
							}
							
//							var list = val;
//							for(var i=0; i<list.length; i++){
//								var str = list[i];
//								$("tbody").append(
//									"<tr>"+
//										"<td>"+str.addr_gu+"</td>"+
//										"<td>"+str.content_id+"</td>"+
//										"<td>"+str.content_nm+"</td>"+
//										"<td>"+str.new_addr+"</td>"+
//										"<td>"+str.cradle_count+"</td>"+
//										"<td>"+str.longitude+"</td>"+
//										"<td>"+str.latitude+"</td>"+
//									"</tr>"
//								);
//							}
						}
					});
		
				} else {
					alert("저장 실패");
				}
			},
			error:function(){
				alert("통신 실패");
			}
		});
	});
}