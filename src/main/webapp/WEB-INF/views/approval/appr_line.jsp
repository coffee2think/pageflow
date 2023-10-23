<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="apprLineSaveList" value="${ requestScope.apprLineSaveList }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/popup.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/appr_line.js"></script>
<script type="text/javascript">
    const NOWPAGE = 6;
    const SUBPAGE = 4;
    const LNKPAGE = 1;
    function inputSubmit(){
        //체크된 것만 넘기기
    	e.preventDefault(); // Prevent the form from being submitted immediately

        // Get an array of selected values
        var checkedValues = $("input[name='check']:checked").map(function() {
          return $(this).closest("tr").find("input").val();
        }).get();

        // Add the selected items as a hidden input to the form
        var selectedItemsInput = $("<input>")
          .attr("type", "hidden")
          .attr("name", "selectedItems")
          .val(selectedItems.join(','));

        console.log('selectedItemsInput : ' + selectedItemsInput);

        $('.input-form').append(selectedItemsInput);

        $('.input-form').submit();
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

                <!--!!!form!!!-->
                <form class="input-form" action="alinsert.do" method="post" onsubmit="inputSubmit();">

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
                        
                        <div class="aproval-line-box">
                            <div class="contents-title small">
                                <span>◆</span> 
                                나의 결재라인
                            </div>
                            <div class="aproval-line-con">
                                <table class="contents-table aprovalline">
                                    <thead>
                                        <tr>
                                            <th>
                                                결재라인명칭
                                            </th>
                                            <th>
                                                결재자 1
                                            </th>
                                            <th>
                                                결재자 2
                                            </th>
                                            <th>
                                                결재자 3
                                            </th>
                                            <th>
                                                결재자 4
                                            </th>
                                            <th>
                                                삭제
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody class="my-approval-body">
                                        <c:forEach items="${ apprLineSaveList }" var="saveLine">
                                            <tr>
                                                
                                                <c:set var="lineName" value=""/>
                                                <c:set var="approver_1" value=""/>
                                                <c:set var="approver_2" value=""/>
                                                <c:set var="approver_3" value=""/>
                                                <c:set var="approver_4" value=""/>
                                                <c:if test="${ !empty saveLine and saveLine.size() > 0 }">
                                                    <c:forEach items="${ saveLine }" var="line" begin="0" end="${ saveLine.size() }" step="1" varStatus="i" >
                                                        
                                                        <input type="hidden" name="savelineId" value="${ line.savelineId }">
                                                        <c:if test="${ i.index eq 0 }">
                                                            <c:set var="approver_1" value="${ line.approverName }"/>
                                                            <c:set var="lineName" value="${ line.lineName }"/>
                                                        </c:if>
                                                        
                                                        <c:if test="${ i.index eq 1 }">
                                                            <c:set var="approver_2" value="${ line.approverName }"/>
                                                        </c:if>

                                                        <c:if test="${ i.index eq 2 }">
                                                            <c:set var="approver_3" value="${ line.approverName }"/>
                                                        </c:if>

                                                        <c:if test="${ i.index eq 3 }">
                                                            <c:set var="approver_4" value="${ line.approverName }"/>
                                                        </c:if>
                                                        
                                                    </c:forEach>
                                                </c:if>
                                                <td class="td-150">
                                                    ${ lineName }
                                                </td>
                                                <td class="td-100">
                                                    ${ approver_1 }
                                                </td>
                                                <td class="td-100">
                                                    ${ approver_2 }
                                                </td>
                                                <td class="td-100">
                                                    ${ approver_3 }
                                                </td>
                                                <td class="td-100">
                                                    ${ approver_4 }
                                                </td>
                                                <td class="td-100">
                                                    <input type="button" name="delete" class="contents-input-btn noline" value="삭제">
                                                </td>
                                            </tr>
                                        </c:forEach>
                                            
                                        
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <!--서치영역-->
                        <div class="search-container padding-bottom-none boder-none">
                            <div class="search-form">
                                
                                <div class="input-box">
                                    <div class="select-pan-nemo width-100">
                                        결재라인명칭
                                    </div>

                                    <input type="text" class="input-box-input noline">
                                </div>
                                <!--
                                <div class="input-box">
                                    <div class="select-pan-nemo width-100">
                                        참조자
                                    </div>

                                    <div class="search-box-all">
                                        <div class="search-box">
                                            <button class="search-btn">
                                                <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                            </button>
                                            <input type="text" placeholder="참조자" class="search-box-text" value="">
                                        </div>
                                    </div>
                                </div>
                                -->
                            </div>
                        </div>
                        <!--서치영역 end-->

                        <!--컨텐츠영역-->
                        <div class="contents-container sort-row">
                            <div class="contents-box aprovalline">
                                <!--테이블 1-->
                                <table class="contents-table aprovalline">
                                    <tbody class="my-line-body">
                                        <tr>
                                            <th class="td-50">
                                                
                                            </th>
                                            <th class="td-50">
                                                체크
                                            </th>
                                            <th class="td-150">
                                                결재자
                                            </th>
                                            <th class="td-100">
                                                직책
                                            </th>
                                            <th class="td-100">
                                                부서
                                            </th>
                                            <th class="td-50">
                                                순서
                                            </th>
                                        </tr>

                                        <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1" id="mySaveLine_1">
                                            <td>
                                                <div class="contents-check-div">
                                                    <button class="contents-input-plus" onclick="makePlusSaveLine(); return false;">
                                                        <img src="${ pageContext.servletContext.contextPath }/resources/images/plus.png">
                                                    </button>
                                                    <input type="hidden" name="empId" value="">
                                                </div>
                                            </td>
                                            <td>
                                                <div class="contents-check-div">
                                                    <input type="checkbox" name="check" value="">
                                                </div>
                                            </td>
                                            <td>
                                                <div class="contents-input-div input-search">
                                                    <button class="input-search-btn">
                                                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                    </button>
                                                    <input type="input" name="empName" class="contents-input" value="">
                                                    
                                                </div>
                                            </td>
                                            <td>
                                                <div class="contents-input-div input-readonly">
                                                    <input type="input" name="posName" class="contents-input" value="" readonly>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="contents-input-div input-readonly">
                                                    <input type="input" name="depName" class="contents-input" value="" readonly>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="contents-input-div">
                                                    <input type="input" name="order" class="contents-input" value="1">
                                                </div>
                                            </td>
                                        </tr>
                                        <!--
                                        <tr data-parent="1" data-num="2" data-depth="1" class="table-td-depth1" id="mySaveLine_2">
                                            <td>
                                                <div class="contents-check-div">
                                                    <button class="contents-input-plus" onclick="makePlusSaveLine(); return false;">
                                                        <img src="${ pageContext.servletContext.contextPath }/resources/images/plus.png">
                                                    </button>
                                                    <button class="contents-input-minus" onclick="makeMinusSaveLine(); return false;">
                                                        <img src="${ pageContext.servletContext.contextPath }/resources/images/minus.png">
                                                    </button>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="contents-check-div">
                                                    <input type="checkbox" name="check" value="">
                                                </div>
                                            </td>
                                            <td>
                                                <div class="contents-input-div input-search">
                                                    <button class="input-search-btn">
                                                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                    </button>
                                                    <input type="input" name="approver" class="contents-input" value="">
                                                </div>
                                            </td>
                                            <td>
                                                <div class="contents-input-div input-readonly">
                                                    <input type="input" name="position" class="contents-input" value="" readonly>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="contents-input-div input-readonly">
                                                    <input type="input" name="category" class="contents-input" value="" readonly>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="contents-input-div">
                                                    <input type="input" name="order" class="contents-input" value="2">
                                                </div>
                                            </td>
                                        </tr>
                                        -->
                                    </tbody>
                                    
                                </table>
                                <!--테이블 1end-->

                            </div>
                        </div>
                        <!--컨텐츠영역 end-->
                        
                    </div>
                    <!--내용 end-->

                    <div class="submit-box">
                        <input type="submit" class="contents-input-btn big2 noline" id="btn_save" value="나의결재라인 추가">
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