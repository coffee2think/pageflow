<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="boardList" value="${ requestScope.boardList }"/>
<c:set var="depId" value="${ boardList.get(0).depId }" />
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

    $(function(){
        newCount();
    })

    function newCount(){
        $.ajax({
            url : 'bdlistnewcount.do'
            ,type : 'get'
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
                    <div class="side-icon-box">
                        <a href="bdmoveinsert.do?depId=${ depId }" class="side-write-btn">글쓰기</a>
                        <div class="side-icon-menu">
                            <a class="side-icon-btn" id="sideBtn_new" href="#">
                                <span class="side-icon" id="new_count">0</span>
                                <span>최신글</span>
                            </a>
                            <a class="side-icon-btn" id="sideBtn_my" href="#">
                                <span class="side-icon">
                                    <img src="${ pageContext.servletContext.contextPath }/resources/images/my_1.png">
                                </span>
                                <span>내 게시글</span>
                            </a>
                        </div>
                    </div>
					
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
                        <form class="search-form">

                            <div class="select-search">
                                <div class="select-box">
                                    <div class="select-pan">
                                        <label for="sel_code"></label>
                                        <select name="code" id="sel_code">
                                            <option value="">내용</option>
                                            <option value="">작성자</option>
                                            <option value="">댓글</option>
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
                                <div class="select-pan-nemo">
                                    작성날짜
                                </div>

                                <input type="date" class="select-date select-date-first">
                                <input type="date" class="select-date select-date-second">

                                <input type="button" name="week" class="select-pan-btn" value="일주일">
                                <input type="button" name="month" class="select-pan-btn" value="한달">
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
                        <div class="contents-box notice">

                            <div class="contents-title notice-tit">
                                ${ boardList.get(0).depName } 게시판
                            </div>
                            
                            <c:forEach var="b" items="${ boardList }">
                            
                            	<c:url var="listUrl" value="bdselect.do">
                            		<c:param name="empId" value="${ b.empId }" />
                            		<c:param name="depId" value="${ b.depId }" />
                            		<c:param name="boardId" value="${ b.boardId }" />
                            	</c:url>
                            	
                            	<a class="contents-notice" href="${ listUrl }">
	                                <div class="contents-notice-title">
	                                    <span class="alarm"></span>
	                                    ${ b.boardTitle }
	                                </div>
	                                <div class="contents-notice-line">
	                                    <span>${ b.empName }</span>
	                                    <span>|</span>
	                                    <span>${ b.createDate }</span>
	                                    <span>|</span>
	                                    <span>읽음 ${ b.viewsNum }</span>
	                                    <span>|</span>
	                                    <span>
	                                    	<img src="${ pageContext.servletContext.contextPath }/resources/images/msg.png">
	                                    	${ b.replyCount }
	                                    </span>
	                                    <span>|</span>
	                                </div>
	                            </a>
                            </c:forEach>
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