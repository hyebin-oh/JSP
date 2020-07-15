<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.5.1.min.js"></script>
<script>
	$(document).ready(function(){
		$("#send").on("click", function(){
		
			$.getJSON("zipCheckPro.jsp",{"dong" : $("#dong").val()},
							function(data){//콜백함수								
								var htmlStr="<table>";
								$.each(data, function(key, val){
									htmlStr+="<tr>";
									htmlStr+="<td>"+ val.zipcode+"</td>";
									htmlStr+="<td>"+ val.sido+"</td>";
									htmlStr+="<td>"+ val.gugun+"</td>";
									htmlStr+="<td>"+ val.dong+"</td>";
									htmlStr+="<td>"+ val.bunji+"</td>";
									htmlStr+="</tr>";									
								})
								htmlStr+="</table>";
								$("#area").html(htmlStr);
							}
			);//getjson
		});//send
		
		$("#getSend").on("click", function(){
			$.get("zipCheckPro.jsp",{"dong" : $("#dong").val()},
					function(data){
						var htmlStr="<table>";
						d=$.parseJSON(data); //파싱
						for(var i=0 ; i<d.length;i++){
							htmlStr+="<tr>";
							htmlStr+="<td>"+ d[i].zipcode+"</td>";
							htmlStr+="<td>"+ d[i].sido+"</td>";
							htmlStr+="<td>"+ d[i].gugun+"</td>";
							htmlStr+="<td>"+ d[i].dong+"</td>";
							htmlStr+="<td>"+ d[i].bunji+"</td>";
							htmlStr+="</tr>";
						}
						htmlStr+="</table>";
						$("#area").html(htmlStr);
					}
			);//get
		});//getSend
		
		$("#area").on("click","tr",function(){
			var address = $("td:eq(1)",this).text()+" " + $("td:eq(2)",this).text()+" " + $("td:eq(3)",this).text()+" " + $("td:eq(4)",this).text(); 
			$(opener.document).find("#zipcode").val($("td:eq(0)",this).text());//this는 tr을 의미
			$(opener.document).find("#addr").val(address);
			self.close();
			
		});//area
		
	});//document
</script>
</head>
<body>
	<table>
		<tr>
			<td>동이름 입력<input type="text" name="dong" id="dong">
			<input type="button" value="검색" id="send">
			<input type="button" value="get검색" id="getSend">
			
			</td>
		</tr>
	</table>
<div id="area"></div>
</body>
</html>