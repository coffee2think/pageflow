<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css"
	href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<link rel="stylesheet" type="text/css"
	href="${ pageContext.servletContext.contextPath }/resources/css/notice.css">
<script type="text/javascript"
	src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<title></title>
<style>
	.search-div {
		width: 100%;
		height: 22px;
	}
	.search-div  tr{
		position: relative;
	}

	.search-div .input-search-btn {
		top: -3px;
	}

	.search-table {
		width: 100%;
	}

	.search-div .input-search-box {
		text-align: left;
    	margin-left: 30px;
	}

	.close-search {
		font-size: 10px;
		background-color: #eee;
	}

	.select-box {
		text-align: left;
	}

</style>
<script type="text/javascript">
	const NOWPAGE = 1;
	const SUBPAGE = 1;
	const LNKPAGE = 1;

	$(function(){
		$('#btn_insert').click(function() {
			var sendText = $('.content-input').text();
			sendText = sendText.replace(/(?:\r\n|\r|\n)/g, '<br>');
			$('#appendTextArea').val(sendText);

			// 필독을 체크 시 유효성 검사
			if($('#importance').prop('checked') == true) {
				var today = new Date();
				var year = today.getFullYear();
				var month = today.getMonth() + 1;
				var date = today.getDate();
				if($('#importance_date').val() == '') {
					alert('필독기간을 설정해주세요.');
					return false;
				} else if($('#importance_date').val() < (year + '-' + month + '-' + date)) {
					alert('필독기간을 확인해주세요.');
					return false;
				}
			}
			
			// 만약 부서 공지이고, 부서가 선택되지 않았을 경우
			if($('#classify').val() == 'dept' && $('#dep_type').val() == 'all') {
				alert('부서를 선택해주세요.');
				return false;
			}

			return true;
		});
		
		// 공지유형이 부서공지로 바뀔 경우 부서 목록 보이기
		$('.classify-sub').hide();
		checkSelectBox();
	});
	
	function inputSubmit(){
		$('#btn_insert').click();
	}
	
	function checkSelectBox() {
		$('#classify').on('change', function() {
			var classify = $('#classify').val();
			
			if(classify == 'dept') {
				$('#dep_type').show();
			} else {
				$('#dep_type').hide();
			}
			
			if(classify == 'emp') {
				$('#emp_list').show();
			} else {
				$('#emp_list').hide();
			}
		});
	}
</script>
</head>
<body>
	<div id="container">

		<!--헤더-->
		<header class="main-header">
			<!--header-container-->
			<div class="header-container">
				<!-- 헤더 들어감 -->
				<c:import url="../common/header.jsp" />
			</div>
			<!--header-container end-->
		</header>
		<!--헤더 end-->

		<main class="main-wrapper">

			<!--main-side-->
			<div class="main-side">
				<div class="side-container">
					<div class="side-title"></div>
					<div class="side-icon-box">
						<a href="nemoveinsert.do" class="side-write-btn margin-bottom-0">공지글쓰기</a>
					</div>

					<!-- 리스트 들어감 -->
					<c:import url="../common/side.jsp" />
				</div>
			</div>
			<!--main-side end-->

			<!--main-container-->
			<div class="main-container notice">

				<!--main-header-bar-->
				<div class="main-header-bar">
					<div class="main-title-box">
						<img
							src="${ pageContext.servletContext.contextPath }/resources/images/header-icon.png">
						<span class="main-title"></span>
					</div>
					<button class="header-left-btn"></button>
				</div>
				<!--main-header-bar end-->

				<!--내용-->
				<div class="main-contents-box normal">

					<!--컨텐츠영역-->
					<div class="contents-container sort-row">
						<div class="contents-box notice">

							<div class="contents-title notice-tit">글쓰기</div>

							<form action="noinsert.do" method="post"
								enctype="multipart/form-data" onsubmit="inputSubmit();">
								<input type="hidden" value="${ sessionScope.loginMember.empId }"
									name="empId">
								<div class="select-box">
									<div class="select-pan-nemo">제목</div>

									<div class="select-pan">
										<input type="text" class="input-box-input noline"
											placeholder="제목을 입력하세요." name="noticeTitle">
									</div>
								</div>

								<div class="select-box">
									<div class="select-pan-nemo">
										
										<input type="checkbox" class="select-checkbox"
											name="importance" value="Y" id="importance" checked>
										필독

									</div>

									<input type="date"
										class="select-date select-date-first font-size13" name="importanceDate" id="importance_date" placeholder="필독기간">
								</div>

								<div class="select-box">
									<div class="select-pan font-size13">
										<label for="classsify"></label>
										<select name="classify" id="classify">
											<option value="all" selected>전체 공지</option>
											<option value="dept">부서 공지</option>
											<!-- <option value="emp">직원 공지</option> -->
										</select>
									</div>

									<div class="select-pan font-size13 search-div">
										<div>
											<label for="sel_code"></label>
											<select class="classify-sub" name="depType" id="dep_type">
												<option value="all">부서</option>
												<c:forEach items="${ depList }" var="dep">
													<option value="${ dep.depId }">${ dep.depName }</option>
												</c:forEach>
											</select>
										</div>
										<%-- <table class="search-table">
											<tr>
												<td>
													<div class="contents-input-div input-search classify-sub" id="emp_list">
														<button type="button" class="input-search-btn">
															<img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
														</button>
														<input type="text" name="empList" class="contents-input" value="">
														<div class="input-search-box">
															<span>홍길동<span class="close-search">x</span></span> 
															<span>홍길동<span class="close-search">x</span></span>
														</div>
													</div>
												</td>
											</tr>
										</table> --%>
									</div>
								</div>

								<div class="select-box">
									<div class="select-pan-nemo">파일첨부</div>
									<div class="select-pan">
										<input type="file" name="nofile" class="select-file">
									</div>
								</div>

								<div class="content-input-area  height-long">
									<div class="content-input" contenteditable="true"></div>
									<textarea name="noticeDetail" id="appendTextArea"></textarea>
								</div>

								<div class="content-input-btn-box">
									<input type="submit" class="contents-input-btn big noline"
										id="btn_insert" value="등록">
								</div>

							</form>
						</div>
						<!--컨텐츠영역 end-->

					</div>
					<!--내용 end-->
				</div>
				<!--main-container end-->

				<!--modal-pop-area-->
	            <div class="modal-pop-area">
	                <c:import url="../common/popup.jsp" />
	            </div>
				<!--modal-pop-area end-->
		</main>

	</div>
</body>
</html>