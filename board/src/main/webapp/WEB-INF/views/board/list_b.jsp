<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/resources/header/header.jsp"/>

<script type="text/javascript">
if('${resMsg}' != ''){
	alert('${resMsg}');	
}

// 페이지로 이동
function page(page){
	//location.href = "/board/list?pageNo="+page;
	document.listForm.action="/board/list";
	document.listForm.pageNo.value=page;
	document.listForm.submit();
	
}

// 상세보기 이동
function detail(bno){
	document.listForm.action="/board/get";
	document.listForm.bno.value=bno;
	document.listForm.submit();
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
                        <div class="panel-heading">
                            <button type="button" onclick="location.href='/board/register'" class="btn btn-default">등록</button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" >
                                <thead>
                                    <tr>
                                        <th>번호</th>
                                        <th>제목</th>
                                        <th>작성자</th>
                                        <th>작성시간</th>
                                        
                                    </tr>
                                </thead>
                                <tbody>
                                
                                <c:forEach var="vo" items="${list}">
                                    <tr class="odd gradeX">
                                        <td>${vo.bno }</td>
                                        <td onClick=detail(${vo.bno})><a href="#">${vo.title }</a></td>
                                        <td>${vo.writer }</td>
                                        <td class="center">${vo.regdate }</td>
                                    </tr>
                                </c:forEach>
                                   
                                <c:if test="${list.size() == 0 }">
                                <tr class="odd gradeX">
                                	<td colspan='4' align="center">
                                	게시글이 존재하지 않습니다.
                                	</td>
                                </tr>
                                </c:if>
                                
                                </tbody>
                            </table>
                              
                            <!-- 페이지 -->                          
                            <nav aria-label="...">
							  
							  <ul class="pagination">
							  
							  <!-- prev 표시 -->
							  <c:if test="${pageNavi.prev }">
							    <li class="page-item" onClick="javascript:page(${pageNavi.startPage-1});">
							      <a class="page-link" href="#" tabindex="-1">Previous</a>
							    </li>
							  </c:if>
							  <c:forEach begin="${pageNavi.startPage }" end="${pageNavi.endPage }" var="page">
							  
							  	<c:choose>
							  		<c:when test="${page eq pageNavi.cri.pageNo }">
									  	<li class="page-item active" onClick="page(${page})">
									      <a class="page-link" href="#" >${page } <span class="sr-only">(current)</span></a>
									    </li>
								    </c:when>
								  	<c:otherwise>
								    	<li class="page-item" onClick="page(${page})"><a class="page-link" href="#">${page }</a></li>
								    </c:otherwise>
							  	</c:choose>
							  
							  </c:forEach>
							  <!-- next 표시 -->
							  <c:if test="${pageNavi.next }">
							    <li class="page-item" onClick="page(${pageNavi.endPage + 1})">
							      <a class="page-link" href="#">Next</a>
							    </li>
							  </c:if>
							  </ul>
							  
							</nav>
						    <!-- 페이지끝 -->
							
							<!-- 검색 -->
							<form method=get action=/board/list name=listForm>
	                            <!-- 상세보기 검색 유지용 -->
	                            ${pageNavi.cri.type }
	                            <input type=hidden name=bno>
	                            <input type=hidden name=pageNo value=${pageNavi.cri.pageNo }> 
	                            <!-- 상세보기 검색 유지용 끝 -->
							<div  class="form-inline">
							
								<select class="form-control" name=type>
								
								 
	                                <option value=title  <c:if test="${pageNavi.cri.type == 'title'}">selected</c:if>  >제목</option>
	                                <option value=content <c:if test="${pageNavi.cri.type == 'content'}">selected</c:if>>내용</option>
	                                <option value=writer <c:if test="${pageNavi.cri.type == 'writer'}">selected</c:if>>작성자</option>
	                            </select>
	                            
	                            <input class="form-control" name=keyword value=${pageNavi.cri.keyword } >
	                            <button type="submit" onClick="page(1)" class="btn btn-default">검색</button>
							</div>
							</form>
							<!-- 검색 끝 -->
							
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
        </div>
        <!-- /#page-wrapper -->
        
<jsp:include page="/resources/header/bottom.jsp"/>
