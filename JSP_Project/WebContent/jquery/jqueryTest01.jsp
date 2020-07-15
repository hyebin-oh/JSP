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
	  $("button").click(function(){
		  $("#displayArea").html("<img src='cover.jpg' border=0>")
		  alert($(this).text())//버튼의 글자를 가져옴(get의 역할)
		  $(this).text("여기도 바뀔까")//this는 버튼으로 버튼의 글자가 바뀜(set의 역할)
	  }); //button
	  
	  $("#txtBtn").on("click", function(){
		  alert($("#txt").val())
		 $("#txt").val("txt 내용 변경")
	  });//txtBtn	  
  }); //document
 </script>
</head>

<body>
 <div id="displayArea">이곳의 내용이 변경</div>
  <button>표시</button><br>
  <input type="text" id="txt" value="초기값"><br>
  <input type="button" value="txt클릭" id="txtBtn">
</body>
</html>