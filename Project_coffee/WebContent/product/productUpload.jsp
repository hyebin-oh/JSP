<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<div class="main_slider">
	<div id=container1>
		<h1>상품등록하기</h1>
		<form action="fileUpload.me" enctype="multipart/form-data" method="post" >
		<div id="prod-pic">
			<input type="file" name="file">
		
		</div>
		<div id="desc">
			<ul>
				<li> 상품종류
					<select name="ptype" id="ptype">
						<option value="single">싱글오리진</option>
						<option value="blend">블렌딩</option>
					</select>
				</li>
				<li>상품명 <input type="text" id="product" name="product"></li>
				<li>상세 정보
					<textarea id="pdetail" name="pdetail"></textarea>
				</li>
			</ul>
		</div>
		<input type="submit" value="업로드">
		</form>
	</div>