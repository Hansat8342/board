<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script type="text/javascript">
	//화면이 준비가 되면 실행
	$(document).ready(function(){
		
		$("#attachNo").on("change", function(){
			getFileList($("#attachNo").val()); //this.value 도 가능
		});
		
		//id 가 uploadBtn 인 엘리먼트에 클릭 이벤트 부여
		$("#uploadBtn").on("click",function(){
			//formData 생성
			var formData = new FormData(document.uploadForm);
			console.log("attachNo"+formData.get("uploadFile"));
			
			// 파일 업로드 컨트롤러 호출
			// 파일을 서버에 저장 합니다.
			$.ajax({
				url : '/fileUploadAjax',
				method : 'post',
				
				processData : false,
				contentType : false,
				data : formData,
				dataType : 'json',
				
				success : function(result){
					console.log("uploadAjax result", result);
					$("#attachNo").val(result.attachNo);
					$("#uploadFile").val("");
					//document.uploadForm.uploadFile.value=""; 도 가능
					// 파일 저장후 리스트를 호출 합니다.
					getFileList(result.attachNo);
				},
				error : function(){
					console.log("error");
				}
			});
		});
	});
	function getFileList(attachNo){
		$.ajax({
			url: '/getFileList/'+attachNo,
			method:'get',
			dataType:'json',
			
			success: function(result){
				$.each(result, function(index,item){
					htmlContent += "<li>"+vo.fileName+"</li>";
				});
				$("#fileListView").html(htmlContent);
				console.log("getFileList", result);
			}
		});
	}
</script>
</head>
<body>
	<form action="/uploadFormAction" method="post" enctype="multipart/form-data" name="uploadForm">
		attachNo : <input type="text" id="attachNo" name="attachNo" value="0"><br>
		<input type='file' id="uploadFile" name='uploadFile' multiple>
		<button type="button" id="uploadBtn">Ajax전송</button>
		
		<!-- 파일 리스트를 받아서 출력 -->
		<div>
			<ul id="fileListView">
				<li>fileName</li>
			</ul>
		</div>
	</form>
</body>
</html>