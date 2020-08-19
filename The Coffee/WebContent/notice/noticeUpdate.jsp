<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/adminMypage.jsp" %>


<form action="noticeUpdate.me" method="post">

	<div class="form-group">
		<label for="nnum">글 번호</label>
		<input type="text" class="form-control" id="nnum" name="nnum" value="${notice.nnum}" readonly>
	</div>	

	<div class="form-group">					 
    	<label for="nsubject">제목</label>
		<input type="text" class="form-control" name="nsubject" id="nsubject" value="${notice.nsubject }">
    </div>
    
    <div class="form-group">	
    	<label for="ndate">작성일</label>
		<input type="text" class="form-control" name="ndate" id="ndate" value="${notice.ndate }" readonly>
    </div>
    
    <div class="form-group">	
    	<label for="nview">조회수</label>
		<input type="text" class="form-control" name="nview" id="nview" value="${notice.nview }">
    </div>
    
    <div class="form-group">
   		<label for="ncontent">내용</label>
		<textarea class="form-control" rows="15" name="ncontent" id="ncontent">${notice.ncontent }</textarea>
    </div>

	<div align="center">
		<input type="submit" id="btnUpdate" value="수정완료" class="btn btn-primary">
		<input type="button" value="작성 취소" onclick="location.href='noticeList.me'" class="btn btn-primary">
	</div>
</form>
