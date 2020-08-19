<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/community.jsp" %>

	<div class="form-group">
		<label for="nnum">글 번호</label>
		<input type="text" class="form-control" id="nnum" name="nnum" value="${notice.nnum}" readonly>
	</div>	

	<div class="form-group">					 
    	<label for="nsubject">제목</label>
		<input type="text" class="form-control" name="nsubject" id="nsubject" value="${notice.nsubject }"readonly>
    </div>
    
    <div class="form-group">	
    	<label for="ndate">작성일</label>
		<input type="text" class="form-control" name="ndate" id="ndate" value="${notice.ndate }" readonly>
    </div>
    
    <div class="form-group">	
    	<label for="nview">조회수</label>
		<input type="text" class="form-control" name="nview" id="nview" value="${notice.nview }"readonly>
    </div>
    
    <div class="form-group">
   		<label for="ncontent">내용</label>
		<textarea class="form-control" rows="15" name="ncontent" id="ncontent" readonly>${notice.ncontent }</textarea>
    </div>
    
    <div align="center">
    <c:if test="${admin==1 }">
		<input type="button" id="btnUpdate" value="수정하기" onclick="location='noticeUpdate.me?nnum=${notice.nnum }'" class="btn btn-primary">
	</c:if>
	</div>
	
	<div align="left">
		<input type="button" value="목록" onclick="location='noticeList.me'" class="btn btn-primary"> 
	</div>
