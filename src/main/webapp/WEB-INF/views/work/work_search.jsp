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

    $(function(){
        //서치 버튼 클릭 시
        $('#search_keyword').on('click', function(){
            searchKey();
        })
        $('.search-box-text').on('keyup',function(key){
            if(key.keyCode==13) {
                searchKey();
            }
        });
        
        var type = '<c:out value="${ searchKewordType }" />'
        console.log('type : ' + type);
        if(type == '') $('#sel_code').val('title');
        else $('#sel_code').val(type);
        
    })

    function searchKey(){
        var begin = '<c:out value="${ begin }" />';
    	var end = '<c:out value="${ end }" />';

        var url = 'bdsearch.do?';
            url += 'begin=' + begin;
            url += '&end=' + end;
            url += '&depId=' + '<c:out value="${ depId }" />';
            url += '&keyword=' + $('.search-box-text').val();
            url += '&searchType='+ $('#sel_code option:selected').val();

        console.log('==sel_code option:selected.val() : ' + $('#sel_code option:selected').val());
        console.log('url : ' + url);
        location.href = url;
    }

    function searchByDate(dateType) {
    	var begin = $('#begin_' + dateType).val();
    	var end = $('#end_' + dateType).val();
    	
    	var url = 'bdlistdate.do?';
    	url += 'begin=' + begin;
    	url += '&end=' + end;
        url += '&depId=' + '<c:out value="${ depId }" />';
    	url += '&dateType=' + dateType;
        
    	location.href = url;
    }
</script>
</head>
<body>
    <div class="select-search">
        <div class="select-box">
            <div class="select-pan">
                <label for="sel_code"></label>
                <select name="code" id="sel_code">
                    <option value="title" selected>제목</option>
                    <option value="content">내용</option>
                    <option value="writer">작성자</option>
                </select>
            </div>
        </div>

        <div class="search-box">
            <button class="search-btn" id="search_keyword">
                <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
            </button>
            <c:if test="${ empty keyword }">
                <input type="text" placeholder="키워드를 입력하세요." class="search-box-text">
            </c:if>
            <c:if test="${ !empty keyword }">
                <input type="text" placeholder="키워드를 입력하세요." class="search-box-text" value="${ keyword }">
            </c:if>
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

        <c:url var="searchWeekUrl" value="bdlistdate.do">
            <c:param name="begin" value="${ weekago }" />
            <c:param name="end" value="${ today }" />
            <c:param name="depId" value="${ depId }" />
            <c:param name="dateType" value="startDate" />
        </c:url>

        <c:url var="searchMonthUrl" value="bdlistdate.do">
            <c:param name="begin" value="${ monthago }" />
            <c:param name="end" value="${ today }" />
            <c:param name="depId" value="${ depId }" />
            <c:param name="dateType" value="startDate" />
        </c:url>

        <input type="button" name="week" class="select-pan-btn" value="일주일" onclick="javascript: location.href='${ searchWeekUrl }'">
        <input type="button" name="month" class="select-pan-btn" value="한달" onclick="javascript: location.href='${ searchMonthUrl }'">
        <input type="button" name="search" class="select-pan-btn" value="검색" onclick="searchByDate('startDate'); return false;">
    </div>
    
</body>
</html>