<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
  <script src="/Project_coffee/js/member_join.js"></script>
  
  
<div class="main_slider">
<div class ="left-side">
<h2>Login</h2>
<ul>
	<li><a href="login.me">로그인</a></li>
	<li><a href="join.jsp">회원가입</a></li>
</ul>
</div>


<div class="content">
	<h4>회원가입</h4>
  <div class="container">

  <form action="join.me" method="post" id="frm">
    <div class="row">
	    <div class="col">
	      <label for="userid">id</label>
	      <input type="text" class="form-control" id="userid"name="userid" min=4 max=8 >
	    </div>
      <div class="col align-self-end" >
          <button  type="button"  id="idcheckBtn"  class="btn btn-primary">중복확인</button>
          <span id="idcheck"></span>
    </div>

    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd"  name="pwd">
    </div>
    <div class="form-group">
      <label for="pwd_check">Password Confirm:</label>
      <input type="password" class="form-control" id="pwd_check" name="pwd_check">
    </div>
        <div class="form-group">
      <label for="name">Name:</label>
      <input type="text" class="form-control" id="name" name="name">
    </div>
    <div class="form-group">
      <label for="birth">Birth:</label>
      <input type="text" class="form-control" id="birth" name="birth">
    </div>
    <div class="form-group">
      <label for="addr">Address:</label>
      <input type="text" class="form-control" id="addr" name="addr">
    </div>
    <div class="form-group">
      <label for="phone">Phone:</label>
      <input type="text" class="form-control" id="phone" placeholder="Enter Phone" name="phone">
    </div>
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="text" class="form-control" id="email" placeholder="Enter Eamil" name="email">
    </div>
	<br/>
	<button  id="join"  class="btn btn-primary">Submit</button>
	<input type="reset" class="btn btn-primary" value="다시작성">
  </form>
</div>
</div>
</div>

