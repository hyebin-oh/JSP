<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/community.jsp" %>

<form action="qnaUpdate.me" method="post">
	<input type="hidden" name="qnum" id="qnum" value="${qna.qnum }">

	<br>
	<h3>문의하기 수정</h3>
	<hr>
	<div class="form-group">					 
    	<label for="qsubject">제목</label>
		<input type="text" name="qsubject" id="qsubject" value="${qna.qsubject }" class="form-control">
    </div>
    
    <div class="form-group">	
    	<label for="userid">작성자</label>
		<input type="text" class="form-control" name="userid" id="userid" value="${qna.userid }" readonly>
    </div>
    
     <div class="form-group">	
    	<label for="qdate">작성일</label>
		<input type="text" class="form-control" name="qdate" id="qdate" value="${qna.qdate }" readonly>
    </div>
    
    <div class="form-group">	
    	<label for="qview">조회수</label>
		<input type="text" class="form-control" name="qview" id="qview" value="${qna.qview }" readonly>
    </div>
    
	<div class="form-group">					 
      	<label for="qcontent">내용</label>
		<textarea class="form-control" rows="8" name="qcontent" id="qcontent">${qna.qcontent }</textarea>
    </div>

	<div align="center">
    	<input type="submit" id="btnQnAUpdate" value="수정완료" class="btn btn-primary">
		<input type="button" value="작성 취소" onclick="location.href='qnaList.me'" class="btn btn-primary">
	</div>
	
</form>
	

