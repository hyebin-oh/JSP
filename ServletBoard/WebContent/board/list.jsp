<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	getData(1,"","");
	
	$("#btnSearch").on("click",function(){//검색버튼 클릭
		getData(1,$("#field").val(),$("#word").val());
	})
	
})//document

//페이지
function getData(pageNum, field, word){
	$.get("list",
		  {"pageNum":pageNum, "field":field, "word":word}, 
		  function(d){
			 
		    $("#result").html(d);
	})
}

</script>

<body>

<div id="result"></div>
<div align="center">
	<form name ="search" id="search">
		<select name="field" id="field">
			<option value="writer">이름</option>
			<option value="content">내용</option>
		</select>
		<input type="text" name="word" id="word">
		<input type="button" value="찾기" id="btnSearch">
	</form>
</div>

</body>




