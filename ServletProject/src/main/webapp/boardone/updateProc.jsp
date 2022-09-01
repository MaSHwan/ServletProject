<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.boardone.BoardVO" %>
    <%@ page import="com.boardone.BoardDAO" %>
    <% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="article" class="com.boardone.BoardVO" scope="page">
	<jsp:setProperty name="article" property="*"></jsp:setProperty>
</jsp:useBean>
<%
	String pageNum = request.getParameter("pageNum");
	BoardDAO dbPro = BoardDAO.getInstance();
	
	int check = dbPro.updateArticle(article);
	
	if(check == 1) {
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<meta http-equiv="Refresh" content="0; url=list.jsp?pageNum=<%=pageNum%>">
<body>
	
	<% } else { %>
		<script type="text/javascript">
			alert("비밀번호가 맞지 않습니다.");
			history.go(-1);
		</script>
	<% } %>
</body>
</html>