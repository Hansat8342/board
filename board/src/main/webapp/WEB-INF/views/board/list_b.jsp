<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
var msg = '${resMsg}'
if(msg!=''){
	alert(msg)
}

// 페이지로 이동
function page(page){
	document.listForm.action="/board/list";
	document.listForm.pageNo.value=page;
	document.listForm.submit();
}

//상세보기 이동
function detail(bno){
	document.listForm.action="/board/get";
	document.listForm.bno.value=bno;
	document.listForm.submit();
}
function enterkey() {
    if (window.event.keyCode == 13) {

         // 엔터키가 눌렸을 때 실행하는 반응
         $("#form").submit();
    }
}
</script>
<jsp:include page="/resources/header/header.jsp"/>
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
                        <div class="panel-heading">
                            DataTables Advanced Tables
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>번호</th>
                                        <th>제목</th>
                                        <th>작성자</th>
                                        <th>작성시간</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list }" var="vo">
                                    <tr class="odd gradeX">
                                        <td>${vo.bno }</td>
                                        <td onClick="detail(${vo.bno })"><a href="#">${vo.title }</a></td>
										<td>${vo.writer }</td>
										<td class="center">${vo.regdate }</td>
                                    </tr>
								</c:forEach>
                                
                                <c:if test="${list.size()==0 }">
                                	<tr class="odd gradex">
                                		<td colspan='4' style="text-align:center">
                                			게시글이 존재하지 않습니다.
                                		</td>
                                	</tr>
                                </c:if>
                                
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                            <button type="button" class="btn btn-default" onclick="location.href='register'">글쓰기</button>
                            <nav aria-label="Page navigation example">
							  <ul class="pagination justify-content-center">
							    <li class="page-item">
                            		<c:if test="${pageNavi.prev }"><a href="/board/list?pageNo=${pageNavi.startPage -1 }">이전</a></c:if>
							    </li>
							    <li class="page-item">
		                            <c:forEach begin="${pageNavi.startPage }" end="${pageNavi.endPage }" var="page">
		                            	<a href="/board/list?pageNo=${page}"> ${page} </a>
		                            </c:forEach>
                            	</li>
							    <li class="page-item">
                            		<c:if test="${pageNavi.next }"><a href="/board/list?pageNo=${pageNavi.endPage +1 }">다음</a></c:if>
							    </li>
							  </ul>
							</nav>
							<!-- 리스트 목록 -->
														
							<form method="get" action="/board/list" name="listForm">
							<div class="form-inline">
                                <select class="form-control" name="type">
                                    <option value="title" <c:if test="${pageNavi.cri.type == 'title'}">selected</c:if>>제목</option>
                                    <option value="content" <c:if test="${pageNavi.cri.type == 'content'}">selected</c:if>>내용</option>
                                    <option value="writer" <c:if test="${pageNavi.cri.type == 'writer'}">selected</c:if>>작성자</option>
                                </select>
                            <!-- 상세보기 검색 유지용 -->
                            <input type="hidden" name="bno">
                            <input type="hidden" name="pageNo" value=${pageNavi.cri.pageNo }>
                            
                            <input type="text" onkeyup="enterkey()" class="form-control" name="keyword" value=${pageNavi.cri.keyword } >
                            <button type="button" onClick="page(1)" class="btn btn-default">검색</button>
                            </div>
							</form>
							
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
           
        </div>
        <!-- /#page-wrapper -->
<jsp:include page="/resources/header/bottom.jsp"/>