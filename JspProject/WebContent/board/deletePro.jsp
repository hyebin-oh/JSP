<%@page import="com.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
	int num=Integer.parseInt(request.getParameter("num"));
	BoardDAO dao=BoardDAO.getInstace();
	dao.boardDelete(num);
	response.sendRedirect("list.jsp");	
%>