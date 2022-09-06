<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.boardone.BoardDAO"%>
    <%@page import="com.boardone.BoardVO"%>
    <%@page import="java.util.List" %>
    <%@page import="java.text.SimpleDateFormat" %>
    <%@ include file = "view/color.jsp" %> 
    
    <!--심플 데이터의 객체 생성  -->
    <% 
    
    //한 페이지에  보여줄 글 목록 수 지정
    int pageSize = 3;
    
    //날짜 형식 지정
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm"); 
    
    //페이지 num을 가져온다
    String pageNum = request.getParameter("pageNum");
    
    
    //검색 파라미터값으로 가져온다
    //무엇을 검색할지 파라미터 가져와야함(작성자,제목,내용)
    String searchWhat = request.getParameter("searchWhat");
    
    //검색할 내용
    String searchText = request.getParameter("searchText");
    
    //가져온 파라미터를 한글로 인코딩 변환
   if(searchText != null){                  //utf-8 두번쓰는이유가 하나는 키 다른건 파라미터               
      searchText = new String(searchText.getBytes("UTF-8"),"UTF-8");
   }
    
    if(pageNum == null){
       pageNum = "1";
    }
    //현재 페이지 
    int currentPage =Integer.parseInt(pageNum);
    
    int startRow = (currentPage-1) * pageSize + 1; 
    int endRow = currentPage* pageSize;
    
    
    
    
    int count = 0;
      int number =0;
      List<BoardVO> articleList = null;
      BoardDAO dbPro = BoardDAO.getInstance(); // db연결
      
      //검색이 아니면 전체 목록을 보여줘야되고 , 검색이면 검색한 내용만 보여줘야 된다.
      if(searchText == null) { //null 이면 검색이 아닌경우
         
          //전체글수를 가져옴
          count = dbPro.getArticleCount(); 
       if(count > 0){ 
       //하나라도 존재 하면 리스트를 출력해라
       articleList = dbPro.getArticles(startRow, endRow);
    }
       
} else { // 검색 일 경우
   
   count = dbPro.getArticleCount(searchWhat, searchText); 
      if(count > 0){ 
      //하나라도 존재 하면 리스트를 출력해라
      articleList = dbPro.getArticles(searchWhat, searchText, startRow, endRow);
   }
      
}
   
  
    number = count - (currentPage -1) * pageSize;
    
    %>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body bgcolor = "<%= bodyback_c%>">

<div align ="center"><b>글 목록(전체 글 :<%= count%>)</b>
   
<table width ="700">
   <tr>
      <td align ="right" bgcolor = "<%= value_c %>">
         <a href ="writeForm.jsp">글쓰기</a>
      </td>
   </tr>
</table>   

   <% if(count == 0) {%> 
   
<table width ="700" border = "1" cellpadding ="0" cellspacing = "0"> 
   <tr>
      <td align ="center">
         게시판에 저장된 글이 없습니다.
      </td>
   </tr>
</table>
   
<% } else { %>

<table width ="700" border = "1" cellpadding ="0" cellspacing = "0" align = "center">
      <tr height = "30" bgcolor ="<%=value_c %>">
         <td align ="center" witdh="50">번호</td>
         <td align ="center" witdh="250">제목</td>
         <td align ="center" witdh="100">작성자</td>
         <td align ="center" witdh="150">작성일</td>
         <td align ="center" witdh="50">조회수</td>
         <td align ="center" witdh="100">IP</td>
   </tr>
   
<% 
   //article리스트 뿌려야된다 
   for(int i =0; i<articleList.size(); i++){
   BoardVO article = (BoardVO)articleList.get(i);
   
%>   
   <tr height = "30">   <!--내림차순으로 출력하기 위해 -- 연산자  -->
   <td align = "center" width ="50"> <%= number-- %></td>
   <!-- 제목을 눌렀을때 글의 내용이 보이도록 연결 시켜줘아댜된다 -->
   <td width ="250">
   
   <%
   int wid = 0;
   
   if(article.getDepth() > 0) {
      wid = 5 * (article.getDepth());   
      
   %>
   
   <img alt="" src="img/level.gif" width= "<%= wid %>" height = "16">
   <img src= "img/re.gif">
   
   <% } else { %>
   <img alt="" src="img/level.gif" width= "<%= wid %>" height = "16">
   <% } %>
   
   
   
   <a href = "content.jsp?num=<%=article.getNum() %>&pageNum=<%=currentPage %>">
   <%= article.getSubject() %></a>
   <% if(article.getReadcount() >= 20){ %> <!-- 조회수가 20번이상 때 이미지 삽입 -->
   <img src="img/hot.gif" border = "0" height = "16">
   <% } %>
   
   </td>
   <td align = "center" width ="100">
   <a href = "mailto: <%= article.getEmail() %>">
   <%= article.getWriter() %></a>
   </td>
   
   <td align ="center" witdh="150">
   <%= sdf.format(article.getRegdate()) %>
   </td>
   
   <td align ="center" witdh="50"><%= article.getReadcount() %></td>
   
   <td align ="center" witdh="100"><%= article.getIp() %></td>
   
   
         
   
   
<% } //end for문 %>   

</table> 

<% } // end else문 %>


<!-- 여기다가 페이지 블록  -->
<%
//페이지 블럭

if(count > 0){
 
 int pageBlock = 4;

 int imsi = count % pageSize == 0? 0:1;
 
 int pageCount = count/pageSize + imsi;
 
 //시작 페이지
 int startPage = (int)((currentPage-1)/pageBlock)*pageBlock+1;   
 
 //마지막 페이지
 int endPage = startPage + pageBlock -1;
 
 //마지막으로 보여줄 페이지
 if(endPage > pageCount)endPage = pageCount;
 
 //페이지 블럭을 이전과 다음 작업
 if(startPage > pageBlock){
    //검색일 경우와 아닐 경우 페이지 처리
    if(searchText == null){
%>
    <a href="list.jsp?pageNum=<%=startPage-pageBlock%>">[이전]</a>
 <%}else{%>
    <a href="list.jsp?pageNum=<%=startPage-pageBlock%>&searchWhat=<%=searchWhat%>&searchText=<%=searchText%>">[이전]</a>
<%
 }//end else
}//end if
   for(int i = startPage; i <= endPage; i++){
    if(searchText == null){
%>
   <a href="list.jsp?pageNum=<%=i %>">[<%=i%>]</a>
    <%}else{ %>
   <a href="list.jsp?pageNum=<%=i %>&searchWhat=<%=searchWhat%>&searchText=<%=searchText%>">[<%=i%>]</a>
<%
    }//end else
 }//end for
    if(endPage < pageCount){
       if(searchText == null){
%>   
    <a href="list.jsp?pageNum=<%=startPage+pageBlock%>">[다음]</a>
    <%}else{ %>
    <a href="list.jsp?pageNum=<%=startPage+pageBlock%>&searchWhat=<%=searchWhat%>&searchText=<%=searchText%>">[다음]</a>
<%
    }//endelse
 }//end if
}//end 종합 if(count > 0)
%>   

<!-- 검색 창 -->
   <form action="list.jsp">
      <select name = "searchWhat" >
         <option value ="writer">작성자</option>
         <option value ="subject">제목</option>
         <option value ="content">내용</option>
      </select>
      <input type="text" name ="searchText">
      <input type="submit" name ="검색">
   </form>




</div>

</body>
</html>