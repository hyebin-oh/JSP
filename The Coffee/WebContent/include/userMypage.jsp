<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="header.jsp" %>
    
<div class="main_slider">
	<div class ="left-side">
		<h4>마이페이지</h4>
			<ul>
				<li><a href="../order/userOrderList.me?userid=${userid }">나의 신청 내역</a></li>
				<li><a href="../QnA/qnaUserList.me?userid=${userid }">나의 문의 내역</a></li>
				<li><a href="../member/userView.me?userid=${userid }">회원 정보 수정 / 탈퇴</a></li>
			</ul>
	</div>
	<div class="content">