<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/sales_func_new.js"></script>
<script>
    const NOWPAGE = 3;
    const SUBPAGE = 4;
    const LNKPAGE = 1;
</script>
<title>작가현황</title>
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
                    <div class="search-container">
                        <form class="search-form">
                            <div class="select-search">
                                <div class="select-box">
                                    <div class="select-pan">
                                        <label for="sel_code"></label>
                                        <select name="code" id="search_type">
                                            <option value="writer" <c:if test="${ searchType == 'writer' }">selected</c:if>>작가명</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="search-box">
                                   <input type="search" placeholder="키워드를 입력하세요." class="search-box-text" value="${ keyword }" name="keyword">
                                    <button class="search-btn" onclick="searchKeyword('wtlistkwd.do'); return false;">
                                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                    </button>
                                </div>
                            </div>

                        </form>

                        <div class="paging-box">
                            <!-- 페이징 -->
                            <c:import url="../common/paging.jsp" />
                        </div>

                        <button class="search-visible-btn" id="search_visible_btn">
                            <img class="search-close" src="${ pageContext.servletContext.contextPath }/resources/images/cursor_1.png">
                            <img class="search-open" src="${ pageContext.servletContext.contextPath }/resources/images/cursor_2.png">
                        </button>

                    </div>
                    <!--서치영역 end-->

                    <!--컨텐츠영역-->
                    <div class="contents-container sort-row">
                        <div class="contents-box">
                            <table class="contents-table book" id="table_list">
                                <tr>
                                    <th>체크</th>
                                    <th>작가번호</th>
                                    <th>작가명</th>
                                    <th>연락처</th>
                                    <th>생년월일</th>
                                    <th>이메일</th>
                                    <th>주소</th>
                                    <th>은행명</th>
                                    <th>계좌번호</th>
                                    <th>수정</th>
                                </tr>
                                <c:if test="${ !empty writerList }">
	                                <c:forEach items="${ writerList }" var="writer">
		                                <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1" id="tr_${ writer.writerId }">
		                                    <td class="td-50">
		                                        <input type="checkbox" name="check" value="${ writer.writerId }" >
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="writerId" class="contents-input noline" value="${ writer.writerId }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-250">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="writerName" class="contents-input noline" value="${ writer.writerName }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="phone" class="contents-input noline changeable" value="${ writer.phone }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="writerBirth" class="contents-input noline changeable" value="${ writer.writerBirth }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="email" class="contents-input noline changeable" value="${ writer.email }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="address" class="contents-input noline changeable" value="${ writer.address }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-70">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="bank" class="contents-input noline changeable" value="${ writer.bank }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="account" class="contents-input noline changeable" value="${ writer.account }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-70">
		                                        <input type="button" class="contents-input-btn noline update-btn" value="수정" id="updateBtn_${ writer.writerId }" onclick="onUpdate(this); return false;">
		                                        <input type="button" class="contents-input-btn noline complete-btn" value="완료" id="completeBtn_${ writer.writerId }" onclick="submitUpdate(this, 'wtupdate.do'); return false;" style="display: none;">
		                                        <input type="button" class="contents-input-btn noline cancel-btn" value="취소" id="cancelBtn_${ writer.writerId }" onclick="cancelUpdate(this); return false;" style="display: none;">
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

                
                <div class="submit-box">
                    <input type="button" class="contents-input-btn big noline" id="btn_delete" value="선택삭제" onclick="deleteCheckedRow('wtdelete.do'); return false;">
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