<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="boardList" value="${ requestScope.boardList }"/>
<c:set var="depId" value="${ requestScope.depId }" scope="session" />
<c:set var="depName" value="${ requestScope.depName }" scope="session" />
<c:set var="begin" value="${ requestScope.begin }" scope="session" />
<c:set var="end" value="${ requestScope.end }" scope="session" />
<c:set var="keyword" value="${ requestScope.keyword }" scope="session" />
<c:set var="searchType" value="${ requestScope.searchType }" scope="session" />
<c:set var="firstType" value="${ requestScope.firstType }" scope="session" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/notice.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<title></title>
<script type="text/javascript">
    const NOWPAGE = 1;
    const SUBPAGE = 2;
    const LNKPAGE = 1;
</script>
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

                    <c:if test="${ !empty loginMember }">
                        <c:import url="../common/side_icon.jsp" />
                    </c:if>

                    <!-- 리스트 들어감 -->
                    <c:import url="/WEB-INF/views/common/side.jsp" />
                </div>
            </div>
            <!--main-side end-->

            <!--main-container-->
            <div class="main-container notice">

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
                    <div class="search-container noline">
                        <div class="search-form">
                            <c:import url="./work_search.jsp" />
                        </div>

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
                        <div class="contents-box notice">

                            <div class="contents-title notice-tit">
                                ${ depName } 게시판
                            </div>

                            <table class="contents-table board-notice-table">
                                
                                <tr>
                                    <th>번호</th>
                                    <th>제목</th>
                                    <th>작성자</th>
                                    <th>날짜</th>
                                    <th>조회수</th>
                                </tr>

                                <c:if test="${ !empty boardList }">
                                    <c:forEach var="b" items="${ boardList }">
                                    
                                        <c:url var="listUrl" value="bdselect.do">
                                            <c:param name="empId" value="${ b.empId }" />
                                            <c:param name="depId" value="${ b.depId }" />
                                            <c:param name="boardId" value="${ b.boardId }" />

                                            <c:param name="begin" value="${ begin }" />
                                            <c:param name="end" value="${ end }" />
                                        </c:url>
                                        
                                        <tr>
                                            <td>
                                                <a class="board-a" href="${ listUrl }">
                                                    ${b.boardId}
                                                </a>
                                            </td>
                                            <td>
                                                <a href="${ listUrl }">
                                                    <span class="alarm"></span>
                                                    ${ b.boardTitle }
                                                </a>
                                            </td>
                                            
                                            <td>
                                                <a class="board-a" href="${ listUrl }">
                                                    ${ b.empName }
                                                </a>
                                            </td>
                                            <td>
                                                <a class="board-a" href="${ listUrl }">
                                                    ${ b.createDate }
                                                </a>
                                            </td>
                                            <td>
                                                <a class="board-a" href="${ listUrl }">
                                                    <span>읽음 ${ b.viewsNum }</span>
                                                    <span>
                                                        <img src="${ pageContext.servletContext.contextPath }/resources/images/msg.png">
                                                        ${ b.replyCount }
                                                    </span>
                                                </a>
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

                <!--
                <div class="submit-box">
                    <input type="button" class="contents-input-btn big noline" id="btn_write" value="글쓰기">
                </div>
                -->
            </div>
            <!--main-container end-->
        </main>
        
    </div>
</body>
</html>
