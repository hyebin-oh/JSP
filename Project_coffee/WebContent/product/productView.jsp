<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
  <script src="/Project_coffee/js/product.js"></script>
<head>
</head>
<style>
#contain{
    width: 700px;
    margin: 0 auto;
}

#prod-pic, #desc{
    float: left;
}

#prod-pic{
    margin: 20px, 20px, auto 10px;
    padding: 0;
}



#desc{
    width: 300px;
    padding-top: 20px;
    margin-bottom: 50px;
    display: center;
}


#desc button{
    margin-top: 20px;
    margin-bottom: 20px;
    width: 100%;
    padding: 10px;
}

#desc ul{
    list-style: none;
}

#desc li{
    font-size: 0.9em;
    line-height: 1.8;
}

#desc a{
    text-decoration: none;
    font-size: 0.9em;
    color: blue;
    padding-left: 40px;
}

hr{
    clear: both;
  
}


h1{
    font-size: 2em;
}

h2{
    font-size: 1.5em;
    color: #bebebe;
    font-weight: normal;
}

h3{
    font-size: 1.1em;
    color: #222;
}

p{
    font-size: 0.9em;
    line-height: 1.5;
    text-align: center;
}


</style>




</head>
<body>
<div class="main_slider">
    <div id="contain">
        <h1 id="heading">${product.product}</h1>
        <div id="prod-pic">
            <img src="../upload/${product.fileName }" alt="${product.product}" id="cup" width="200" height="200">
        </div>
        <div id="desc">
            <ul>                 
                <li>상품명 : ${product.product}</li>

                <li>수량:
               		<select name="oCount" id="oCount">
					<option value="1">1</option>
					<option value="2">2</option>
					</select>
				</li>

            </ul>
            
                 <input type="button" value="장바구니 담기">
                <c:if test="${admin==1 }">
               <input type="button" value="수정하기" id="btnProductUpdate" 
                		onclick="location.href=productUpdate.me?product=${product.product}'">
                </c:if>

    </div>
    
	<br>
        <div id="pdetail">
                        <hr>
            <h2>상품 정보</h2>

            <p>${product.pdetail }</p>

        </div>
    
    </div>
</div>
</body>

</html>

