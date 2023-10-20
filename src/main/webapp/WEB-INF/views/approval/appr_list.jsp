<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="searchKewordType" value=""/>
<c:if test="${ !empty searchType }">
    <c:set var="searchKewordType" value="${ searchType }"/>
</c:if>
<c:set var="apType" value="${ requestScope.apType }"  scope="session" />
<c:set var="empId" value="${ requestScope.empId }"  scope="session" />
<c:set var="approvalList" value="${ requestScope.approvalList }"  scope="session" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/appr_list.js"></script>
<script type="text/javascript">
    
    const appr_url = new URL(window.location.href);
    const urlParams = appr_url.searchParams;
    
    const appr_param = urlParams.get('apType');
    console.log('appr_param : ' + appr_param);
    
    const NOWPAGE = 6;
    const SUBPAGE = (appr_param == 'all') ? 2 : 1;
    const LNKPAGE = (appr_param == 'my') ? 1 : 2;
    console.log('SUBPAGE : ' + SUBPAGE + ' LNKPAGE :  ' + LNKPAGE);
    const searchType = '<c:out value="${ searchKewordType }" />'
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
                        <img src="${ pageContext.servletContext.contextPath }/resources/images/header-icon.png">
                        <span class="main-title"></span>
                    </div>
                    <button class="header-left-btn">
                    </button>
                </div>
                <!--main-header-bar end-->

                <!--내용-->
                <div class="main-contents-box">
                    <div class="main-content">
                        
                        <!--서치영역-->
                        <div class="search-container">
                            <div class="search-form">
                                <c:import url="./appr_search.jsp" />
                            </div>

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
                                <table class="contents-table">
                                    <tr>
                                        <th>체크</th>
                                        <th>기안번호</th>
                                        <th>기안일자</th>
                                        <th>제목</th>
                                        <th>구분</th>
                                        <th>기안자</th>
                                        <th>결재자</th>
                                        <th>진행상태</th>
                                        <th>결재</th>
                                        <th>수정</th>
                                    </tr>

                                    <c:if test="${ !empty approvalList }">
                                        <c:forEach items="${ approvalList }" var="appr">
                                            <tr class="table-td-depth1 table-apprList" id="tr_${ appr.apprId }">
                                                <td class="td-50">
                                                    <input type="checkbox" name="check" value="" >
                                                </td>
                                                <td class="td-100">
                                                    <div class="contents-input-div">
                                                        <input type="input" name="no" class="contents-input noline" value="${ appr.apprId }" readonly>
                                                    </div>
                                                </td>
                                                <td class="td-120">
                                                    <div class="contents-input-div">
                                                        <input type="input" name="date" class="contents-input noline" value="${ appr.apprDate }" readonly>
                                                    </div>
                                                </td>
                                                <td class="td-250">
                                                    <div class="contents-input-div">
                                                        <c:set var="draftTitle" value="연차 결재 부탁드립니다."/>
                                                        <c:if test="${ !empty appr.title }">
                                                            <c:set var="draftTitle" value="연차"/>
                                                        </c:if>

                                                        <input type="input" name="title" class="contents-input noline changeable" value="${ draftTitle }" readonly>
                                                    </div>
                                                </td>
                                                <td class="td-100">
                                                    <div class="contents-input-div">
                                                        <c:set var="draftTypeStr" value="연차"/>
                                                        <c:if test="${ !empty appr.draftType }">
                                                            <c:set var="draftTypeStr" value="연차"/>
                                                        </c:if>

                                                        <input type="input" name="sort" class="contents-input noline" value="${ draftTypeStr }" readonly>
                                                    </div>
                                                </td>
                                                <td class="td-100">
                                                    <div class="contents-input-div">
                                                        <input type="input" name="drafter" class="contents-input noline" value="${ appr.drafterName }" readonly>
                                                    </div>
                                                </td>
                                                <td class="td-100">
                                                    <div class="contents-input-div">
                                                        <input type="input" name="approver" class="contents-input noline changeable" value="${ appr.approverName }" readonly>
                                                    </div>
                                                </td>
                                                <td class="td-100">
                                                    <div class="contents-input-div">
                                                        <c:set var="progress" value="진행중"/>
                                                        <c:if test="${ appr.apprState == 'complete' }">
                                                            <c:set var="progress" value="결재완료"/>
                                                        </c:if>
                                                        <c:if test="${ appr.apprState == 'companion' }">
                                                            <c:set var="progress" value="반려"/>
                                                        </c:if>

                                                        <input type="input" name="progress" class="contents-input noline" value="${ progress }" readonly>
                                                    </div>
                                                </td>
                                                <td class="td-100">
                                                    <input type="button" class="contents-input-link search-pop-draft" data-id="${ appr.apprId }" value="결재"/>
                                                </td>
                                                
                                                <td class="td-100">
                                                    <c:if test="${ empId == appr.drafter }">
                                                        <input type="button" class="contents-input-btn noline" value="수정" id="updateBtn_${ appr.apprId }" onclick="onUpdate(${ appr.apprId }); return false;">
                                                        <input type="button" class="contents-input-btn noline" value="완료" id="completeBtn_${ appr.apprId }" onclick="submitUpdate(${ appr.apprId }, 'apupdate.do?apType='+appr_param); return false;" style="display: none;">
                                                        <input type="button" class="contents-input-btn noline" value="취소" id="cancelBtn_${ appr.apprId }" onclick="cancelUpdate(${ appr.apprId }); return false;" style="display: none;">
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </table>
                            </div>
                        </div>
                        <!--컨텐츠영역 end-->
                    </div>
                </div>
                <!--내용 end-->
                <div class="submit-box">
                    <input type="submit" class="contents-input-btn big noline" id="btn_delete" value="선택삭제" onclick="deleteCheckedRow('apdelete.do'); return false;">
                </div>

            </div>
            <!--main-container end-->

            <!--modal-pop-area-->
            <div class="modal-pop-area" id="approval_pop_area">
                <!-- 팝업 들어감 -->
                <c:import url="./appr_popup.jsp" />
            </div>
            <!--modal-pop-area end-->

        </main>
        
    </div>
</body>
</html>