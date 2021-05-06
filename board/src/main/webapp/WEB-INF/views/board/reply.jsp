<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>


<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<!-- Ajax.js -->
<script src="/resources/js/reply.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function(){
	
	// addReply 버튼을 클릭하면 모달창을 보여준다
	$("#addReplyBtn").on("click",function(){
		// 초기화
		$("#updateBtn").hide();
		$("#removeBtn").hide();
		$("#replyInsertBtn").show();
		$("#reply").val("");
		$("#replyer").val("");
		// 모달창 보여줌
		$("#myModal").modal("show");
	});
	
	// 선택자
	// 아이디 : # , class : . , 태그
	// $("#id")
	// $(".class")
	// $("태크명")
	$("#removeBtn").on("click",function(){
		deleteAjax();
	});
	
	
	$("#updateBtn").on("click",function(){
		updateAjax();
	});
	
	
	$("#replyInsertBtn").on("click",function(){
		// 리플 작성
		ajaxInsert();
		
	});
	
	// 실질적으로 리시트를 조회 해서 화면에 뿌려준다
	getAjaxList();
});

function replyDetail(rno){
	// 선택된 rno세팅
	$("#rno").val(rno);
	
	// 버튼 숨김 처리
	$("#replyInsertBtn").hide();
	$("#updateBtn").show();
	$("#removeBtn").show();
	
	// 모달창 보여주기
	$("#myModal").modal("show");
	
	//상세내용 조회
	getAjax();
	
}

/**
 * 리플 페이지 생성
 */
function replyPage(pageNavi){
	var startPage = pageNavi.startPage;
	var endPage = pageNavi.endPage;
	var pageContent = "";
	
	// 이전 페이지네비게이션으로 이동
	if(pageNavi.prev){
		pageContent += 
			'<li class="page-item" onClick=pageMove('+(startPage-1)+')>'
     		+	'<a class="page-link" href="#" tabindex="-1">Previous</a>'
   			+'</li>';
	   
	}
	// 페이지 번호 생성
	// 1 - 10
	for(startPage;startPage<=endPage; startPage++){
		// 지금 보여주고려고 하는 번호 = startPage
		// pageNavi.cri.pageNo
		if(startPage != pageNavi.cri.pageNo){
			pageContent += '<li onClick=pageMove('+startPage+') class="page-item"><a class="page-link" href="#">'+startPage+'</a></li>';	
		} else {
			pageContent += '<li class="page-item active">'
		      			+'<a class="page-link" href="#">'+ startPage +'<span class="sr-only">(current)</span></a>'
		    			+'</li>';
		}
		
		
	    
	}
	// 다음 페이지네비게이션으로 이동
	
	if(pageNavi.next){
		pageContent += '<li class="page-item" onClick=pageMove('+(endPage+1)+')>'
	      			+'<a class="page-link" href="#">Next</a>'
	    			+'</li>';
	}

	$(".pagination").html(pageContent);

}

/**
 * 페이지 번호를 매개변수로 받아서
 * 해당 페이지 리스트를 서버로 부터 받아와서 화면에 뿌려준다
 */
function pageMove(pageNo){
	$("#replyPageNo").val(pageNo);
	getAjaxList();
}
</script>

       
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            
                            
                            
                           <!-- 답글 -->
                           <div class='row'>

							  <div class="col-lg-12">
							
							    <!-- /.panel -->
							    <div class="panel panel-default">
							      
							      <div class="panel-heading">
							        <i class="fa fa-comments fa-fw"></i> Reply
							        <button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New Reply</button>
							      </div>      
							      
							      
							      <!-- /.panel-heading -->
							      <div class="panel-body">        
							      
							        <ul class="chat">
										
							        </ul>
							        <!-- ./ end ul -->
							      </div>
							      <!-- /.panel .chat-panel -->
							
								<div class="panel-footer">
								
									<!-- 페이징 -->
									<nav aria-label="...">
									  <ul class="pagination">
									  </ul>
									</nav>
									<!-- /페이징 -->
								</div>
<!-- 리스트 호출시 bno와 pageNo 값을 가지고 감!!!!!!! -->							
<input type="text" value="${vo.bno}" id="bno"><br>
<input type="hidden" id="replyPageNo" value="1"><br>
<input type="hidden" id="rno"><br>

									</div>
							  </div>
							  <!-- ./ end row -->
							  
							  
							  
							</div>
                            
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            

			
			
			
        <!-- 모달 Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                   <div class="modal-dialog">
                       <div class="modal-content">
                           <div class="modal-header">
                               <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                               <h4 class="modal-title" id="myModalLabel">Reply</h4>
                           </div>
                           <div class="modal-body">
                                 <ul class="list-group list-group-flush">
							    <li class="list-group-item">
									<input type="text" class="form-control ml-2" placeholder="replyer" id="replyer">
								</li>
								<li class="list-group-item">
									<textarea class="form-control" id="reply" placeholder="reply" rows="3"></textarea>
							    </li>
							</ul>
                           </div>
                           <div class="modal-footer">
                           
	                           <button type="button" class="btn btn-warning" data-dismiss="modal" id="updateBtn">Modify</button>
	                           <button type="button" class="btn btn-danger" data-dismiss="modal" id="removeBtn">Remove</button>
                               <button type="button" class="btn btn-default" data-dismiss="modal">cancle</button>
                               <button type="button" class="btn btn-primary" id="replyInsertBtn">save</button>
                           </div>
                       </div>
                       <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->
                

        


