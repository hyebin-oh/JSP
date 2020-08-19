<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
<link href="../boot/plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../boot/plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="../boot/plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="../boot/plugins/OwlCarousel2-2.2.1/animate.css">
<link rel="stylesheet" href="../bootplugins/themify-icons/themify-icons.css">
<link rel="stylesheet" type="text/css" href="../boot/plugins/jquery-ui-1.12.1.custom/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="../boot/styles/single_styles.css">
<link rel="stylesheet" type="text/css" href="../boot/styles/single_responsive.css">


<script>
	$(document).ready(function(){
		$("#btnCart").click(function(){
			if(${sessionScope.userid==null||sessionScope.userid==""}){
				alert("로그인이 필요합니다");
				location.href="../member/login.jsp";
			}else{
				alert("장바구니에 추가되었습니다.");
			}
			
		})//btncart
	});//document
</script>

<form action="../cart/cartAdd.me" method="post">
<input type="hidden" id="product" name="product" value="${product.product}">s
<input type="hidden" id="ptype" name="ptype" value="${product.ptype }">
	<div class="container single_product_container">
		<div class="row">
			<div class="col">

				<!-- Breadcrumbs -->

				<div class="breadcrumbs d-flex flex-row align-items-center">
					<ul>
						<li><a href="../main/main.jsp">Home</a></li>
						<li><a href="singleProductList.me"><i class="fa fa-angle-right" aria-hidden="true"></i>${product.ptype }</a></li>
						<li class="active"><i class="fa fa-angle-right" aria-hidden="true"></i>${product.product}</a></li>
					</ul>
				</div>

			</div>
		</div>

		<div class="row">
			<div class="col-lg-7">
				<div class="single_product_pics">
					<div class="row">
						<div class="col-lg-3 thumbnails_col order-lg-1 order-2">

						</div>
						<div class="col-lg-9 image_col order-lg-2 order-1">
							<div class="single_product_image">
								<div class="single_product_image_background">
								<img src="../upload/${product.fileName }" alt="${product.product}" width="100%" height="100%">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-5">
				<div class="product_details">
				<br>
					<div class="product_details_title">
						<h2>${product.product}</h2>
						<br>
						<p>${product.pdetail}<p>
						<br>
					</div>
					<div class="free_delivery d-flex flex-row align-items-center justify-content-center">
						<span class="ti-truck"></span><span>주문 확인 후 배송은 2~3일 소요됩니다</span>
					</div>
			<br>
					<div class="quantity d-flex flex-column flex-sm-row align-items-sm-center">
						<span>수량</span>
						<div >
							<select name="count" id="count">
								<option value="1">1</option>
								<option value="2">2</option>
							</select>
	
						</div>
						<c:if test="${admin==0 }">
							<div><input type="submit" value="장바구니 추가" id="btnCart" class="btn btn-primary"></div>
						</c:if>
					</div>
					
				</div>
				 <c:if test="${admin==1 }">
               <input type="button" value="수정하기" id="btnProductUpdate" 
                		onclick="location.href='productUpdate.me?pnum=${product.pnum}'" class="btn btn-primary">
                </c:if>
			</div>
		</div>

	</div>

</form>

<%@ include file="../include/footer.jsp" %>