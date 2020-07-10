<%@page import="com.address.Address"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.address.AddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div.divCss{
	text-align: right;
	background-color: darkgray;
	}
	a:hover{text-decoration: none;}
	a:link{text-decoration: none;}
	a:visited {text-decoration: none;}
</style>
<%
	AddressDAO dao = AddressDAO.getInstance();
	ArrayList<Address> arr = dao.addrList();
	
%>
</head>
<body>
<div class="divCss">
<a href="insert.jsp">추가하기</a>
</div>
<table>
	<thead>
		<tr>
			<td>번호</td>
			<td>이름</td>
			<td>전화번호</td>
			<td>주소</td>
		</tr>
	</thead>
	<tbody>
<%
	for(int i=0; i<arr.size() ; i++){
%>
		<tr>
			<td><%=arr.get(i).getNum()%></td>
			<td><a href="detail.jsp?num=<%=arr.get(i).getNum()%>"><%=arr.get(i).getName() %></a></td>
			<td><%=arr.get(i).getTel() %></td>
			<td><%=arr.get(i).getAddr() %></td>
		</tr>		
<%
	}
%>
	
	</tbody>



</table>
</body>
</html>