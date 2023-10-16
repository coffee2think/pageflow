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
<script>
    const NOWPAGE = 5;
    const SUBPAGE = 3;
    const LNKPAGE = 1;
    
    var clientInfo;
    
	// 수정 버튼 클릭 시 수정 가능 상태로 변경
    function onUpdate(clientId) {
    	// 행 버튼 보이기/숨기기 상태 변경
    	$('#completeBtn_' + clientId).show();
    	$('#cancelBtn_' + clientId).show();
    	$('#updateBtn_' + clientId).hide();
    	
    	// 정보 임시 보관
    	var currentRow = $('#updateBtn_' + clientId).parent().parent();
    	console.log(currentRow);
    	
    	currentRow.children('td').each(function(index) {
    		if(index <= 1 || index >= 13) {
    			return; // 체크박스와 수정버튼 열의 정보는 건너뜀
    		}
    		
    		console.log(index);
    		console.log($(this).find('input').val());
    	});
    	
    	// 수정 중인 행의 스타일 변경
    	$('#tr_' + clientId).css('border', 'solid 3px #1a70d3');
    	
    	// class=changeable인 input 태그 값 변경 가능하도록 변경
    	$('#tr_' + clientId + ' .changeable').attr('readonly', false);
    }
    
    // 취소 버튼 클릭 시 수정 가능 상태로 변경
    function cancelUpdate(clientId) {
    	// 같은 주문번호의 행들을 모두 선택
    	
    	// 원래 정보로 되돌리기(reset)
    	
    	
    	// 수정 취소한 행의 스타일 원래대로 설정
    	$('#tr_' + clientId).css('border', 'none');
    	
    	// 행 버튼 보이기/숨기기 상태 변경
    	$('#completeBtn_' + clientId).hide();
    	$('#cancelBtn_' + clientId).hide();
    	$('#updateBtn_' + clientId).show();
    	
    	// 행의 모든 셀 readonly
    	$('#tr_' + clientId + ' .changeable').attr('readonly', true);
    }
    
    function submitUpdate(clientId) {
    	var currentRow = $('#updateBtn_' + clientId).parent().parent();
    	var json = {};
    	
    	currentRow.find('input').each(function(index) {
    		if(index == 0 || index >= 13){
    	        return;
    	    }
    		
    		// 유효성 검사
    		// endDate가 비었을 경우 json에 담지 않음
    		if(index == 12 && $(this).val() == '') {
    	        return;
    	    }

    		// input 태그을 이용하여 name:value로 json에 담기
    	    json[$(this).attr('name')] = $(this).val();
    	});
    	
    	// ajax로 update 요청 보내기
    	$.ajax({
    		url: "clupdate.do",
    		type: "post",
    		data: json,
    		success: function(data){
    			console.log("success : " + data);
    			if(data == "success") {
    				alert(clientId + " 거래처 정보 수정 성공");
    			} else {
    				alert("거래처 정보 수정 실패");
    			}
    			
    			// 행 버튼 보이기/숨기기 상태 변경
    	    	$('#completeBtn_' + clientId).hide();
    	    	$('#cancelBtn_' + clientId).hide();
    	    	$('#updateBtn_' + clientId).show();
    	    	
    	    	// 수정 취소한 행의 스타일 원래대로 설정
    	    	$('#tr_' + clientId).css('border', 'none');
    	    	
    	    	// 행 버튼 보이기/숨기기 상태 변경
    	    	$('#completeBtn_' + clientId).hide();
    	    	$('#cancelBtn_' + clientId).hide();
    	    	$('#updateBtn_' + clientId).show();
    	    	
    	    	// 행의 모든 셀 readonly
    	    	$('#tr_' + clientId + ' .changeable').attr('readonly', true);
    			
    		},
    		error: function(jqXHR, textStatus, errorThrown){
    			console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
    		}
    	});
    }
    
    function deleteCheckedRow() {
    	// form 태그에 담아서 post 전송
    	const form = document.createElement('form'); // form 태그 생성
    	const url = 'cldelete.do';
        form.setAttribute('method', 'post'); // 전송 방식 결정 (get or post)
        form.setAttribute('action', url); // 전송할 url 지정
    	
    	$('#client_table').find('input[type="checkbox"]:checked').each(function() {
    	    const clientId = $(this).val();
    		
    	    // input 태그 생성하여 데이터 담기
    	    const data = document.createElement('input');
        	data.setAttribute('type', 'hidden');
            data.setAttribute('name', 'clientIDs'); // 데이터의 name
            data.setAttribute('value', clientId); // 데이터의 value
            
         	// form 태그에 input 태그 넣기 
            form.appendChild(data);
    	});
    	
        // body에 form 태그 추가하고 submit 전송
        document.body.appendChild(form);
        form.submit();
    }
    
</script>
<title>거래처 현황</title>
</head>
<body>
	<c:set var="page" value="1" />
	<c:if test="${ !empty currentPage }">
		<c:set var="page" value="${ currentPage }" />
	</c:if>
	<c:set var="limit" value="10" />
	<c:if test="${ !empty limit }">
		<c:set var="limit" value="${ limit }" />
	</c:if>
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
                <div class="main-contents-box normal">

                    <!--서치영역-->
                    <div class="search-container">
                        <form class="search-form">
                        	<input type="hidden" name="page" value="${ currentPage }">
                        	<input type="hidden" name="limit" value="${ limit }">
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
                                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                    </button>
                                    <input type="text" placeholder="키워드를 입력하세요." class="search-box-text" value="">
                                </div>
                            </div>

                            <div class="select-box">
                                <div class="select-pan">
                                    <label for="client_type"></label>
                                    <select name="code" id="client_type">
                                        <option value="all">분류별</option>
                                        <option value="bookStore">서점</option>
                                        <option value="printOffice">인쇄소</option>
                                        <option value="storage">창고</option>
                                    </select>
                                </div>
                            </div>

                            <div class="select-box">
                                <div class="select-pan-nemo">
                                    기간
                                </div>

                                <input type="date" class="select-date select-date-first">
                                <input type="date" class="select-date select-date-second">

                                <c:set var="today_" value="<%= new java.util.Date() %>" />
								<fmt:formatDate var="today" value="${ today_ }" pattern="yyyy-MM-dd" />
								<c:set var="weekago_" value="<%= new java.util.Date(new java.util.Date().getTime() - 60*60*24*1000*6) %>" />
								<fmt:formatDate var="weekago" value="${ weekago_ }" pattern="yyyy-MM-dd" />
								<c:set var="monthago_" value="<%= new java.util.Date(new java.util.Date().getTime() - 60*60*24*1000*30) %>" />
								<fmt:formatDate var="monthago" value="${ monthago_ }" pattern="yyyy-MM-dd" />
								
								<c:url var="searchWeekUrl" value="bolistdate.do">
									<c:param name="begin" value="${ weekago }" />
									<c:param name="end" value="${ today }" />
								</c:url>
								
								<c:url var="searchMonthUrl" value="bolistdate.do">
									<c:param name="begin" value="${ monthago }" />
									<c:param name="end" value="${ today }" />
								</c:url>
                                
                                <input type="button" name="week" class="select-pan-btn" value="일주일" onclick="javascript: location.href='${ searchWeekUrl }'">
                                <input type="button" name="month" class="select-pan-btn" value="한달" onclick="javascript: location.href='${ searchMonthUrl }'">
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
                            <table class="contents-table" id="client_table">
                                <tr>
                                    <th>체크</th>
                                    <th>거래처코드</th>
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
                                    <th>거래종료일</th>
                                    <th>수정</th>
                                </tr>
                                <c:if test="${ !empty list }">
	                                <c:forEach items="${ list }" var="client">
		                                <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1" id="tr_${ client.clientId }">
		                                    <td class="td-50">
		                                        <input type="checkbox" name="check" value="${ client.clientId }" >
		                                    </td>
		                                    <td class="td-70">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="clientId" class="contents-input noline" value="${ client.clientId }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="category" class="contents-input noline" value="${ client.category }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="clientName" class="contents-input noline changeable" value="${ client.clientName }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-150">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="eid" class="contents-input noline" value="${ client.eid }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-300">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="clientAddress" class="contents-input noline changeable" value="${ client.clientAddress }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-150">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="clientContact" class="contents-input noline changeable" value="${ client.clientContact }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-150">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="clientUrl" class="contents-input noline changeable" value="${ client.clientUrl }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="manager" class="contents-input noline changeable" value="${ client.manager }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-150">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="managerContact" class="contents-input noline changeable" value="${ client.managerContact }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-150">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="managerEmail" class="contents-input noline changeable" value="${ client.managerEmail }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="startDate" class="contents-input noline changeable" value="${ client.startDate }" required readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="endDate" class="contents-input noline changeable" value="${ client.endDate }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <input type="button" class="contents-input-btn noline" value="수정" id="updateBtn_${ client.clientId }" onclick="onUpdate(${ client.clientId }); return false;">
		                                        <input type="button" class="contents-input-btn noline" value="완료" id="completeBtn_${ client.clientId }"  onclick="submitUpdate(${ client.clientId }); return false;" style="display: none;">
		                                        <input type="button" class="contents-input-btn noline" value="취소" id="cancelBtn_${ client.clientId }"  onclick="cancelUpdate(${ client.clientId }); return false;" style="display: none;">
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
                    <input type="button" class="contents-input-btn big noline" id="btn_delete" value="선택삭제" onclick="deleteCheckedRow(); return false;">
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