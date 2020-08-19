<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/community.jsp" %>

<script>
$(document).ready(function(){
	commentList();
		$("#commentBtn").on("click", function(){
			$.ajax({
				type: "get",
				url: "commentInsert.me",
				data:{"msg": $("#msg").val(), "qnum":${qna.qnum}, "userid":$("#userid").val()},
			
				success: function(d){
					commentList();
				},
				error: function(e){
					alert("error: "+e)
				} 
					
			});//ajax
		});//commentBtn
});//document



function commentList(){
	$.getJSON("commentList.me?qnum="+$("#qnum").val(), function(data){
		var htmlStr="";
		htmlStr+="<table class='table table-hover'>";
		$.each(data.commentList, function(key, val){
			
			htmlStr+="<tr>";
			htmlStr+="<td>"+val.cwriter+"</td>";
			htmlStr+="<td>"+val.msg+"</td>"
			htmlStr+="<td>"+val.cdate+"</td>";
			htmlStr+="</tr>";
			
		});
		htmlStr+="</table>"
		$("#commentResult").html(htmlStr);
	})
		
	
}




</script>




	<br>
	<h3></h3>
	<hr>
	<input type="hidden" name="qnum" id="qnum" value="${qna.qnum }">
	<div class="form-group">					 
    	<label for="qsubject">제목</label>
		<input type="text" class="form-control" name="qsubject" id="qsubject" value="${qna.qsubject }" readonly>
    </div>
    
    <div class="form-group">	
    	<label for="userid">작성자</label>
		<input type="text" class="form-control" name="userid" id="userid" value="${qna.userid }" readonly>
    </div>
    
     <div class="form-group">	
    	<label for="qdate">작성일</label>
		<input type="text" class="form-control" name="qdate" id="qdate" value="${qna.qdate }" readonly>
    </div>
    
    <div class="form-group">	
    	<label for="qview">조회수</label>
		<input type="text" class="form-control" name="qview" id="qview" value="${qna.qview }" readonly>
    </div>
    
	<div class="form-group">					 
      	<label for="qcontent">내용</label>
		<textarea class="form-control" rows="8" name="qcontent" id="qcontent" readonly>${qna.qcontent }</textarea>
    </div>
    
     <div id="commentResult">
     
     </div>

	<div class="form-group">					 
      	<label for="comment">답글</label>
		<textarea class="form-control" rows="5" name="msg" id="msg"></textarea>
		
		 <c:if test="${admin==1 }">
		<input type="button" value="writeComment" id="commentBtn" class="btn btn-primary">
		</c:if>
		
    </div>


	<div align="left">
		<input type="button" value="목록" onclick="location='qnaList.me'" class="btn btn-primary"> 	
	</div>
	

