<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import = "com.oreilly.servlet.MultipartRequest" %>
    <%@ page import = "com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
    <%@ page import = "java.util.*" %>
    <%@ page import = "java.io.*" %>
    
    <%
    
    String realFolder =""; // 실제폴더 몰르니 null값
    String saveFolder ="upload";
    String encType ="utf-8";
    int maxSize = 10* 1024 * 1024;
    ServletContext context = getServletContext();
    realFolder = context.getRealPath(saveFolder);
    
    ArrayList saveFiles = new ArrayList();
    ArrayList origFiles = new ArrayList();
    
    //파라미터를 가져와야됨
	String paramUser = "";
    String paramTitle ="";
    String paramAbstract ="";
    
    try{
    	//객체 생성
    	MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
    	
    	paramUser = multi.getParameter("txtUser");
    	paramTitle = multi.getParameter("txtTitle");
    	paramAbstract = multi.getParameter("txtAbstract");
 		
    	Enumeration files = multi.getFileNames();
    	
    	//있으면 가져옴 메서드
    	while(files.hasMoreElements()) {
    		String name = (String)files.nextElement(); //있으면 가져오는데 형변환 시켜줘야됨
    		saveFiles.add(multi.getFilesystemName(name));
    		origFiles.add(multi.getOriginalFileName(name));
    	}
    %>
   
   
 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File info Page</title>
<link rel="stylesheet" type="text" href="style.css"></link>
</head>
<body>
<table width="75%" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor ="#660000" bgcolor="#ffff99">
	<tr>
		<td width="10%" bgcolor="#FFCC00">
			<div align = "right"><strong>user</strong></div>
		</td>
		<td width="30%"><%=paramUser %></td>
		
		<td width="10%" bgcolor="#FFCC00">
			<div align = "right"><strong>title</strong></div>
		</td>
		<td width="30%"><%=paramTitle %></td>
	</tr>
	
	<tr>
		<td width="10%" bgcolor="#FFCC00">
			<div align = "right"><strong>Abstract</strong></div>
		</td>
		<td width="50%" colspan="3">
			<textarea rows="5" cols="50"><%=paramAbstract %></textarea>
		</td>
	</tr>
	
		<tr><td colspan="4" bgcolor="#ffffff">&nbsp;</td></tr>
		<tr><td colspan="4"><strog>업로드된 파일들입니다.</strog></td></tr>
		
		<%for(int i=0; i<saveFiles.size(); i++){ %>
			<tr bgcolor="#ffcc00">
				<td colsapn="4">
					<a href="<%= "/ServletProject2/"+saveFolder+"/"+saveFiles.get(i)%>">
						<strong><%=origFiles.get(i) %></strong>
					</a>
				</td>
			</tr>

		<%} %>
</table>

</body>
</html>
<%}catch(IOException ie) {
	ie.printStackTrace();
}catch(Exception e){
	e.printStackTrace();
}
%>