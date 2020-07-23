<%@page import="com.member.MemberVO"%>
<%@page import="com.member.memberDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<%
	String userid = (String) session.getAttribute("userid");
	memberDAOImpl dao = memberDAOImpl.getInstace();
	MemberVO member = dao.memberView(userid);
%>
</head>

<body>
<div align="left">
<%=userid %>님 반갑습니다./ <a href="logout.jsp">로그아웃</a>
<a href="../board/list.jsp">게시판으로</a>
<h2>
회원정보변경 / <a href="userDelete.jsp">회원탈퇴</a>
</h2>
</div>
<form action="updatePro.jsp" method="post">
<input type="hidden" name="userid" value="<%=userid %>">
<table>
	<tr>
		<th>이름</th>
		<td><input type="text" name="name" id="name" value="<%=member.getName()%>"></td>
	</tr>
	<tr>
			<th>암호</th>
			<td><input type="password" name="pwd" id="pwd" disabled="disabled" value="<%=member.getPwd() %>"></td>
	</tr>
	<tr>
		<th>이메일</th>
		<td><input type="text" name="email" id="email" value="<%=member.getEmail() %>"></td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td><input type="tel" name="phone" id="phone" value="<%=member.getPhone() %>"></td>
	</tr>
	<tr>
		<th>등급</th>
		<td><input type="radio" name="admin" value="0" checked="checked">일반회원
				<input type="radio" name="admin" value="1">관리자</td>
		<script>
			if(<%=member.getAdmin()%>==0){
				$("input:radio[value='0']").prop("checked", true);
			}else{
				$("input:radio[value='1']").prop("checked", true);
			}
		</script>
	</tr>
	<tr>
		<td><input type="submit" value="수정" id="updateBtn">
				<input type="reset" value="취소"></td>
	</tr>
</table>
</form>
</body>
</html>