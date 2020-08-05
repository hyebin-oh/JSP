<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

	<h1>Guest</h1>
	<div align="right">
	게시글 수: ${count}
	</div>
	<div class="container">
	<table class="table table-hover">
		<thead>
			<tr>
				<th>번호</th>
				<th>Seq</th>
				<th>작성자</th>
				<th>내용</th>
				<th>평가</th>
				<th>작성일</th>
				<th>ip주소</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${arr }" var="guest" varStatus="st">
				<tr>
					<td>${rowNo-st.index }</td>
					<td>${guest.num }</td>
					<td><a href="javascript:fview(${guest.num})">${guest.name }</a></td>
					<td>${guest.content }</td>
					<td>${guest.grade }</td>
					<td>${guest.created}</td>
					<td>${guest.ipaddr }</td>
				</tr>
			</c:forEach>
		</tbody>	
	</table>
	</div>
	<div align="center">
		<c:if test="${pu.startPage>pu.pageBlock }"><!-- 이전 -->
			<a href="javascript:getData(${pu.startPage-pu.pageBlock})">[이전]</a>
		</c:if>
		
		<!-- 페이지출력 -->
		<c:forEach begin="${pu.startPage }" end="${pu.endPage }" var="i">
			<c:if test="${i==pu.currentPage }"><!-- 현재페이지 -->
				<c:out value="${i }"/>				
			</c:if>
			<c:if test="${i!=pu.currentPage }"><!-- 현재페이지 아닌경우 링크 부여 -->
				<a href="javascript:getData(${i})">${i }</a>				
			</c:if>
		</c:forEach>
		
		<c:if test="${pu.endPage<pu.totPage }"><!-- 다음 -->
			<a href="javascript:getData(${pu.endPage+1 })">[다음]</a>
		</c:if>
	</div>
