<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table{
	with: 600px;
	height: 100px;
}

table,th,td{
	border: 1px solid black;
}
</style>
</head>
<body>
<form action="tableResult.jsp">
	<table>
		<tr>
			<th>성명</th>
			<td><input type = "text" name = "name"></td>
			<th>성별</th>
			<td><input type = "radio" name = "gender" value = "man" checked> 남자
			<input type = "radio" name = "gender" value = "man"> 여자
			</td>
		</tr>
		
		<tr>
			<th>생년월일</th>			
			<td colspan = 3><input type = "text" name = ""></td>
		
		</tr>
		
		
		<tr>
			<th>주소</th>
			<td colspan = 3><input type = "text" name = "addr"></td>
		
		</tr>
		
		<tr>
			<th></th>
			<td colspan = 3><input type = "text" name = ""></td>
		
		</tr>
		
		<tr>
			<th></th>
			<td colspan = 3><input type = "text" name = ""></td>
		
		</tr>
		
		<tr>
			<th></th>
			<td colspan = 3><input type = "text" name = ""></td>
		
		</tr>
		
	
	
	</table>
	

</body>



</html>