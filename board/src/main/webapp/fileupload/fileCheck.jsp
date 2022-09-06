<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
    
    <!--fileUpload에 만든 변수들을  매개변수로 넘겨야만 된다 fileCheck.jsp로  -->
    <!--이것을 안해높으면 업로드 확인 및 다운로드 페이지로 이동이 안됨 -->
    <%
    request.setCharacterEncoding("utf-8");
    
    String name = request.getParameter("name");
    String subject = request.getParameter("subject");
    String filename1= request.getParameter("filename1");
    String filename2 = request.getParameter("filename2");
    String origfilename1= request.getParameter("origfilename1");
    String origfilename2= request.getParameter("origfilename2");
 
  
    %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 확인 및 다운로드</title>
</head>
<body>

올린사람 : <%= name %>
제목 : <%=subject %>
파일명1 :<a href ="/ServletProject2/upload/<%=filename1%>"><%= origfilename1 %></a>
파일명2 :<a href ="/ServletProject2/upload/<%=filename2%>"><%= origfilename2 %></a>

</body>
</html>