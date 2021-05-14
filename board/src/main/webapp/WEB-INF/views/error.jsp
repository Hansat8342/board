<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<% response.setStatus(200); %>
<!DOCTYPE html>
<head>
<script type="text/javascript">
alert("작업중 오류가 발생했습니다.");
history.back(-1);
</script>
</head>