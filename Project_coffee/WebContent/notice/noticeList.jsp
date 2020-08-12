<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/community.jsp" %>    

    
    
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
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${notice }" var="notice" varStatus="st">
      	<tr>
     	    <th>${rowNo-st.index }</th>
    	    <th><a href="noticeView.me?nsubject=${notice.nsubject }">${notice.nsubject }</a></th>
     	   <th>관리자</th>
     	   <th>${notice.ndate }</th>
     	   <th>${notice.nview }</th>
    	  </tr>
		</c:forEach>
    </tbody>
  </table>
  <div align = "center">
	  	<c:if test = "${pu.startPage>pu.pageBlock}"> <!-- 이전-->
	  		<a href = "noticeList.jsp?pageNum=${pu.startPage-pu.pageBlock}">[이전]</a>
	  	</c:if>
	  	<c:forEach begin ="${pu.startPage}" end = "${pu.endPage}" var = "i"> <!-- 이전-->
  			<c:if test = "${i==pu.currentPage}"> <!-- 현재페이지-->
 				<c:out value = "${i}"/>
  			</c:if>
  			<c:if test = "${i!=pu.currentPage}"> <!-- 현재페이지 아닌 경우 링크 부여-->
  				<a href = "noticeList.jsp?pageNum=${i}">${i}</a>
  			</c:if>
	  	</c:forEach>
	  	<c:if test = "${pu.endPage < pu.totPage}"> <!-- 다음-->
	  		<a href = "noticeList.jsp?pageNum=${pu.endPage+1}">[다음]</a>
	  	</c:if>
	  </div> 
</div>