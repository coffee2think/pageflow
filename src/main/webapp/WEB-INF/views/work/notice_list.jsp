<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <c:set var="list" value="${ requestScope.list }" />
<c:set var="begin" value="${ requestScope.begin }" />
<c:set var="end" value="${ requestScope.end }" /> --%>
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
<script>
	const NOWPAGE = 1;
	const SUBPAGE = 1;
	const LNKPAGE = 1;
	
	function search(){
		url = "nsearch.do?action=" + $("#sel_code").val() + "&keyword=" + $("#nkeyword").val();
		location.href = url;
		console.log(url);
			
	}
	
	/* // 날짜 검색 버튼 클릭시
 	function searchByDate() {
    	var begin = $('#begin').val();
    	var end = $('#end').val();
    	
    	var url = 'ndate.do?';
    	url += 'begin=' + begin;
    	url += '&end=' + end;
    	
    	location.href = url;
    } */
	
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
						<a href="nemoveinsert.do?empId=${ empId }" class="side-write-btn">글쓰기</a>
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
				<div class="main-contents-box">

					<!--서치영역-->

					<div class="search-container noline">
						<form class="search-form">

							<div class="select-search">
								<div class="select-box">
									<div class="select-pan">
										<label for="sel_code"></label> <select name="action"
											id="sel_code">
											<option value="title">제목</option>
											<option value="writer">작성자</option>

										</select>
									</div>
								</div>

								<div class="search-box">
									<button class="search-btn" onclick="search(); return false;">
										<img class="search-image"
											src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
									</button>
									<input type="search" placeholder="키워드를 입력하세요." name="keyword"
										class="search-box-text" value="" id="nkeyword">
								</div>
							</div>

							<%-- <div class="select-box">
						   <div class="select-pan-nemo">날짜 </div>
						        <c:choose> 
						            <c:when test="${ !empty firstType and firstType eq 'first' }">
						                <input type="date" class="select-date select-date-first" id="begin_startDate">
						                <input type="date" class="select-date select-date-second" id="end_startDate">
						            </c:when> 
						            
						            <c:otherwise>
						                <input type="date" class="select-date select-date-first" id="begin_startDate" value="${ begin }">
						                <input type="date" class="select-date select-date-second" id="end_startDate" value="${ end }">
						            </c:otherwise> 
						        </c:choose> 
						        
						        <c:set var="today_" value="<%= new java.util.Date() %>" />
						        <fmt:formatDate var="today" value="${ today_ }" pattern="yyyy-MM-dd" />
						
						        <c:set var="weekago_" value="<%= java.util.Date.from(java.time.LocalDate.now().minusWeeks(1).plusDays(1).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()) %>" />
						        <fmt:formatDate var="weekago" value="${ weekago_ }" pattern="yyyy-MM-dd" />
						
		
						        <c:set var="monthago_" value="<%= java.util.Date.from(java.time.LocalDate.now().minusMonths(1).plusDays(1).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()) %>" />
						        <fmt:formatDate var="monthago" value="${ monthago_ }" pattern="yyyy-MM-dd" />
						
						        <c:url var="searchWeekUrl" value="ndate.do">
						            <c:param name="begin" value="${ weekago }" />
						            <c:param name="end" value="${ today }" />
						            <c:param name="dateType" value="startDate" />
						        </c:url>
						
						        <c:url var="searchMonthUrl" value="ndate.do">
						            <c:param name="begin" value="${ monthago }" />
						            <c:param name="end" value="${ today }" />
						            <c:param name="dateType" value="startDate" />
						        </c:url>
						
						        <input type="button" name="week" class="select-pan-btn" value="일주일" onclick="javascript: location.href='${ searchWeekUrl }'">
						        <input type="button" name="month" class="select-pan-btn" value="한달" onclick="javascript: location.href='${ searchMonthUrl }'">
						        <input type="button" name="search" class="select-pan-btn" value="검색" onclick="searchByDate('startDate'); return false;">
						 </div>
							 --%>

							<div class="paging-box">
								<!-- 페이징 -->
								<c:import url="../common/paging.jsp" />
							</div>

							<button class="search-visible-btn" id="search_visible_btn">
								<img class="search-close"
									src="${ pageContext.servletContext.contextPath }/resources/images/cursor_1.png">
								<img class="search-open"
									src="${ pageContext.servletContext.contextPath }/resources/images/cursor_2.png">
							</button>
					</div>
					<!--서치영역 end-->

					<!--컨텐츠영역-->

					<%-- 조회된 공지사항 목록 출력 --%>


					<div class="contents-container sort-row">
						<div class="contents-box notice">

							<div class="contents-title notice-tit">공지사항</div>
							<c:forEach items="${ requestScope.list }" var="notice">
								<c:url var="listUrl" value="nolist.do">
									<c:param name="noticeId" value="${ notice.noticeId }" />
									<c:param name="empId" value="${ notice.empId }" />
									<c:param name="noticeTitle" value="${ notice.noticeTitle }" />
									<c:param name="EmpName" value="${ notice.empName }" />
									<c:param name="noticeOriginalFileName"
										value="${ notice.noticeOriginalFileName }" />
								</c:url>
								<diV>
									<a class="contents-notice" href="${ listUrl}">
										<div class="contents-notice-title">
											<span class="alarm">${notice.noticeTitle }</span>
										</div>

										<div class="contents-notice-line">
											<span>${notice.noticeId }</span> <span>|</span> <span>${notice.empName}</span>
											<span>|</span> <span>${notice.noticeCreateDate }</span> <span>|</span>
											<span>${notice.noticeReadCount }</span>
											<c:if test="${!empty notice.noticeOriginalFileName}">
												<span>첨부파일 : ★ </span>
											</c:if>
											<c:if test="${empty notice.noticeOriginalFileName}">
												<span>첨부파일 :  </span>
											</c:if>

										</div>
									</a>
							</c:forEach>

						</div>
					</div>
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



	<br>

	</main>

	</div>
</body>
</html>