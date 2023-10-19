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
		//공지사항 수정페이지로 이동
    	$('.notice-update').on('click', function(){
            if(confirm('수정 화면으로 이동하시겠습니까?')) {
                location.href = 
                'ndmoveupdate.do?noticeId=${ notice.noticeId }';
            }
    	});
    	
    	//공지사항 삭제
    	$('.notice-delete').on('click', function(){
    		if(confirm('정말 삭제하시겠습니까?')) {
                location.href = 'ndelete.do?noticeId=${ notice.noticeId }';
            }
    	});
	});
	
	
		
    	
		
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

					<!--서치영역-->
					<div class="search-container noline">
						<form class="search-form">

							<div class="select-search">
								<div class="select-box">
									<div class="select-pan">
										<label for="sel_code"></label> <select name="code"
											id="sel_code">
											<option value="">내용</option>
											<option value="">작성자</option>
											<option value="">댓글</option>
										</select>
									</div>
								</div>

								
							</div>

							<div class="select-box">
								<div class="select-pan-nemo">작성날짜</div>

								<input type="date" class="select-date select-date-first">
								<input type="date" class="select-date select-date-second">
								<input type="button" name="week" class="select-pan-btn"
									value="일주일"> <input type="button" name="month"
									class="select-pan-btn" value="한달">
							</div>

						</form>

						<button class="search-visible-btn" id="search_visible_btn">
							<img class="search-close"
								src="${ pageContext.servletContext.contextPath }/resources/images/cursor_1.png">
							<img class="search-open"
								src="${ pageContext.servletContext.contextPath }/resources/images/cursor_2.png">
						</button>

					</div>
					<!--서치영역 end-->

					<!--컨텐츠영역-->
					<div class="contents-container sort-row">
						<div class="contents-box notice" data-num="">



							<!--contents-notice-->
						<!--컨텐츠영역-->
                    <div class="contents-container sort-row">
                        <div class="contents-box notice" data-num="">

                            <div class="contents-title notice-tit small">
                                ${ notice.noticeId} 공지사항
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
							<div class="contents-notice-title big">${ notice.noticeTitle }
						</div>
							<div class="contents-notice-line">
								<div class="notice-profile">
									<img
										src="${ pageContext.servletContext.contextPath }/resources/images/profile.png">
									<span>
										<div class="notice-profile-one">
											
										</div>
										<div>
											<span>${ notice.empName }</span>
											<span>${ notice.noticeCreateDate }</span> <span>|</span> 
											<span class="bluetext">${notice.noticeReadCount }</span>

										</div>
									</span>
								</div>
							</div>
							<c:url var="bdown" value="bddown.do">
								<c:param name="ofile" value="${ notice.noticeOriginalFileName }" />
								<c:param name="rfile" value="${ notice.noticeRenameFileName }" />
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

						<div class="notice-contents-box">${ notice.noticeDetail }</div>
						<div class="contents-notice-buttonbox">
						
						<div class="button-box" text-align:center;>
					
						<a class="button-a notice-update" href="#"> 수정 </a>
					   	<a class="button-a notice-delete" href="#"> 삭제 </a>
					   	
					   	</div>
						
					   
							


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