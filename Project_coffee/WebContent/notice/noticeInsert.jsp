<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/adminMypage.jsp" %>

<form action="noticeInsert.me" method="post">
<label for="nsubject">제목</label>
<input type="text" name="nsubject" id="nsubject">
<label for="ncontent">내용</label>
<textarea name="ncontent" id="ncontent"></textarea>
<input type="submit" id="btnInsert" value="등록">
<input type="reset" value="다시작성하기">

</form>