<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
 <%@ include file="../include/userMypage.jsp" %>

<form action="orderUpdate.me" method="post">
	<div align="left">
	<h3>${orderInfo.userid }님의 주문 내역</h3>
	</div>
	<div class="form-group">
		<label for="name">주문자명</label>
		<input type="text" class="form-control" id="name" name="name" value="${orderInfo.name }"  readonly>
	</div>
	<div class="form-group">
		<label for="addr">배송주소</label>
		<input type="text" class="form-control" id="addr" name="addr" value="${orderInfo.addr }" readonly>
	</div>
	
	<div class="form-group">
		<label for="phone">연락처</label>
		<input type="text" class="form-control" id="phone" name="phone" value="${orderInfo.phone }" readonly>
	</div>

    
     <div class="form-group">	
    	<label for="orderdate">주문일</label>
		<input type="text" class="form-control" name="orderdate" id="orderdate" value="${orderInfo.orderDate }" readonly>
    </div>
    
    <table class="table table-hover">
  
    <thead>
      <tr>
        <th>상품명</th>
        <th>수량</th>
        <th>주문 상태</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${order}" var="order" varStatus="st">
      <tr>
      	<td>${order.product }</td>
      	<td>${order.count }</td>
      	<td>${order.orderState }</td>
      <tr>
      </c:forEach>
      </tbody>
      </table>
	<div align="center">
		<input type="button" value="주문수정요청하기" onclick="location='../QnA/qnaInsert.jsp'" class="btn btn-primary">
	</div>
	
	<div align="left">
		<input type="button" value="목록" onclick="location='userOrderList.me'" class="btn btn-primary"> 
	</div>
