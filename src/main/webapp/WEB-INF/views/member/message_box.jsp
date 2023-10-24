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
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/sales_func_new.js"></script>
<script>
    const NOWPAGE = 5;
    const SUBPAGE = 1;
    const LNKPAGE = 1;
    
    $(function() {
    	// 수량 변경 시 총액 계산
    	$('#table_list').find('input[name=orderQuantity]').each(function() {
    		$(this).change(function() { 
    			var tr = $(this).parent().parent().parent();
    			var bookPrice = tr.find('input[name=bookPrice]').val();
    			var quantity = $(this).val();
    			var totalPrice = tr.find('input[name=totalPrice]');
    			totalPrice.val(bookPrice * quantity);
    		});
    	});
    }); // document ready
    
</script>
<title></title>
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
                                        	<option value="book" <c:if test="${ searchType == 'book' }">selected</c:if>>도서명</option>
                                          	<option value="bookStore" <c:if test="${ searchType == 'bookStore' }">selected</c:if>>서점명</option>
                                          	<option value="location" <c:if test="${ searchType == 'location' }">selected</c:if>>지역</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="search-box">
                                    <input type="search" placeholder="키워드를 입력하세요." class="search-box-text" value="${ keyword }" name="keyword">
                                    <button class="search-btn" onclick="searchKeyword('bolistkw.do'); return false;">
                                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                    </button>
                                </div>
                            </div>

                            <div class="select-box">
                                <div class="select-pan">
                                    <label for="sel_code"></label>
                                    <select name="code" id="sel_code">
                                        <option value="all">상태</option>
                                        <option value="">주문접수</option>
                                        <option value="">입고요청</option>
                                        <option value="">출고대기</option>
                                    </select>
                                </div>
                            </div>

                            <div class="select-box">
                                <div class="select-pan-nemo">
                                    기간
                                </div>

                                <input type="date" class="select-date select-date-first" name="begin" id="begin" value="${ begin }">
                                <input type="date" class="select-date select-date-second" name="end" id="end" value="${ end }">
								
								<c:set var="today_" value="<%= new java.util.Date() %>" />
								<fmt:formatDate var="today" value="${ today_ }" pattern="yyyy-MM-dd" />
								
								<!-- LocalDate 객체를 통해 일주일 전 날짜를 구한 후 Date 객체로 변환 -->
								<c:set var="weekago_" value="<%= java.util.Date.from(java.time.LocalDate.now().minusWeeks(1).plusDays(1).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()) %>" />
								<fmt:formatDate var="weekago" value="${ weekago_ }" pattern="yyyy-MM-dd" />
								
								<!-- LocalDate 객체를 통해 한달 전 날짜를 구한 후 Date 객체로 변환 -->
								<c:set var="monthago_" value="<%= java.util.Date.from(java.time.LocalDate.now().minusMonths(1).plusDays(1).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()) %>" />
								<fmt:formatDate var="monthago" value="${ monthago_ }" pattern="yyyy-MM-dd" />
								
								<c:url var="searchWeekUrl" value="bolistdate.do">
									<c:param name="begin" value="${ weekago }" />
									<c:param name="end" value="${ today }" />
								</c:url>
								
								<c:url var="searchMonthUrl" value="bolistdate.do">
									<c:param name="begin" value="${ monthago }" />
									<c:param name="end" value="${ today }" />
								</c:url>

                                <input type="button" name="week" class="select-pan-btn" value="일주일" onclick="javascript: location.href='${ searchWeekUrl }'">
                                <input type="button" name="month" class="select-pan-btn" value="한달" onclick="javascript: location.href='${ searchMonthUrl }'">
                                <input type="button" name="searchBtn" class="select-pan-btn" value="검색" onclick="searchByDate('bolistdate.do'); return false;">
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
                                    <th>주문번호</th>
                                    <th>도서코드</th>
                                    <th>도서명</th>
                                    <th>지역</th>
                                    <th>서점명</th>
                                    <th>정가</th>
                                    <th>주문수량</th>
                                    <th>금액</th>
                                    <th>상태</th>
                                    <th>주문일시</th>
                                    <th>수정</th>
                                </tr>
                                <c:if test="${ !empty list }">
	                                <c:forEach items="${ list }" var="bookOrder" >
		                                <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1"">
		                                    <td class="td-50">
		                                        <input type="checkbox" name="check" value="${ bookOrder.orderId }_${ bookOrder.bookId }">
		                                    </td>
		                                    <td class="td-120">
		                                        <div class="contents-input-div">
		                                            <input type="number" name="orderId" class="contents-input noline" value="${ bookOrder.orderId }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="number" name="bookId" class="contents-input noline" value="${ bookOrder.bookId }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-200">
		                                        <div class="contents-input-div">
		                                            <input type="text" name="bookName" class="contents-input noline" value="${ bookOrder.bookName }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="text" name="location" class="contents-input noline" value="강남" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="text" name="bookStoreName" class="contents-input noline" value="${ bookOrder.bookStoreName }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-70">
		                                        <div class="contents-input-div">
		                                            <input type="number" name="bookPrice" class="contents-input noline" value="${ bookOrder.bookPrice }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="number" name="orderQuantity" class="contents-input noline changeable" value="${ bookOrder.orderQuantity }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-120">
		                                        <div class="contents-input-div">
		                                            <input type="number" name="totalPrice" class="contents-input noline" value="${ bookOrder.totalPrice }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-70">
		                                        <div class="contents-input-div">
		                                            <input type="text" name="state" class="contents-input noline changeable" value="${ bookOrder.state }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="date" name="orderDate" class="contents-input noline" value="${ bookOrder.orderDate }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-70">
		                                        <input type="button" class="contents-input-btn noline update-btn" value="수정" id="updateBtn_${ bookOrder.orderId }" onclick="onUpdate(this); return false;">
		                                        <input type="button" class="contents-input-btn noline complete-btn" value="완료" id="completeBtn_${ bookOrder.orderId }"  onclick="submitUpdate(this, 'boupdate.do'); return false;" style="display: none;">
		                                        <input type="button" class="contents-input-btn noline cancel-btn" value="취소" id="cancelBtn_${ bookOrder.orderId }"  onclick="cancelUpdate(this); return false;" style="display: none;">
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
                    <input type="button" class="contents-input-btn big noline" id="btn_delete" value="선택삭제" onclick="deleteCheckedRow('bodelete.do'); return false;">
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