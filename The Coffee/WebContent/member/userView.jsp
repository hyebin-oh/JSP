<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 <%@ include file="../include/userMypage.jsp" %>
    
<script>
	$(document).ready(function(){
		$("#deleteBtn").click(function(){
			if(confirm("탈퇴하시겠습니까?")){
				location.href="userDelete.me?userid="+$("#userid").val();
			}
		})
	})

</script>
	
  		<div class="container">
  			<h4>${member.userid }님의 정보</h4>
	  		<form action="userUpdate.me" method="post" id="frm"> 
	   			<div class="form-group">
	     		 	<label for="userid">id</label>
	     			<input type="text" class="form-control" id="userid" name="userid" value=${member.userid } readonly>
    			</div>
   				<div class="form-group">
      				<label for="pwd">Password:</label>
      				<input type="text" class="form-control" id="pwd"  name="pwd" value=${member.pwd }>
    			</div>
        		<div class="form-group">
      				<label for="name">Name:</label>
      				<input type="text" class="form-control" id="name" name="name" value=${member.name }>
   				</div>
    			<div class="form-group">
      			 	<label for="birth">Birth:</label>
      				<input type="text" class="form-control" id="birth" name="birth" value=${member.birth }>
    			</div>
    			<div class="form-group">
      				<label for="addr">Address:</label>
      				<input type="text" class="form-control" id="addr" name="addr" value=${member.addr }>
    			</div>
    			<div class="form-group">
      				<label for="phone">Phone:</label>
      				<input type="text" class="form-control" id="phone" name="phone" value=${member.phone }>
    			</div>
    			<div class="form-group">
      				<label for="email">Email:</label>
      				<input type="text" class="form-control" id="email" name="email" value=${member.email }>
    			</div>
				<br/>
				<button  id="update"  class="btn btn-primary">수정하기</button>
				<input type="reset" class="btn btn-primary" value="취소하기">
				<button type="button" id="deleteBtn" class="btn btn-primary">탈퇴하기</button>
  			</form>
		</div>
	</div>
</div>