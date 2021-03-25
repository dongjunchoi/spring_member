<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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

<title>회원 등록</title>

<%@include file="/common/common.jsp" %>
<script>
//문서로딩 완료
	$(function(){
		$("#modifyBtn").on("click",function(){
			$("#frm").attr("method","GET");
			$("#frm").attr("action","${cp}/user/userModify");
			$("#frm").submit();
		});
		
		$("#deleteBtn").on("click",function(){
			check = confirm("정말 삭제하시겠습니까?");
			if(check== true){
				$("#frm").attr("method","POST");
				$("#frm").attr("action","${cp}/user/deleteUser");
				$("#frm").submit();
			}
		});
	});
</script>
</head>
<body class="hold-transition sidebar-mini">
	<form class="form-horizontal" id="frm" role="form">
		<input type="hidden" name="userid" value="${user.userid}">
	</form>
	<div class="wrapper">

		<%@include file="/common/navbar.jsp" %>

		<%@include file="/common/aside.jsp" %>


		<div id="if_list_div" style="position: relative; padding: 0; overflow: hidden;">
			<!-- Content Wrapper. Contains page content -->
			<div class="content-wrapper">

				<!-- Main content -->
				<section class="content register-page" style="height:100%;">
					<div class="container-fluid">
						<div class="login-logo">
							<b>회원 정보</b>
						</div>
						<!-- form start -->
						<div class="card">
							<div class="register-card-body">
								<form role="form" class="form-horizontal" >
									<div class="input-group mb-3">
										<div class="mailbox-attachments clearfix" style="text-align: center; width:100%;">
											<div class="mailbox-attachment-icon has-img" id="pictureView" style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;">
												<a href="${cp}/user/profileDownload?userid=${user.userid}">
													<img src="${cp}/user/profile?userid=${user.userid}"  style="width:100%; height:100%;">
												</a>
											</div>
										</div>
										<br />
									</div>
									
									
									
									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">
											아이디
										</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span class="input-group-append-sm">${user.userid}</span>
										</div>
									</div>
									
									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">
											패스워드
										</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span class="input-group-append-sm">******</span>
										</div>
									</div>
									
									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">
											이름
										</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span class="input-group-append-sm">${user.usernm}</span>
										</div>
									</div>
									
									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">
											이름
										</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span class="input-group-append-sm">${user.alias}</span>
										</div>
									</div>
									
									<div class="form-group row">
										<label for="addr1" class="col-sm-3 control-label">주소</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span class="input-group-append-sm">${user.addr1}</span>&nbsp;&nbsp;&nbsp;&nbsp;
											<span class="input-group-append-sm">${user.addr2}</span>&nbsp;&nbsp;&nbsp;&nbsp;
											우편번호 &nbsp;&nbsp;<span class="input-group-append-sm">${user.zipcode}</span>&nbsp;&nbsp;&nbsp;&nbsp;
										</div>
									
								
									</div>
									
									<div class="card-footer">
										<div class="row">
											<div class="col-sm-6">
												<button type="button" id="modifyBtn" class="btn btn-info">수정</button>
												<button type="button" id="deleteBtn" class="btn btn-info">삭제</button>
											</div>
									
										</div>
									</div>
								</form>
							</div>
							<!-- register-card-body -->
						</div>
					</div>
				</section>
				<!-- /.content -->
			</div>
			<!-- /.content-wrapper -->
		</div>
	</div>

	<%@include file="/common/footer.jsp" %>

	<!-- ./wrapper -->

	<%@include file="/common/requiered.jsp" %>
</body>
</html>







