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
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	$(function() {
		// 주소검색 버튼이 클릭 되었을 때 다음주소 api 팝업을 연다
		$("#addrBtn").on("click",function(){
		    new daum.Postcode({
		        oncomplete: function(data) {
		            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
		            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
		            $("#userAddr1").val(data.roadAddress);	//도로주소
		            $("#userZip").val(data.zonecode);		//우편번호
		            
		            //사용자 편의성을 위해 상세주소 입력 input 태그로 focus 설정
		            $("#userAddr2").focus();
		        }
		    }).open();
			
		})
		
		$("#modifyBtn").on("click",function(){
			
			pass = $("#userPass").val();
			if(pass == ""){
				alert("비밀번호 입력은 필수사항 입니다.");
				return false;
			}
			
			userNm = $("#userNm").val();
			if(userNm == ""){
				alert("이름 입력은 필수사항 입니다.");
				return false;
			}
			
			$("#frm").attr("method","POST");
			$("#frm").attr("action","${cp}/user/userModify");
			$("#frm").submit();
		});
		
		$("#cancelBtn").on("click",function(){
			$("#frm").attr("method","GET");
			$("#frm").attr("action","${cp}/user/user");
			$("#frm").submit();
		});
	})
</script>
</head>
<body class="hold-transition sidebar-mini">
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
							<b>회원 수정</b>
						</div>
						<!-- form start -->
						<div class="card">
							<div class="register-card-body">
								<form role="form" class="form-horizontal" id="frm" enctype="multipart/form-data" method="post">
									<div class="input-group mb-3">
										<div class="mailbox-attachments clearfix" style="text-align: center; width:100%;">
											<div class="mailbox-attachment-icon has-img" id="pictureView" style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;">
												<img id="pictureViewImg" src="${cp}/user/profile?userid=${user.userid}"  style="width:100%; height:100%;">
											</div>
											<div class="mailbox-attachment-info">
												<div class="input-group input-group-sm">
													<input id="picture" class="form-control"
														   type="file" name="profile" accept=".gif, .jpg, .png" style="height:37px;"/>
												</div>
											</div>
										</div>
										<br />
									</div>
									
									
									<input type="hidden" name="userid" value="${user.userid}">
									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">
											아이디
										</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span class="input-group-append-sm">${user.userid}</span>
										</div>
									</div>
									
									<div class="form-group row">
										<label for="pwd" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>패스워드</label>
										<div class="col-sm-9 input-group-sm">
											<input class="form-control" name="pass" value="${user.pass}" type="password" class="form-control" id="userPass" placeholder="비밀번호" />
										</div>
									</div>
									
									<div class="form-group row">
										<label for="name" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>이 름
										</label>
										<div class="col-sm-9 input-group-sm">
											<input class="form-control" name="usernm" value="${user.usernm}" type="text" id="userNm" placeholder="이름" />
										</div>

									</div>
									<div class="form-group row">
										<label for="alias" class="col-sm-3" style="font-size: 0.9em;">별명</label>
										<div class="col-sm-9 input-group-sm">
											<input class="form-control" name="alias" value="${user.alias}" type="text" id="userAlias" placeholder="별명">
										</div>
									</div>
									<div class="form-group row">
										<label for="addr1" class="col-sm-3 control-label">주소</label>
										<div class="col-sm-3 input-group-sm">
											<input name="addr1" type="text" value="${user.addr1}" class="form-control" id="userAddr1" placeholder="주소" readonly>
										</div>
										<div class="col-sm-3 input-group-sm">
											<input name="addr2" type="text" value="${user.addr2}" class="form-control" id="userAddr2" placeholder="상세주소">	
										</div>
										
										<div class="col-sm-2 input-group-sm">
											<input name="zipcode" type="text" value="${user.zipcode}" class="form-control" id="userZip" placeholder="우편번호" readonly>
										</div>
										<div class="col-sm-1 input-group-sm">
											<span class="input-group-append-sm">
												<button type="button" id= "addrBtn" class="btn btn-info btn-sm btn-append">주소검색</button>
											</span>
										</div>
									</div>
									
									<div class="card-footer">
										<div class="row">
											<div class="col-sm-6">
												<button type="button" id="modifyBtn" class="btn btn-info">수정</button>
											</div>

											<div class="col-sm-6">
												<button type="button" id="cancelBtn" class="btn btn-default float-right">&nbsp;&nbsp;&nbsp;취 &nbsp;&nbsp;소&nbsp;&nbsp;&nbsp;</button>
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







