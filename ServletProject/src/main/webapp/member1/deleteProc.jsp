<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="com.member1.*" %>
    <jsp:useBean id="dao" class="com.member1.StudentDAO"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
</head>
<%  String id= (String)session.getAttribute("loginID");
	String pass= request.getParameter("pass");
	int check=dao.deleteMember(id, pass);
	if(check == 1){
		session.invalidate();
	
%>

<meta http-equiv="Refresh" content="3;url=login.jsp">
<body>
<div align="center">
	<font size="5" face="궁서체">
	회원탈퇴완료<br><br>
	3초후에 login page로 이동합니다.
	</font>
</div>

<%} else{%>
	<script type="text/javascript">
	alert("비밀번호가 맞지 않습니다")
	history.go(-1);
	</script>
<%} %>

</body>
</html>