<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="nowdays_" value="<%= java.util.Date.from(java.time.LocalDate.now().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()) %>" />
<fmt:formatDate var="nowdays" value="${ nowdays_ }" pattern="yyyy년 MM월 dd일" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script type="text/javascript">
    const NOWPAGE = 6;
    const SUBPAGE = 3;
    const LNKPAGE = 1;

    $(function(){
        $('.search-btn-appr').on('click', function(){
            return false;
        })
    })
    function inputSubmit(){
    	$('#appendTextArea').val($('.detail_area').text());
    }
    
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
                    <!--!!!form!!!-->
                    <form class="input-form" action="apinsert.do" method="post" onsubmit="inputSubmit();" enctype="multipart/form-data">
                        <input type="hidden" name="drafter" value="${ empId }">
                        <input type="hidden" name="draftType" value="annual">
                        <input type="hidden" name="depName" value="${ employee.depName }">
                        <input type="hidden" name="posName" value="${ employee.posName }">
                        <input type="hidden" name="drafterName" value="${ employee.empName }">
                    
                    <!--서치영역-->
                    <div class="search-container">
                        <div class="search-form">

                            <div class="select-box">
                                <div class="select-pan">
                                    <label for="sel_code"></label>
                                    <select name="code" id="sel_code">
                                        <option value="yoncha" selected>연차신청서</option>
                                    </select>
                                </div>

                            </div>
                            
                            <div class="input-box">
                                <div class="select-pan-nemo width-70">
                                    제목
                                </div>

                                <input type="text" name="title" class="input-box-input noline">
                            </div>

                            <div class="input-box">
                                <div class="select-pan-nemo width-70">
                                    결재라인
                                </div>

                                <div class="search-box-all">
                                    <div class="search-box">
                                        <button class="search-btn-appr">
                                            <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                        </button>
                                        <input type="text" placeholder="결재라인" class="search-box-text" value="">
                                    </div>
                                    <div class="search-box">
                                        <button class="search-btn-appr">
                                            <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                        </button>
                                        <input type="text" placeholder="결재자" class="search-box-text" value="">
                                    </div>
                                    <!--
                                    <div class="search-box">
                                        <button class="search-btn-appr">
                                            <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                        </button>
                                        <input type="text" placeholder="참조자" class="search-box-text" value="">
                                    </div>
                                    -->
                                </div>
                                
                            </div>
                            
                            <div class="input-box">
                                <div class="select-pan-nemo width-70">
                                    첨부
                                </div>

                                <input type="file" name="upfile" class="input-file">
                            </div>

                        </div>
                    </div>
                    <!--서치영역 end-->

                    <!--컨텐츠영역-->
                    <div class="contents-container sort-row">
                        <div class="contents-box aproval">
                            <div class="contents-title"><span>◆</span> 연차신청서</div>
                            <table class="contents-table aproval">
                                
                                <tbody>
                                    <tr>
                                        <td>소속</td>
                                        <td colspan="5" class="approval-td emphtext">${ employee.depName }</td>
                                    </tr>
                                    <tr>
                                        <td>성명</td>
                                        <td colspan="5" class="approval-td emphtext">${ employee.empName }</td>
                                    </tr>
                                    <tr>
                                        <td>직위</td>
                                        <td colspan="5" class="approval-td emphtext">${ employee.posName }</td>
                                    </tr>
                                    <tr>
                                        <td>종류</td>
                                        <td>연차( <input type="radio" name="detailType" value="annual" id="detailType_day" class="detail-radio"> )</td>
                                        <td>월차( <input type="radio" name="detailType" value="month" id="detailType_month" class="detail-radio"> )</td>
                                        <td>보건( <input type="radio" name="detailType" value="health" id="detailType_health" class="detail-radio"> )</td>
                                        <td>특별( <input type="radio" name="detailType" value="special" id="detailType_special" class="detail-radio"> )</td>
                                        <td>공가( <input type="radio" name="detailType" value="official" id="detailType_oficial" class="detail-radio"> )</td>
                                    </tr>
                                    <tr>
                                        <td>사유</td>
                                        <td colspan="5">
                                            <div class="detail_area" contenteditable="true">

                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>기간</td>
                                        <td colspan="5">
                                            시작날짜 <input type="date" name="startDate" class=""> &nbsp;&nbsp;&nbsp;~&nbsp;&nbsp;&nbsp; 
                                            종료날짜 <input type="date" name="endDate" class="">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>비상연락망</td>
                                        <td colspan="5"><input type="tel" name="emergency" class="detail_area detail_emergency" ></td>
                                    </tr>
                                    <tr class="height-200">
                                        <td colspan="6">
                                            <div class="table-empty-box">
                                                <div>
                                                    위와 같이 휴가를 신청하오니 승인하여 주시기 바랍니다.
                                                </div>
                                                
                                                <div>
                                                    ${ nowdays }
                                                </div>
                                                <div>
                                                    <b>소속 : ${ employee.depName }</b> 
                                                </div>
                                                <div>
                                                    <b>성명 : ${ employee.empName }</b>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                                
                            </table>
                        </div>
                        <textarea name="detail" id="appendTextArea"></textarea>
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