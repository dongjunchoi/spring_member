<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<title>회원 리스트</title>
<%@include file="/common/common.jsp" %>

<script>
	$(function(){
		$("#pageSizeSelect").val("${ps}");
		$("#searchType").val("${searchType}");
		$("#keyword1").val("${keyword}");
		
		$(".user").on("click",function(){
			//this : 클릭 이벤트가 발생한 element
			// data-속성명 data-userid, 속성명은 대소문자 무시하고 소문자로 인식
			// data-userId ==> data-userid
			var userid = $(this).data("userid");
			$("#userid").val(userid);
			
			$("#frm").submit();
		})
		
		$("#pageSizeSelect").on("change",function(){
			$("#pfrm").submit();
		})
	})
</script>

</head>
<body class="hold-transition sidebar-mini">
	
	<div class="wrapper">
	
	
		<%@include file="/common/navbar.jsp" %>

		<%@include file="/common/aside.jsp" %>

		<form id="frm" action="${cp}/user/user">
			<input type="hidden" id="userid" name="userid" value="">
		</form>

		<div id="if_list_div" style="position: relative; padding: 0; overflow: hidden; height: 750px;">
			<div class="content-wrapper" style="min-height: 584px;">
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<div class="container-fluid">
						<div class="row md-2">
							<div class="col-sm-6">
								<h1>회원리스트</h1>
							</div>
							<div class="col-sm-6">
								<ol class="breadcrumb float-sm-right">
									<li class="breadcrumb-item">회원리스트</li>
									<li class="breadcrumb-item">목록</li>
								</ol>
							</div>
						</div>
					</div>
				</section>
				<!-- Main content -->
				<section class="content">
					<div class="card">
						<div class="card-header with-border">
							<button type="button" class="btn btn-primary" onclick="location.href='${cp}/user/registUser'">회원등록</button>
							<div id="keyword" class="card-tools" style="width: 550px;">
								
								<form action="${cp}/user/pagingUser" id="pfrm">
								<div class="input-group row">
									<!-- sort num -->
									
									
										<select class="form-control col-md-3" name="ps" id="pageSizeSelect">
											<option value="">정렬개수</option>
											<option value="3">3개씩</option>
											<option value="5">5개씩</option>
											<option value="7">7개씩</option>
										</select>
										<!-- search bar -->
										<select class="form-control col-md-3" name="searchType" id="searchType">
											<option value="">검색구분</option>
											<option value="i">아이디</option>
											<option value="n">이름</option>
											<option value="a">별명</option>
										</select> <input class="form-control" type="text" name="keyword" id="keyword1" placeholder="검색어를 입력하세요." > <span class="input-group-append">
											<button class="btn btn-primary" type="submit" id="searchBtn" data-card-widget="search">
												<i class="fa fa-fw fa-search"></i>
											</button>
										</span>
								
									<!-- end : search bar -->
								</div>
								</form>
								
							</div>
						</div>
						<div class="card-body" style="text-align: center;">
							<div class="row">
								<div class="col-sm-12">
									<table class="table table-bordered">
										<tbody>
											<tr>
												<th>아이디</th>
												<th>이름</th>
												<th>별명</th>
												<th>도로주소</th>
												<th>등록날짜</th>
												<!-- yyyy-MM-dd  -->
											</tr>
											<c:if test="${userList != null }">
												<c:forEach items="${userList}" var="user">
													<tr class = "user" data-userid="${user.userid}">
														<td>${user.userid}</td>
														<td>${user.usernm}</td>
														<td>${user.alias}</td>
														<td>${user.addr1}</td>
														<td><fmt:formatDate value="${user.reg_dt}" pattern="yyyy-MM-dd"/></td>
													</tr>
												</c:forEach>
											</c:if>
											
										</tbody>
									</table>
								</div>
								<!-- col-sm-12 -->
							</div>
							<!-- row -->
						</div>
						<!-- card-body -->
						<div class="card-footer">
							<nav aria-label="member list Navigation">
								<ul class="pagination justify-content-center m-0">
									<li class="page-item"><a class="page-link" href="${cp}/user/pagingUser?page=1&pageSize=${pageVo.pageSize}&searchType=${searchType}&keyword=${keyword}&ps=${ps}"><i class="fas fa-angle-double-left"></i></a></li>
									
									<c:choose>
										<c:when test="${pageVo.page-1<1}">
											<li class="page-item"><a class="page-link" href="${cp}/user/pagingUser?page=${pageVo.page}&pageSize=${pageVo.pageSize}&searchType=${searchType}&keyword=${keyword}&ps=${ps}"><i class="fas fa-angle-left"></i></a></li>
										</c:when>
										<c:otherwise>										
											<li class="page-item"><a class="page-link" href="${cp}/user/pagingUser?page=${pageVo.page-1}&pageSize=${pageVo.pageSize}&searchType=${searchType}&keyword=${keyword}&ps=${ps}"><i class="fas fa-angle-left"></i></a></li>
										</c:otherwise>
									</c:choose>
									
									<c:if test="${startPage!=1}">
										<li class="page-item"><a class="page-link" href="${cp}/user/pagingUser?page=1&pageSize=${pageVo.pageSize}&searchType=${searchType}&keyword=${keyword}&ps=${ps}">1</a></li>
									</c:if>
									
									<c:forEach begin="${startPage}" end="${endPage}" var="i">
										<c:if test="${startPage!=1 && i==startPage}">
											<li class="page-item"><a class="page-link" href="#">...</a></li>
										</c:if>
										<c:choose>
											<c:when test="${i == pageVo.page}">
												<li class="page-item active"><span class="page-link">${i}</span></li>
											</c:when>
											<c:otherwise>
												<c:if test="${i != 0}">
													<li class="page-item"><a class="page-link" href="${cp}/user/pagingUser?page=${i}&pageSize=${pageVo.pageSize}&searchType=${searchType}&keyword=${keyword}&ps=${ps}">${i}</a></li>	
												</c:if>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:if test="${endPage!=pagination}">
										<li class="page-item"><a class="page-link" href="#">...</a></li>
										<li class="page-item"><a class="page-link" href="${cp}/user/pagingUser?page=${pagination}&pageSize=${pageVo.pageSize}&searchType=${searchType}&keyword=${keyword}&ps=${ps}">${pagination}</a></li>
									</c:if>
									<!-- <li class="page-item active"><a class="page-link" href="#">1</a></li> -->
									<c:choose>
										<c:when test="${pageVo.page+1>pagination}">
											<li class="page-item"><a class="page-link" href="${cp}/user/pagingUser?page=${pageVo.page}&pageSize=${pageVo.pageSize}&searchType=${searchType}&keyword=${keyword}&ps=${ps}"><i class="fas fa-angle-right"></i></a></li>
										</c:when>
										<c:otherwise>										
											<li class="page-item"><a class="page-link" href="${cp}/user/pagingUser?page=${pageVo.page+1}&pageSize=${pageVo.pageSize}&searchType=${searchType}&keyword=${keyword}&ps=${ps}"><i class="fas fa-angle-right"></i></a></li>
										</c:otherwise>
									</c:choose>
									<li class="page-item"><a class="page-link" href="${cp}/user/pagingUser?page=${pagination}&pageSize=${pageVo.pageSize}&searchType=${searchType}&keyword=${keyword}&ps=${ps}"><i class="fas fa-angle-double-right"></i></a></li>
								</ul>
							</nav>

						</div>
						<!-- card-footer -->
					</div>
					<!-- card  -->
				</section>
			</div>
		</div>
	</div>

	<%@include file="/common/footer.jsp" %>
	
	<!-- ./wrapper -->

	<%@include file="/common/requiered.jsp" %>

</body>
</html>







