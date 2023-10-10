<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="currentPage" value="${ requestScope.paging.currentPage }" />
<c:set var="urlMapping" value="${ requestScope.paging.urlMapping }" />
<c:set var="startPage" value="${ requestScope.paging.startPage }"/>
<c:set var="endPage" value="${ requestScope.paging.endPage }"/>
<c:set var="maxPage" value="${ requestScope.paging.maxPage }"/>
<c:set var="limit" value="${ requestScope.paging.limit }"/>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<title></title>
</head>
<body>

	<c:if test="${ currentPage eq 1 }">
		<span class="paging-list-btn prev greybtn">
	        <span><img src="${ pageContext.servletContext.contextPath }/resources/images/left-btn2.png"></span>
	    </span>
	</c:if>
	<c:if test="${ currentPage > 1 }">
		<a href="" class="paging-list-btn prev">
	        <span><img src="${ pageContext.servletContext.contextPath }/resources/images/left-btn2.png"></span>
	    </a>
	</c:if>
	
    <c:if test="${ (currentPage - limit) < startPage && (currentPage - limit) > 1 }">
		<a href="" class="paging-list-btn prev">
	        <span><img src="${ pageContext.servletContext.contextPath }/resources/images/left-btn.png"></span>
	    </a>
	</c:if>
	
	<c:if test="${ !((currentPage - limit) < startPage && (currentPage - limit) > 1) }">
		<span class="paging-list-btn prev greybtn">
	        <span><img src="${ pageContext.servletContext.contextPath }/resources/images/left-btn.png"></span>
	    </span>
	</c:if>
	
	<c:forEach var="p" begin="${ startPage }" end="${ endPage }" step="1">
		<c:if test="${ p eq currentPage }">
			<a href="" class="paging-list-btn active"><span>${ p }</span></a>
		</c:if>
		<c:if test="${ p ne currentPage }">
			<a href="" class="paging-list-btn"><span>${ p }</span></a>
		</c:if>
	</c:forEach>
    
	<c:if test="${ (currentPage + limit) > endPage && (currentPage + limit) < maxPage }">
		<a href="" class="paging-list-btn next">
	        <span><img src="${ pageContext.servletContext.contextPath }/resources/images/right-btn.png"></span>
	    </a>
	</c:if>
	
	<c:if test="${ !((currentPage + limit) > endPage && (currentPage + limit) < maxPage) }">
		<span class="paging-list-btn next greybtn">
	        <span><img src="${ pageContext.servletContext.contextPath }/resources/images/right-btn.png"></span>
	    </span>
	</c:if>
	
    <c:if test="${ currentPage >= maxPage }">
		<span class="paging-list-btn next greybtn">
	        <span><img src="${ pageContext.servletContext.contextPath }/resources/images/right-btn2.png"></span>
	    </span>
	</c:if>
	<c:if test="${ !(currentPage >= maxPage) }">
		<a href="" class="paging-list-btn next">
	        <span><img src="${ pageContext.servletContext.contextPath }/resources/images/right-btn2.png"></span>
	    </a>
	</c:if>
</body>
</html>