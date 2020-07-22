<%@page import="com.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");	
%>
<jsp:useBean id="board" class="com.board.BoardVO"></jsp:useBean>
<jsp:setProperty property="*" name="board"></jsp:setProperty>
<%
	BoardDAO dao=BoardDAO.getInstace();
	String ip=request.getRemoteAddr();
	board.setIp(ip);
	int flag=dao.boardUpdate(board);
	if(flag==1){//비번일치
		response.sendRedirect("list.jsp");
	}else{
%>
		<script>
			alert("비밀번호가 틀렸습니다.");
			history.back();//history.go(-1):이전페이지로이동
		</script>
	<%
	}
	%>