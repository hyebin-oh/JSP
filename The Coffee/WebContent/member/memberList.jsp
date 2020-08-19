<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
 
 <script>
 $(document).ready(function(){

	 getData(1,"","");
	
	
	  $("#btnSearch").on("click",function(){
			 getData(1,$("#field").val(), $("#word").val());
	 }) 
 })
 
 function getData(pageNum, field, word){
		 $.get("memberList.me",
			 {"pageNum": pageNum, "field": field, "word": word},
			 function(data){
				 $("#result").html(data);
			 }
	 )
 } 
 </script>

 <div id="result"></div>



 
 