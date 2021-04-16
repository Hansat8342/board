<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
<style type="text/css">
body{text-align:center;}
table{
	border:3px double #7F9DB9;
	margin:auto;
	height:60%;
	width:60%;
}
h1{text-align:center;}
</style>
</head>
<script type="text/javascript">
var msg = '${resMsg}'
if(msg!=''){
	alert(msg)
}
</script>
<body>
	<h1>${vo.title }</h1>
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
		<input type="button" onclick="location.href='edit?bno=${vo.bno}'" value="수정"> <!-- 수정화면으로 이동. 수정프로세스를 작동시키는것이 아님. -->
		<input type="button" onclick="location.href='delete?bno=${vo.bno}'" value="삭제">
</body>
</html>