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

					<!--컨텐츠영역-->
					<div class="contents-container sort-row">
						<div class="contents-box notice" data-num="">

							<!--contents-notice-->
						<!--컨텐츠영역-->
                    <div class="contents-container sort-row">
                        <div class="contents-box notice" data-num="">

                            <div class="contents-title notice-tit small">
                                ${ notice.noticeId} 번 공지사항
                                
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
							<div class="contents-notice-title big">
							<h3> 제목 : ${ notice.noticeTitle }</h3>
						</div>
							
								
							<div> 
								
								작성자 : ${ notice.empName }
								작성 날짜 : ${ notice.noticeCreateDate }
								조회 수 : ${notice.noticeReadCount }
								첨부파일 : ${notice.noticeOriginalFileName }
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
										<img class="down-img"src="${ pageContext.servletContext.contextPath }/resources/images/down.png">
									</a>
								</div>
							</c:if>
							
						</div>
						
						<div class="notice-contents-box">
						
							${ notice.noticeDetail }
						
						</div>
                
						<div class="button-box" >
					
						<a class="button-a notice-update" href="#"> 수정 </a>
					   	<a class="button-a notice-delete" href="#"> 삭제 </a>
					   	
					   	</div>
					   
     
              
							
							
		</main>

	</div>
</body>
</html>