<%@page import="com.member.memberDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userid = (String) session.getAttribute("userid");
	memberDAOImpl dao = memberDAOImpl.getInstace();
	dao.memberDel(userid);
	session.invalidate();
	response.sendRedirect("loginForm.jsp");
%>