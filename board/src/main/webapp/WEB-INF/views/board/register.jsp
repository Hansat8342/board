<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<style type="text/css">
</style>
</head>
<body>
<form method="post" action="/board/register">
	<div>
		<p>제목 : <input type="text" name="title">
		<p>내용 : <textarea name="content"></textarea>
		<p>작성자 : <input type="text" name="writer">
	</div>
	<input type="submit" value="제출">
</form>
</body>
</html>