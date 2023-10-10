<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="/pageflow/resources/css/main.css">
<link rel="stylesheet" type="text/css" href="/pageflow/resources/css/notice.css">
<script type="text/javascript" src="/pageflow/resources/js/lib/jquery.min.js"></script>
<script>
    const NOWPAGE = 1;
    const SUBPAGE = 2;
    const LNKPAGE = 1;
</script>
<title></title>
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
					
                    <!--컨텐츠영역-->
                    <div class="contents-container sort-row">
                        <div class="contents-box notice">

                            <div class="contents-title notice-tit">
                                글쓰기
                            </div>

                            <div class="select-box">
                                <div class="select-pan-nemo">
                                    게시판
                                </div>

                                <div class="select-pan">
                                    <label for="sel_code"></label>
                                    <select name="code" id="sel_code">
                                        <option value="development">개발팀</option>
                                        <option value="design">디자인팀</option>
                                    </select>
                                </div>
                            </div>

                            <form>
                                <div class="select-box">
                                    <div class="select-pan-nemo">
                                        제목
                                    </div>

                                    <div class="select-pan">
                                        <input type="text" class="input-box-input noline" placeholder="제목을 입력하세요.">
                                    </div>
                                </div>

                                <div class="select-box">
                                    <div class="select-pan-nemo">
                                        파일첨부
                                    </div>

                                    <div class="select-pan">
                                        <input type="file" class="select-file">
                                    </div>
                                </div>

                                <div class="content-input-area height-long">
                                    <div class="content-input" contenteditable="true">

                                    </div>
                                </div>

                                <div class="content-input-btn-box">
                                    <input type="button" class="contents-input-btn big noline" id="btn_insert" value="입력">
                                </div>
                            </form>
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