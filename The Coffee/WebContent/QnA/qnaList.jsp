<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/community.jsp" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
    
    <script>
    $(document).ready(function(){
    	$("#qnaWriteBtn").click(function(){
    		if(${sessionScope.userid==null||sessionScope.userid==""}){
    			alert("로그인이 필요합니다");
    			location.href="../member/login.jsp";
    		}else{
    			location.href="qnaInsert.jsp";
    		}
    	})
    })
    
    </script>
    
    
<div class="container">
  <h2>문의하기</h2> 
  <table class="table table-hover">
  <input type="hidden" name="qnum" id="qnum">
    <thead>
      <tr>
        <th>no</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>조회수</th>
        <c:if test="${admin==1 }">
        <th>삭제</th>
        </c:if>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${qna }" var="qna" varStatus="st">
      	<tr>
     	   <td>${st.count }</td>
    	   <td><a href="qnaView.me?qnum=${qna.qnum }">${qna.qsubject }</a></td>
     	   <td>${qna.userid }</td>
     	   <td>${qna.qdate }</td>
     	   <td>${qna.qview }</td>
     	   <c:if test="${admin==1 }">
     	   <td><a href="qnaDelete.me?qnum=${qna.qnum }">삭제</a></td>
     	   </c:if>
    	  </tr>
		</c:forEach>
    </tbody>
  </table>
  
  <div align="center">
  <form class=table-form>
  		<select name="f">
  			<option ${(param.f =="qsubject")?"selected":""} value="qsubject">제목</option>
  			<option ${(param.f =="userid")?"selected":""} value="userid">작성자</option>
  			<!-- 다른 페이지 할 때 value값을 테이블의 컬럼명과 동일하게 해야 함 -->
  		</select>
  		<input type="text" name="q" value="${param.q }"/>
  		<!-- param.q는 검색어가 남아 있는 것 -->
  		<input type="submit" value="검색"/>  
  </form>
    </div>
  
  
  <div align="right">
  	<c:if test="${admin==0 }">  	
  		<button type="button" id="qnaWriteBtn"  class="btn btn-primary">글쓰기</button>
	</c:if>
  </div>
  
  <div align="center">
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