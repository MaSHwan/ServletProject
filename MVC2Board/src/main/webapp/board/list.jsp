<%@page import="org.apache.commons.collections4.bag.SynchronizedSortedBag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%
    	
    	pageContext.setAttribute("ls", ls);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../view/list.css">
<title>공지게시판 목록</title>
</head>
<body>
<h2>공지게시글 목록</h2>

<div>
<a class="write" href="<c:url value="/board/registForm.jsp"/>"><span class="write_button">포스트 작성</span></a>
</div>
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
		<td><a href="${pageContext.request.contextPath}/board/boardDetail.jsp?num=${board.num}">${board.title}</a></td>
		<td>${board.writer}</td>
		<td>${board.regdate}</td>
		<td>${board.cnt}</td>
		<td>${board.likenum}</td>
</tr>
</c:forEach>
</table>

<%-- <a href="<c:url value="/board/registForm.jsp"/>"><button>글등록</button></a> --%>

<!-- <a id="write_new_post_btn">
	<span>포스트 작성</span>
</a>

 <div style="position: relative;height:100%;width:100%;overflow:auto;"> 컨테이너 
	 <div class="modal-dialog write-note-pop"> 모달을 감쌀 박스
		<div class="modal-content"> 실질적 모달팝업
			<div class="modal-header"> 로고영역
				<h5 class="modal-title">포스트 작성</h5>
				<button type="button" class="close btn-note-edit-cancel">
				<span aria-hidden="true">x</span>
				</button>
			</div>
			<div class="modal-body">
			<div class="write-note-box">
			<div class="write-inner">
			<div id="note-title-panel" class="note-tit"> 게시판 선택 박스 & 제목이 들어갈 패널
				<div class="category dropdown">
					<a class="dropdown-toggle note-category-selected" data-toggle="dropdown" aria-expanded="false">자유 게시판</a>
					
					<div class="dropdown-menu">
						<ul class="note-category-options">
							<li><a data-value="1">자유</a></li>
							<li><a data-value="2">루트추천</a></li>
							<li><a data-value="3">루트질문</a></li>
							<li><a data-value="4">동행</a></li>
							<li><a data-value="5">나눔</a></li>
						</ul>
					</div>
				</div>
				
				<div class="title">
					<div>
						<p title="subject">
						<input type="text" name id="note-title-input" maxlength="200" placeholder="제목" value>
						</p>
					</div>
				</div>
			</div> note-tit
			
			
			<div class="write-area"> 컨텐츠 영역
				<div id="editor_panel" class="editable medium-editor-element medium-editor-placeholder" style="min-height: 250px;"
				data-placeholder="내용을 적어주세요." ondragstart="return false;" ondrop="return false;" contenteditable="true" spellcheck="true"
				data-medium-editor-editor-index="3" medium-editor-index="5ef7da48-7b0f-ab7d-8692-c94ddd6593b7">
					
				</div>
			</div>
		</div> write-inner
	 </div> write-note-box
</div>
</div>
</div>
</div> -->

</body>
</html>