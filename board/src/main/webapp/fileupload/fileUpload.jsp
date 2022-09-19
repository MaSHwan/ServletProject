<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!--cos.jar라이브러리가 없으면 완성이 안댐  -->
    <%@ page import = "com.oreilly.servlet.MultipartRequest" %>
    <%@ page import = "com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
    <%@ page import = "java.util.*" %>
    
    
    <!--파일 업로드는 트라이 캐치문과 같이  -->
    
   <%
   //업로드 경로 만들기 
   String uploadPath = request.getRealPath("/upload"); //upload 폴더에다
   int size = 10*1024*1024; //<- 10메가바이트임 포스트방식일때는 용량제한이 없지만 get방식일때는 용량제한이 있음
   
   String name ="";
   String subject="";
   String filename1="";
   String filename2="";
   String origfilename1 = ""; //orig ->오리지널
   String origfilename2 = ""; //orig ->오리지널
                                                  
   try{
	   MultipartRequest multi = new MultipartRequest(request, 
			 uploadPath, size, "utf-8", new DefaultFileRenamePolicy());
			
	   name = multi.getParameter("name");
	   subject = multi.getParameter("subject");
	
	   Enumeration files = multi.getFileNames();
	   
	   String file1= (String)files.nextElement(); //형변환 시켜줘야됨
	   filename1 = multi.getFilesystemName(file1);
	   origfilename1 = multi.getOriginalFileName(file1);
	   //여기서 쓰이는 메소드 놈들 servlet홈페이지가서 읽어야됨 ..
	   
	   String file2= (String)files.nextElement(); //형변환 시켜줘야됨
	   filename2 = multi.getFilesystemName(file2);
	   origfilename2 = multi.getOriginalFileName(file2);
	  //이파일들을 가지고 hidden으로 넘겨서 체크를 한다
	   
      }catch(Exception e) {
	   e.printStackTrace();
   }
   %> 
   
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<form action="fileCheck.jsp" method= "post" name="filecheck">
	<input type="hidden" name="name" value="<%=name%>">
	<input type="hidden" name="subject" value="<%=subject%>">
	<input type="hidden" name="filename1" value="<%=filename1%>">
	<input type="hidden" name="filename2" value="<%=filename2%>">
	<input type="hidden" name="origfilename1" value="<%=origfilename1%>">
	<input type="hidden" name="origfilname2" value="<%=origfilename2%>">
 
	
</form>
<a href="#" onclick="javascript:filecheck.submit()">업로드 확인 및 다운로드 페이지로 이동</a>

</body>
</html>