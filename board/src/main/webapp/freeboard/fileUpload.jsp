<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="freeboard.*" %>
    <%@ page import="java.util.*" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
    <%
    request.setCharacterEncoding("utf-8");
    %>
    <jsp:useBean id="fvo" class="freeboard.FreeBoardVO"/>
    <jsp:useBean id="fdao" class="freeboard.FreeBoardDao"/>
    <jsp:setProperty name="fvo" property="*"/>
    <%
    	fdao.insert(fvo);
    
    // response.sendRedirect(request.getContextPath() + "/freeboard/list.jsp");
    // <c:redirect url="${pageContext.request.contextPath}/board/list.jsp"></c:redirect>
    // 둘이 같은 식
   
    %>
   <c:redirect url="../freeboard/freelist.jsp"/>
   <!-- <c:redirect url="http://localhost:9090/freeboard/list.jsp"/> -->
  <!--   <c:redirect url="${ reqeust.getContextPath()}/freelist.jsp"/> -->

    