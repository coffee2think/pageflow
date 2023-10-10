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
    const NOWPAGE = 5;
    const SUBPAGE = 3;
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
                <div class="main-contents-box normal">

                    <!--서치영역-->
                    <div class="search-container">
                        <form class="search-form">
                            <div class="select-search">
                                <div class="select-box">
                                    <div class="select-pan">
                                        <label for="sel_code"></label>
                                        <select name="code" id="sel_code">
                                            <option value="">코드</option>
                                            <option value="">거래처명</option>
                                            <option value="">사업자등록번호</option>
                                            <option value="">대표자</option>
                                            <option value="">사업장주소</option>
                                            <option value="">담당자</option>
                                            <option value="">담당자연락처</option>
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
                                        <option value="all">거래처별</option>
                                        <option value="">교보문고</option>
                                        <option value="">영풍문고</option>
                                        <option value="">yes24</option>
                                    </select>
                                </div>
                                
                                <div class="select-pan">
                                    <label for="sel_code"></label>
                                    <select name="code" id="sel_code">
                                        <option value="all">분류별</option>
                                        <option value="">서점</option>
                                        <option value="">총판</option>
                                    </select>
                                </div>
                                
                            </div>

                            <div class="select-box">
                                <div class="select-pan-nemo">
                                    기간
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
                                    <th>코드</th>
                                    <th>거래처명</th>
                                    <th>사업자등록번호</th>
                                    <th>대표자</th>
                                    <th>분류</th>
                                    <th>사업장주소</th>
                                    <th>담당자</th>
                                    <th>담당자연락처</th>
                                    <th>거래시작일</th>
                                    <th>거래종료일</th>
                                    <th>수정</th>
                                </tr>
                                <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1">
                                    <td class="td-50">
                                        <input type="checkbox" name="check" value="" >
                                    </td>
                                    <td class="td-70">
                                        <div class="contents-input-div">
                                            <input type="input" name="code" class="contents-input noline" value="1002" readonly>
                                        </div>
                                    </td>
                                    <td class="td-100">
                                        <div class="contents-input-div">
                                            <input type="input" name="name" class="contents-input noline" value="교보문고" readonly>
                                        </div>
                                    </td>
                                    <td class="td-150">
                                        <div class="contents-input-div">
                                            <input type="input" name="no" class="contents-input noline" value="534-00-444" readonly>
                                        </div>
                                    </td>
                                    <td class="td-70">
                                        <div class="contents-input-div">
                                            <input type="input" name="ceo" class="contents-input noline" value="이승철" readonly>
                                        </div>
                                    </td>
                                    <td class="td-50">
                                        <div class="contents-input-div">
                                            <input type="input" name="classify" class="contents-input noline" value="서점" readonly>
                                        </div>
                                    </td>
                                    <td class="td-300">
                                        <div class="contents-input-div">
                                            <input type="input" name="address" class="contents-input noline" value="서울 서초구 강남대로 465 교보타워 지하 1층~지하 2층" readonly>
                                        </div>
                                    </td>
                                    <td class="td-50">
                                        <div class="contents-input-div">
                                            <input type="input" name="manager" class="contents-input noline" value="신종식" readonly>
                                        </div>
                                    </td>
                                    <td class="td-150">
                                        <div class="contents-input-div">
                                            <input type="input" name="phone" class="contents-input noline" value="010-0651-4374" readonly>
                                        </div>
                                    </td>
                                    <td class="td-100">
                                        <div class="contents-input-div">
                                            <input type="input" name="date_start" class="contents-input noline" value="2015.03.05" readonly>
                                        </div>
                                    </td>
                                    <td class="td-100">
                                        <div class="contents-input-div">
                                            <input type="input" name="date_end" class="contents-input noline" value="2015.03.05" readonly>
                                        </div>
                                    </td>
                                    <td class="td-100">
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