<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
		$("#commentBtn").on("click", function(){
			$.ajax({
				type: "get",
				url: "commentInsert",
				data:{"msg": $("#msg").val(), "num":${board.num}},
				success: function(d){
					
				},
				error: function(e){
					alert("error: "+e)
				} 
					
			})//ajax
		})//commentBtn
	
})//document

</script>
</head>
<body>
	<table>
	<tr>
			<td>글번호<td>
			<td>${board.num }<td>
		</tr>
		<tr>
			<td>글쓴이<td>
			<td>${board.writer }<td>
		</tr>
		<tr>
			<td>제목<td>
			<td>${board.subject }<td>
		</tr>
		<tr>
			<td >내용<td>
			<td>${board.content }<td>
		</tr>
		<tr>
			<td >조회수<td>
			<td>${board.readcount }<td>
		</tr>
		<tr>
			<td >작성일<td>
			<td>${board.reg_date }<td>
		</tr>
		
		<tr>
			<td colspan="2">
			<input type="submit" value="수정">
			<input type="reset" value="취소">
			<td>
		</tr>
	</table>
	<br><br>
	
	<br>
	<div align="center">
		<textarea row="5" cols="50" id="msg"></textarea>
		<input type="button" value="write Comment" id="commentBtn">
	</div>



</body>
</html>