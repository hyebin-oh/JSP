<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.5.1.min.js"></script>
<script>
	$(function(){
		$("#BtnIn").click(function(){
			if($("#name").val()==""){
				alert("이름을 입력하세요");
				return;
			}
			if($("#addr").val()==""){
				alert("주소를 입력하세요");
				return;
			}
			if($("#tel").val()==""){
				alert("전화번호를 입력하세요");
				return;
			}
			frm.submit();
		});
	});
</script>

<script>
	$(function(){
		$("#zipBtn").click(function(){
			window.open("zipCheck.jsp","","width=800 height=500");
		})//zipBtn
	});//fucntion
	
</script>

</head>
<body>
	<form action="insertPro.jsp" method="post" name="frm">
		<a href="list.jsp">전체보기</a>
		<h3>주소록 등록하기</h3>
		<table>
			<tr>
				<th>이름 </th>
				<td><input type="text" name="name" id="name"></td>
			</tr>
			<tr>
				<th>우편번호 </th>
				<td><input type="text" name="zipcode" size=7 id="zipcode">
						<input type="button" value="검색" id="zipBtn">
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="addr" size=50 id="addr"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="tel" id="tel"><td>
			</tr>
			<tr>
				<td><input type="submit" value="등록" id="BtnIn">
						<input type="reset" value=취소>
				</td>		
			</tr>
		</table>
	</form>
</body>
</html>