<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/resources/header/header.jsp"/>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script src="/resources/js/reply.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	//버튼을 클릭하면 모달창을 보여준다.
	$("#addReplyBtn").on("click",function(){
		$("#replyInsertBtn").show();
		$("#reply").val(""); // 값 초기화
		$("#replyer").val("");
		$("#myModal").modal("show");
	});
	
	//저장버튼을 클릭하면 저장하고 모달창을 닫아준다.
	//모달창을 닫은후 리스트를 재 조회.
	$("#replyInsertBtn").on("click",function(){
		//리플 작성
		AjaxInsert();
		//여기서 모달창 닫기와 새로고침을 넣으면 인서트가 완료되기 전에 창을 닫고 새로고침을 해서 제대로 적용되지 않음(비동기)
	});
	
	//리플 리스트 조회
	getAjaxList();
});

function replyDetail(rno){
	//선택된 rno 세팅
	$("#rno").val(rno); 
	//버튼 숨김처리
	$("#replyInsertBtn").hide(); 
	//모달창 보여주기
	$("#myModal").modal("show"); 
	//상세내용 조회
	getAjax();
}
</script>


        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
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
										<li class='left clearfix' data-rno='"+list.rno+"'>
										<div>
											<div class='header'><strong class='primary-font'>[1] 홍길동</strong> 
				    							<small class='pull-right text-muted'>12:00:00</small>
				    						</div>
				     						<p>수고가 많으십니다!</p>
				     					</div>
				     					</li>
							        </ul>
							        <!-- ./ end ul -->
							      </div>
							      <!-- /.panel .chat-panel -->
							
								<div class="panel-footer"></div>
							bno<input type="text" value="1" id="bno"><br>
							rno<input type="text" id="rno"><br>
							
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
            
        </div>
        <!-- /#page-wrapper -->
			
			
			
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
                               <button type="button" class="btn btn-default" data-dismiss="modal">cancle</button>
                               <button type="button" class="btn btn-primary" id="replyInsertBtn">save</button>
                           </div>
                       </div>
                       <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->
                

        
<jsp:include page="/resources/header/bottom.jsp"/>


