<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
$(document).ready(function(){
	$("#send").click(function(){
		if($("#dong").val()==""){
			alert("동이름을 입력하세요");
			$("#dong").focus();
			return;
		}
		$.post("zip.do",{"dong": $("#dong").val()}, 
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
		);//post
	})//send
})//document

</script>
</head>
<body>
	<table>
		<tr>
			<td>동이름 입력<input type="text" name="dong" id="dong">
			<input type="button" value="검색" id="send">			
			</td>
		</tr>
	</table>
	<div id="area"></div>
</body>
</html>