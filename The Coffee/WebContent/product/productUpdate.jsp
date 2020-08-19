<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>



<div class="container single_product_container">

<form action="productUpdate.me" enctype="multipart/form-data" method="post" >
<input type="hidden" id="pnum" name="pnum" value="${product.pnum }">
<input type="hidden" id="fileName" name="fileName" value="${product.fileName }">
	<br>
	<h3>${product.product } 수정하기 </h3>
	<hr>
				
	<!-- 상품 선택, 입력 -->	
	<div class="product_details" align="left">
		<input type="file" name="file" ><span>${product.fileName }</span>
		
		<div class="form-group">
     		<label for="ptype">상품종류</label>
   			<select class="form-control" id="ptype" name="ptype">
				<option value="single">싱글오리진</option>
				<option value="blend">블렌딩</option>		
	  		</select>		
	  	</div>
					
		<div class="form-group">					 
      		<label for="product">상품명:   </label>
      		<input type="text" class="form-control" id="product" name="product" value="${product.product }" }>
    	</div>
    
		<div class="form-group">
  			<label for="pdetail">상품 설명</label>
 			<textarea class="form-control" rows="8" id="pdetail" name="pdetail">"${product.pdetail }"</textarea>
		</div>

	</div>
	
	<div align="center">
    	<input type="submit" value="수정완료" class="btn btn-primary">
        <input type="button" value="수정 취소" onclick="location.href='productView.me?pnum=${product.pnum }'" class="btn btn-primary">
	</div>			

</form>

</div>