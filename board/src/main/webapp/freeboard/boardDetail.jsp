<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="freeboard.*" %>
    <%@ page import="java.util.*" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
     <jsp:useBean id="fdao" class="freeboard.FreeBoardDao"></jsp:useBean> <!-- BoardDao dao = new BoardDao();  -->
     <%
     	request.setCharacterEncoding("utf-8");
     	int num = Integer.parseInt(request.getParameter("num"));
     	FreeBoardVO fvo = fdao.selectOne(num);
     	pageContext.setAttribute("fvo", fvo);
     %>															 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글내용</title>


</head>
<body>
<h3>글정보</h3>
<p>번호:${fvo.num}</p>
<p>제목:${fvo.title}</p>
<p>작성자:${fvo.writer}</p>
<p>내용:${fvo.content}</p>
<p>등록일자:${fvo.regdate}</p>
<p>조회수:${fvo.cnt}</p>
<div>
			<div class="w3-border w3-center w3-padding">
				<c:if test="${ id == null }">
					추천 기능은 <button type="button" id="newLogin"><b class="w3-text-blue">로그인</b></button> 후 사용 가능합니다.<br />
					<i class="fa fa-heart" style="font-size:16px;color:red"></i>
					<span class="rec_count"></span>					
				</c:if>
				<c:if test="${ id != null }">
					<button class="w3-button w3-black w3-round" id="rec_update">
						<i class="fa fa-heart" style="font-size:16px;color:red"></i>
						&nbsp;<span class="rec_count"></span>
					</button> 
				</c:if>
			</div>
		</div>

<a href="<c:url value="/board/editForm.jsp?num=${vo.num}"/>"><button>수정</button></a>
<a href="<c:url value="/board/deleteForm.jsp?num=${vo.num}"/>"><button>삭제</button></a>
</body>
</html>