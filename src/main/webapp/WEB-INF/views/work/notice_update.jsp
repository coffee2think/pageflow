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
<script >
	const NOWPAGE = 1;
	const SUBPAGE = 1;
	const LNKPAGE = 1;

	$(function() {
		//공지사항  수정
		$('#btn_update').on('click', function() {
			$('#appendTextArea').val($('.content-input').text());
			$('#upnotice').submit();
			
		});

		
		
		
     });
	
	function inputSubmit(){
    	$('#btn_update').click();
	}
	
	
         
    
	
	/* function cancelSubmit(){
    	$('#btn_cancel').click();      
    }
	 */
	
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

			<form action="noupdate.do" method="post"
				enctype="multipart/form-data" id="upnotice" onsubmit="inputSubmit();">
				<input type="hidden" value="${notice.noticeId }" name="noticeId">

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

					</div>

								
						

						<!--컨텐츠영역-->
						<div class="contents-container sort-row">
							<div class="contents-box notice" data-num="">
							
								<!--contents-notice-->
								<!--컨텐츠영역-->
								<div class="contents-container sort-row">
									<div class="contents-box notice" data-num="">

										<div class="contents-title notice-tit small">${ notice.noticeId}
											공지사항
										</div>

										<!-- </div>
						contents-notice end

						<div class="button-box">
							<a class="button-a button-list" href="#"> 목록 </a> <a
								class="button-a button-prev" href="#"> ▲ </a> <a
								class="button-a button-next" href="#"> ▼ </a> <a
								class="button-a button-top" href="#"> 위로 </a>
						</div> -->

									</div>
								</div>


								<!--내용-->
								<div class="main-contents-box normal">

									<!--컨텐츠

                            

                 <!--contents-notice-->

									<div class="contents-notice notice">

										<div class="notice-title-box">
											<input type="text" value="${ notice.noticeTitle }"
												name="noticeTitle">
										</div>
										<div class="contents-notice-line">
											<div class="notice-profile">
												<img
													src="${ pageContext.servletContext.contextPath }/resources/images/profile.png">
												<span>
													<div class="notice-profile-one"></div>
													<div>
														<span>${ notice.empName }</span> <span>${ notice.noticeCreateDate }</span>
														<span>|</span> <span class="bluetext">${notice.noticeReadCount }</span>

													</div>
												</span>
											</div>
										</div>
										<c:url var="bdown" value="bddown.do">
											<c:param name="ofile"
												value="${ notice.noticeOriginalFileName }" />
											<c:param name="rfile"
												value="${ notice.noticeRenameFileName }" />
										</c:url>

										<c:if test="${ !empty notice.noticeOriginalFileName }">

											<div class="contents-notice-down-box show">
												<a class="contents-notice-down" href="${ bdown }"> <img
													src="${ pageContext.servletContext.contextPath }/resources/images/side-icon-dep1-over.png">
													<span class="origin-file-name">${ notice.noticeOriginalFileName }</span>
													<img class="down-img"
													src="${ pageContext.servletContext.contextPath }/resources/images/down.png">
												</a>
											</div>
										</c:if>

										<c:if test="${ empty notice.noticeOriginalFileName }">

											<div class="contents-notice-down-box">
												<a class="contents-notice-down" href="${ bdown }"> <img
													src="${ pageContext.servletContext.contextPath }/resources/images/side-icon-dep1-over.png">
													<span class="origin-file-name">${ notice.noticeOriginalFileName }</span>

												</a>
											</div>
										</c:if>
									</div>

									<div class="content-input-area height-long">
										<div class="content-input" contenteditable="true">
											 ${notice.noticeDetail}
										</div>
										<textarea name="noticeDetail" id="appendTextArea"></textarea>
									</div>

									<div class="contents-notice-buttonbox">
										<div class="button-box" >
											<input type="submit" class="contents-input-btn big noline" id="btn_update" value="수정">
											<input type="reset" class="contents-input-btn big nolines" id="btn_cancel" value="취소"
											onclick="javascript:history.go(-1); return false;"> &nbsp;>
			
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</form>





					<!--내용 end-->

					<!--
                <div class="submit-box">
                    <input type="button" class="contents-input-btn big noline" id="btn_write" value="글쓰기">
                </div>
                -->
				
			<!--main-container end-->

		</main>

	</div>

</body>
</html>