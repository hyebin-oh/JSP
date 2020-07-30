var exp =/^[0-9]{3}-[0-9]{4}-[0-9]{4}$/

$(document).ready(function(){
	$("#send").click(function(){
		if($("#userid").val()==""){
			alert("아이디를 입력하세요");
			$("#userid").focus();
			return false;
		}//userid
		
		if($("#name").val()==""){
			alert("이름을 입력하세요");
			$("#name").focus();
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
		
		if($("#email").val()==""){
			alert("이메일을 입력하세요");
			$("#email").focus();
			return false;
		}
		//전화번호 확인
		if(!$("#phone").val().match(exp)){
			alert("전화번호 입력 양식이 아닙니다");
			$("#phone").focus();
			return false;
		}
		$("#frm").submit();
		
	});//send
	
	//아이디 중복 확인페이지 이동
	  $("#idcheckBtn").click(function(){
		  window.open("idCheck.me","","width=400 height=300");
	  })
	  
	  //id 중복 확인
	  $("#useBtn").click(function(){
		  if($("#userid").val()==""){
			  alert("아이디를 입력하세요");
			  $("#userid").focus();
			  return;
		  }
		  $.ajax({
			  type:"post",
		  	  url: "idCheck.me",
		  	  data: {"userid": $("#userid").val()},
		  	  success: function(val){
		  		  if(val.trim()=="yes"){
		  			alert("사용가능한 아이디입니다");  
		  			$(opener.document).find("#userid").val($("#userid").val());
		  			$(opener.document).find("#uid").val($("#userid").val());
		  			self.close();
		  		  }else if(val.trim()=="no"){
		  			  alert("이미 존재하는 아이디입니다.");
		  			  $("#userid").val("");
		  			  $("#userid").focus();
		  		  }		  
		  	  },
		  	  erroer: function(e){
		  		  alert("error: "+e);
		  	  }
		  		  
		  })
	})
		  
});//document

function del(userid){
	if(confirm("정말 삭제할까요?")){
		$.getJSON("userDelete.me?userid="+userid, function(data){
			var htmlStr="";
			$.each(data.root, function(key, val){
				htmlStr+="<tr>";
				htmlStr+="<td>"+val.name+"</td>";
				htmlStr+="<td>"+val.userid+"</td>";
				htmlStr+="<td>"+val.phone+"</td>";
				htmlStr+="<td>"+val.email+"</td>";
				htmlStr+="<td>"+val.mode+"</td>";
				if(val.mode=='일반회원'){
					htmlStr+="<td onclick=del('"+val.userid+"')>삭제</td>";
				}else{
					htmlStr+="<td>Admin</td>";
				}
				htmlStr+="</tr>";
			})//each		
			$("table tbody").html(htmlStr);
			$("#cntSpan").text(data.rootCount.count);
			
		})//geJSON
	}//if
	
}