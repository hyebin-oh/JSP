<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/adminMypage.jsp" %>


<div class="main_slider">
<label for="nsubject">제목</label>
${notice.nsubject }
<label for="ncontent">내용</label>
${notice.ncontent }
<input type="button" id="btnUpdate" value="수정하기" onclick="location='noticeUpdate.me?nsubject=${notice.nsubject }'">
<input type="reset" value="다시작성하기">

</form>