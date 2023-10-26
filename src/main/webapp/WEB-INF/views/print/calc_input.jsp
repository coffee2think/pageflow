<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script>
    const NOWPAGE = 2;
    const SUBPAGE = 3;
    const LNKPAGE = 2;
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
                    <!-- 리스트 들어감 -->
                    <c:import url="../common/side.jsp" />
                </div>
            </div>
            <!--main-side end-->

            <!--main-container-->
            <div class="main-container">

                <!--form-->
                <!-- <form class="input-form" action="/comi/partyi" method="post" enctype="multipart/form-data">-->
                <form class="input-form" action="" method="post">
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
                        
                        <!--컨텐츠영역-->
                        <div class="contents-container sort-row">
                            <div class="contents-box">
                                <table class="contents-table print">
                                    <tr>
                                        <th></th>
                                        <th>거래처코드</th>
                                        <th>인쇄소</th>
                                        <th>마감일</th>
                                        <th>도서코드</th>
                                        <th>도서명</th>
                                        <th>단위</th>
                                        <th>수량</th>
                                        <th>단가</th>
                                        <th>합계</th>
                                        <th>상태</th>
                                    </tr>
                                    <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1">
                                        
                                        <td class="td-50">
                                            <div class="contents-check-div">
                                                <button class="contents-input-plus">
                                                    <img src="${ pageContext.servletContext.contextPath }/resources/images/plus.png">
                                                </button>
                                                <button class="contents-input-minus">
                                                    <img src="${ pageContext.servletContext.contextPath }/resources/images/minus.png">
                                                </button>
                                            </div>
                                        </td>
                                        <td class="td-120">
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <input type="input" name="code" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td class="td-150">
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <input type="input" name="name" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td class="td-120">
                                            <div class="contents-input-div">
                                                <input type="date" name="date_end" class="select-date small">
                                            </div>
                                        </td>
                                        <td class="td-100">
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <input type="input" name="book_code" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td class="td-200">
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <input type="input" name="book_name" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td class="td-70">
                                            <div class="contents-input-div">
                                                <input type="number" name="unit" class="contents-input">
                                            </div>
                                        </td>
                                        <td class="td-70">
                                            <div class="contents-input-div">
                                                <input type="number" name="quantity" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td class="td-100">
                                            <div class="contents-input-div">
                                                <input type="number" name="price" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td class="td-100">
                                            <div class="contents-input-div">
                                                <input type="number" name="amount" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td class="td-50">
                                            <div class="contents-input-div">
                                                <input type="input" name="state" class="contents-input" value="">
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <!--컨텐츠영역 end-->
                        
                    
                    </div>
                    <!--내용 end-->
                    <div class="submit-box">
                        <input type="submit" class="contents-input-btn big noline" id="btn_save" value="저장">
                    </div>

                </form>
                <!--form end-->
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