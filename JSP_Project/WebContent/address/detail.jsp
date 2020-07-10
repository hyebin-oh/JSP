<%@page import="com.address.Address"%>
<%@page import="com.address.AddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	request.setCharacterEncoding("utf-8");
	int num= Integer.parseInt(request.getParameter("num"));
	AddressDAO dao = AddressDAO.getInstance();
	Address address = dao.addrDetail(num);	
%>
</head>
<body>
<form action="updatePro.jsp" method="post">
<input type="hidden" name="num" value=<%=num %>>
주소록 수정하 기<br>
이름 <input type ="text" name ="name" value=<%=address.getName() %>><br>
우편번호 <input type="text" name="zipcode" id="zipcode" value=<%=address.getZipcode() %>>
<input type="button" value="검색" id="btnSearch"><br>
주소 <input type="text" name="addr" id="addr"value=<%=address.getAddr() %>><br>
전화번호 <input type="text" name=tel id="tel"value=<%=address.getTel() %>><br>

<input type="submit" value="수정">
<input type="button" value ="삭제">
<input type="reset" value ="취소">


</form>
</body>
</html>