<%@page import="com.address.Address"%>
<%@page import="com.address.AddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<%
	request.setCharacterEncoding("utf-8");
	int num= Integer.parseInt(request.getParameter("num"));
	AddressDAO dao = AddressDAO.getInstance();
	Address address = dao.addrDetail(num);
%>
<script>
$(document).ready(function(){
	$("#deleteBtn").click(function(){
		if(confirm("정말 삭제할까요?")){
			$(location).attr("href","deletePro.jsp?num=<%=num%>");
		}
	});//deleteBtn
});//document

</script>
</head>
<body>
<form action="updatePro.jsp" method="post">
<input type="hidden" name="num" value=<%=num %>>
	<h3>주소록 수정하기</h3>
	이름 <input type="text" name="name" id="name" value=<%=address.getName() %>><br>
	우편번호 <input type="text" name="zipcode" size=7 id="zipcode" value=<%=address.getZipcode() %>>
			  <input type="button" value="검색" id="zipBtn"><br>
  	주소 <input type="text" name="addr" size=50 id="addr" value=<%=address.getAddr() %>><br>
  	전화번호 <input type="text" name="tel" id="tel" value=<%=address.getTel() %>><br>
	<input type="button" value="수정" id="updateBtn">
	<input type="button" value="삭제" id="deleteBtn">
	<input type="reset" value ="취소">
</form>	
</body>
</html>