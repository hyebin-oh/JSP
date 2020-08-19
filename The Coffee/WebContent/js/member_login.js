//로그인 버튼 클릭
$(document).ready(function(){
$("#loginBtn").click(function(){
	//유효성 검사
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
		//-1 회원 아님, 1 관리자, 0 일반회원, 2 비번오류 
	  $.ajax({
		  type : "post",
		  url : "login.me",
		  data : {"userid" : $("#userid").val(), "pwd": $("#pwd").val()},
		  success: function(data){
			  if(data.trim()==-1){
				  alert("회원이 아닙니다. 회원가입하세요");
				  location.href="join.jsp";
			  }else if(data.trim()==0){
				  alert("반갑습니다");
				  location.href="../main/main.jsp";
				  
			  }else if(data.trim()==1){
				  alert("관리자 로그인");
				  location.href="memberList.jsp";
				  
			  }else if(data.trim()==2){
				  alert("비밀번호를 확인하세요");
			  }
			  
		  },
		  error : function(e){
				alert("error : "+e)
			}  
			  
	  });//ajax
})//loginBtn
})