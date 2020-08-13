<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ include file="../include/adminMypage.jsp" %>

  <div class="container">
  <h2>상품 게시글</h2>
  <table class="table table-hover">  	
    <thead>
      <tr>
        <th>no</th>
        <th>타입</th>
        <th>상품</th>
        <th>삭제</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${product}" var="product" varStatus="st">
      	<tr>
     	    <th>${product.pnum }</th>
    	    <th>${product.ptype }</th>
     	   <th><a href="productView.me?product=${product.product }">${product.product }</a></th>
     	   <th><a href="#">삭제</a></th>
    	  </tr>
		</c:forEach>
    </tbody>
  </table>
 </div>