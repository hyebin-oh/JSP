<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function(){	
	$("#btnSearch").on("click", function(){//검색버튼 클릭
		getData(1,$("#field").val(),$("#word").val());
	})//search
	
})//document

function getData(pageNum, field, word){
	$.getJSON("search",{"pageNum": pageNum, "field":field, "word": word},
			function(data){
				var htmlStr="";
				$.each(data.searchArr,function(key, val){					
				
				htmlStr+="<tr>";
				htmlStr+="<td></td>"
				htmlStr+="<td>"+val.num+"</td>";
				htmlStr+="<td>"+val.writer+"</td>"					
				htmlStr+="<td>"+val.subject+"</td>"
				htmlStr+="<td>"+val.content+"</td>"
				htmlStr+="<td>"+val.reg_date+"</td>"
				htmlStr+="<td>"+val.readcount+"</td>"							
				htmlStr+="</tr>";
				});
				$("table tbody").html(htmlStr);		
				$("#spnCount").html(text.puCount.puCount);
	})//get
}//function



</script>


<div align="center">
<h1>리스트(게시글 수: <span id="spnCount">${count }</span>)</h1>
<table>
	<thead>
		<tr>
			<td>No</td>
			<td>작성순서</td>
			<td>작성자</td>
			<td>제목</td>
			<td>내용</td>
			<td>작성일</td>
			<td>조회</td>
		</tr>
	</thead>
	<tbody>
	  <c:forEach items="${arr }" var="board" varStatus="st">
		<tr>
			<td>${rowNo-st.index }</td>
			<td>${board.num}</td>
			<td>${board.writer }</td>
			<td>${board.subject }</td>
			<td>${board.content }</td>
			<td>${board.reg_date }</td>
			<td>${board.readcount }</td>
		</tr>
	  </c:forEach>
	</tbody>	
</table>
</div>

<div id="view"></div>

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

<div align="center" id="page">
	<!-- 이전 -->
	<c:if test="${pu.startPage>pu.pageBlock }">
		<a href="javascript:getData(${pu.startPage-pu.pageBlock }, '${pu.field}','${pu.word })">[이전]</a>
	</c:if>
	
	<!-- 페이지 출력 -->
	<c:forEach begin="${pu.startPage }" end="${pu.endPage }" var="p">
		<!-- 현재 페이지 -->
		<c:if test="${p==pu.currentPage }">
			<c:out value="${p }"></c:out>
		</c:if>
		<!-- 현재 페이지 아니면 링크 -->
		<c:if test="${p!=pu.currentPage }">
			<a href="javascript:getData('${p }', '${pu.field }','${pu.word }')">${p }</a>
		</c:if>
	</c:forEach>
	
	<!-- 다음 -->
	<c:if test="${pu.endPage<pu.totPage }">
		<a href="javascriipt:getData(${pu.endPage+1 },'${pu.field }','${pu.word }')">[다음]</a>
	</c:if>
</div>


