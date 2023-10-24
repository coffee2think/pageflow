<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/print_func.js"></script>
<script type="text/javascript">
    const NOWPAGE = 2;
    const SUBPAGE = 1;
    const LNKPAGE = 1;
    
	$(function(){
		calculateAmount();
	});
    
	$(function(){
		deleteConfirm();
	});
</script>


<script type="text/javascript">

	//검색 키워드1
	/* var searchKeyword = "";
	// 서치 버튼 클릭
	$(function(){
		$('.search-btn').on('click', function(){
			searchKeyword('pokeyword.do');
		});

		// After Enter Key
		$('.search-box-text').on('keypress', function(e){
			if (e.which === 13) {
				searchKeyword('pokeyword.do');
			}
		});
	}); */

	// 삭제 확인 창 띄우기
	function deleteConfirm(orderId) {
	    if (confirm("정말 삭제하시겠습니까?")) {
	        // AJAX를 이용한 삭제 작업 수행
	        $.ajax({
	            type: "POST",
	            url: "podelete.do",
	            data: { orderId: orderId },
	            success: function(data) {
	                // 성공 시 처리할 내용
	                alert("삭제되었습니다.");
	                // 페이지 새로고침 또는 필요한 동작 수행
	            },
	            error: function() {
	                // 오류 발생 시 처리할 내용
	                alert("삭제 실패. 다시 시도해주세요.");
	            }
	        });
	    }
</script>
<title></title>
<script type="text/javascript">
	
	// 시작날짜 검색 버튼
	function searchByDate(dateType) {
    	var begin = $('#begin_' + dateType).val();
    	var end = $('#end_' + dateType).val();
    	
    	var url = 'polistdate.do?';
    	url += 'begin=' + begin;
    	url += '&end=' + end;
    	url += '&dateType=' + dateType
    	
    	location.href = url;
    }

	// 마감날짜 검색 버튼
	function searchByDate(dateType) {
    	var begin = $('#begin_' + dateType).val();
    	var end = $('#end_' + dateType).val();
    	
    	var url = 'polistdate.do?';
    	url += 'begin=' + begin;
    	url += '&end=' + end;
    	url += '&dateType=' + dateType
    	
    	location.href = url;
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
					<!-- 리스트 들어감 -->
					<c:import url="../common/side.jsp" />
				</div>
			</div>
			<!--main-side end-->

			<!--main-container-->
			<div class="main-container">

				<!--main-header-bar-->
				<div class="main-header-bar">
					<div class="main-title-box">
						<img src="${ pageContext.servletContext.contextPath }/resources/images/header-icon.png">
						<span class="main-title"></span>
					</div>
					<button class="header-left-btn">
					</button>
				</div>
				<!--main-header-bar end-->

				<!--내용-->
				<div class="main-contents-box">

					<!--서치영역-->
					<div class="search-container">
						<form class="search-form">
							<div class="select-search">
								<div class="select-box">
									<div class="select-pan">
										<label for="sel_code"></label> 
										<select name="searchType" id="search_type">
											<option value="orderId" <c:if test="${ searchType == 'orderId' }">seleted</c:if>>발주코드</option>
											<option value="printName" <c:if test="${ searchType == 'printName' }">seleted</c:if>>인쇄소명</option>
											<option value="bookId" <c:if test="${ searchType == 'bookId' }">seleted</c:if>>도서코드</option>
											<option value="bookName" <c:if test="${ searchType == 'bookName' }">seleted</c:if>>도서명</option>
										</select>
									</div>
								</div>

								<div class="search-box">
									<input type="search" placeholder="키워드를 입력하세요." class="search-box-text" value="${ keyword }" name="keyword">
									<button class="search-btn" onclick="searchkeyword('pokeyword.do'); return false; ">
										<img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
									</button>
								</div>
							</div>

							<!-- <div class="select-box">
								<div class="select-pan">
									<label for="sel_code"></label> <select name="code" id="sel_code">
										<option value="all">인쇄소별</option>
										<option value="">거성인쇄</option>
										<option value="">JK인쇄</option>
									</select>
								</div>

								<div class="select-pan">
									<label for="sel_code"></label> <select name="code" id="sel_code">
										<option value="all">상태</option>
										<option value="">지급</option>
										<option value="">미지급</option>
									</select>
								</div>
							</div> -->

							<div class="select-box">
								<div class="select-pan-nemo">발주일</div>

								<input type="date" class="select-date select-date-first" name="begin" value=${ begin }>
								<input type="date" class="select-date select-date-second" name="end" value=${ end }>

								<c:set var="today_" value="<%=new java.util.Date()%>" />
								<fmt:formatDate var="today" value="${ today_ }" 
									pattern="yyyy-MM-dd" />
								<c:set var="weekago_" 
									value="<%=new java.util.Date(new java.util.Date().getTime() - 60 * 60 * 24 * 1000 * 6)%>" />
								<fmt:formatDate var="weekago" value="${ weekago_ }" 
									pattern="yyyy-MM-dd" />
								<c:set var="monthago_" 
									value="<%=new java.util.Date(new java.util.Date().getTime() - 60 * 60 * 24 * 1000 * 30)%>" />
								<fmt:formatDate var="monthago" value="${ monthago_ }" 
									pattern="yyyy-MM-dd" />

								<c:url var="searchWeekUrl" value="polistdate.do">
									<c:param name="begin" value="${ weekago }" />
									<c:param name="end" value="${ today }" />
									<c:param name="dateType" value="startDate" />
								</c:url>

								<c:url var="searchMonthUrl" value="polistdate.do">
									<c:param name="begin" value="${ monthago }" />
									<c:param name="end" value="${ today }" />
									<c:param name="dateType" value="startDate" />
								</c:url>

								<input type="button" name="week" class="select-pan-btn" value="일주일" onclick="javascript: location.href='${ searchWeekUrl }'">
								<input type="button" name="month" class="select-pan-btn" value="한달" onclick="javascript: location.href='${ searchMonthUrl }'">
								 <input type="button" name="searchBtn" class="select-pan-btn" value="검색" onclick="searchByDate(); return false;">
							</div>

							<div class="select-box">
								<div class="select-pan-nemo">마감일</div>

								<input type="date" class="select-date select-date-first" name="begin" value=${ begin }>
								<input type="date" class="select-date select-date-second" name="end" value=${ end }>

								<c:set var="today_" value="<%=new java.util.Date()%>" />
								<fmt:formatDate var="today" value="${ today_ }"
									pattern="yyyy-MM-dd" />
								<c:set var="weekago_"
									value="<%=new java.util.Date(new java.util.Date().getTime() - 60 * 60 * 24 * 1000 * 6)%>" />
								<fmt:formatDate var="weekago" value="${ weekago_ }"
									pattern="yyyy-MM-dd" />
								<c:set var="monthago_"
									value="<%=new java.util.Date(new java.util.Date().getTime() - 60 * 60 * 24 * 1000 * 30)%>" />
								<fmt:formatDate var="monthago" value="${ monthago_ }"
									pattern="yyyy-MM-dd" />

								<c:url var="searchWeekUrl" value="polistdate.do">
									<c:param name="begin" value="${ weekago }" />
									<c:param name="end" value="${ today }" />
									<c:param name="dateType" value="startDate" />
								</c:url>

								<c:url var="searchMonthUrl" value="polistdate.do">
									<c:param name="begin" value="${ monthago }" />
									<c:param name="end" value="${ today }" />
									<c:param name="dateType" value="startDate" />
								</c:url>

								<input type="button" name="week" class="select-pan-btn" value="일주일" onclick="javascript: location.href='${ searchWeekUrl }'">
								<input type="button" name="month" class="select-pan-btn" value="한달" onclick="javascript: location.href='${ searchMonthUrl }'">
								 <input type="button" name="searchBtn" class="select-pan-btn" value="검색" onclick="searchByDate(); return false;">
							</div>

							<div class="select-box">
								<div class="select-pan-nemo">출간일</div>

								<input type="date" class="select-date select-date-first" name="begin" value=${ begin }>
								<input type="date" class="select-date select-date-second" name="end" value=${ end }>

								<c:set var="today_" value="<%=new java.util.Date()%>" />
								<fmt:formatDate var="today" value="${ today_ }"
									pattern="yyyy-MM-dd" />
								<c:set var="weekago_"
									value="<%=new java.util.Date(new java.util.Date().getTime() - 60 * 60 * 24 * 1000 * 6)%>" />
								<fmt:formatDate var="weekago" value="${ weekago_ }"
									pattern="yyyy-MM-dd" />
								<c:set var="monthago_"
									value="<%=new java.util.Date(new java.util.Date().getTime() - 60 * 60 * 24 * 1000 * 30)%>" />
								<fmt:formatDate var="monthago" value="${ monthago_ }"
									pattern="yyyy-MM-dd" />

								<c:url var="searchWeekUrl" value="polistdate.do">
									<c:param name="begin" value="${ weekago }" />
									<c:param name="end" value="${ today }" />
									<c:param name="dateType" value="startDate" />
								</c:url>

								<c:url var="searchMonthUrl" value="polistdate.do">
									<c:param name="begin" value="${ monthago }" />
									<c:param name="end" value="${ today }" />
									<c:param name="dateType" value="startDate" />
								</c:url>

								<input type="button" name="week" class="select-pan-btn" value="일주일" onclick="javascript: location.href='${ searchWeekUrl }'">
								<input type="button" name="month" class="select-pan-btn" value="한달" onclick="javascript: location.href='${ searchMonthUrl }'">
								 <input type="button" name="searchBtn" class="select-pan-btn" value="검색" onclick="searchByDate(); return false;">
									
							</div>

						</form>

						<div class="paging-box">
							<!-- 페이징 -->
							<c:import url="../common/paging.jsp" />
						</div>

						<button class="search-visible-btn" id="search_visible_btn">
							<img class="search-close" src="${ pageContext.servletContext.contextPath }/resources/images/cursor_1.png">
							<img class="search-open" src="${ pageContext.servletContext.contextPath }/resources/images/cursor_2.png">
						</button>

					</div>
					<!--서치영역 end-->

					<!--컨텐츠영역-->
					<div class="contents-container sort-row">
						<div class="contents-box">
							<table class="contents-table" id="table_list">
								<tr>
									<th>체크</th>
									<th>발주코드</th>
									<th>거래처코드</th>
									<th>인쇄소</th>
									<th>발주일</th>
									<th>마감일</th>
									<th>출간일</th>
									<th>도서코드</th>
									<th>도서명</th>
									<th>단위</th>
									<th>수량</th>
									<th>단가</th>
									<th>합계</th>
									<th>상태</th>
									<th>수정</th>  
								</tr>
								<c:if test="${ !empty list }">
									<c:forEach items="${ list }" var="printOrder">
										<tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1" id="tr_${ printOrder.orderId }">
											<td class="td-30">
												<input type="checkbox" class="selectcheckbox" name="selectcheckbox" value="${ printOrder.orderId }">
											</td>
											<td class="td-50">
												<div class="contents-input-div">
													<input type="text" name="orderId" class="contents-input noline" value="${ printOrder.orderId }" readonly>
												</div>
											</td>
											<td class="td-70">
												<div class="contents-input-div">
													<input type="text" name="clientId" class="contents-input noline" value="${ printOrder.clientId }" readonly>
												</div>
											</td>
											<td class="td-100">
												<div class="contents-input-div">
													<input type="text" name="clientName"  class="contents-input noline" value="${ printOrder.clientName }" readonly>
												</div>
											</td>
											<td class="td-100">
												<div class="contents-input-div">
													<input type="date" name="orderDate" class="contents-input noline changeable" value="${ printOrder.orderDate }" readonly>
												</div>
											</td>
											<td class="td-100">
												<div class="contents-input-div">
													<input type="date" name="endDate" class="contents-input noline changeable" value="${ printOrder.endDate }" readonly>
												</div>
											</td>
											<td class="td-100">
												<div class="contents-input-div">
													<input type="date" name="pubDate" class="contents-input noline changeable" value="${ printOrder.pubDate }" readonly>
												</div>
											</td>
											<td class="td-50">
												<div class="contents-input-div">
													<input type="text" name="bookId" class="contents-input noline" value="${ printOrder.bookId }" readonly>
												</div>
											</td>
											<td class="td-250">
												<div class="contents-input-div">
													<input type="text" name="bookName" class="contents-input noline" value="${ printOrder.bookName }" readonly>
												</div>
											</td>
											<td class="td-30">
												<div class="contents-input-div">
													<input type="text" name="unit" class="contents-input noline" value="${ printOrder.unit }" readonly>
												</div>
											</td>
											<td class="td-60">
												<div class="contents-input-div">
													<input type="text" name="quantity" class="contents-input noline changeable" 
													oninput="calculateAmount()" value="${ printOrder.quantity }" readonly>
												</div>
											</td>
											<td class="td-50">
												<div class="contents-input-div">
													<input type="text" name="price" class="contents-input noline changeable" 
													oninput="calculateAmount()" value="${ printOrder.price }" readonly>
												</div>
											</td>
											<td class="td-80">
												<div class="contents-input-div">
													<input type="text" name="amount" class="contents-input noline" value="${ printOrder.amount }" readonly>
												</div>
											</td>
											<td class="td-50">
												<div class="contents-input-div">
													<input type="text" name="state" class="contents-input noline changeable" value="${ printOrder.state }" readonly>
												</div>
											</td>
											<td class="td-50">
												<input type="button" class="contents-input-btn noline" value="수정" id="updateBtn_${ printOrder.orderId }" onclick="onUpdate(${ printOrder.orderId }); return false;">
												<input type="button" class="contents-input-btn noline" value="완료" id="completeBtn_${ printOrder.orderId }" onclick="submitUpdate(${ printOrder.orderId }, 'poupdate.do'); return false;" style="display: none;">
												<input type="button" class="contents-input-btn noline" value="취소" id="cancelBtn_${ printOrder.orderId }" onclick="cancelUpdate(${ printOrder.orderId }); return false;" style="display: none;">
											</td>
										</tr>
									</c:forEach>
								</c:if>
							</table>
						</div>
					</div>
					<!--컨텐츠영역 end-->

				</div>
				<!--내용 end-->


				<div class="submit-box">
                    <input type="button" class="contents-input-btn big noline" id="btn_delete" value="선택삭제" onclick="deleteCheckedRow('podelete.do'); return false;">
                </div>

			</div>
			<!--main-container end-->


			<!--modal-pop-area-->
			<div class="modal-pop-area">
				<!-- 팝업 들어감 -->
				<c:import url="../common/popup.jsp" />
			</div>
			<!--modal-pop-area end-->

		</main>

	</div>
</body>
</html>