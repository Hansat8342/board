<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %> <!-- jstl 태그 라이브러리 적용 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
<script type="text/javascript">
var msg = '${resMsg}'
if(msg!=''){
	alert(msg)
}
</script>
<h1>게시판</h1>
	<table border=1>
		<!-- jstl 태그 라이브러리 사용하여 목록 작성 -->
		<c:forEach items="${list }" var="list"><!-- c:forEach 는 리스트를 반복해서 가져오는 태그 -->
		<tr>
			<td><c:out value="${list.bno }"/></td>
			<td><a href="/board/get?bno=${list.bno }"><c:out value="${list.title }"/></a></td>
			<td><c:out value="${list.writer }"/></td>
			<td><c:out value="${list.regdate }"/></td>
		</tr>
		</c:forEach>
	</table>
	<div>
		<p><input type="button" onclick="location.href='register'" value="글쓰기"></p>
	</div>
</body>
</html>