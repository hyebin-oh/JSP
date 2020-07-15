<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.5.1.min.js"></script>
<script>
 $(document).ready(function(){
	$("#postBtn").click(function(){
		$.post("process.jsp", //process.jsp에 
				{"id" : $("#id").val(), //id의 값과
			 				//=document.getElementById("id").value
					"pwd" : $("#pwd").val(), //pwd의 값을 뿌린다
					 "method" : "post"}, 
				 function(data){//콜백함수-결과값을 result에 뿌림(data는 내가 정하는 변수)
						$("#resultPost").html(data);
				}//function
		);//post
	});//postBtn
	
	$("#getBtn").click(function(){
		$.get("process.jsp", { "id" : $("#id").val(), "pwd" : $("#pwd").val(), "method" : "get"},
				function(ret){
					$("#resultGet").html(ret);
				}//function
		)//get
	});//getBtn
	
	$("#loadBtn").click(function(){
		$("#resultLoad").load("process.jsp", { "id" : $("#id").val(), "pwd" : $("#pwd").val(), "method" : "load"})
	});//loadBtn
	
	$("#ajaxBtn").click(function(){
		$.ajax({
			type : "post",
			url : "process.jsp",
			data : {
				"id" : $("#id").val(),
				"pwd" : $("#pwd").val(),
				"method" : "ajax"				
			},
			success : function(d){
				$("#resultAjax").html(d);				
			},
			error : function(e){
				alert("에러");				
			}
		});//ajax
	});//ajaxBtn
	
	
 });//document
 
</script>
</head>

<body>
 id : <input type="text" id="id" name="id"><br>
 pwd : <input type="password" id="pwd" name="pwd">
 <input type="button" id="postBtn" value="post전송">
 <input type="button" id="getBtn" value="get전송">
 <input type="button" id="loadBtn" value="load전송">
 <input type="button" id="ajaxBtn" value="ajax전송">
 <br>
 <div id="resultPost"></div>
 <div id="resultGet"></div>
 <div id="resultLoad"></div>
 <div id="resultAjax"></div>
 
</body>
</html>