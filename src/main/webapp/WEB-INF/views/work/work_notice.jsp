<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<c:set var="board" value="${ requestScope.board }" />
<c:set var="replyList" value="${ requestScope.replyList }" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="/pageflow/resources/css/main.css">
<link rel="stylesheet" type="text/css" href="/pageflow/resources/css/notice.css">
<script type="text/javascript" src="/pageflow/resources/js/lib/jquery.min.js"></script>
<title></title>
<script>
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
                    <div class="side-icon-box">
                        <a href="/pageflow/views/work/work_input.jsp" class="side-write-btn">글쓰기</a>
                        <div class="side-icon-menu">
                            <button class="side-icon-btn" id="sideBtn_new">
                                <span class="side-icon">3</span>
                                <span>최신글</span>
                            </button>
                            <button class="side-icon-btn" id="sideBtn_my">
                                <span class="side-icon">
                                    <img src="/pageflow/resources/images/my_1.png">
                                </span>
                                <span>내 게시글</span>
                            </button>
                        </div>
                    </div>

                    <!-- 리스트 들어감 -->
                    <c:import url="../common/side.jsp" />
                </div>
            </div>
            <!--main-side end-->

            <!--main-container-->
            <div class="main-container notice">
                <!--main-header-bar-->
                <div class="main-header-bar">
                    <div class="main-title-box">
                        <img src="/pageflow/resources/images/header-icon.png">
                        <span class="main-title"></span>
                    </div>
                    <button class="header-left-btn">
                    </button>
                </div>
                <!--main-header-bar end-->

                <!--내용-->
                <div class="main-contents-box normal">

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
                                        <img class="search-image" src="/pageflow/resources/images/search_btn.png">
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

                        <button class="search-visible-btn" id="search_visible_btn">
                            <img class="search-close" src="/pageflow/resources/images/cursor_1.png">
                            <img class="search-open" src="/pageflow/resources/images/cursor_2.png">
                        </button>

                    </div>
                    <!--서치영역 end-->

                    <!--컨텐츠영역-->
                    <div class="contents-container sort-row">
                        <div class="contents-box notice" data-num="">

                            <div class="contents-title notice-tit small">
                                ${ board.depName }팀 게시판
                            </div>

                            <!--contents-notice-->
                            <div class="contents-notice notice">

                                <div class="notice-title-box">
                                    <div class="contents-notice-title big">
                                        ${ board.boardTitle }
                                    </div>
                                    <div class="contents-notice-line">
                                        <div class="notice-profile">
                                            <img src="/pageflow/resources/images/profile.png">
                                            <span>
                                                <div>${ board.empName }</div>
                                                <div>
                                                    <span>${ board.createDate }</span>
                                                    <span>|</span>
                                                    <span class="bluetext">읽음 ${ board.viewsNum }</span>
                                                </div>
                                            </span>
                                        </div>
                                    </div>
                                    <a class="contents-notice-down" href="#">
                                        <img src="/pageflow/resources/images/side-icon-dep1-over.png">
                                        <span>product_manage.zip</span>
                                        <img class="down-img" src="/pageflow/resources/images/down.png">
                                    </a>
                                </div>
                                
                                <div class="notice-contents-box">
                                    ${ board.boardDetail }
                                </div>

                                <!--notice-reply-box-->
                                <div class="notice-reply-box">
                                    <div class="notice-reply-title">
                                        <div class="reply-title">
                                            댓글 <span>0</span>
                                        </div>
                                        <div class="reply-sort">
                                            <button class="reply-sort-btn">
                                                <img src="/pageflow/resources/images/chk.png">
                                                <span>등록순</span>
                                            </button>
                                            <button class="reply-sort-btn">
                                                <img src="/pageflow/resources/images/chk.png">
                                                <span>최신순</span>
                                            </button>
                                        </div>
                                    </div>

                                    <!--댓글 뎁스 1-->
                                    <div class="notice-reply-con depth1" data-parent="" data-num="">
                                        <div class="contents-notice-line">
                                            <div class="notice-profile">
                                                <img src="/pageflow/resources/images/profile.png">
                                                <span>
                                                    <div>홍길동</div>
                                                    <div>
                                                        <span>2023.05.09</span>
                                                    </div>
                                                </span>
                                            </div>
                                        </div>
                                        
                                        <div class="contents-notice-text">
                                            <span class="contents-notice-text-parent">@전성훤</span>
                                            댓글  댓글1댓글  댓글1
                                            댓글  댓글1댓글  댓글1
                                            댓글  댓글1댓글  댓글1
                                            댓글  댓글1댓글  댓글1
                                            댓글  댓글1댓글  댓글1
                                            댓글  댓글1댓글  댓글1
                                            댓글  댓글1댓글  댓글1
                                            댓글  댓글1댓글  댓글1
                                            댓글  댓글1댓글  댓글1
                                            댓글  댓글1댓글  댓글1
                                            댓글  댓글1댓글  댓글1
                                            댓글  댓글1
                                            댓글  댓글1
                                        </div>

                                        <a class="contents-notice-down" href="#">
                                            <img src="/pageflow/resources/images/side-icon-dep1-over.png">
                                            <span>product_manage2.zip</span>
                                            <img class="down-img" src="/pageflow/resources/images/down.png">
                                        </a>

                                        <button class="reply-btn">
                                            답글
                                        </button>

                                        <div class="reply-right">
											<div class="reply-dropdown">
												<button class="reply-delete-btn">삭제</button>
												<button class="reply-report-btn">신고</button>
											</div>
					                        <button class="reply-right-btn">
					                            <img class="reply-right-btn-img" src="/pageflow/resources/images/right.png">
					                        </button>
					                    </div>
                                    </div>
                                    <!--댓글 뎁스 1 end-->

                                    <!--댓글 뎁스 2-->
                                    <div class="notice-reply-con depth2" data-parent="" data-num="">
                                        <div class="depth2-icon">ㄴ</div>
                                        <div class="contents-notice-line">
                                            <div class="notice-profile">
                                                <img src="/pageflow/resources/images/profile.png">
                                                <span>
                                                    <div>홍길동</div>
                                                    <div>
                                                        <span>2023.05.09</span>
                                                    </div>
                                                </span>
                                            </div>
                                        </div>

                                        <div class="contents-notice-text">
                                            <span class="contents-notice-text-parent">@전성훤</span>
                                            댓글  댓글1
                                            댓글  댓글1
                                        </div>

                                        <a class="contents-notice-down" href="#">
                                            <img src="/pageflow/resources/images/side-icon-dep1-over.png">
                                            <span>product_manage2.zip</span>
                                            <img class="down-img" src="/pageflow/resources/images/down.png">
                                        </a>

                                        <button class="reply-btn">
                                            답글
                                        </button>

                                        <div class="reply-right">
											<div class="reply-dropdown">
												<button class="reply-delete-btn">삭제</button>
												<button class="reply-report-btn">신고</button>
											</div>
					                        <button class="reply-right-btn">
					                            <img class="reply-right-btn-img" src="/pageflow/resources/images/right.png">
					                        </button>
					                    </div>

                                        <div class="reply-input-box depth2">
                                            <div class="depth2-icon-reply">ㄴ</div>
                                            <div class="reply-input-area">
                                                <div class="reply-input" contenteditable="true">
                                                    
                                                </div>
                                            </div>
                                            <div class="reply-input-btn-box">
                                                <input type="file" class="file-btn" id="">
                                                <input type="button" class="contents-input-btn big noline" id="btn_insert" value="입력">
                                            </div>
                                        </div>

                                    </div>
                                    <!--댓글 뎁스 2 end-->

                                </div>
                                <!--notice-reply-box end-->

                                <div class="reply-input-box depth1">
                                    <div class="reply-input-area">
                                        <div class="reply-input" contenteditable="true">

                                        </div>
                                    </div>
                                    <div class="reply-input-btn-box">
                                        <input type="file" class="file-btn" id="">
                                        <input type="button" class="contents-input-btn big noline" id="btn_insert" value="입력">
                                    </div>
                                </div>

                            </div>
                            <!--contents-notice end-->

                            <div class="button-box">
                                <a class="button-a button-list" href="#">
                                    목록
                                </a>
                                <a class="button-a button-prev" href="#">
                                    ▲
                                </a>
                                <a class="button-a button-next" href="#">
                                    ▼
                                </a>
                                <a class="button-a button-top" href="#">
                                    위로
                                </a>
                            </div>

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