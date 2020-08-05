<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		getData(1);
		
		$("#send").click(function(){
			var name = $("#name").val();
			var content = $("#content").val();
			var grade = $("input:radio[name=grade]:checked").val();
			var postString = "name="+name+"&content="+content+"&grade="+grade;
			
			$.ajax({
				type: "post",
				url: "create.gb",
				data: postString,
				success:function(d){					
					$("#result").html(d);					
				},
				beforeSend: showRequest,
				error: function(e){
					alert("error: "+e);
				}
			})
		})
	})
	
	function getData(pageNum){
			$.get("list.gb", {"pageNum": pageNum},
				function(d){			
			$("#result").html(d);
		})
	}
	
	function showRequest(){
		if($("#name").val()==""){
			alert("이름을 입력하세요.");
			$("#name").focus();
			return false;
		}
		if($("#content").val()==""){
			alert("내용을 입력하세요.");
			$("#content").focus();
			return false;
		}
		if($("input:radio[name=grade]:checked").length==0){
			alert("평가를 해주세요");
			return false;
		}
		return true;
	}
	
	function textCount(obj, target){//target: nameCount, contentCount
		var len = $("#"+obj.id).val().length;

		$("#"+target).text(len);
	}	
	
	function fview(){
		$.getJSON("view.gb?name="+name, function(data){
			var htmlStr="";
			
		})
	}
	
	
</script>
</head>
<body>
	<form method="post" action="create.gb" class="w3-container w3-card-4 w3-light-grey">
		<table>
			<tr>
				<td>
					<label for="name">글쓴이</label>
					<input type="text" id="name" name="name" size=20 onKeyup="textCount(this, 'nameCount')" class="w3-input w3-border w3-round">
					*20글자 이내
					(<span id="nameCount" style="color:red">0</span>)
				</td>
			</tr>
			<tr>
				<td>
					<label for="content">내용</label>
					<input type="text" id="content" name="content" size=70 onKeyup="textCount(this,'contentCount')" class="w3-input w3-border w3-round-large">
					*70글자 이내
					(<span id="contentCount" style="color:red">0</span>)
				</td>
			</tr>
			<tr>
				<td>
					<label for="grade">평가</label>
					<input type="radio" name="grade" value="excellent" checked="checked"  class="w3-radio"> 아주잘함(excellent)
					<input type="radio" name="grade" value="good"  class="w3-radio"> 잘함(good)
					<input type="radio" name="grade" value="normal"  class="w3-radio"> 보통(normal)
					<input type="radio" name="grade" value="fail"  class="w3-radio"> 노력(fail)
				</td>
			</tr>
			<tr>
				<td>
				<input type="submit" value="제출" id="btnSubmit" class="w3-button w3-blue">
				<input type="button" value="ajax버튼 전송" id="send" class="w3-button w3-blue" > 
				</td>
			</tr>
		</table>
	</form>
	<br><br><br>
	<div id="result" align="center">
	</div>
	<hr>
	<div id="view" >
	</div>
</body>
</html>