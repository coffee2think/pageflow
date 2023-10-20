<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="searchKewordType" value=""/>
<c:if test="${ !empty searchType }">
    <c:set var="searchKewordType" value="${ searchType }"/>
</c:if>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">

    var apprType = 'all';
    if(NOWPAGE == 6 && SUBPAGE == 1 && LNKPAGE == 1) apprType = 'my';
    else if(NOWPAGE == 6 && SUBPAGE == 1 && LNKPAGE == 2) apprType = 'ap';

    $(function(){
        if(apprType == 'my') {
            $('#search_drafter').hide();
        }

        //서치 버튼 클릭 시
        $('#search_keyword_app').on('click', function(){
            searchKey('approver', $(this).parent().find('.search-box-text').val());
        })
        $('#search_text_app').on('keyup',function(key){
            if(key.keyCode==13) {
                searchKey('approver', $(this).val());
            }
        });
        
        $('#search_keyword_dra').on('click', function(){
            searchKey('drafter', $(this).parent().find('.search-box-text').val());
        })
        $('#search_text_dra').on('keyup',function(key){
            if(key.keyCode==13) {
                searchKey('drafter', $(this).val());
            }
        });

        var type = '<c:out value="${ searchKewordType }" />'
        console.log('type : ' + type);
        if(type != 'complete' && type != 'continue' && type != 'companion') $('#sel_code').val('all');
        else $('#sel_code').val(type);
        
        //셀렉트
        $('#sel_code').on('change', function(){
            searchKey($(this).val());
        })
    })

    function searchKey(stype, key){
        var begin = '<c:out value="${ begin }" />';
    	var end = '<c:out value="${ end }" />';
        var empId = '<c:out value="${ empId }" />'
        var keyword = (key == undefined) ? '<c:out value="${ keyword }" />' : key;
        var url = 'apsearch.do?';
            url += 'begin=' + begin;
            url += '&end=' + end;
            url += '&empId=' + empId;
            url += '&apType=' + apprType
            url += '&keyword=' + keyword;
            url += '&searchType='+ stype;

        console.log('==sel_code option:selected.val() : ' + stype);
        console.log('url : ' + url);
        location.href = url;
    }

    function searchByDate(dateType) {
    	var begin = $('#begin_' + dateType).val();
    	var end = $('#end_' + dateType).val();
    	var empId = '<c:out value="${ empId }" />'

    	var url = 'aplistdate.do?';
    	url += 'begin=' + begin;
    	url += '&end=' + end;
        url += '&empId=' + empId;
        url += '&apType=' + apprType
    	url += '&dateType=' + dateType;
        
    	location.href = url;
    }
</script>
</head>
<body>
    <c:choose>
        <c:when test="${ apType eq 'ap' }">
            <div class="select-search" id="search_drafter">
                <div class="search-box">
                    <button class="search-btn" id="search_keyword_dra">
                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                    </button>
                    <c:if test="${ empty keyword }">
                        <input type="text" placeholder="기안자를 입력하세요." class="search-box-text" id="search_text_dra">
                    </c:if>
                    <c:if test="${ !empty keyword }">
                        <c:if test="${ searchKewordType eq 'drafter' }">
                            <input type="text" placeholder="기안자를 입력하세요." class="search-box-text" id="search_text_dra" value="${ keyword }">
                        </c:if>
                        <c:if test="${ searchKewordType ne 'drafter' }">
                            <input type="text" placeholder="기안자를 입력하세요." class="search-box-text" id="search_text_dra" value="">
                        </c:if>
                    </c:if>
                </div>
            </div>
        </c:when> 
        
        <c:otherwise>
            <div class="select-search">
                <div class="search-box">
                    <button class="search-btn" id="search_keyword_app">
                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                    </button>
                   
                    <c:if test="${ empty keyword }">
                        <input type="text" placeholder="결재자를 입력하세요." class="search-box-text" id="search_text_app">
                    </c:if>
                    <c:if test="${ !empty keyword }">
                        <c:if test="${ searchKewordType eq 'approver' }">
                            <input type="text" placeholder="결재자를 입력하세요." class="search-box-text" id="search_text_app" value="${ keyword }">
                        </c:if>
                        <c:if test="${ searchKewordType ne 'approver' }">
                            <input type="text" placeholder="결재자를 입력하세요." class="search-box-text" id="search_text_app" value="">
                        </c:if>
                    </c:if>
                </div>
            </div>
        
            <div class="select-search" id="search_drafter">
                <div class="search-box">
                    <button class="search-btn" id="search_keyword_dra">
                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                    </button>
                    <c:if test="${ empty keyword }">
                        <input type="text" placeholder="기안자를 입력하세요." class="search-box-text" id="search_text_dra">
                    </c:if>
                    <c:if test="${ !empty keyword }">
                        <c:if test="${ searchKewordType eq 'drafter' }">
                            <input type="text" placeholder="기안자를 입력하세요." class="search-box-text" id="search_text_dra" value="${ keyword }">
                        </c:if>
                        <c:if test="${ searchKewordType ne 'drafter' }">
                            <input type="text" placeholder="기안자를 입력하세요." class="search-box-text" id="search_text_dra" value="">
                        </c:if>
                    </c:if>
                </div>
            </div>
        </c:otherwise> 
    </c:choose>

    

    <div class="select-box">
        <div class="select-pan">
            <label for="sel_code"></label>
            <select name="code" id="sel_code">
                <option value="all" selected>진행상태별</option>
                <option value="complete">결재완료</option>
                <option value="continue">진행중</option>
                <option value="companion">반려</option>
            </select>
        </div>
    </div>

    <div class="select-box">
        <div class="select-pan-nemo">
            작성날짜
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

        <!-- LocalDate 객체를 통해 일주일 전 날짜를 구한 후 Date 객체로 변환 -->
        <c:set var="weekago_" value="<%= java.util.Date.from(java.time.LocalDate.now().minusWeeks(1).plusDays(1).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()) %>" />
        <fmt:formatDate var="weekago" value="${ weekago_ }" pattern="yyyy-MM-dd" />

        <!-- LocalDate 객체를 통해 한달 전 날짜를 구한 후 Date 객체로 변환 -->
        <c:set var="monthago_" value="<%= java.util.Date.from(java.time.LocalDate.now().minusMonths(1).plusDays(1).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()) %>" />
        <fmt:formatDate var="monthago" value="${ monthago_ }" pattern="yyyy-MM-dd" />

        <c:url var="searchWeekUrl" value="aplistdate.do?apType=${ apType }">
            <c:param name="begin" value="${ weekago }" />
            <c:param name="end" value="${ today }" />
            <c:param name="empId" value="${ empId }" />
            <c:param name="dateType" value="startDate" />
        </c:url>

        <c:url var="searchMonthUrl" value="aplistdate.do?apType=${ apType }">
            <c:param name="begin" value="${ monthago }" />
            <c:param name="end" value="${ today }" />
            <c:param name="empId" value="${ empId }" />
            <c:param name="dateType" value="startDate" />
        </c:url>

        <input type="button" name="week" class="select-pan-btn" value="일주일" onclick="javascript: location.href='${ searchWeekUrl }'">
        <input type="button" name="month" class="select-pan-btn" value="한달" onclick="javascript: location.href='${ searchMonthUrl }'">
        <input type="button" name="search" class="select-pan-btn" value="검색" onclick="searchByDate('startDate'); return false;">
    </div>
    
</body>
</html>