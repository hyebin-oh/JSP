<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/community.jsp" %>


<form action="qnaInsert.me" method="post">
	<br>
	<h3>문의하기</h3>
	<hr>
	
	<div class="form-group">					 
    	<label for="qsubject">제목</label>
		<input type="text" name="qsubject" id="qsubject" class="form-control">
    </div>
    
	<div class="form-group">					 
      	<label for="qcontent">내용</label>
		<textarea class="form-control" rows="8" id="qcontent" placeholder="주문변경 요청 시 주문번호를 함께 입력해주세요"></textarea>
    </div>

	<div align="center">
    	<input type="submit" value="작성완료" class="btn btn-primary">
        <input type="reset" value="다시 작성하기" class="btn btn-primary">
	</div>

</form>

