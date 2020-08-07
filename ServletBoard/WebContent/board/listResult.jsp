<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<div align="left">
<a href="insert.jsp">글쓰기</a>
</div>
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
			<td><a href="view.jsp?num=${board.num }">${board.writer }</a></td>
			<td>${board.subject }</td>
			<td>${board.content }</td>
			<td>${board.reg_date }</td>
			<td>${board.readcount }</td>
		</tr>
	  </c:forEach>
	</tbody>	
</table>
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
			<a href="javascript:getData(${p }, '${pu.field }','${pu.word }')">${p }</a>
		</c:if>
	</c:forEach>
	
	<!-- 다음 -->
	<c:if test="${pu.endPage<pu.totPage }">
		<a href="javascriipt:getData(${pu.endPage+1 },'${pu.field }','${pu.word }')">[다음]</a>
	</c:if>
</div>
</div>