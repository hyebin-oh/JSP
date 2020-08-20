<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<script>
	$(document).ready(function(){
		$("#btnSearch").click(function(){
			$.getJSON("searchAction.amy",
						{"field":$("#field").val(), "word":$("#word").val()},
						function(data){
							
							//alert(data);
							
							$("#count").html("총 게시물 수 :" + data.count);
							
							var htmlStr = "";						
							$.each(data.arr, function(key,val){
								htmlStr+= "<tr>";
								htmlStr+="<td>순서</td>"
								htmlStr+="<td>"+val.num+"</td>";
								htmlStr+="<td>"+val.name+"</td>";
								htmlStr+="<td>"+val.addr+"</td>";
								htmlStr+="<td>"+val.tel+"</td>";		
								htmlStr+="</tr>";
							})							
							$("table tbody").html(htmlStr);
							
						}					
			)//getjson
		})//btnsearch
	
		
	})//document
	
	function fdelete(num){
		$.getJSON("deleteAjaxAction.amy",
						{"num":num},
						function(data){
							
							$("#count").html("총 :" + data.count);
							
							var htmlStr = "";						
							$.each(data.arr, function(key,val){
								htmlStr+= "<tr>";
								htmlStr+="<td>순서</td>"
								htmlStr+="<td>"+val.num+"</td>";
								htmlStr+="<td>"+val.name+"</td>";
								htmlStr+="<td>"+val.addr+"</td>";
								htmlStr+="<td>"+val.tel+"</td>";	
								htmlStr+="<td onclick='fdelete(${address.num}')>"+삭제+"</td>";
								htmlStr+="</tr>";
							})							
							$("table tbody").html(htmlStr);
						}					
		)//getjson
	}
	
	

</script>

</head>
<body>
<div align="right">
	<a href="insert.jsp">회원등록</a>
</div>
<h3>주소록 </h3>
<div id="count">총: ${count }개</div>
<table>
	<thead>
		<td>순서</td>
		<td>번호</td>
		<td>이름</td>
		<td>주소</td>
		<td>전화번호</td>
		<td>삭제</td>
	</thead>
	<tbody>
		<c:forEach items="${arr}" var="address" varStatus="st">
		<tr>
			<td>${count - st.index }
			<td>${address.num }</td>
			<td><a href="viewAction.amy?num=${address.num }">${address.name }</a></td>
			<td>${address.addr }</td>
			<td>${address.tel }</td>	
			<td onclick="fdelete(${address.num})">삭제</td>		
		</tr>
		</c:forEach>
	</tbody>
</table>

<br>
<div align="center">
	<form name="search" id="search">
		<select name="field" id="field">
			<option value="name">이름</option>
			<option value="tel">전화번호</option>
		</select>
		<input type="text" name="word" id="word">
		<input type="button" value="찾기" id="btnSearch">	
	</form>
</div>

</body>
</html>