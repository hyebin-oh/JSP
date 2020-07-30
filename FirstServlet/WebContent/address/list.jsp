<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="right">
	<a href="insert.do">회원등록</a> /주소록 갯수 : ${count }
</div>
<table>
	<thead>
		<td>번호</td>
		<td>이름</td>
		<td>주소</td>
		<td>전화번호</td>
	</thead>
	<tbody>
		<c:forEach items="${listArr}" var="address">
		<tr>
			<td>${address.num }</td>
			<td><a href="detail.do?num=${address.num }">${address.name }</a></td>
			<td>${address.addr }</td>
			<td>${address.tel }</td>			
		</tr>
		</c:forEach>
	</tbody>
</table>

</body>
</html>