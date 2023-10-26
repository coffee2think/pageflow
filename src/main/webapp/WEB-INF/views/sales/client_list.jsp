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
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/sales_func.js"></script>
<script>
    const NOWPAGE = 5;
    const SUBPAGE = 3;
    const LNKPAGE = 1;
    
    $(function() {
    	requestListByClientType();
    });
    
    function requestListByClientType() {
    	$('#client_type').change(function() {
    		location.href = 'cllistkw.do?searchType=clientType&keyword=' + $(this).val();
    	});
    }
    
    function searchByDate(dateType) {
    	var begin = $('#begin_' + dateType).val();
    	var end = $('#end_' + dateType).val();
    	
    	var url = 'cllistdate.do?';
    	url += 'begin=' + begin;
    	url += '&end=' + end;
    	url += '&dateType=' + dateType;
    	
    	location.href = url;
    }
</script>
<title>거래처 현황</title>
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
                <div class="main-contents-box normal">

                    <!--서치영역-->
                    <div class="search-container">
                        <form class="search-form">
                            <div class="select-search">
                                <div class="select-box">
                                    <div class="select-pan">
                                        <label for="search_type"></label>
                                        <select name="searchType" id="search_type">
                                            <option value="clientName" <c:if test="${ searchType == 'clientName' }">selected</c:if>>거래처명</option>
                                            <option value="clientAddress" <c:if test="${ searchType == 'clientAddress' }">selected</c:if>>사업장주소</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="search-box">
                                    <button class="search-btn"  onclick="searchKeyword('cllistkw.do'); return false;">
                                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                    </button>
                                    
                                    <c:choose>
                                		<c:when test="${ !empty searchType && searchType != 'clientType' }">
                                			<input type="search" placeholder="키워드를 입력하세요." class="search-box-text" value="${ keyword }" name="keyword">
                                		</c:when>
                                		<c:otherwise>
                                			<input type="search" placeholder="키워드를 입력하세요." class="search-box-text" value="" name="keyword">
                                		</c:otherwise>
                                	</c:choose>
                                </div>
                            </div>

                            <div class="select-box">
                                <div class="select-pan">
                                    <label for="client_type"></label>
                                    <select name="clientType" id="client_type">
                                        <option value="all">분류별</option>
                                        <option value="서점" <c:if test="${ !empty searchType && searchType == 'clientType' && keyword == '서점' }">selected</c:if>>서점</option>
                                        <option value="인쇄소" <c:if test="${ !empty searchType && searchType == 'clientType' && keyword == '인쇄소' }">selected</c:if>>인쇄소</option>
                                        <option value="창고" <c:if test="${ !empty searchType && searchType == 'clientType' && keyword == '창고' }">selected</c:if>>창고</option>
                                    </select>
                                </div>
                            </div>

                            <div class="select-box">
                                <div class="select-pan-nemo">
                                	거래시작일
                                </div>

                                <input type="date" class="select-date select-date-first" id="begin_startDate" value="${ begin_startDate }">
                                <input type="date" class="select-date select-date-second" id="end_startDate" value="${ end_startDate }">

                                <input type="button" name="searchBtn" class="select-pan-btn" value="검색" onclick="searchByDate('startDate'); return false;">
                            </div>
                            
                            <div class="select-box">
                                <div class="select-pan-nemo">
                                	거래종료일
                                </div>

                                <input type="date" class="select-date select-date-first" id="begin_endDate" value="${ begin_endDate }">
                                <input type="date" class="select-date select-date-second" id="end_endDate" value="${ end_endDate }">

                                <input type="button" name="month" class="select-pan-btn" value="검색" onclick="searchByDate('endDate'); return false;">
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
                            <table class="contents-table sales" id="table_list">
                                <tr>
                                    <th>체크</th>
                                    <th>거래처코드</th>
                                    <th>분류</th>
                                    <th>거래처명</th>
                                    <th>사업자등록번호</th>
                                    <th>사업장주소</th>
                                    <th>거래처연락처</th>
                                    <th>거래처홈페이지</th>
                                    <th>담당자</th>
                                    <th>담당자연락처</th>
                                    <th>담당자이메일</th>
                                    <th>거래시작일</th>
                                    <th>거래종료일</th>
                                    <th>수정</th>
                                </tr>
                                <c:if test="${ !empty list }">
	                                <c:forEach items="${ list }" var="client">
		                                <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1" id="tr_${ client.clientId }">
		                                    <td class="td-50">
		                                        <input type="checkbox" name="check" value="${ client.clientId }" >
		                                    </td>
		                                    <td class="td-70">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="clientId" class="contents-input noline" value="${ client.clientId }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="category" class="contents-input noline" value="${ client.category }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="clientName" class="contents-input noline changeable" value="${ client.clientName }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-150">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="eid" class="contents-input noline" value="${ client.eid }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-300">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="clientAddress" class="contents-input noline changeable" value="${ client.clientAddress }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-150">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="clientContact" class="contents-input noline changeable" value="${ client.clientContact }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-150">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="clientUrl" class="contents-input noline changeable" value="${ client.clientUrl }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="manager" class="contents-input noline changeable" value="${ client.manager }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-150">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="managerContact" class="contents-input noline changeable" value="${ client.managerContact }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-150">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="managerEmail" class="contents-input noline changeable" value="${ client.managerEmail }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="startDate" class="contents-input noline changeable" value="${ client.startDate }" required readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="endDate" class="contents-input noline changeable" value="${ client.endDate }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <input type="button" class="contents-input-btn noline" value="수정" id="updateBtn_${ client.clientId }" onclick="onUpdate(${ client.clientId }); return false;">
		                                        <input type="button" class="contents-input-btn noline" value="완료" id="completeBtn_${ client.clientId }"  onclick="submitUpdate(${ client.clientId }, 'clupdate.do'); return false;" style="display: none;">
		                                        <input type="button" class="contents-input-btn noline" value="취소" id="cancelBtn_${ client.clientId }"  onclick="cancelUpdate(${ client.clientId }); return false;" style="display: none;">
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
                    <input type="button" class="contents-input-btn big noline" id="btn_delete" value="선택삭제" onclick="deleteCheckedRow('cldelete.do'); return false;">
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