<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
 
<link rel="stylesheet" type="text/css" href="../styles/bootstrap4/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../styles/single_styles.css">
<link rel="stylesheet" type="text/css" href="../styles/single_responsive.css"> 
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../style/style.css">

<script>
$(document).ready(function(){
	$("#toCart").click(function(){
		if(${sessionScope.userid==null||sessionScope.userid==""}){
			alert("로그인이 필요합니다");
			location.href="../member/login.jsp";
		}else{
			 location.href="../cart/cartList.me" 
		}
	})
})
</script>

<header class="header trans_300">

	<!-- Top Navigation -->
<div class="top_nav">
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<div class="top_nav_left"></div>
			</div>
			<div class="col-md-6 text-right">
				<div class="top_nav_right">
					<ul class="top_nav_menu">
						<!-- Login / Join us / Cart / My Page -->
						<c:choose>
							<c:when test="${empty sessionScope.userid }">
								<li class="currency"><a href="../member/login.jsp">Login</a></li>
								<li class="currency"><a href="../member/join.jsp">Join Us</a></li>
							</c:when>						
							<c:otherwise>
								<li class="currency"><a href="../member/logout.me">Logout</a></li>
							</c:otherwise>
						</c:choose>
						<c:if test="${sessionScope.userid==null}">
							<li class="currency"><a href="../member/login.jsp" onclick="alert('로그인이 필요합니다')">Cart</a></li>  
						</c:if>
						<c:if test="${sessionScope.userid!=null }">
							<li class="currency"><a href="../cart/cartList.me">Cart</a></li>
						</c:if>
						<c:if test="${admin==0 }"> <!-- 일반회원 -->
						<li class="currency"><a href="../member/userView.me">My Page</a></li>
						</c:if>
							<c:if test="${admin==1 }">
								<!-- 관리자 -->
								<li class="currency"><a href="../member/memberList.jsp">My Page</a></li>
							</c:if>

						</ul>
				</div>
			</div>
		</div>
	</div>
</div>

		<!-- Main Navigation -->
<div class="main_nav_container">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-right">
				<div class="logo_container">
					<a href="../main/main.jsp"><img src="../image/logo.jpg" width="90" height="90"></a>
				</div>
				<nav class="navbar">
					<ul class="navbar_menu">
						<li><a href="../main/main.jsp">Home</a></li>
						<li><a href="#">About Us</a></li>
						<li><a href="../product/singleProductList.me">싱글 오리진</a></li>
						<li><a href="../product/blendProductList.me">블렌드</a></li>
						<li><a href="../notice/noticeList.me">커뮤니티</a></li>
		
					</ul>							
				</nav>						
			</div>
		</div>
	</div>
</div>

</header>
