<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="today_" value="<%= new java.util.Date() %>" />
<fmt:formatDate var="today" value="${ today_ }" pattern="yyyy-MM-dd" />
<c:set var="newdays" value="3" />

<!-- LocalDate 객체를 통해 일주일 전 날짜를 구한 후 Date 객체로 변환 -->
<c:set var="before_" value="<%= java.util.Date.from(java.time.LocalDate.now().minusDays(3).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()) %>" />
<fmt:formatDate var="before" value="${ before_ }" pattern="yyyy-MM-dd" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<title>Insert title here</title>

<script type="text/javascript">
    $(function(){
        newCount();
    })

    function newCount(){
		
		let begin = '<c:out value="${ before }" />';
		let end = '<c:out value="${ today }" />';
		console.log('begin : ' + begin + ', end : ' + end);

        $.ajax({
            url : 'bdlistdatecount.do'
            ,type : 'get'
            ,data: {
            	begin : begin
            	,end : end
            }
            ,contentType: "application/json; charset=utf-8" // mimiType
    		,dataType : 'text'
            ,success : function(data) {
                console.log('data : ' + data);
                $('#new_count').text(data);
            },error: function(request, status, errorData){
                console.log("error : " + request + ", " + status + ", " + errorData);
            }
        })
    }
</script>
</head>
<body>
	<div class="side-icon-box">
	    <c:url var="bdwrite" value="bdmoveinsert.do">
	        <c:param name="depId" value="${ depId }"/>
	    </c:url>
	    <a href="${ bdwrite }" class="side-write-btn">글쓰기</a>
	
	    <div class="side-icon-menu">
	        <c:url var="bdselnew" value="bdlistnew.do">
	            <c:param name="empId" value="${ loginMember.empId }"/>
	            <c:param name="depId" value="${ depId }"/>
				<c:param name="newdays" value="${ newdays }"/>
	        </c:url>
	        <a class="side-icon-btn" id="sideBtn_new" href="${ bdselnew }">
	            <span class="side-icon" id="new_count">0</span>
	            <span>최신글</span>
	        </a>
	        
	        <c:url var="bdselmy" value="bdlistmy.do">
	            <c:param name="empId" value="${ loginMember.empId }"/>
	            <c:param name="depId" value="${ depId }"/>
				<c:param name="begin" value="${ begin }"/>
				<c:param name="end" value="${ end }"/>
	        </c:url>
	        <a class="side-icon-btn" id="sideBtn_my" href="${ bdselmy }">
	            <span class="side-icon">
	                <img src="${ pageContext.servletContext.contextPath }/resources/images/my_1.png">
	            </span>
	            <span>내 게시글</span>
	        </a>
	    </div>
    </div>
</body>
</html>