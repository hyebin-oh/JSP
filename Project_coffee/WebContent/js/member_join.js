$(document).ready(function(){
	//유효성 검사
	$("#join").click(function(){
		if($("#userid").val()==""){
			alert("아이디를 입력하세요");
			$("#userid").focus();
			return false;
		}
		
		if($("#pwd").val()==""){
			alert("비밀번호를 입력하세요");
			$("#pwd").focus();
			return false;
		}
		
		if($("#pwd").val()!=$("#pwd_check").val()){
			alert("비밀번호가 일치하지 않습니다");
			$("#pwd_check").focus();
			return false;
		}
		
		if($("#name").val()==""){
			alert("이름을 입력하세요");
			$("#name").focus();
			return false;
		}
		
		if($("#birth").val()==""){
			alert("생년월일을 입력하세요");
			$("#birth").focus();
			return false;
		}
		
		if($("#addr").val()==""){
			alert("주소를 입력하세요");
			$("#addr").focus();
			return false;
		}
		
		if($("#phone").val()==""){
			alert("연락처를 입력하세요");
			$("#phone").focus();
			return false;
		}
		
		if($("#email").val()==""){
			alert("이메일을 입력하세요");
			$("#email").focus();
			return false;
		}
		
		
	})//send
	
	
	$("#idcheckBtn").click(function(){
			$.ajax({
			type: "get",
			url: "join.me",
			data: {userid: $("#userid").val()},
			success: function(data){
				if($("#userid").val()==""||$("#userid").val()==null){
					alert("아이디를 입력하세요");
					$("#userid").focus();
					return false;
				}
				if(data.trim()=="yes"){
					$("#idcheck").html("사용가능한 아이디");
				}else{
					$("#idcheck").html("사용불가능한 아이디");
					 $("#userid").val("");
		  			  $("#userid").focus();
				}
				
			},
			error: function(e){
				alert("error: "+e)
			}
				
		})
	})

})//document