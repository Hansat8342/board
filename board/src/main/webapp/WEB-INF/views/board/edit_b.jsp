<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/resources/header/header.jsp"/>

<form method="post" action="/board/edit">
<input type=text name=bno value="${vo.bno}">
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
                            <div class="form-group">
                                <label>제목</label>
                                <input class="form-control" value="${vo.title }" name="title">
                            </div>
                            <div class="form-group">
                                <label>내용</label>
                                <textarea class="form-control" rows="3" name="content">${vo.content }</textarea>
                            </div>
                            <div class="form-group">
                                <label>작성자</label>
                                <input class="form-control" value="${vo.writer }" name="writer">
                            </div>
                            <div class="form-group">
                                <label>등록시간</label>
                                <input class="form-control" value="${vo.regdate }" name="regdate">
                            </div>
                            <!-- /.table-responsive -->
                            <button type="submit" class="btn btn-default" onclick="location.href='edit?bno=${vo.bno}'">수정</button>
                           
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
</form>
<jsp:include page="/resources/header/bottom.jsp"/>