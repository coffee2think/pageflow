<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/notice.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<title>${ notice.noticeTitle }</title>
<style>
.notice-info-box {
	width: 100%;
	height: 50px;
	background-color: gainsboro;
	display: flex;
	align-items: center;
}

.notice-info-box span {
	margin: 10px;
	font-size: medium;
}
</style>
<script type="text/javascript">
	const NOWPAGE = 1;
	const SUBPAGE = 1;
	const LNKPAGE = 1;
	
	$(function(){
		//공지사항 수정페이지로 이동
    	$('.button-update').on('click', function(){
            if(confirm('수정 화면으로 이동하시겠습니까?')) {
                location.href = 'nmoveup.do?noticeId=${ notice.noticeId }';
            }
    	});
    	
    	//공지사항 삭제
    	$('.button-delete').on('click', function(){
    		if(confirm('정말 삭제하시겠습니까?')) {
    			url = 'ndelete.do';
    			
    			// form 태그에 담아서 post 전송
    		    const form = document.createElement('form'); // form 태그 생성
    		    form.setAttribute('method', 'post'); // 전송 방식 결정 (get or post)
    		    form.setAttribute('action', url); // 전송할 url 지정
    		    
    		 	// input 태그 생성하여 데이터 담기
		        const data = document.createElement('input');
		        data.setAttribute('type', 'hidden');
		        data.setAttribute('name', 'noticeId'); // 데이터의 name
		        data.setAttribute('value', '${ notice.noticeId }'); // 데이터의 value
		        form.appendChild(data); // form 태그 안에 input 태그 추가
    		    
    		    // body에 form 태그 추가하고 submit 전송
    		    document.body.appendChild(form);
    		    form.submit();
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
						<a href="${ pageContext.servletContext.contextPath }/views/work/notice_input.jsp"
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
						<img src="${ pageContext.servletContext.contextPath }/resources/images/header-icon.png">
						<span class="main-title"></span>
					</div>
					<button class="header-left-btn"></button>
				</div>
				<!--main-header-bar end-->

				<!--컨텐츠영역-->
				<div class="contents-container sort-row">
					<div class="contents-box notice" data-num="">

						<!--contents-notice-->
						<div class="contents-notice notice">
							<div class="notice-title-box">
								<div class="contents-notice-title big">
									${ notice.noticeTitle }
								</div>
								<div class="contents-notice-line">
									<div class="notice-profile">
										<img src="${ pageContext.servletContext.contextPath }/resources/images/profile.png">
										<span>
											<div class="notice-profile-one">${ notice.empName }</div>
											<div>
												<span>${ notice.noticeCreateDate }</span>
												<span>|</span>
												<span class="bluetext">읽음 ${ notice.noticeReadCount }</span>
											</div>
										</span>
									</div>
								</div>
								<c:url var="ndown" value="nfdown.do">
									<c:param name="ofile" value="${ notice.noticeOriginalFileName }" />
									<c:param name="rfile" value="${ notice.noticeRenameFileName }" />
								</c:url>

								<c:if test="${ !empty notice.noticeOriginalFileName }">
									<div class="contents-notice-down-box show">
										<a class="contents-notice-down" href="${ ndown }">
											<img src="${ pageContext.servletContext.contextPath }/resources/images/side-icon-dep1-over.png">
											<span class="origin-file-name">${ notice.noticeOriginalFileName }</span>
											<img class="down-img" src="${ pageContext.servletContext.contextPath }/resources/images/down.png">
										</a>
									</div>
								</c:if>
							</div>
							
							<div class="notice-contents-box">
								${ notice.noticeDetail }
							</div>
							<div class="contents-notice-buttonbox">
								<div class="button-box">
									<c:url var="nlistUrl" value="nlist.do">
										<c:if test="${ !empty page }">
											<c:param name="page" value="${ page }" />
										</c:if>
									</c:url>
									<a class="button-a button-list" style="width: 100px; height: 30px;" href="${ nlistUrl }">
										목록으로
									</a>
									<c:if test="${ loginMember.empId == notice.empId }">
										<a class="button-a button-update">수정</a>
									</c:if>
									<c:if test="${ loginMember.empId == notice.empId || loginMember.adminYN == 'Y' }">
										<a class="button-a button-delete">삭제</a>
									</c:if>
								</div>
							</div>
						</div>
						<!--contents-notice end-->

					</div>
				</div>
				<!--컨텐츠영역 end-->

			</div>		   	
		</main>
	</div>
</body>
</html>