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
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script>
function searchCheck(){
	if($("#word").val()==""){
		alert("검색어를 입력하세요");
		$("#word").focus();
		return;
	}
	$("#searchFrm").submit();
}
 function delFunc(no){
	 if(confirm("정말 삭제할까요?")){
		 location.href="deletePro.jsp?num="+no;
	 }
 }
</script>
<%
	request.setCharacterEncoding("utf-8");
	String word = "";
	String field = "";
	if(request.getParameter("word")!=null){
		field = request.getParameter("field");
		word = request.getParameter("word");
	}
	AddressDAO dao = AddressDAO.getInstance();
	ArrayList<Address> arr = dao.addrList(field, word);
	int count = dao.getCount(field, word);
%>

<style>

	div.divCss{
	text-align: right;
	background-color: darkgray;
	padding-right:20px;
	}
	a:hover{text-decoration: none;}
	a:link{text-decoration: none;}
	a:visited {text-decoration: none;}
</style>
</head>
<body>
<div class="divCss">
	주소록 갯수: <%=count%><br>
	<a href="insert.jsp">추가하기</a> /
	<a href="list.jsp">전체보기</a>
</div>
<table class="table table-striped">
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
	for(int i=0; i<arr.size() ; i++){
%>
		<tr>
			<td><%=arr.get(i).getNum()%></td>
			<td><a href="detail.jsp?num=<%=arr.get(i).getNum()%>"><%=arr.get(i).getName() %></a></td>
			<td><%=arr.get(i).getTel() %></td>
			<td><%=arr.get(i).getAddr() %></td>
			<td onclick="delFunc(<%=arr.get(i).getNum()%>)">삭제</td>
		</tr>		
<%
	}
%>
	</tbody>
</table>
<form action="list.jsp" name="searchFrm" id="searchFrm">
<select name="field">
	<option value="name">이름</option>
	<option value="tel">전화번호</option>
</select>
<input type="text" name="word" id="word">
<input type="button" value="검색" class="btn btn-primary" onclick="searchCheck()">
</form>
</body>
</html>