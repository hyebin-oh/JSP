<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="updateAction.amy" method="post">
<input type="hidden" name="num" value=${address.num }>
<h3>주소록 수정하기</h3>
이름 <input type="text" name="name" value=${address.name }><br>
우편번호 <input type="text" name="zipcode" value=${address.zipcode }>
<input type="button" value="검색" ><br>
주소 <input type="text" name="addr" value=${address.addr }><br>
전화번호 <input type="text" name="tel" value=${address.tel }><br>
<input type="submit" value="수정">
<input type="button" value="삭제" onclick="location.href='deleteAction.amy?num=${address.num}'">
<input type="reset" value="취소">
</form>
</body>
</html>