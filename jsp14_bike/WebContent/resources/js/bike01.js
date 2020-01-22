
$(function() {
	getJsonTest();
});

function getJsonTest(){
	$.getJSON("resources/json/bike.json",function(data){ 
		// url에서 json받아서 callback 함수 실행
		$.each(data, function(key, val){
			// (collections, callback 함수)
			if(key=="DESCRIPTION"){
				$("table").attr("border","1");
				$("thead").append(
						"<tr>"+
							"<th>"+val.ADDR_GU+"</th>"+
							"<th>"+val.CONTENT_ID+"</th>"+
							"<th>"+val.CONTENT_NM+"</th>"+
							"<th>"+val.NEW_ADDR+"</th>"+
							"<th>"+val.CRADLE_COUNT+"</th>"+
							"<th>"+val.LONGITUDE+"</th>"+
							"<th>"+val.LATITUDE+"</th>"+
						"</tr>"	
				);
			} else if(key=="DATA"){
				var list = val;
				for(var i = 0; i < list.length; i++){
					var str = list[i];
					$("tbody").append(
							"<tr>"+
								"<td>"+str.addr_gu+"</td>"+
								"<td>"+str.content_id+"</td>"+
								"<td>"+str.content_nm+"</td>"+
								"<td>"+str.new_addr+"</td>"+
								"<td>"+str.cradle_count+"</td>"+
								"<td>"+str.longitude+"</td>"+
								"<td>"+str.latitude+"</td>"+
								"<input type='hidden' name='bike' value='"+
									str.addr_gu+"/"+
									str.content_id+"/"+
									str.content_nm+"/"+
									str.new_addr+"/"+
									str.cradle_count+"/"+
									str.longitude+"/"+
									str.latitude+"'>"+
							"</tr>"	
					);
				}
			}
		});
	})
}