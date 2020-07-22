<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src=member.js></script>
</head>
<body>
<div class="divCSS">
<a href="memberList.jsp" >전체보기</a>
</div>
<h2>회원가입</h2>
<p>'*'표시 항목은 필수입력 항목입니다.</p>
<form action = "memberPro.jsp" id="frm" method="post">
<input type="hidden" name="uid" id="uid">
	<table>
		<tr>
			<th>이름</th>
			<th><input type="text" name="name" id="name">*</th>
		</tr>
		<tr>
			<th>아이디</th>
			<td><input type="text" name="userid" id="userid" disabled="disabled">*</td>
			<td><input type="button" value="중복체크" id="idBtn"></td>
		</tr>
		<tr>
			<th>암호</th>
			<td><input type="password" name="pwd" id="pwd">*</td>
		</tr>
		<tr>
			<th>암호 확인</th>
			<td><input type="password" name="pwd_check" id="pwd_check">*</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" name="email" id="email"></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type="tel" name="phone" id="phone"></td>
		</tr>
		<tr>
			<th>등급</th>
			<td><input type="radio" name="admin" value="0" checked="checked">일반회원
					<input type="radio" name="admin" value="1">관리자</td>
		</tr>
			<th></th>
			<td><input type="button" value="확인" id="send">
					<input type="reset" value="취소"></td>
	</table>
</form>
</body>
</html>