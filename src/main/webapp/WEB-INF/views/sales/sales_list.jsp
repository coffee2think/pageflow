<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script>
    const NOWPAGE = 5;
    const SUBPAGE = 2;
    const LNKPAGE = 1;
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
                                        <select name="code" id="sel_code">
                                            <option value="">주문번호</option>
                                            <option value="">도서코드</option>
                                            <option value="">도서명</option>
                                            <option value="">서점명</option>
                                            <option value="">정가</option>
                                            <option value="">주문수량</option>
                                            <option value="">금액</option>
                                            <option value="">수금액</option>
                                            <option value="">잔액</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="search-box">
                                    <button class="search-btn">
                                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                    </button>
                                    <input type="text" placeholder="키워드를 입력하세요." class="search-box-text" value="">
                                </div>
                            </div>

                            <div class="select-box">
                                <div class="select-pan">
                                    <label for="sel_code"></label>
                                    <select name="code" id="sel_code">
                                        <option value="all">지역</option>
                                        <option value="">강남</option>
                                        <option value="">서초</option>
                                        <option value="">인천</option>
                                    </select>
                                </div>
                                
                            </div>

                            <div class="select-box">
                                <div class="select-pan-nemo">
                                    기간
                                </div>

                                <input type="date" class="select-date select-date-first" value="${ begin }">
                                <input type="date" class="select-date select-date-second" value="${ end }">

                                <input type="button" name="week" class="select-pan-btn" value="일주일">
                                <input type="button" name="month" class="select-pan-btn" value="한달">
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
                            <table class="contents-table">
                                <tr>
                                    <th>체크</th>
                                    <th>주문번호</th>
                                    <th>도서코드</th>
                                    <th>도서명</th>
                                    <th>지역</th>
                                    <th>서점명</th>
                                    <th>주문일시</th>
                                    <th>정가</th>
                                    <th>주문수량</th>
                                    <th>금액</th>
                                    <th>수금액</th>
                                    <th>잔액</th>
                                    <th>수정</th>
                                </tr>
                                <c:set var="sumQuantity" value="0" />
                                <c:set var="sumTotalPrice" value="0" />
                                <c:set var="sumCollectedAmount" value="0" />
                                <c:set var="sumBalance" value="0" />
                                
                                
                                
                                <c:if test="${ !empty list }">
									<c:forEach items="${ list }" var="sales">
		                                <fmt:formatNumber var="bookPrice" value="${ sales.bookPrice }" type="number" />
		                                <fmt:formatNumber var="orderQuantity" value="${ sales.orderQuantity }" type="number" />
		                                <fmt:formatNumber var="totalPrice" value="${ sales.totalPrice }" type="number" />
		                                <fmt:formatNumber var="collectedAmount" value="${ sales.collectedAmount }" type="number" />
		                                <fmt:formatNumber var="balance" value="${ sales.totalPrice - sales.collectedAmount }" type="number" />
		                                
		                                <c:set var="sumQuantity" value="${ sumQuantity + sales.orderQuantity }" />
		                                <c:set var="sumTotalPrice" value="${ sumTotalPrice + sales.totalPrice }" />
		                                <c:set var="sumCollectedAmount" value="${ sumCollectedAmount + sales.collectedAmount }" />
		                                <c:set var="sumBalance" value="${ sumBalance + (sales.totalPrice - sales.collectedAmount) }" />
		                                
		                                <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1">
		                                    <td class="td-50">
		                                        <input type="checkbox" name="check" value="" >
		                                    </td>
		                                    <td class="td-120">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="tradeId" class="contents-input noline" value="${ sales.tradeId }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="bookId" class="contents-input noline" value="${ sales.bookId }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-200">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="bookName" class="contents-input noline" value="${ sales.bookName }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="location" class="contents-input noline" value="강남" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="bookStoreName" class="contents-input noline" value="${ sales.bookStoreName }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="orderDate" class="contents-input noline" value="${ sales.orderDate }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-70">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="bookPrice" class="contents-input noline" value="${ bookPrice }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="orderQuantity" class="contents-input noline" value="${ orderQuantity }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-120">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="totalPrice" class="contents-input noline" value="${ totalPrice }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-120">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="collectedAmount" class="contents-input noline" value="${ collectedAmount }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-120">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="balance" class="contents-input noline" value="${ balance }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-70">
		                                        <input type="button" name="update" class="contents-input-btn noline" value="수정">
		                                    </td>
		                                </tr>
									</c:forEach>
                                </c:if>

                                <!--합계-->
                                <fmt:formatNumber var="sumQuantity" value="${ sumQuantity }" type="number" />
                                <fmt:formatNumber var="sumTotalPrice" value="${ sumTotalPrice }" type="number" />
                                <fmt:formatNumber var="sumCollectedAmount" value="${ sumCollectedAmount }" type="number" />
                                <fmt:formatNumber var="sumBalance" value="${ sumBalance }" type="number" />
                                
                                <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1 sum">
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>합계</td>
                                    <td>${ sumQuantity }</td>
                                    <td>${ sumTotalPrice }</td>
                                    <td>${ sumCollectedAmount }</td>
                                    <td>${ sumBalance }</td>
                                    <td></td>
                                </tr>
                                <!--합계end-->

                            </table>
                        </div>
                    </div>
                    <!--컨텐츠영역 end-->

                </div>
                <!--내용 end-->

                
                <div class="submit-box">
                    <input type="button" class="contents-input-btn big noline" id="btn_delete" value="선택삭제">
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