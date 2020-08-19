<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<div class="container single_product_container">
<br>
<h3>장바구니</h3>
<hr>
 <div class="container">
 <c:if test="${count==0 }">
 <div align="center">
 	상품이 존재하지 않습니다
 </div>
 </c:if>
<c:if test="${count!=0 }">
 <div align="left">
 총 ${count}개의 상품이 존재합니다
 </div>
<table class="table table-hover">
<thead>
      <tr>
        <th>no</th>
        <th>타입</th>     
        <th>상품</th>
        <th>수량</th>
        <th>삭제</th>
        </tr>
    </thead>
    <tbody>
      <c:forEach items="${cart}" var="cart" varStatus="st">
      	<tr>
     	    <td>${st.count}</td>
     	    <td>${cart.ptype }</td>
     	    <td>${cart.product}</td>
     	    <td>${cart.count }</td>
     	    <td><a href="cartDelete.me?cnum=${cart.cnum }">삭제</a></td>
     	  </tr>     	    
    </c:forEach>
    </tbody>
  </table>
  </c:if>
  </div>
  
   <c:if test="${count!=0 }">
  		<input type="button" class="btn btn-primary" value="주문하기" onclick="location.href='../order/orderCheck.me?userid=${userid}'">
  	</c:if>
  </div>
