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
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/sales_func_new.js"></script>
<script>
    const NOWPAGE = 3;
    const SUBPAGE = 1;
    const LNKPAGE = 1;
    
    function searchByDate(dateType) {
    	var begin = $('#begin_' + dateType).val();
    	var end = $('#end_' + dateType).val();
    	
    	var url = 'ctrlistdate.do?';
    	url += 'begin=' + begin;
    	url += '&end=' + end;
    	url += '&dateType=' + dateType;
        
    	location.href = url;
    }
    
/*     function searchStatus(url) {
        var selectedStatus = $('#search_type').val();
        
        // form 태그에 담아서 post 전송
        const form = document.createElement('form'); // form 태그 생성
        form.setAttribute('method', 'post'); // 전송 방식 결정 (get or post)
        form.setAttribute('action', url); // 전송할 url 지정
        
        const data1 = document.createElement('input');
        data1.setAttribute('type', 'hidden');
        data1.setAttribute('name', 'status'); // 데이터의 name
        data1.setAttribute('value', selectedStatus); // 데이터의 value
        form.appendChild(data1);
        
        // body에 form 태그 추가하고 submit 전송
        document.body.appendChild(form);
        form.submit();
    } */
    
    function changeTab() {
        var selectedTabId = selectElement.value;
        var tabs = document.querySelectorAll(".nav-tabs li");

        for (var i = 0; i < tabs.length; i++) {
            var tab = tabs[i];
            var tabId = tab.getAttribute("data-tabid");
            if (tabId === selectedTabId) {
                tab.classList.add("active");
            } else {
                tab.classList.remove("active");
            }
        }
    }
</script>
<title>계약현황</title>
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
                                        <select name="code" id="search_type">
                                            <option value="book" <c:if test="${ searchType == 'book' }">selected</c:if>>도서명</option>
                                            <option value="category" <c:if test="${ searchType == 'category' }">selected</c:if>>카테고리</option>
                                            <option value="writer" <c:if test="${ searchType == 'writer' }">selected</c:if>>작가명</option>
                                            <option value="employee" <c:if test="${ searchType == 'employee' }">selected</c:if>>담당직원</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="search-box">
                                   <input type="search" placeholder="키워드를 입력하세요." class="search-box-text" value="${ keyword }" name="keyword">
                                    <button class="search-btn" onclick="searchKeyword('ctrlistkwd.do'); return false;">
                                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                    </button>
                                </div>
                            </div>
							
							<!-- <div class="nav nav-tabs" style="width: 755px;">
							    <label for="tabSelect" style="margin-right: 10px;">상태 선택:</label>
							    <select id="tabSelect" onchange="changeTab()">
							        <option value="tabAll">전체</option>
							        <option value="ing">진행중</option>
							        <option value="finish">계약완료</option>
							    </select>
							</div>
							
							<button class="search-btn" onclick="searchStatus('ctrliststs.do'); return false;">검색</button> -->
							
							<div class="select-box">
							    <div class="select-pan">
							        <label for="sel_code"></label>
							        <select name="code" id="search_type">
							            <option value="all" <c:if test="${searchType == 'all'}">selected</c:if>>전체</option>
							            <option value="ing" <c:if test="${searchType == 'ing'}">selected</c:if>>진행중</option>
							            <option value="finish" <c:if test="${searchType == 'finish'}">selected</c:if>>계약완료</option>
							        </select>
							    </div>
							    <button class="search-btn" onclick="searchStatus('ctrliststs.do'); return false;">
							    </button>
							</div>
							
                           <%--  <div class="select-box">
                                <div class="select-pan">
                                    <label for="sel_code"></label>
                                    <select name="code" id="sel_code">
                                        <option value="all" <c:if test="${ searchType == 'all' }">selected</c:if>>진행상태</option>
                                        <option value="ing" <c:if test="${ searchType == 'ing' }">selected</c:if>>진행중</option>
                                        <option value="finish" <c:if test="${ searchType == 'finish' }">selected</c:if>>계약완료</option>
                                    </select>
                                </div>
                            </div> --%>

                            <div class="select-box">
                                <div class="select-pan-nemo">
                                    계약일자
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
						        
						        <c:set var="today_" value="<%= new java.util.Date() %>" />
						        <fmt:formatDate var="today" value="${ today_ }" pattern="yyyy-MM-dd" />
						
						        <c:set var="weekago_" value="<%= java.util.Date.from(java.time.LocalDate.now().minusWeeks(1).plusDays(1).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()) %>" />
						        <fmt:formatDate var="weekago" value="${ weekago_ }" pattern="yyyy-MM-dd" />
		
						        <c:set var="monthago_" value="<%= java.util.Date.from(java.time.LocalDate.now().minusMonths(1).plusDays(1).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()) %>" />
						        <fmt:formatDate var="monthago" value="${ monthago_ }" pattern="yyyy-MM-dd" />
						
						        <c:url var="searchWeekUrl" value="ctrlistdate.do">
						            <c:param name="begin" value="${ weekago }" />
						            <c:param name="end" value="${ today }" />
						            <c:param name="dateType" value="startDate" />
						        </c:url>
						
						        <c:url var="searchMonthUrl" value="ctrlistdate.do">
						            <c:param name="begin" value="${ monthago }" />
						            <c:param name="end" value="${ today }" />
						            <c:param name="dateType" value="startDate" />
						        </c:url>
						
						        <input type="button" name="week" class="select-pan-btn" value="일주일" onclick="javascript: location.href='${ searchWeekUrl }'">
						        <input type="button" name="month" class="select-pan-btn" value="한달" onclick="javascript: location.href='${ searchMonthUrl }'">
						        <input type="button" name="search" class="select-pan-btn" value="검색" onclick="searchByDate('startDate'); return false;">
						        
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
                                    <th>계약번호</th>
                                    <th>도서명</th>
                                    <th>카테고리</th>
                                    <th>작가명</th>
                                    <th>담당직원번호</th>
                                    <th>담당직원</th>
                                    <th>진행상태</th>
                                    <th>계약일자</th>
                                    <th>계약서url</th>
                                    <th>수정</th>
                                </tr>
                                <c:if test="${ !empty contractList }">
	                                <c:forEach items="${ contractList }" var="contract">
		                                <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1" id="tr_${ contract.contrId }">
		                                    <td class="td-50">
		                                        <input type="checkbox" name="check" value="${ contract.contrId }" >
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="contrId" class="contents-input noline" value="${ contract.contrId }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-250">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="bookName" class="contents-input noline" value="${ contract.bookName }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="category" class="contents-input noline" value="${ contract.category }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="writerName" class="contents-input noline" value="${ contract.writerName }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="empId" class="contents-input noline changeable" value="${ contract.empId }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name=empName class="contents-input noline changeable" value="${ contract.empName }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="contrState" class="contents-input noline changeable" value="${ contract.contrState }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="date" name="contrDate" class="contents-input noline changeable" value="${ contract.contrDate }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="contrDoc" class="contents-input noline changeable" value="${ contract.contrDoc }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-70">
		                                        <input type="button" class="contents-input-btn noline update-btn" value="수정" id="updateBtn_${ contract.contrId }" onclick="onUpdate(this); return false;">
		                                        <input type="button" class="contents-input-btn noline complete-btn" value="완료" id="completeBtn_${ contract.contrId }" onclick="submitUpdate(this, 'ctrupdate.do'); return false;" style="display: none;">
		                                        <input type="button" class="contents-input-btn noline cancel-btn" value="취소" id="cancelBtn_${ contract.contrId }" onclick="cancelUpdate(this); return false;" style="display: none;">
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
                    <input type="button" class="contents-input-btn big noline" id="btn_delete" value="선택삭제" onclick="deleteCheckedRow('ctrdelete.do'); return false;">
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