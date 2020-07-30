<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="update.do" method="post">

<input type="hidden" name="num" value=${dto.num }>
<h3>주소록 수정하기</h3>
이름 <input type="text" name="name" value=${dto.name }><br>
우편번호 <input type="text" name="zipcode" value=${dto.zipcode }>
<input type="button" value="검색" ><br>
주소 <input type="text" name="addr" value=${dto.addr }><br>
전화번호 <input type="text" name="tel" value=${dto.tel }><br>
<input type="submit" value="수정">
<input type="button" value="삭제" onclick="location.href='delete.do?num=${dto.num}'">
<input type="reset" value="취소">

</form>
</body>
</html>