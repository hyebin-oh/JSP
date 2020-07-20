<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
	$(function(){
		$("#loginBtn").click(function(){
			if($("#userid").val()==""){
				alert("아이디를 입력하세요.");
				$("#userid").focus();
				return;
			}
			
			if($("#pwd").val()==""){
				alert("비밀번호를 입력하세요.");
				$("#pwd").focus();
				return;
			}
			
			$.ajax({
				type : "post",
				url : "loginPro.jsp",
				data : {"userid" : $("#userid").val(), "pwd" : $("#pwd").val()},
				success : function(value){
					//alert(value.trim());
					if(value.trim()==-1){
						alert("회원이 아닙니다. 회원가입하세요");
					}else if(value.trim()==0){
						alert("일반회원 로그인");
						$(location).attr("href","memberView.jsp");
					}else if(value.trim()==1){
						alert("관리자 로그인");
						location.href="memberList.jsp";
					}else if(value.trim()==2){
						alert("비밀번호를 확인하세요");
					}
				},
				error : function(e){
					alert("error : "+e)
				}
				
			})//ajax
		})//loginbtn
	})
</script>
</head>
<body>
<h2>로그인</h2>
<form>
<table>
	<tr>
		<th>아이디</th>
		<td><input type="text" id="userid">
	</tr>
	<tr>
		<th>암호</th>
		<td><input type="text" id="pwd"></td>
	</tr>
	<tr colspan="2" align="center">
		<td><input type="button" value="로그인" id="loginBtn"></td>
		<td>	<input type="reset" value="취소" id="cancel">
				<input type="button" value="회원가입" onclick="location.href='memberForm.jsp'"></td>
	</tr>
</table>
</form>
</body>
</html>