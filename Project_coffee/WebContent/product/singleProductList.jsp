<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>


<div class="main_slider">

<div class="new_arrivals">
		<div class="container">
			<div class="row">
				<div class="col text-center">
					<div class="section_title new_arrivals_title">
						<h2>싱글오리진</h2>
					</div>
				</div>
			</div>

			<div class="row">
				
				<div class="col">
								
					<div class="product-grid" data-isotope='{ "itemSelector": ".product-item", "layoutMode": "fitRows" }'>

						<!-- Product 1 -->
<c:forEach items="${single }" var="single">
						<div class="product-item">

							<div class="product discount product_filter">
							
								<div class="product_image">
									<img src="../upload/${single.fileName }" alt="">
								</div>
								
								<div class="product_info">
									<h6 class="product_name"><a href="productView.me?product=${single.product}">${single.product}</a></h6>
								</div>
							</div>
											<div class="red_button add_to_cart_button"><a href="#">장바구니에 담기</a></div>
				
						</div>
													</c:forEach>
				
						

						
					</div>

					
				</div>
			</div>
				
		</div>
	</div>