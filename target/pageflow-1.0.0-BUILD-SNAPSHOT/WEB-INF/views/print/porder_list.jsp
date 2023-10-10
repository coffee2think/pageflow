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
<script type="text/javascript" src="/pageflow/resources/js/lib/jquery.min.js"></script>
<script>
    const NOWPAGE = 2;
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
                        <img src="/pageflow/resources/images/header-icon.png">
                        <span class="main-title"></span>
                    </div>
                    <button class="header-left-btn">
                    </button>
                </div>
                <!--main-header-bar end-->

                <!--내용-->
                <div class="main-contents-box">

                    <!--서치영역-->
                    <div class="search-container">
                        <form class="search-form">
                            <div class="select-search">
                                <div class="select-box">
                                    <div class="select-pan">
                                        <label for="sel_code"></label>
                                        <select name="code" id="sel_code">
                                            <option value="">인쇄소코드</option>
                                            <option value="">인쇄소명</option>
                                            <option value="">도서코드</option>
                                            <option value="">도서명</option>
                                            <option value="">수량</option>
                                            <option value="">단가</option>
                                            <option value="">합계</option>
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
                                <div class="select-pan">
                                    <label for="sel_code"></label>
                                    <select name="code" id="sel_code">
                                        <option value="all">인쇄소별</option>
                                        <option value="">거성인쇄</option>
                                        <option value="">JK인쇄</option>
                                    </select>
                                </div>
                                
                                <div class="select-pan">
                                    <label for="sel_code"></label>
                                    <select name="code" id="sel_code">
                                        <option value="all">상태</option>
                                        <option value="">지급</option>
                                        <option value="">미지급</option>
                                    </select>
                                </div>
                            </div>

                            <div class="select-box">
                                <div class="select-pan-nemo">
                                    발주일
                                </div>

                                <input type="date" class="select-date select-date-first">
                                <input type="date" class="select-date select-date-second">

                                <input type="button" name="week" class="select-pan-btn" value="일주일">
                                <input type="button" name="month" class="select-pan-btn" value="한달">
                            </div>

                            <div class="select-box">
                                <div class="select-pan-nemo">
                                    마감일
                                </div>

                                <input type="date" class="select-date select-date-first">
                                <input type="date" class="select-date select-date-second">

                                <input type="button" name="week" class="select-pan-btn" value="일주일">
                                <input type="button" name="month" class="select-pan-btn" value="한달">
                            </div>

                            <div class="select-box">
                                <div class="select-pan-nemo">
                                    출간일
                                </div>

                                <input type="date" class="select-date select-date-first">
                                <input type="date" class="select-date select-date-second">

                                <input type="button" name="week" class="select-pan-btn" value="일주일">
                                <input type="button" name="month" class="select-pan-btn" value="한달">
                            </div>

                        </form>

                        <div class="paging-box">
                            <!-- 페이징 -->
                            <c:import url="../common/paging.jsp" />
                        </div>

                        <button class="search-visible-btn" id="search_visible_btn">
                            <img class="search-close" src="/pageflow/resources/images/cursor_1.png">
                            <img class="search-open" src="/pageflow/resources/images/cursor_2.png">
                        </button>

                    </div>
                    <!--서치영역 end-->

                    <!--컨텐츠영역-->
                    <div class="contents-container sort-row">
                        <div class="contents-box">
                            <table class="contents-table">
                                <tr>
                                    <th>체크</th>
                                    <th>거래처코드</th>
                                    <th>인쇄소</th>
                                    <th>발주일</th>
                                    <th>마감일</th>
                                    <th>출간일</th>
                                    <th>도서코드</th>
                                    <th>도서명</th>
                                    <th>단위</th>
                                    <th>수량</th>
                                    <th>단가</th>
                                    <th>합계</th>
                                    <th>상태</th>
                                    <th>수정</th>
                                </tr>
                                <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1">
                                    <td class="td-50">
                                        <input type="checkbox" name="check" value="" >
                                    </td>
                                    <td class="td-100">
                                        <div class="contents-input-div">
                                            <input type="input" name="code" class="contents-input noline" value="AK-1012" readonly>
                                        </div>
                                    </td>
                                    <td class="td-120">
                                        <div class="contents-input-div">
                                            <input type="input" name="name" class="contents-input noline" value="거성인쇄" readonly>
                                        </div>
                                    </td>
                                    <td class="td-100">
                                        <div class="contents-input-div">
                                            <input type="input" name="date_order" class="contents-input noline" value="2015.03.05" readonly>
                                        </div>
                                    </td>
                                    <td class="td-100">
                                        <div class="contents-input-div">
                                            <input type="input" name="date_end" class="contents-input noline" value="2015.03.05" readonly>
                                        </div>
                                    </td>
                                    <td class="td-100">
                                        <div class="contents-input-div">
                                            <input type="input" name="date_pub" class="contents-input noline" value="2015.03.05" readonly>
                                        </div>
                                    </td>
                                    <td class="td-70">
                                        <div class="contents-input-div">
                                            <input type="input" name="book_code" class="contents-input noline" value="10100" readonly>
                                        </div>
                                    </td>
                                    <td class="td-250">
                                        <div class="contents-input-div">
                                            <input type="input" name="book_name" class="contents-input noline" value="혼자 공부하는 자바" readonly>
                                        </div>
                                    </td>
                                    <td class="td-50">
                                        <div class="contents-input-div">
                                            <input type="input" name="phone" class="contents-input noline" value="EA" readonly>
                                        </div>
                                    </td>
                                    <td class="td-70">
                                        <div class="contents-input-div">
                                            <input type="input" name="date_start" class="contents-input noline" value="100" readonly>
                                        </div>
                                    </td>
                                    <td class="td-100">
                                        <div class="contents-input-div">
                                            <input type="input" name="date_end" class="contents-input noline" value="18000" readonly>
                                        </div>
                                    </td>
                                    <td class="td-120">
                                        <div class="contents-input-div">
                                            <input type="input" name="date_end" class="contents-input noline" value="1800000" readonly>
                                        </div>
                                    </td>
                                    <td class="td-50">
                                        <div class="contents-input-div">
                                            <input type="input" name="state" class="contents-input noline" value="지급" readonly>
                                        </div>
                                    </td>
                                    <td class="td-70">
                                        <input type="button" name="update" class="contents-input-btn noline" value="수정">
                                    </td>
                                </tr>

                            </table>
                        </div>
                    </div>
                    <!--컨텐츠영역 end-->

                </div>
                <!--내용 end-->

                
                <div class="submit-box">
                    <input type="button" class="contents-input-btn big noline" id="btn_delete" value="선택삭제">
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