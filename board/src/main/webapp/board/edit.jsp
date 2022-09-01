<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="board.*" %>
    <%@ page import="java.util.*" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%
    request.setCharacterEncoding("utf-8");
    %>
    <jsp:useBean id="vo" class="board.BoardVO"/>
    <jsp:useBean id="dao" class="board.BoardDao"/>
    <jsp:setProperty name="vo" property="*"/>
    <%
    	dao.update(vo);
    pageContext.setAttribute("vo", vo);
    
    // response.sendRedirect(request.getContextPath() + "/board/list.jsp");
    // <c:redirect url="${pageContext.request.contextPath}/board/list.jsp"></c:redirect>
    // 둘이 같은 식
   
    %>
    <c:redirect url="${pageContext.request.contextPath}/boardDetail.jsp?num=${vo.num }"></c:redirect>