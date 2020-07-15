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
<script src="../js/jquery-3.5.1.min.js"></script>
<%
	request.setCharacterEncoding("utf-8");
	String field = "";
	String word = "";
	
	AddressDAO dao = AddressDAO.getInstance();
	ArrayList<Address> arr = dao.addrList(field, word);
%>
<script>
function delFunc(no){
	if(confirm("정말 삭제할까요?")){
		location.href = "deletePro.jsp?num="+no;
	}
}
</script>
</head>
<body>
<table>
	<thead>
		<tr>
			<td>번호</td>
			<td>이름</td>
			<td>전화번호</td>
			<td>주소</td>
			<td>삭제</td>
		</tr>
	</thead>
	<tbody>
		<%
			for(int i=0 ; i<arr.size(); i++){

		%>
			<tr>
				<td><%=arr.get(i).getNum() %></td>
				<td><a href="detail.jsp?num=<%=arr.get(i).getNum() %>"><%=arr.get(i).getName() %></a></td>
				<td><%=arr.get(i).getTel() %></td>
				<td><%=arr.get(i).getAddr() %></td>
				<td onclick="delFunc(<%=arr.get(i).getNum() %>)">삭제</td>
		<%
			}
		%>
	</tbody>
</table>

</body>
</html>