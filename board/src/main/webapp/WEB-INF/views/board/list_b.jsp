<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
var msg = '${resMsg}'
if(msg!=''){
	alert(msg)
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
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
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
                                        <td><c:out value="${vo.bno }"/></td>
                                        <td><a href="/board/get?bno=${vo.bno }"><c:out value="${vo.title }"/></a></td>
										<td><c:out value="${vo.writer }"/></td>
										<td class="center"><c:out value="${vo.regdate }"/></td>
                                    </tr>
								</c:forEach>
                                    
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                            <button type="button" class="btn btn-default" onclick="location.href='register'">글쓰기</button>
                            <nav aria-label="Page navigation example">
							  <ul class="pagination justify-content-center">
							    <li class="page-item disabled">
                            		<c:if test="${pageNavi.prev }"><a href="${pageNavi.startPage -1 }">이전</a></c:if>
							    </li>
							    <li class="page-item">
		                            <c:forEach begin="${pageNavi.startPage }" end="${pageNavi.endPage }" var="page">
		                            	<a href="${page}"> ${page} </a>
		                            </c:forEach>
                            	</li>
							    <li class="page-item">
                            		<c:if test="${pageNavi.next }"><a href="${pageNavi.endPage +1 }">다음</a></c:if>
							    </li>
							  </ul>
							</nav>
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