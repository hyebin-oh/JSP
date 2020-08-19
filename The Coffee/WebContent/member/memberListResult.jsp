<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 <%@ include file="../include/adminMypage.jsp" %>
 
 <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <script>
 $(document).ready(function(){
	
	
	  $("#btnSearch").on("click",function(){
			 getData(1,$("#field").val(), $("#word").val());
	 }) 
 })
 
 function getData(pageNum, field, word){
		 $.get("memberList.me",
			 {"pageNum": pageNum, "field": field, "word": word},
			 function(data){
				 $("#result").html(data);
			 }
	 )
 }

 </script>

 <div class="container">
  <h2>회원관리(총 인원: <span id="memberCount">${count }</span>)</h2>
  <table class="table table-hover">
    <thead>
      <tr>
        <th>No</th>
        <th>아이디</th>
        <th>이름</th>
        <th>생년월일</th>
        <th>주소</th>
        <th>전화번호</th>
        <th>이메일</th>
        <th>가입일</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${homeuser }" var="member" varStatus="st">
      <tr>
        <td>${rowNo-st.index }</td>
        <td><a href="memberView.me?userid=${member.userid}">${member.userid }</a></td>
        <td>${member.name }</td>
        <td>${member.birth }</td>
        <td>${member.addr }</td>
        <td>${member.phone }</td>
        <td>${member.email }</td>
        <td>${member.signupdate }</td>
      
      </tr>
	  </c:forEach>
    </tbody>
  </table>
  <div align = "center">
	  	<c:if test = "${pu.startPage>pu.pageBlock}"> <!-- 이전-->
	  		<a href = "javascript:getData(${pu.startPage-pu.pageBlock},'${pu.field}','${pu.word}')">[이전]</a>
	  	</c:if>
	  	<c:forEach begin ="${pu.startPage}" end = "${pu.endPage}" var = "i"> <!-- 이전-->
  			<c:if test ="${i==pu.currentPage}"> <!-- 현재페이지-->
 				<c:out value = "${i}"/>
  			</c:if>
  			<c:if test = "${i!=pu.currentPage}"> <!-- 현재페이지 아닌 경우 링크 부여-->
  				<a href = "javascript:getData(${i},'${pu.field}','${pu.word}')">${i}</a>
  			</c:if>
	  	</c:forEach>
	  	<c:if test = "${pu.endPage < pu.totPage}"> <!-- 다음-->
	  		<a href = "javascript:getData(${pu.endPage+1},'${pu.field}','${pu.word}')">[다음]</a>
	  	</c:if>
	  </div> 
</div>
</br>
	<div align = "center">
		<form name = "search" id = "search">
			<select name = "field" id = "field">
				<option value = "name">이름</option>
				<option value = "birth">생년월일</option>
			</select>
			<input type = "text" name = "word" id = "word">
			<input type = "button" value = "찾기" id = "btnSearch" class="btn btn-primary"> 
		</form>
		</div>
</div>
   
</div>
