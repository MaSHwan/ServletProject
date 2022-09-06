<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ include file = "view/color.jsp" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body bgcolor = "${bodyback_c}">
<!-- 폼안 테이블에 뿌려준다.  -->
<div align = "center"><b>글 내용 상세 보기</b>
<br><br> 
	<form>
		<table width = "500" border = "1" cellpadding ="0" cellspacing = "0" bgcolor = "${value_c}" align = "center">
			<tr height = "50"> <!-- 글 번호 하고 조회수가 나와야됨 -->
				<td align = "center" width ="125" bgcolor="${bodyback_c}">글번호</td>
				<td align = "center" width ="125">
				${article.num }
				</td>
				<td align = "center" width ="125" bgcolor="${bodyback_c}">조회수</td>
				<td align = "center" width ="125">
				${article.readcount }
				</td>
			</tr>
			
			<!--작성자와 작성일을  -->
			<tr height = "50"> <!-- 글 번호 하고 조회수가 나와야됨 -->
				<td align = "center" width ="125" bgcolor="${value_c}">작성자</td>
				<td align = "center" width ="125">
				${article.writer }
				</td>
				<td align = "center" width ="125" bgcolor="${value_c}">작성일</td>
				<td align = "center" width ="125">
				${article.regdate }
				</td>
			</tr>	
				
				
				<!--글 제목  글 내용 출력-->
			<tr height = "50">
				<td align = "center" width ="125" bgcolor="${value_c}">글제목</td>
				<td align = "center" width ="375" colspan="3">
				${article.subject }
				</td>
			</tr>
			
			<tr height = "50">
				<td align = "center" width ="125" bgcolor="${value_c}">글내용</td>
				<td align = "left" width ="375" colspan="3">
				<pre>${article.content }</pre> <!--pre태그 미리 정의된 형식의 텍스트를 정의할 떄 사용-->
				</td>
			</tr>	
				
					<!-- -->
			<tr height = "30">
				<td align = "right" bgcolor="${value_c}" colspan="4">
				 	<input type = "button" value = "글 수정" onclick ="document.location.href='/Mvc2Project/board/updateForm.bdo?num=${article.num }&pageNum=${pageNum }'">
				 	&nbsp;&nbsp;&nbsp;&nbsp;
				 	
					
				 	<input type = "button" value = "글 삭제" onclick ="document.location.href='/Mvc2Project/board/deleteForm.bdo?num=${article.num }&pageNum=${pageNum }'">
				 	&nbsp;&nbsp;&nbsp;&nbsp;
				 	
				 	<input type = "button" value = "답글쓰기" onclick ="document.location.href='/Mvc2Project/board/writeForm.bdo?num=${article.num }&ref=${article.ref }&step=${article.step }&depth=${article.depth }'">
				 	&nbsp;&nbsp;&nbsp;&nbsp;
				 	
				 	<input type = "button" value = "글 목록" onclick ="document.location.href='/Mvc2Project/board/list.bdo?pageNum=${pageNum }'">
				 	&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>	
				
		</table>

	</form>
</div>





</body>
</html>