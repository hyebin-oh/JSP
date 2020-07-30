<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../include/header.jsp" %>

  <h3>로그인</h3>
 <div class="container">
  <form action="login.me" method="post">
    <div class="form-group">
      <label for="userid">id:</label>
      <input type="text" class="form-control" id="userid" placeholder="Enter userid" name="userid">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
    </div>
    <div class="form-group form-check">
      <label class="form-check-label">
        <input class="form-check-input" type="checkbox" name="remember"> Remember me
      </label>
    </div>
     <button type="button" class="btn btn-primary" id="loginBtn">Submit</button>
  </form>
  </div>
  <script>
  $("#loginBtn").click(function(){
	  if($("#userid").val()==""){
		  alert("ID를 입력하세요");
		  $("#userid").focus();
		  return false;
	  }
	  
	  if($("#pwd").val()==""){
		  alert("비밀번호를 입력하세요");
		  $("#pwd").focus();
		  return false;
	  }
	  $.ajax({
			type : "post",
			url : "login.me",
			data : {"userid" : $("#userid").val(), "pwd" : $("#pwd").val()},
			success : function(value){
				if(value.trim()==-1){
					alert("회원이 아닙니다. 회원가입하세요");
					location.href="insert.me";
				}else if(value.trim()==0){
					
					alert("일반회원 로그인");
					location.href="view.me";
				}else if(value.trim()==1){
					alert("관리자 로그인");
					location.href="list.me";
				}else if(value.trim()==2){
					alert("비밀번호를 확인하세요");
				}
			},
			error : function(e){
				alert("error : "+e)
			}
			
		})
	  
  })
  </script>

<%@ include file="../include/footer.jsp"%>
