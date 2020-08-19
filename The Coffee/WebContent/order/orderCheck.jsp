<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>


<div class="container single_product_container">
<div class="container">
<form action="orderInsert.me" method="post" >
	<br>
	<h3>${order.userid }님의 신청 확인 </h3>
	<hr>
				
	<!-- 상품 선택, 입력 -->	
	<div class="product_details" align="left">
		
		<div class="form-group">					 
      		<label for="name">주문자명 </label>
      		<input type="text" class="form-control" id="name" name="name" value="${order.name }" >
    	</div>
    
		<div class="form-group">					 
      		<label for="phone">연락처 </label>
      		<input type="text" class="form-control" id="phone" name="phone" value="${order.phone }" >
    	</div>
    	
    	<div class="form-group">					 
      		<label for="addr">주소 </label>
      		<input type="text" class="form-control" id="addr" name="addr" value="${order.addr }" >
    	</div>
  
 		<table class="table table-bordered table-sm">
 			<label>신청 내역 </label>
	    	<thead class="thead-dark">
   				<tr>
        			<th>제품명</th>
        			<th>수량</th>        			
      			</tr>
    		</thead>
    		
    		<tbody>
    		  <c:forEach items="${cartList }" var="cartList" varStatus="var">
      			<tr>
        			<td><input type="hidden"  id="product" name="product" value="${cartList.product }" readonly="readonly">${cartList.product }</td>
        			<td> <input type="hidden" id="count" name="count" value="${cartList.count }" readonly>${cartList.count }</td>
        		</tr>
        	  </c:forEach>
        	</tbody>
        </table>

	</div>
	
	<div align="center">
    	<input type="submit" value="주문완료" class="btn btn-primary"> 
        <input type="button" value="취소" onclick="location.href='../cart/cartList.me'" class="btn btn-primary">
	</div>			

</form>

</div>