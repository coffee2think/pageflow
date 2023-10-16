<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script>
    const NOWPAGE = 4;
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
                                            <option value="bookId">도서코드</option>
                                            <option value="">도서명</option>
                                            <option value="">입고부수</option>
                                            <option value="">정가</option>
                                            <option value="">입고금액</option>
                                            <option value="">인수자</option>
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
                                        <option value="all">입고창고</option>
                                        <option value="">제1창고</option>
                                        <option value="">제2창고</option>
                                    </select>
                                </div>
                                
                            </div>

                            <div class="select-box">
                                <div class="select-pan-nemo">
                                    입고일자
                                </div>                       
                                <input type="date" class="select-date select-date-first" name="begin" value="${ begin }">
                                <input type="date" class="select-date select-date-second" name="end"  value="${ end }">
                                
                                <c:set var="today_" value="<%= new java.util.Date() %>" />
                                <fmt:formatDate var="today" value="${ today_ }" pattern="yyyy-MM-dd" />
								<c:set var="weekago_" value="<%= new java.util.Date(new java.util.Date().getTime() - 60*60*24*1000*6) %>" />
								<fmt:formatDate var="weekago" value="${ weekago_ }" pattern="yyyy-MM-dd" />
								<c:set var="monthago_" value="<%= new java.util.Date(new java.util.Date().getTime() - 60*60*24*1000*30) %>" />
								<fmt:formatDate var="monthago" value="${ monthago_ }" pattern="yyyy-MM-dd" />
     
                                <input type="button" name="week" class="select-pan-btn" value="기간검색">
                                
                                <c:url var="searchWeekUrl" value="storedate.do">
                                	<c:param name="begin" value="${ weekago }" />
                                	<c:param name="end" value="${ today }" />
                                </c:url>
                                
                                <c:url var="searchMonthUrl" value="storedate.do">
                                	<c:param name="begin" value="${ monthago }" />
                                	<c:param name="end" value="${ today }" />
                                </c:url>
                                
                                <input type="button" name="week" class="select-pan-btn" value="일주일" onclick="javascript: location.href='${ searchWeekUrl }'">
                                <input type="button" name="month" class="select-pan-btn" value="한달" onclick="javascript: location.href='${ searchMonthUrl }'">
                            </div>

                        </form>

                        <div class="paging-box">
                            <!-- 페이징 -->
                            <c:import url="/WEB-INF/views/common/paging.jsp" />
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
                                    <th>도서코드</th>
                                    <th>도서명</th>
                                    <th>입고창고</th>
                                    <th>인수자</th>
                                    <th>입고일자</th>  
                                    <th>정가</th>
                                    <th>입고부수</th>
                                    <th>입고금액</th>
                                    <th>수정</th>
                                </tr>
                                <c:set var="totalStoreNum" value="0" />
                                <c:set var="totalStorePrice" value="0" />
                                <c:forEach var="sto" items="${ requestScope.list }">
	                                <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1">
	                                    <td class="td-50">
	                                        <input type="checkbox" name="check" value="" >
	                                    </td>
	                                    <td class="td-100">
	                                        <div class="contents-input-div">
	                                            <input type="input" name="bookId" class="contents-input noline" value="${ sto.bookId }">
	                                        </div>
	                                    </td>
	                                    <td class="td-250">
	                                        <div class="contents-input-div">
	                                            <input type="input" name="bookName" class="contents-input noline" value="${ sto.bookName }">
	                                        </div>
	                                    </td>
	                                    <td class="td-100">
	                                        <div class="contents-input-div">
	                                            <input type="input" name="clientName" class="contents-input noline" value="${ sto.clientName }">
	                                        </div>
	                                    </td>
	                                    <td class="td-70">
	                                        <div class="contents-input-div">
	                                            <input type="input" name="empName" class="contents-input noline" value="${ sto.empName }">
	                                        </div>
	                                    </td>
	                                    <td class="td-120">
	                                        <div class="contents-input-div">
	                                            <input type="input" name="storeDate" class="contents-input noline" value="${ sto.storeDate }">
	                                        </div>
	                                    </td>
	                                    <td class="td-70">
	                                        <div class="contents-input-div">
	                                            <input type="input" name="bookPrice" class="contents-input noline" value="${ sto.bookPrice }">
	                                        </div>
	                                    </td>
	                                    <td class="td-70">
	                                        <div class="contents-input-div">
	                                            <input type="input" name="storeNum" class="contents-input noline" value="${ sto.storeNum }">
	                                        </div>
	                                    </td>         
	                                    <td class="td-100">
	                                        <div class="contents-input-div">
	                                            <input type="input" name="storeNum" class="contents-input noline" value="${ sto.storePrice }">
	                                        </div>
	                                    </td>
	                                    <td class="td-70">
	                                        <input type="button" name="update" class="contents-input-btn noline" value="수정">
	                                    </td>
	                                </tr>
	                            <c:set var="totalStoreNum" value="${ totalStoreNum + sto.storeNum }"/>
	                             <c:set var="totalStorePrice" value="${ totalStorePrice + sto.storePrice }"/>
								</c:forEach>
                                <!--합계-->
                                <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1 sum">
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>합계</td>
                                    <td>${ totalStoreNum }</td>
                                    <td>${ totalStorePrice }</td>
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
                    <input type="button" class="contents-input-btn big noline" id="btn_delete" value="선택삭제" onclick="deleteStore(); return false;">
                </div>
                
            </div>
            <!--main-container end-->


            <!--modal-pop-area-->
            <div class="modal-pop-area">
                <!-- 팝업 들어감 -->
                <c:import url="/WEB-INF/views/common/popup.jsp" />
            </div>
            <!--modal-pop-area end-->

        </main>
        
    </div>
</body>
</html>