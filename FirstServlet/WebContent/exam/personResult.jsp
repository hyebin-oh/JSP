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
이름: ${p.name}<br>
아이디: ${p.id}<br>
패스워드: ${p.password}<br>
성별: ${p.gender}<br>
메일수신: 
<c:forEach items="${p.notice}" var="mail">
	${mail }
</c:forEach><br>
직업: ${p.job}
</body>
</html>