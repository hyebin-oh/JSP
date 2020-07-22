<%@page import="com.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="board" class="com.board.BoardVO"></jsp:useBean>
<jsp:setProperty property="*" name="board"></jsp:setProperty>
<%
	BoardDAO dao = BoardDAO.getInstace();	
	String ip = request.getRemoteAddr(); //아이피 주소 받기
	board.setIp(ip);
	dao.boardInsert(board);
	response.sendRedirect("list.jsp");
%>