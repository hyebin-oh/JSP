<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.getParameter("utf-8");
	
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String method = request.getParameter("method");
	String str = "처리결과 <br>";
	str+="아이디 : " + id +"<br>";
	str+="비밀번호 : " + pwd + "<br>";
	str+="메소드 형식 : " + method + "<br>";
	out.println(str);
%>