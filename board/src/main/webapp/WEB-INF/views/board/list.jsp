<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<style type="text/css">
h1{text-align:center;}
table{
	margin:auto;
	height:60%;
	width:60%;
	margin:auto;
}
</style>
</head>
<body>
<h1>게시판</h1>
	<table border=1>
		<!-- jstl 태그 라이브러리 사용하여 목록 작성 -->
		<c:forEach items="${list }" var="list"><!-- c:forEach 는 리스트를 반복해서 가져오는 태그 -->
		<tr>
			<td><c:out value="${list.bno }"/></td>
			<td><c:out value="${list.title }"/></td>
			<td><c:out value="${list.writer }"/></td>
			<td><c:out value="${list.regdate }"/></td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>