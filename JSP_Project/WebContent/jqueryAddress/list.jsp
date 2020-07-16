<%@page import="com.jqueryAddress.Address"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jqueryAddress.JAddressDAO"%>
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
		JAddressDAO dao = JAddressDAO.getInstance();
	ArrayList<Address> arr = dao.addrList();
	int count = dao.getCount();
%>
<script>
$(document).ready(function(){
	$("#searchBtn").click(function(){
		$.getJSON("searchPro.jsp",{"field" : $("#field").val(), "word" : $("#word").val()},
						function(data){//콜백함수
							var htmlStr="";
							$.each(data, function(key,val){
								htmlStr+="<tr>";
								htmlStr+="<td>"+val.num+"</td>";
								htmlStr+="<td>"+val.name+"</td>";
								htmlStr+="<td>"+val.tel+"</td>";
								htmlStr+="<td>"+val.addr+"</td>";
								htmlStr+="<td onclick=delFunc(" + val.num+")>삭제</td>";
								htmlStr+="</td>";
							}) //each
							$("table tbody").html(htmlStr);
						}//function
		)//getJSON
	});//searchBtn
});//document

function delFunc(no){
	if(confirm("정말 삭제할까요?")){
		location.href = "deletePro.jsp?num="+no;
	}
}
</script>

</head>
<body>
<div class="divCss">
	주소록 갯수: <%=count%><br>
	<a href="insert.jsp">추가하기</a> /
	<a href="list.jsp">전체보기</a>
</div>
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
				<td><a href="detail.jsp?num=<%=arr.get(i).getNum() %>"><%=arr.get(i).getName()%></a></td>
				<td><%=arr.get(i).getTel() %></td>
				<td><%=arr.get(i).getAddr() %></td>
				<td onclick="delFunc(<%=arr.get(i).getNum() %>)">삭제</td>
		<%
			}
		%>
	</tbody>
</table>
<form action="list.jsp" name="searchFrm" id="searchFrm">
<select name="field" id="field">
	<option value="name">이름</option>
	<option value="tel">전화번호</option>
</select>
<input type="text" name="word" id="word">
<input type="button" value="검색" class="btn btn-primary" id="searchBtn" >
</form>
</body>
</html>