<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<script>
function zipfinder(){
	window.open("zipAction.amy","","width=700 height=400");
}
</script>

</head>
<body>
<a href="listAction.amy">전체보기</a>
<form action="insertAction.amy" method="post" name="frm">
Mybatis 주소록등록하기<br>
이름 <input type ="text" name ="name" id ="name"><br>
우편번호 <input type="text" name="zipcode" id="zipcode">
<input type="button" value="검색" onclick="zipfinder()"><br>
주소 <input type="text" name="addr" id="addr"><br>
전화번호 <input type="text" name=tel id="tel"><br>

<input type="submit" value ="등록" id="btnIn">
<input type="reset" value ="취소">
</form>
</body>
</html>