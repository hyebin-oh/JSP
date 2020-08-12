<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
  <script src="/Project_coffee/js/member_login.js"></script>
  
<div class="main_slider">
<div class ="left-side">
<h2>Login</h2>
<ul>
	<li><a href="login.me">로그인</a></li>
	<li><a href="join.jsp">회원가입</a></li>
</ul>
</div>
<div class="content">
	<h4>로그인</h4>
  <div class="container">

  <form action="login.me" method="post">
    <div class="row">
	    <div class="col">
	      <label for="userid">id</label>
	      <input type="text" class="form-control" id="userid"name="userid" >
	    </div>

    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd"  name="pwd">
    </div>
	<div id="loginResult"></div>
	<button type="button" id="loginBtn"  class="btn btn-primary">로그인하기</button>
	<input type="reset" class="btn btn-primary" value="취소">
  </form>
</div>
</div>
</div>
