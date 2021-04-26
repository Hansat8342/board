/**
 *  리플 ajax 펑션 따로 작성
 */

function getAjaxList(){
	
	$.ajax({
		url:'/reply/list/'+ $("#bno").val() + '/' + $("#pageNo").val,
		method:'get',
		dataType:'json',
		success:function(data, status, xhr){
			console.log("data",data);
			debugger;
			var htmlContent="";

			//리스트에 데이터가 0건이면
			if(data.list.length == 0){
				//'등록된 댓글이 없습니다.' 메시지 출력
				htmlContent += 
				"<li class='left clearfix'>"
				+	"<div>"
				+		"<p> 등록된 댓글이 없습니다. </p>" 
				+	"</div>"
				+"</li>";
				$(".chat").html(htmlContent);
			}
			//리스트 보여주기
			else{
				$.each(data.list, function(index, item){
					htmlContent += 
						"<li onclick=replyDetail('" + item.rno + "') class='left clearfix' data-rno=''>"
						+"<div>"
						+	"<div class='header'><strong class='primary-font'>["+ item.rno +"] "+ item.replyer +"</strong>" 
						+		"<small class='pull-right text-muted'>"+ item.updatedate +"</small>"
						+	"</div>"
						+		"<p>"+ item.reply +"</p>"
						+	"</div>"
						+	"</li>";
					
				});
				// 리스트 생성
				$(".chat").html(htmlContent);
				
				//페이지 생성
				replyPage(data.pageNavi);
			}
			
	       
		},
		error:function(xhr, status, error){
			console.log("error",error)
		}
	});
	
}
function AjaxInsert(){
	
	var replyData = {
		bno:$("#bno").val(),
		reply:$("#reply").val(),
		replyer:$("#replyer").val()
	};
	
	console.log("obj", replyData);
	console.log("json", JSON.stringify(replyData));
	
	$.ajax({
		
		url:'/reply/insert',
		method:'post',
		dataType: 'json', //서버에서 받을때
		
		//JSON 형식으로 변환, 보낼때 타입
		data:JSON.stringify(replyData),
		
		contentType:'application/json; charset=UTF-8',
		
		success: function(data, status){
			console.log(data); //서버로부터 받은 데이터

			if(data.result == "success"){
				//모달창을 닫기
				$("#myModal").modal("hide");
				//리스트 재 조회, 펑션 호출
				getAjaxList();
			}else{
				alert("입력중 오류가 발생했습니다.");
			}
		},
		error:function(xhr,status,error){
			console.log(error);
		}
	});
}

/*
 *리플 업데이트 
 */
function updateAjax(){
	console.log("updateAjax 시작");
	
	var replyData = {
			bno:$("#bno").val(),
			reply:$("#reply").val(),
			replyer:$("#replyer").val(),
			rno : $("#rno").val()
		};
		
		console.log("obj", replyData);
		console.log("json", JSON.stringify(replyData));
		
		$.ajax({
			
			url:'/reply/update',
			method:'post',
			dataType: 'json', //서버에서 받을때
			
			//JSON 형식으로 변환, 보낼때 타입
			data:JSON.stringify(replyData),
			
			contentType:'application/json; charset=UTF-8',
			
			success: function(data, status){
				console.log(data); //서버로부터 받은 데이터

				if(data.result == "success"){
					//모달창을 닫기
					$("#myModal").modal("hide");
					//리스트 재 조회, 펑션 호출
					getAjaxList();
				}else{
					alert("입력중 오류가 발생했습니다.");
				}
			},
			error:function(xhr,status,error){
				console.log(error);
			}
		});
}

/*
 * 댓글 삭제
 * @returns
 */
function deleteAjax(){
	console.log("deleteAjax 시작");
	$.ajax({
		url : '/reply/delete/' + $("#rno").val() ,
		method : 'get',
		dataType : 'json',
		
		success: function(data){
			console.log(data);
		},
		error : function(error){
			console.log(error);
		}
		
	});
}

// 한건의 리플을 조회
// @returns
function getAjax(){
	
	$.ajax({
		url : '/reply/get/' + $("#rno").val(),
		method : 'get',
		dataType : 'json',
		
		success : function(data, status){
			console.log(data);

			$("#reply").val(data.reply);
			$("#replyer").val(data.replyer);
			
		},
		error : function(xhr, status, error){
			console.log(data);
		}
		
		
	});
}

//공통부분 빼는것 설명용 펑션
function commAjax(url,method,data,callback,error){ 
	$.ajax({
			
			url:url,
			method:method,
			dataType: 'json',
			
			data:JSON.stringify(data),
			contentType:'application/json; charset=UTF-8',
			success: function(data, status){
				console.log(data); 
				if(callback){
					callback(data);
				}
			},
			error:function(xhr,status,error){
				console.log(error);
			}
		});
}



	
