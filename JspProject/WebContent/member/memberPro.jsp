<%@page import="com.member.memberDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="member" class="com.member.MemberVO"></jsp:useBean>
<jsp:setProperty property="*" name="member" />
<%
	memberDAOImpl dao = memberDAOImpl.getInstace();
	String uid= request.getParameter("uid");
	member.setUserid(uid);
	dao.memberInsert(member);
	response.sendRedirect("memberList.jsp");
%>