<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<style type="text/css">
body{
	text-align:center;
}
div{
	border:1px solid #ccc;
	margin:auto;
	height:60%;
	weight:40%;
}
p{
	margin:auto;
	padding:5px;
}
</style>
</head>
<body>
	<h1>게시글 수정</h1>
	<form method="post" action="/board/edit">
		<input type=text name=bno value="${vo.bno}">
		<div>
			<p>제목 : <input type="text" name="title" value="${vo.title }">
			<p>내용 : <textarea name="content">${vo.content }</textarea>
			<p>작성자 : <input type="text" name="writer" value="${vo.writer }">
		</div>
		<input type="submit" value="수정">
		<input type="reset" value="취소">
	</form>
</body>
</html>