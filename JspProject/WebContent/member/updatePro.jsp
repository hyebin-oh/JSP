<%@page import="com.member.memberDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="mvo" class="com.member.MemberVO"/>
<jsp:setProperty property="*" name="mvo"/>

<%
	memberDAOImpl dao = memberDAOImpl.getInstace();
	int flag=dao.memberUpdate(mvo);
	if(flag==1){
		session.invalidate();
		response.sendRedirect("loginForm.jsp");
	}
%>