<%@page import="org.apache.commons.collections4.bag.SynchronizedSortedBag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="freeboard.*" %>
    <%@ page import="java.util.*" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%
    	request.setCharacterEncoding("utf-8");
    	FreeBoardDao dao = new FreeBoardDao();
    	List<FreeBoardVO> ls = dao.selectAll();
    	
    	
    	pageContext.setAttribute("ls", ls);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../view/list.css">
<title>자유 게시판 목록</title>

<style>
.board-write-modal{
position : absolute;
top: 0;
left: 0;

width: 100%;
height: 100%;

display: none;

background-color: rgba(0, 0, 0, 0.4)
}
</style>
</head>
<body>
<div class="title">
<h2>자유 게시글 목록</h2>
</div>
<%-- <div>
<a class="write" href="<c:url value="/freeboard/registForm.jsp"/>"><button class="write_button">포스트 작성</button></a>
</div> --%>

<div class="present">
<div class="tabs-list">
<ul class="tabs-list">
	
	<li>
		<a href="../board/list.jsp">공지 게시판</a><br>
	</li>
	
	<li>
		<a href="../freeboard/freelist.jsp">자유 게시판</a>
	</li>
	
	<li>
		<a href="#">루트추천 게시판</a>
	</li>
	
	<li>
		<a href="#">루트질문 게시판</a>
	</li>
	
	<li>
		<a href="#">동행 게시판</a>
	</li>
	
	<li>
		<a href="#">나눔 게시판</a>
	</li>
</ul>
</div>
<div class="conts-box-list search">
	<div class="inner-box">
		<div class="community">
			<div class="write-tour-story">
				<p>당신의 이야기를 남겨주세요</p>
				<a class="write" href="<c:url value="/freeboard/registForm.jsp"/>"><span class="write_button">포스트 작성</span></a>
			</div>
<table border="1">
<tr><th>번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>등록일</th>
	<th>조회수</th>
	<th>좋아요</th>
</tr>
<c:forEach var="board" items="${ls}">
<tr>
		<td>${board.num}</td>
		<td><a href="${pageContext.request.contextPath}/freeboard/boardDetail.jsp?num=${board.num}">${board.title}</a></td>
		<td>${board.writer}</td>
		<td>${board.regdate}</td>
		<td>${board.cnt}</td>
		<td>${board.likenum}</td>
</tr>
</c:forEach>
</table>

</div> <!-- end community -->
</div> <!-- end inner-box -->

</div> <!-- end conts-box-list search -->
</div> <!-- end present -->




</body>
</html>