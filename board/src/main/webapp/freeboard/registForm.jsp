<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../view/regist.css">
<title>글 등록</title>
</head>
<body>
<h3>등록하기</h3>
<form action="../freeboard/regist.jsp" method="post">
	<input type="text" name="title" placeholder="제목" required><br>
	<input type="text" name="writer" placeholder="작성자" required><br>
	<textarea rows="30" cols="120" name ="content" placeholder="내용"></textarea><br>
	
	
	<div class="btn-box-option">
	<a id="note-photo-button" class="wi-photo">
	<img src="../images/icon-photo.png">
	<input id="note-img-upload" type="file" name="file"><br>
	</a>
	
	
	<div class="write-btn-box">
	 <input id="btn-save" class="write-btn-primary" type="submit" value="저장">
	</div>
	</div> <!-- btn-box-option -->
</form>
</body>
</html>