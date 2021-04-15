<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
</head>
<body>
	<table border=1>
		<!-- jstl 태그 라이브러리 사용하여 목록 작성 -->
		<tr>
			<td>제목</td><td><c:out value="${vo.title }"/></td>
		</tr>
		<tr>
			<td>내용</td><td><c:out value="${vo.content }"/></td>
		</tr>
		<tr>
			<td>작성자</td><td><c:out value="${vo.writer }"/></td>
		</tr>
	</table>	
</body>
</html>