<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/popup.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery-ui-zoom.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.ui.touch-punch.min.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/popup_test.js"></script>
<title></title>
</head>
<body>
	<!-- modal-pop-box -->
    <div class="modal-pop-box small pop-box-1" id="popup_book">
        <div class="modal-pop-title">
            도서검색
            <button class="modal-pop-close">
                <img src="${ pageContext.servletContext.contextPath }/resources/images/close.png">
            </button>
        </div>
        
        <!--modal-pop-->
        <div class="modal-pop">
            <div class="modal-pop-search">
                <div class="select-box">
                    <div class="select-pan">
                        <label for="sel_code"></label>
                        <select name="code" id="sel_code">
                            <option value="name">도서명</option>
                            <option value="code">도서코드</option>
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

            <div class="modal-pan-center">
                <table class="contents-table">
                    <thead>
                        <th></th>
                        <th>
                            No.
                        </th>
                        <th>
                            도서코드
                        </th>
                        <th>
                            도서명
                        </th>
                        <th>
                            재고현황
                        </th>
                    </thead>
                    <tbody>
                        <tr onclick=""  class="cursor-pointer">
                            <td>
                                <input type="checkbox" name="check" value="" >
                            </td>
                            <td>1</td>
                            <td>10102</td>
                            <td>이것이 자바다</td>
                            <td>140</td>
                        </tr>
                    </tbody>
                    
                </table>
            </div>

            <div class="modal-pan-bottom flex-center">
                <input type="button" class="contents-input-btn big noline" id="btn_register" value="등록">
            </div>
        </div>
        <!--modal-pop end-->

    </div>
    <!-- modal-pop-box end -->

    <!-- modal-pop-box -->
    <div class="modal-pop-box small pop-box-1" id="popup_printoffice">
        <div class="modal-pop-title">
            인쇄소 검색
            <button class="modal-pop-close">
                <img src="${ pageContext.servletContext.contextPath }/resources/images/close.png">
            </button>
        </div>
        
        <!--modal-pop-->
        <div class="modal-pop">
            <div class="modal-pop-search">
                <div class="select-box">
                    <div class="select-pan">
                        <label for="sel_code"></label>
                        <select name="code" id="sel_code">
                            <option value="name">인쇄소명</option>
                            <option value="code">인쇄소코드</option>
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

            <div class="modal-pan-center">
                <table class="contents-table">
                    <thead>
                        <th></th>
                        <th>
                            No.
                        </th>
                        <th>
                            인쇄소코드
                        </th>
                        <th>
                            인쇄소명
                        </th>
                    </thead>
                    <tbody>
                        <tr onclick=""  class="cursor-pointer">
                            <td>
                                <input type="checkbox" name="check" value="" >
                            </td>
                            <td>1</td>
                            <td>AK-1213</td>
                            <td>거성인쇄</td>
                        </tr>
                    </tbody>
                    
                </table>
            </div>
            
            <div class="modal-pan-bottom flex-center">
                <input type="button" class="contents-input-btn big noline" id="btn_register" value="등록">
            </div>
            
        </div>
        <!--modal-pop end-->

    </div>
    <!-- modal-pop-box end -->
</body>
</html>