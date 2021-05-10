<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script type="text/javascript">

	// 화면이 준비가 되면 실행 합니다!
	$(document).ready(function(){
		
		$("#attachNo").on("change",function(){
			//this.val() -> this.value
			getFileList($("#attachNo").val());
		});
		
		// id가 uploadBtn인 엘리먼트에 클릭 이벤트를 부여 합니다.
		$("#uploadBtn").on("click",function(){
			
			// formData 생성
			var formData = new FormData(document.uploadForm);
			console.log("attachNo"+formData.get("attachNo"));
			
			// 파일 업로드 컨트롤러 호출 
			// 파일을 서버에 저장 합니다.
			$.ajax({
				url : '/fileUploadAjax', 
				method : 'post',
				dataType : 'json',
				
				// processData속성과 contentType속성은 false로 지정 합니다.
				processData : false,
				contentType : false,
				data : formData,
				
				success : function(result){
					console.log("uploadAjax result",result);
					//$("#attachNo").val(result.attachNo);
					//name 속성이 attachNo인 엘리먼트의 값을 모두 변경
					$("input[name=attachNo]").val(result.attachNo);
					
					$("#uploadFile").val("");
					//document.uploadForm.uploadFile.value="";
					// 파일 저장후 파일 리스트를 호출 합니다.
					getFileList(result.attachNo);
					
				}
			});
		});
	});
	
	
	function getFileList(attachNo){
		$.ajax({
			url : '/getFileList/' + attachNo,
			method : 'get',
			dataType : 'json',
			
			success : function(result){
				//result : List<attachFileVo>
				var htmlContent = "";
				$.each(result, function(index,vo){
					// 이미지 썸네일의 경로를 인코딩 처리해서 서버에 보내줍니다.
					// uploadPath경로의 일부( \ )를 전송 하기 위해 encodeURIComponent(문자열)가 필요 합니다.
					// 문자나 특수문자를 웹 서버와 브라우저에서 보편적으로 허용되는 형식으로 변화
					// savePath = uploadPath + uuid + _ + 파일이름
					var s_savePath = encodeURIComponent(vo.s_savePath);
					var savePath = encodeURIComponent(vo.savePath);
					console.log("인코딩 전",vo.s_savePath);
					console.log("인코딩 후",savePath);
					// 만약에 이미지 이면 이미지를 출력
					if(vo.fileType == "Y"){
						htmlContent += "<li><a href=/download?fileName="+savePath+">"
									+"<img src=/display?fileName="+s_savePath+"></a>"
									+"<span onClick=attachFileDelete('"+vo.uuid+"','"+vo.attachNo+"') data-type='image'>X</span>"
									+"</li>";	
					} else {
					// 이미지가 아니면 파일 이름을 출력
						htmlContent += "<li><a href=/download?fileName="+savePath+">"+vo.fileName+"</a>"
									+"<span onClick=attachFileDelete('"+vo.uuid+"','"+vo.attachNo+"') data-type='image'>X</span>"
									+"</li>";
					}
				});
				$("#fileListView").html(htmlContent);
				console.log("getFileList", result);
			}
		});
		
	}
	
	function attachFileDelete(uuid, attachNo){
		console.log("func : ", attachFileDelete);
		console.log("uuid", uuid);
		console.log("attachNo", attachNo);
		$.ajax({
			url : '/attachFileDelete/'+uuid+'/'+attachNo,
			method : 'get',
			success : function(result){
				console.log("delete", result);
				//파일 저장후 호출
				getFileList(attachNo);
			},
			error : function(){
				console.log("error");
			}
		});
	}
</script>
<style>
.uploadResult {
	width: 100%;
	background-color: gray;
}

.uploadResult ul {
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}

.uploadResult ul li {
	list-style: none;
	padding: 10px;
}

.uploadResult ul li img {
	width: 100px;
}
</style>

</head>
<body>

<form action="/uploadFormAction" 
		method="post" 
		enctype="multipart/form-data"
		name="uploadForm">
	<div id=fileInputArea>
		attachNo : <input type="text" name="attachNo" id="attachNo" value="0"><br>
		<input type="file" name="uploadFile" id="uploadFile" multiple>
		<button type="button" id="uploadBtn">Ajax 파일 업로드</button>
	</div>
	<!-- 파일 리스트를 출력 합니다. -->
	<div class="uploadResult">
		<ul id = "fileListView">
			<li>fileName1</li>
			<li>fileName2</li>
		</ul>
	</div>
</form>




</body>
</html>











