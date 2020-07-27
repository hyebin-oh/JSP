<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	request.setCharacterEncoding("utf-8");
	String name=request.getParameter("name");
	String passwd=request.getParameter("passwd");
	String id=request.getParameter("id");
%>
</head>
<body>
<sql:setDataSource dataSource="jdbc/member" var="dataSource" scope="application" />
<sql:update dataSource="${dataSource }">
	insert into jsp_member values(?,?,?)
	<sql:param value="<%=id %>"/>
	<sql:param value="<%=passwd %>"/>
	<sql:param value="<%=id %>"/>
</sql:update>
<c:import url="sqlList.jsp"></c:import>

</body>
</html>