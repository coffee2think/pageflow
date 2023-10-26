<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="today_" value="<%= new java.util.Date() %>" />
<fmt:formatDate var="today" value="${ today_ }" pattern="yyyy-MM-dd" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/notice.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<title>공지사항</title>
<script>
	const NOWPAGE = 1;
	const SUBPAGE = 1;
	const LNKPAGE = 1;

	function searchNotice() {
		url = "nsearch.do?action=" + $("#sel_code").val() + "&keyword="
				+ $("#nkeyword").val();
		location.href = url;
		console.log(url);
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
						<a href="nemoveinsert.do?empId=${ empId }" class="side-write-btn">공지글쓰기</a>
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

				<!--내용-->
				<div class="main-contents-box">

					<!--서치영역-->
					<div class="search-container noline">
						<form class="search-form">
							<div class="select-search">
								<div class="select-box">
									<div class="select-pan">
										<label for="sel_code"></label>
										<select name="action" id="sel_code">
											<option value="title">제목</option>
											<option value="writer">작성자</option>
										</select>
									</div>
								</div>

								<div class="search-box">
									<button class="search-btn" onclick="searchNotice(); return false;">
										<img class="search-image"
											src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
									</button>
									<input type="search" placeholder="키워드를 입력하세요." name="keyword"
										class="search-box-text" value="" id="nkeyword">
								</div>
							</div>
						</form>
					</div>
					<!--서치영역 end-->

					<!--컨텐츠영역-->
					<%-- 조회된 공지사항 목록 출력 --%>
					<div class="contents-container sort-row">
						<div class="contents-box notice" style="width: 1200px;">
							<div class="contents-title notice-tit">공지사항</div>
								<table class="contents-table work">
									<tr>
										<th>번호</th>
										<th>부서</th>
										<th>제목</th>
										<th>조회수</th>
										<th>날짜</th>
									</tr>
									<c:if test="${ !empty importantList }">
										<c:forEach items="${ importantList }" var="notice">
											<script>
												
											</script>
											<tr>
												<td>[ 필독 ]</td>
												<td>
													<c:choose>
														<c:when test="${ !empty notice.refDepName }">
															[ ${ notice.refDepName } ]
														</c:when>
														<c:otherwise>
															[ 전체 ]
														</c:otherwise>
													</c:choose>
												</td>
												<td>
													<c:url var="detailUrl" value="ndetail.do">
														<c:param name="noticeId" value="${ notice.noticeId }" />
													</c:url>
													<a href="${ detailUrl }">
														${ notice.noticeTitle }
													</a>
													<c:if test="${ notice.noticeCreateDate == today }">
														<img src="${ pageContext.servletContext.contextPath }/resources/images/notice-new.png" width="13" height="13">
													</c:if>
													<c:if test="${ !empty notice.noticeOriginalFileName }">
														<img src="${ pageContext.servletContext.contextPath }/resources/images/notice-attach.png" width="13" height="13">
													</c:if>
												</td>
												<td>${ notice.noticeReadCount }</td>
												<td>${ notice.noticeCreateDate }</td>
											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${ !empty list }">
										<c:forEach items="${ list }" var="notice">
											<tr>
												<td>${ notice.noticeId }</td>
												<td>
													<c:choose>
														<c:when test="${ !empty notice.refDepName }">
															[ ${ notice.refDepName } ]
														</c:when>
														<c:otherwise>
															[ 전체 ]
														</c:otherwise>
													</c:choose>
												</td>
												<td>
													<c:url var="detailUrl" value="ndetail.do">
														<c:param name="noticeId" value="${ notice.noticeId }" />
													</c:url>
													<a href="${ detailUrl }">
														${ notice.noticeTitle }
													</a>
													<c:if test="${ notice.noticeCreateDate == today }">
														<img src="${ pageContext.servletContext.contextPath }/resources/images/notice-new.png" width="13" height="13">
													</c:if>
													<c:if test="${ !empty notice.noticeOriginalFileName }">
														<img src="${ pageContext.servletContext.contextPath }/resources/images/notice-attach.png" width="13" height="13">
													</c:if>
												</td>
												<td>${ notice.noticeReadCount }</td>
												<td>${ notice.noticeCreateDate }</td>
											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${ empty list }">
										<tr>
											<td colspan="5">등록된 공지사항이 없습니다.</td>
										</tr>
									</c:if>
								</table>
								<div class="paging-box">
									<!-- 페이징 -->
									<c:import url="../common/paging.jsp" />
								</div>
							</div>
						</div>
					</div>
					<!--컨텐츠영역 end-->
				</div>
				<!--내용 end-->
			</div>
			<!--main-container end-->
		</main>
	</div>
</body>
</html>