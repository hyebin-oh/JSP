<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/community.jsp" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
   <script>

   
   $(document).ready(function(){
	   function fdelete(nsubject){
		   alert("mm");
		   if(confirm("게시물을 삭제하시겠습니까?")){
			   location.href="noticeDelete.me";
		   }
	   }
   })
   </script>
    
<div class="container">
  <h2>공지사항</h2>
  <div align="right">전체 게시글 수:<span>${count }</span></div>       
  <table class="table table-hover">  	
    <thead>
      <tr>
        <th>no</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>조회수</th>
        <th>삭제</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${notice }" var="notice" varStatus="st">
      	<tr>
     	    <td>${notice.nnum }</td>
    	    <td><a href="noticeView.me?nsubject=${notice.nsubject }">${notice.nsubject }</a></td>
     	   <td>관리자</td>
     	   <td>${notice.ndate }</td>
     	   <td>${notice.nview }</td>
     	   <td><a href="noticeDelete.me?nsubject=${notice.nsubject  }">삭제</a></td>
    	  </tr>
		</c:forEach>
    </tbody>
  </table>
  <form class=table-form>
  		<select name="f">
  			<option ${(param.f =="nsubject")?"selected":""} value="nsubject">제목</option>
  			<!-- 다른 페이지 할 때 value값을 테이블의 컬럼명과 동일하게 해야 함 -->
  		</select>
  		<input type="text" name="q" value="${param.q }"/>
  		<!-- param.q는 검색어가 남아 있는 것 -->
  		<input type="submit" value="검색"/>
  
  </form>
  
  <div>
  	<c:set var="page" value="${(empty param.p)?1:param.p }"/>
  	<c:set var="startNum" value="${page-(page-1)%5 }"/>
  		<!-- 시작번호는1, 페이지 사이즈는 5 -->
  	<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10), '.')}"/>
  		<!-- 한 페이지에 출력될 리스트 수는 10개 -->
  	
  	<c:if test="${startNum>1 }">
  		<a href="?p=${startNum-1 }&f=&q=">이전</a>
  	</c:if>
  	<c:if test="${startNum<=1 }">
  		<a onclick="alert('이전페이지가없습니다');">이전</a>
  	</c:if>

  	<c:forEach var="i" begin="0" end="4">
  	<c:if test="${(startNum+i)<= lastNum}">
  	<a href="?p=${startNum+i}&f=${param.f }&q=${param.q}">${startNum+i }</a>
  	</c:if>
  	</c:forEach>  	

  	<c:if test="${startNum+4<lastNum }">
  		<a href="?p=${startNum+5 }&f=&q=">다음</a>
  	</c:if>
  	<c:if test="${startNum+4>=lastNum }">
  		<a onclick="alert('다음페이지가 없습니다');">다음</a>
  	</c:if>

  </div> 
  
</div>