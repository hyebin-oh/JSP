<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="login.gb" method="post">
  <h3>로그인</h3>
	<table>
		<tr>
			<td>ID</td>
			<td><input type="text" name="userid" id="userid"></td>
		</tr>
		<tr>
			<td>PW</td>
			<td><input type="password" name="pwd" id="pwd"> </td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="submit" value="로그인">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<span style="color:red">${errMsg }</span>
			</td>
		</tr>
	</table>
</form>
</body>
</html>