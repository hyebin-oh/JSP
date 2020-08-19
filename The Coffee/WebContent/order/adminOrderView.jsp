<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
 <%@ include file="../include/adminMypage.jsp" %>

<script>
$(document).ready(function(){
	function chageOrder(changeState){
		$.ajax({
			type:"post",
			url:"../order/adminOrderView.me",
			data:{"orderstate": chageState},
			success: function(date){
				location.href="orderUpdate.me";
			},
			error:function(e){
				alert("에러"+e)
			}
		})
	}
})

</script>



<form action="orderUpdate.me" method="post">
	<div align="left">
	<h3>${orderInfo.userid }님의 주문 내역</h3>
	</div>
	<input type="hidden" name="ordernum" id="ordernum" value="${orderInfo.ordernum }">
	<div class="form-group">
		<label for="name">주문자명</label>
		<input type="text" class="form-control" id="name" name="name" value="${orderInfo.name }" >
	</div>
	<div class="form-group">
		<label for="addr">배송주소</label>
		<input type="text" class="form-control" id="addr" name="addr" value="${orderInfo.addr }" >
	</div>
	
	<div class="form-group">
		<label for="phone">연락처</label>
		<input type="text" class="form-control" id="phone" name="phone" value="${orderInfo.phone }" >
	</div>

    
     <div class="form-group">	
    	<label for="orderdate">주문일</label>
		<input type="text" class="form-control" name="orderdate" id="orderdate" value="${orderInfo.orderDate }" >
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
      	<td><input type="text" name="orderstate" id="orderstate" value="${order.orderState }"></td>
      <tr>
      </c:forEach>
      </tbody>
      </table>
	<div align="center">
		<input type="submit" value="수정완료" class="btn btn-primary" >
		<input type="reset" value="수정취소" class="btn btn-primary">
		<input type="button" value="주문 삭제" class="btn btn-primary" onclick="location='adminOrderDelete.me?ordernum=${orderInfo.ordernum}'">
		
	</div>
</form>	
	<div align="left">
		<input type="button" value="목록" onclick="location='adminOrderList.me'"> 
	</div>

