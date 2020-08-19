<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/adminMypage.jsp" %>


<form action="noticeInsert.me" method="post">
	<br>
	<h3>공지사항 등록하기</h3>
	<hr>
	
	<div class="form-group">					 
    	<label for="nsubject">제목</label>
		<input  type="text" name="nsubject" id="nsubject" class="form-control">
    </div>
    
	<div class="form-group">					 
      	<label for="ncontent">내용</label>
		<textarea class="form-control" rows="8" name="ncontent" id="ncontent"></textarea>
    </div>

	<div align="center">
    	<input type="submit" value="작성완료" class="btn btn-primary">
        <input type="reset" value="다시 작성하기" class="btn btn-primary">
	</div>

</form>

