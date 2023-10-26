<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/print_func.js"></script>
<script type="text/javascript">
    const NOWPAGE = 2;
    const SUBPAGE = 2;
    const LNKPAGE = 1;
    
    $(function(){
		calculateAmount();
	});
    
</script>
<title></title>


<script type="text/javascript">



// 마감날짜 검색
function searchByEDate(dateType) {
	var begin = $('#begin_' + dateType).val();
	var end = $('#end_' + dateType).val();
	
	var url = 'pclistEdate.do?';
	url += 'begin=' + begin;
	url += '&end=' + end;
	url += '&dateType=' + dateType;
    
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
                                        <select id="search_type" name="searchType">
                                            <option value="printName" <c:if test="${ searchType == 'printName' }">selected</c:if>>인쇄소명</option>
                                            <option value="bookName" <c:if test="${ searchType == 'bookName' }">selected</c:if>>도서명</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="search-box">
                                	<input type="search" placeholder="키워드를 입력하세요." class="search-box-text" value="${ keyword }" name="keyword">
                                    <button class="search-btn" onclick="searchKeyword('pckeyword.do'); return false; ">
                                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                    </button>
                                </div>
                            </div>

                            <!-- <div class="select-box">
                                <div class="select-pan">
                                    <label for="sel_code"></label>
                                    <select name="code" id="sel_code">
                                        <option value="all">인쇄소별</option>
                                       
                                    </select>
                                </div>
                                
                                <div class="select-pan">
                                    <label for="sel_code"></label>
                                    <select name="code" id="sel_code">
                                        <option value="all">상태</option>
                                        <option value="">완결</option>
                                        <option value="">미결</option>
                                    </select>
                                </div>
                            </div> -->

                            <div class="select-box">
                                <div class="select-pan-nemo">
                                    마감일
                                </div>
								
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

								<c:set var="today_" value="<%=new java.util.Date()%>" />
								<fmt:formatDate var="today" value="${ today_ }" 
									pattern="yyyy-MM-dd" />
								<c:set var="weekago_" value="<%= java.util.Date.from(java.time.LocalDate.now().minusWeeks(1).plusDays(1).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()) %>" />
								<fmt:formatDate var="weekago" value="${ weekago_ }"
									pattern="yyyy-MM-dd" />
								<c:set var="monthago_" value="<%= java.util.Date.from(java.time.LocalDate.now().minusMonths(1).plusDays(1).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()) %>" />
								<fmt:formatDate var="monthago" value="${ monthago_ }"
									pattern="yyyy-MM-dd" />
									
								<c:url var="searchWeekUrl" value="pclistEdate.do">
									<c:param name="begin" value="${ weekago }" />
									<c:param name="end" value="${ today }" />
								</c:url>
								
								<c:url var="searchMonthUrl" value="pclistEdate.do">
									<c:param name="begin" value="${ monthago }" />
									<c:param name="end" value="${ today }" />
								</c:url>

                                <input type="button" name="week" class="select-pan-btn" value="일주일" onclick="javascript: location.href='${ searchWeekUrl }'">
								<input type="button" name="month" class="select-pan-btn" value="한달" onclick="javascript: location.href='${ searchMonthUrl }'">
								<input type="button" name="searchBtn" class="select-pan-btn" value="검색" onclick="searchByEDate('startDate'); return false;">
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
                                    <!-- <th></th> -->
                                    <th>정산코드</th>
                                    <th>거래처코드</th>
                                    <th>인쇄소</th>
                                    <th>마감일</th>
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
	                                <c:forEach items="${ list }" var="printCalc" >
		                                <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1" id="tr_${ printCalc.orderId }">
		                                    <%-- <td class="td-30">
		                                       <input type="checkbox" class="selectcheckbox" name="selectcheckbox" value="${ printCalc.orderId }" style="display:none">
		                                    </td> --%>
		                                    <td class="td-70">
												<div class="contents-input-div">
													<input type="checkbox" class="selectcheckbox" name="selectcheckbox" value="${ printCalc.orderId }" style="display:none">
													<input type="text" name="orderId" class="contents-input noline" value="${ printCalc.orderId }" readonly>
													
		                                    
												</div>
											</td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="text" name="clientId" class="contents-input noline" value="${ printCalc.clientId }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-120">
		                                        <div class="contents-input-div">
		                                            <input type="text" name="clientName" class="contents-input noline" value="${ printCalc.clientName }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-120">
		                                        <div class="contents-input-div">
		                                            <input type="date" name="endDate" class="contents-input noline changeable" value="${ printCalc.endDate }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-70">
		                                        <div class="contents-input-div">
		                                            <input type="text" name="bookId" class="contents-input noline" value="${ printCalc.bookId }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-250">
		                                        <div class="contents-input-div">
		                                            <input type="text" name="bookName" class="contents-input noline" value="${ printCalc.bookName }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="text" name="unit" class="contents-input noline" value="${ printCalc.unit }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-70">
		                                        <div class="contents-input-div">
		                                            <input type="text" name="quantity" class="contents-input noline changeable" 
		                                            oninput="calculateAmount()" value="${ printCalc.quantity }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="text" name="price" class="contents-input noline changeable" 
		                                            oninput="calculateAmount()" value="${ printCalc.price }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-120">
		                                        <div class="contents-input-div">
		                                            <input type="text" name="amount" class="contents-input noline" value="${ printCalc.amount }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="text" name="state" class="contents-input noline changeable" value="${ printCalc.state }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-70">
		                                        <input type="button" class="contents-input-btn noline" value="수정" id="updateBtn_${ printCalc.orderId }" onclick="onUpdate(${ printCalc.orderId }); return false;">
												<input type="button" class="contents-input-btn noline" value="완료" id="completeBtn_${ printCalc.orderId }" onclick="submitUpdate(${ printCalc.orderId }, 'pcupdate.do'); return false;" style="display: none;">
												<input type="button" class="contents-input-btn noline" value="취소" id="cancelBtn_${ printCalc.orderId }" onclick="cancelUpdate(${ printCalc.orderId }); return false;" style="display: none;">
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

                
                <!-- <div class="submit-box">
                    <input type="button" class="contents-input-btn big noline" id="btn_delete" value="선택삭제">
                </div> -->
                
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