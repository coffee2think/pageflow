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
<script type="text/javascript">
	const NOWPAGE = 1;
	const SUBPAGE = 1;
	const LNKPAGE = 1;

  $(function(){
		$('#btn_insert').click(function() {
            $('#appendTextArea').val($('.content-input').text());
        });
	});
	function inputSubmit(){
    	$('#btn_insert').click();
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
						<a
							href="${ pageContext.servletContext.contextPath }/views/work/notice_input.jsp"
							class="side-write-btn margin-bottom-0">글쓰기</a>
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

							<input type="checkbox" class="select-checkbox" name="importance" value="Y" id="importance" checked> 필독

									</div>

									<input type="date"
											class="select-date select-date-first font-size13"
									>
								</div>

								<div class="select-box">
									<div class="select-pan font-size13">
										<label for="classsify"></label> <select name="classify"
											id="classify">
											<option value="all" selected>전체 공지</option>
											<option value="dept">부서 공지</option>
											<option value="emp">직원 공지</option>
											
										</select>
										
									</div>

									<div class="select-pan font-size13">
										<label for="sel_code"></label> <select name="code"
											id="sel_code">
											<option value="">부서</option>
											<option value="">작성자</option>
											<option value="">제목</option>
										</select>
									</div>

									<input type="button" class="notice-sel-btn" value="직원목록">
								</div>

								<div class="select-box">
									<div class="select-pan-nemo">파일첨부</div>
										<c:url var="ndown" value="bddown.do">
                                                <c:param name="ofile" value="${ notice.noticeoriginFileName }" />
                                                <c:param name="rfile" value="${ notice.renameFileName }" />
                                        </c:url>
                                            <div class="contents-notice-down-box show">
                                                <a class="contents-notice-down" href="${ ndown }">
                                                    <img src="${ pageContext.servletContext.contextPath }/resources/images/side-icon-dep1-over.png">
                                                    <span class="origin-file-name">${ notice.noticeOriginalFileName }</span>
                                                    <img class="down-img" src="${ pageContext.servletContext.contextPath }/resources/images/down.png">
                                                </a>
                                            </div>
                                 <div class="select-pan margin-left20px">
                                                <input type="file" name="nofile" class="select-file">
                                 </div>
                                        
                                       
                                          <!--   <div class="select-pan">
                                                <input type="file" name="nfile" class="select-file">
                                            </div>
                                         -->
								</div>
								
								
								<div class="content-input-area  height-long">
									<div class="content-input" contenteditable="true">

									
									
								

									</div>
									<textarea name="noticeDetail" id="appendTextArea" ></textarea>
								</div>
									
							
								<div class="content-input-btn-box">
									<input type="submit" class="contents-input-btn big noline" id="btn_insert" value="등록">
								</div>

							</form>	
								
								
								
							 
						

					</div>
					<!--컨텐츠영역 end-->

				</div>
				<!--내용 end-->

				<!--
                <div class="submit-box">
                    <input type="button" class="contents-input-btn big noline" id="btn_write" value="글쓰기">
                </div>
                -->
			</div>
			<!--main-container end-->

			<!--modal-pop-area-->
			<div class="modal-pop-area"></div>
			<!--modal-pop-area end-->
		</main>

	</div>
</body>
</html>