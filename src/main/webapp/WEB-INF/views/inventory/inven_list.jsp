<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="invenList" value="${ requestScope.invenList }" />
<c:set var="keyword" value="${ requestScope.keyword }" />
<c:set var="searchType" value="${ requestScope.searchType }" />
<c:set var="firstType" value="${ requestScope.firstType }" />
<c:set var="begin" value="${ requestScope.begin }" />
<c:set var="end" value="${ requestScope.end }" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script type="text/javascript">
	
</script>
<script>
	const NOWPAGE = 4;
	const SUBPAGE = 1;
	const LNKPAGE = 1;
</script>
<title></title>
<script type="text/javascript">
	var curinput = "";
	$(function(){
		// 서치 버튼 클릭
		$('.search-button').on('click', function(){
			searchKey();
		})
		// 엔터 누르고 난뒤
		$('.search-box-text').on('keyup', function(key){
			if(key.keyCode==13){
				searchKey();
			}
		});
		
		var type = '<c:out value="${ searchType }" />'
		console.log('type : ' + type);
		
	})
	
	function searchKey(){
    	var url = 'invselectkeyword.do?';
            url += 'keyword=' + $('.search-box-text').val();
            url += '&searchType='+ $('#sel_code option:selected').val();
           /*   수정 하기    */
            location.href = url;
	}
	 
	function searchByDate() {
	    	var begin = $('#begin_startDate').val();
	    	var end = $('#end_startDate').val();
	    	
	    	console.log('begin : ' + begin);
	    	console.log('end : ' + end);
	    	
	    	var url = 'invlistdate.do?';
	    	url += 'begin=' + begin;
	    	url += '&end=' + end;
	        
	    	location.href = url;
	    }
	
	$(document).ready(function(){
		$('#btn_delete').click(function(){
			const selectedCheckbokes = $('input[name="selectedItems"]:checked');
			const selectedStoreIds = [];
			
			selectedCheckbokes.each(function(){
				selectedStoreIds.push($(this).val());
			});
			
			if(selectedStoreIds.length === 0){
				alert('선택된 항목이 없습니다.');
			}else{
				$.ajax({
					type:'post',
					url:'storedelete.do',
					dataType: "json",
					data: { selectedStoreIds: selectedStoreIds.join(',') },
					success: function(response){
						alert('선택한 출고내역이 삭제되었습니다.');
						location.reload();
					},
					error: function(){
						alert('삭제 실패! 관리자에게 문의 하세요');
					}
				});
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
				<c:import url="/WEB-INF/views/common/header.jsp" />
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
					<c:import url="/WEB-INF/views/common/side.jsp" />
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
					<button class="header-left-btn"></button>
				</div>
				<!--main-header-bar end-->
				<script type="text/javascript">
					
				</script>
				<!--내용-->
				<div class="main-contents-box">

					<!--서치영역-->
					<div class="search-container">
						<div class="search-form">
							<div class="select-search">
								<div class="select-box">
									<div class="select-pan">
										<label for="sel_code"></label> 
										<select name="code" id="sel_code">
											<option value="bookId">도서코드</option>
											<option value="bookName">도서명</option>
											<option value="clientName">창고명</option>
										</select>
									</div>
								</div>

								<div class="search-box">
									<button class="search-button">
										<img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
									</button>
									<c:if test="${ empty keyword }">
										<input type="text" placeholder="키워드를 입력하세요." class="search-box-text" > 
									</c:if>
										<c:if test="${ !empty keyword }">
										<input type="text" placeholder="키워드를 입력하세요." class="search-box-text" value="${ keyword }"> 
									</c:if>
								</div>
							</div>
						<div class="select-box">
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
						
						        <c:url var="searchWeekUrl" value="invlistdate.do">
						            <c:param name="begin" value="${ weekago }" />
						            <c:param name="end" value="${ today }" />
						            <c:param name="dateType" value="startDate" />
						        </c:url>
						
						        <c:url var="searchMonthUrl" value="invlistdate.do">
						            <c:param name="begin" value="${ monthago }" />
						            <c:param name="end" value="${ today }" />
						            <c:param name="dateType" value="startDate" />
						        </c:url>
						
						        <input type="button" name="week" class="select-pan-btn" value="일주일" onclick="javascript: location.href='${ searchWeekUrl }'">
						        <input type="button" name="month" class="select-pan-btn" value="한달" onclick="javascript: location.href='${ searchMonthUrl }'">
						        <input type="button" name="search" class="select-pan-btn" value="검색" onclick="searchByDate('startDate'); return false;">
						 </div>

						<div class="paging-box">
							<!-- 페이징 -->
							<c:import url="/WEB-INF/views/common/paging.jsp" />
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
						<div class="contents-container sort-row">
							<div class="contents-box">
								<table class="contents-table" id="tblist">
									<tr>
										<th></th>
										<th>도서코드</th>
										<th>도서명</th>
										<th>창고</th>
										<th>구분</th>
										<th>이전재고</th>
										<th>증감</th>
										<th>현재재고</th>
										<th>비고</th>
									</tr>
									<c:set var="totalPrevCurrInven" value="0" />
									<c:set var="totalIncrease" value="0" />
									<c:set var="totalCurrInven" value="0" />
									<c:forEach var="inv" items="${ invenList }">
										<tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1">
											<td class="td-50">
												
											</td>
											<td class="td-155">
												<div class="contents-input-div">
													<input type="input" name="bookId" class="contents-input noline" value="${ inv.bookId }">
												</div>
											</td>
											<td class="td-250">
												<div class="contents-input-div">
													<input type="input" name="bookName" class="contents-input noline" value="${ inv.bookName }">
												</div>
											</td>
											<td class="td-70">
												<div class="contents-input-div">
													<input type="input" name="clientName" class="contents-input noline" value="${ inv.clientName }">
												</div>
											</td>
											<td class="td-105">
												<div class="contents-input-div">
													<input type="input" name="classify" class="contents-input noline" value="${ inv.classify }">
												</div>
											</td>

											<td class="td-110">
												<div class="contents-input-div">
													<input type="input" name="currInven" class="contents-input noline" value="${ inv.currInven }">
												</div>
											</td>
											<c:if test="${ inv.classify eq '입고' }">
												<td class="td-100">
													<div class="contents-input-div">
														<input type="input" name="increase" class="contents-input noline" value="+${ inv.increase }">
													</div>
												</td>
											</c:if>
											<c:if test="${ inv.classify eq '출고' }">
												<td class="td-100">
													<div class="contents-input-div">
														<input type="input" name="increase" class="contents-input noline" value="${ inv.increase }">
													</div>
												</td>
											</c:if>
											<c:if test="${ inv.classify eq '반품' }">
												<td class="td-100">
													<div class="contents-input-div">
														<input type="input" name="increase" class="contents-input noline" value="+${ inv.increase }">
													</div>
												</td>
											</c:if>
											<c:if
												test="${ inv.classify eq '입고' or inv.classify eq '반품' }">
												<td class="td-100">
													<div class="contents-input-div">
														<input type="input" name="plus"
															class="contents-input noline"
															value="${ inv.currInven + inv.increase }">
													</div>
												</td>
											</c:if>
											<c:if test="${ inv.classify eq '출고' }">
												<td class="td-100">
													<div class="contents-input-div">
														<input type="input" name="plus"
															class="contents-input noline"
															value="${ inv.currInven - inv.increase }">
													</div>
												</td>
											</c:if>
											<td class="td-120">
												<div class="contents-input-div">
													<input type="input" name="remark"
														class="contents-input noline" value="${ inv.remark }">
												</div>
											</td>

										</tr>
										<c:set var="totalPrevCurrInven"
											value="${ totalPrevCurrInven + inv.currInven }" />
										<c:if test="${ inv.classify eq '반품' or inv.classify eq '입고'}">
											<c:set var="totalIncrease"
												value="${ totalIncrease + inv.increase }" />
										</c:if>
										<c:if test="${ inv.classify eq '출고' }">
											<c:set var="totalIncrease"
												value="${ totalIncrease - inv.increase }" />
										</c:if>
									</c:forEach>
									<c:set var="totalCurrInven"
										value="${ totalCurrInven + (totalPrevCurrInven + totalIncrease) }" />
									<!--합계-->
									<fmt:formatNumber var="totalPrevCurrInven" value="${ totalPrevCurrInven }" type="number"/>
									<fmt:formatNumber var="totalIncrease" value="${ totalIncrease }" type="number"/>
									<fmt:formatNumber var="totalCurrInven" value="${ totalCurrInven }" type="number"/>

									<tr data-parent="1" data-num="1" data-depth="1"
										class="table-td-depth1 sum">
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td>합계</td>
										<td>${ totalPrevCurrInven }</td>
										<td>${ totalIncrease }</td>
										<td>${ totalCurrInven }</td>
										<td></td>
										<td></td>
									</tr>
									<!--합계end-->

								</table>
							</div>
						</div>
						<!--컨텐츠영역 end-->

					</div>
					<!--내용 end-->
				</div>
				<!--main-container end-->


				<!--modal-pop-area-->
				<div class="modal-pop-area">
					<!-- 팝업 들어감 -->
					<c:import url="/WEB-INF/views/common/popup.jsp" />
				</div>
				<!--modal-pop-area end-->
			</div>
		</main>

	</div>
</body>
</html>