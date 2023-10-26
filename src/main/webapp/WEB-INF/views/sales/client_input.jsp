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
    const NOWPAGE = 5;
    const SUBPAGE = 3;
    const LNKPAGE = 2;
</script>
<title>거래처 등록</title>
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
                <form class="input-form" action="clinsert.do" method="post">
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
                                <table class="contents-table sales">
                                    <tr>
                                        <th>분류</th>
                                        <th>거래처명</th>
                                        <th>사업자등록번호</th>
                                        <th>사업장주소</th>
                                        <th>거래처연락처</th>
                                        <th>거래처홈페이지</th>
                                        <th>담당자</th>
                                        <th>담당자연락처</th>
                                        <th>담당자이메일</th>
                                        <th>거래시작일</th>
                                    </tr>
                                    <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1">
                                        <td class="td-50">
                                            <div class="contents-input-div">
                                                <!-- <input type="" name="classify" class="contents-input"> -->
                                                <select name="category" style="width: 95%;">
                                                	<option value="서점">서점</option>
                                                	<option value="인쇄소">인쇄소</option>
                                                	<option value="창고">창고</option>
                                                </select>
                                            </div>
                                        </td>
                                        <td class="td-150">
                                            <div class="contents-input-div">
                                                <input type="input" name="clientName" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td class="td-100">
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <input type="input" name="eid" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td class="td-200">
                                            <div class="contents-input-div">
                                                <input type="input" name="clientAddress" class="contents-input">
                                            </div>
                                        </td>
                                        <td class="td-200">
                                            <div class="contents-input-div">
                                                <input type="input" name="clientContact" class="contents-input">
                                            </div>
                                        </td>
                                        <td class="td-200">
                                            <div class="contents-input-div">
                                                <input type="input" name="clientUrl" class="contents-input">
                                            </div>
                                        </td>
                                        <td class="td-100">
                                            <div class="contents-input-div">
                                                <input type="input" name="manager" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td class="td-100">
                                            <div class="contents-input-div">
                                                <input type="input" name="managerContact" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td class="td-200">
                                            <div class="contents-input-div">
                                                <input type="input" name="managerEmail" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td class="td-120">
                                            <div class="contents-input-div">
                                                <input type="date" name="startDate" class="select-date">
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