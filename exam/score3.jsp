<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function test(){
	if(document.getElementById("name").value==""){
		alert("이름을 입력하세요.");
		return;
	}
	if(document.getElementById("kor").value==""|| isNaN(document.getElementById("kor").value)){//isNaN은 숫자가 아닐때를 의미
		alert("국어점수를 입력하세요");
		return;
	}
	if(document.getElementById("eng").value==""){
		alert("영어점수를 입력하세요");
		return;
	}
	if(document.getElementById("math").value==""){
		alert("수학점수를 입력하세요");
		return;
	}
	frm.submit();
	//frm이 id일 경우 : documment.getElementById("frm").submit();
}


</script>
</head>
<body>
<form action="scoreResult3.jsp" name = "frm"> 
이름 : <input type="text" name="name" id="name"><br>
국어: <input type="text" name="kor" id="kor"><br>
영어: <input type="text" name="eng" id="eng"><br>
수학: <input type="text" name="math" id="math"><br>
<input type="button" value="전송" onclick="test()">
</form>
</body>
</html>