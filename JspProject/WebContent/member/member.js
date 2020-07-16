var exp = /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/ //정규식 표현의 시작/^ 끝$/
$(document).ready(function(){
	$("#send").click (function(){
		if($("#name").val()==""){
			alert("이름을 입력하세요.");
			$("#name").focus();
			return;
		}
		
		if($("#userid").val()==""){
			alert("ID를 입력하세요.");
			$("#userid").focus();
			return;
		}
		
		if($("#pwd").val()==""){
			alert("비밀번호를 입력하세요.");
			$("#pwd").focus();
			return;
		}
		
		if($("#pwd_check").val()==""){
			alert("비밀번호를 입력하세요.");
			$("#pwd_check").focus();
			return;
		}
	 
		if($("#pwd_check").val()!=$("#pwd").val()){
			alert("비밀번호가 다릅니다.");
			$("#pwd_check").focus();
			return;
		}
 
		if($("#email").val()==""){
			alert("이메일을 입력하세요.");
			$("#email").focus();
			return;
		}
		
		if(!$("#phone").val().match(exp)){
			alert("전화번호 입력양식이 아닙니다.");
			$("#phone").focus();
			return;
		}

		$("#frm").submit();
		
	});//send
	
	$("#idBtn").click(function(){
		window.open("idCheck.jsp","","width=800 height=500");
	});//idBtn
	
	//아이디 중복확인
	$("#idCheckBtn").click(function(){
		if($("#userid").val()==""){
			alert("아이디를 입력하세요");
			$("#userid").focus();
			return;
		}
		$.ajax({
			type :"post",
			url :"idCheckPro.jsp",
			data :{"userid" : $("#userid").val()},
			success :function(d){
				if(d.trim()=="yes"){
					alert("사용 가능한 아이디입니다.")
					$(opener.document).find("#userid").val( $("#userid").val());
					$(opener.document).find("#uid").val( $("#userid").val());
					self.close();
				}else{
					alert("사용 불가능한 아이디입니다.")
				}
			},
			error : function(e){
				alert("error:"+e)
			}
		});//ajax
		
	})//idCheckBtn
	
});//document