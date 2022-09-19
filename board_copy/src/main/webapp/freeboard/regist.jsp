<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="freeboard.*" %>
    <%@ page import="java.util.*" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
	<%@page import="com.oreilly.servlet.MultipartRequest"%>
    <%@ page import= "java.awt.Graphics2D" %>
    <%@ page import= "java.awt.image.renderable.ParameterBlock" %>
    <%@ page import= "java.awt.image.BufferedImage" %>
    <%@ page import= "javax.media.jai.JAI" %>
    <%@ page import= "javax.media.jai.RenderedOp" %>
    <%@ page import= "javax.imageio.ImageIO" %>
    <%@ page import = "java.util.*" %>
    <%@ page import = "java.io.*" %>
    <%
    request.setCharacterEncoding("utf-8");
    %>
    <jsp:useBean id="fvo" class="freeboard.FreeBoardVO"/>
    <jsp:useBean id="fdao" class="freeboard.FreeBoardDao"/>
    <jsp:setProperty name="fvo" property="*"/>
    <%
    	fdao.insert(fvo);
    %>
    <%
   	String imagePath = request.getRealPath("upload");
   	int size = 10 * 1024 * 1024; // 100mb
   	
   	//form에서 넘어오는 파일네임
   	String filename = "";
   	
    try {
    	
    	 MultipartRequest multi = new MultipartRequest(request, 
    	 imagePath, size, "utf-8", new DefaultFileRenamePolicy());
    			
    	  Enumeration files = multi.getFileNames();
    	  
    	 String file = (String)files.nextElement(); //형변환 시켜줘야됨
   	  	 filename = multi.getFilesystemName(file);
   
   	 	}catch(Exception e){
    	e.printStackTrace();
    	}
   		//파라미터블록 객체 생성
   		ParameterBlock pb = new ParameterBlock();
   		
   		//add 메소드를 써서 이미지 패스와 파일네임 추가
   		pb.add(imagePath+"/"+filename);
   		
   		//렌더링 만들기 이미지를 불러다가 파일을 만드는게 렌더링
   		RenderedOp rOp = JAI.create("fileload", pb);
   		
   		//버퍼에 렌더링한것을  버퍼드 이미지에 임시로 저장
   		BufferedImage bi = rOp.getAsBufferedImage(); 
   		//버퍼 사이즈 =가로와 세로 타입은 어떤건지 설정해줘야됨 
   		BufferedImage thumb = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
   		
   		
   		Graphics2D g = thumb.createGraphics();
   				//시작위치(0,0)부터    옵저버를 null
   		g.drawImage(bi, 0, 0, 100, 100, null);
   		
   				//파일로 불러오기 
   		File file = new File(imagePath+"/sm_"+filename);
   					//이미지 확장자 출력형태
   		ImageIO.write(thumb, "jpg", file);
   %> 
   <c:redirect url="../freeboard/freelist.jsp"/>
  

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
-원본 이미지 - <br>
<img alt="" src="/board/upload/<%=filename%>"><p>
- 썸 네일 이미지 -<br>
<img alt="" src="/board/upload/sm_<%=filename%>"><p>
</body>
</html>