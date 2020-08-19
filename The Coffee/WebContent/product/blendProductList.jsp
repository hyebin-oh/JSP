<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<script>

function goCart(pname,ptype){
	$.ajax({
		type: "post",
		url: "../cart/cartAdd.me",
		data: {"product": pname, "count":1, "ptype": ptype},
		success: function(data){
			if(${sessionScope.userid==null||sessionScope.userid==""}){
				alert("로그인이 필요합니다");
			}else{
				if(${admin==1}){
					alert("관리자는 장바구니에 추가할 수 없습니다");
				}else{
					alert("장바구니에 추가되었습니다");
					location.href="../cart/cartList.me";
				}			
			}
		},
		error: function(e){
			alert("에러"+e);
		}
	})
}
</script>


<div class="main_slider">
<br>
	

	<div class="blogs">
		<div class="container">
			<div class="row">
				<div class="col text-center">
					<div class="section_title">
						<h2>블렌드</h2>
					</div>
				</div>
			</div>
			<div class="row blogs_container">
		<c:forEach items="${blend}" var="blend">	
				<div class="col-lg-4 blog_item_col">
					<div class="blog_item">
						<div class="blog_background">
							<img src="../upload/${blend.fileName }" class="coffeeimg" width="100%" height="100%">
						</div>
						<div class="blog_content d-flex flex-column align-items-center justify-content-center text-center">
							<h4 class="blog_title"><a href="productView.me?pnum=${blend.pnum}">${blend.product}</a></h4>
							<span class="blog_meta"></span>
							
							<!-- 장바구니 담기 -->
							
							<a class="blog_more" id="goCart" href="javascript:goCart('${blend.product }','${blend.ptype }')">장바구니 담기</a>
						</div>
					</div>
				</div>
		</c:forEach>		
		
	
			</div>
		</div>
	</div>
	
	

	
	</div>
