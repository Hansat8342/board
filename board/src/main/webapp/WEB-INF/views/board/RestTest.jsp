<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script type="text/javascript">

// 페이지 초기화 작업
$(document).ready(function() { 
	getAjaxList();
	
	// id가 getListBtn인 엘리먼트에 이벤트를 걸어줍니다 
	$("#getListBtn").on("click", function(){
		console.log("test");
		getAjaxList();
	});
});

function getAjaxList(){
	$.ajax({
		// 서버접속 url   222-> BNO 
		url : '/reply/list/222',
		method : 'get',
		dataType : 'json',
		
		success : function( data, textStatus, jqXHR ){
			
			console.log("data",data);
			
			var tblContent = "";
			
			$.each(data, function(index, item){
				tblContent +="<tr><td>" + item.reply + "</td><td>"+item.replyer+"</td></tr>";
			});
			
			$("#repleTbl").html(tblContent);
			
		},
		error : function ( jqXHR, textStatus, errorThrown ){
			console.log("textStatus", textStatus);
		}
		
	});
}
</script>


</head>
<body>
<button id="getListBtn">리스트</button>
<br>
<table border="1" id="repleTbl">

<tr><td>리플</td><td>작성자</td></tr>

</table>
</body>
</html>