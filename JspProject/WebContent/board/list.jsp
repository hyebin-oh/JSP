<%@page import="com.board.BoardVO"%>
<%@page import="com.board.BoardDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<%
	BoardDAO dao=BoardDAO.getInstace();
	String field ="", word="";
	ArrayList<BoardVO > arr = null;
	int count=0;
	if(request.getParameter("word")!=null){
		field=request.getParameter("field");
		word=request.getParameter("word");
		arr=dao.boardList(field, word);
		count=dao.boardCount(field, word);
	}else{
		arr = dao.boardList();
		count = dao.boardCount();
	}
	
%>
</head>
<body>
<div align="right">
<a href=""><% %></a>반갑습니다.
<a href="logout.jsp">로그아웃</a><br>
전체 게시글 수 :<span id="cntSpan"> <%=count%></span><br>
<a href="writeForm.jsp">글쓰기</a>
</div>
<table class="table table-striped">
	<thead>
		<tr>
			<th scope="col">번호</th>
			<th scope="col">제목</th>
			<th scope="col">작성자</th>
			<th scope="col">작성일</th>
			<th scope="col">조회수</th>
			<th scope="col">IP주소</th>
		</tr>
	</thead>
	<tbody>
		<% for(BoardVO vo : arr){ %>
		
		<tr>
			<td><%=vo.getNum() %></td>
			<td><a href="boardView.jsp?num=<%=vo.getNum() %>"><%=vo.getSubject() %></a></td>
			<td><%=vo.getWriter() %></td>
			<td><%=vo.getReg_date() %></td>
			<td><%=vo.getReadcount() %></td>
			<td><%=vo.getIp() %></td>
		</tr>
		<% } %>
	</tbody>
</table>
<form action="list.jsp" name="search" method="get">
	<table>
		<tr>
			<td align=center>
			<select name="field" size=1>
				<option value="subject">제 목
				<option value="writer">작성자
			</select>
			<input type="text" size=16 name="word">
			<input type="submit" value="찾기">
			</td>
		</tr>
	</table>	
</form>
<div align="center">
	<%
		int pageSize=5;
		if(count>0){ //11  =   53/5             (53%5==0)
			int pageCount=count/pageSize+(count%pageSize==0?0:1);
			int pageBlock=3;
			int startPage = (int)((currentPage-1)/pageBlock)*pageBlock+1;
			int endPage = startPage+pageBlock-1;
			if(endPage>pageCount){
				endPage = pageCount;//endpage=11
			}
			//이전
			
			//for
			
			///다음
		}
	%>
</div>


</body>
</html>