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
    const SUBPAGE = 1;
    const LNKPAGE = 1;
    
    $(function() {
    	const searchType = '${ searchType }';
    	const selectBox = $('#search_type');
    	const len = selectBox.options.length;
    	
    	for(i = 0; i < len; i++) {
    		if(selectBox.options[i].value == searchType){
    			selectBox.options[i].selected = true;
    	    }
    	}
    }); // document ready
    
 	// 수정 버튼 클릭 시 수정 가능 상태로 변경
    function onUpdate(tradeId) {
    	// 행 버튼 보이기/숨기기 상태 변경
    	$('#completeBtn_' + tradeId).show();
    	$('#cancelBtn_' + tradeId).show();
    	$('#updateBtn_' + tradeId).hide();
    	
    	// 정보 임시 보관
    	var currentRow = $('#updateBtn_' + tradeId).parent().parent();
    	
    	currentRow.children('td').each(function(index) {
    		if(index <= 1 || index >= 13) {
    			return; // 체크박스와 수정버튼 열의 정보는 건너뜀
    		}
    		
    		console.log(index);
    		console.log($(this).find('input').val());
    	});
    	
    	// 수정 중인 행의 스타일 변경
    	$('#tr_' + tradeId).css('border', 'solid 3px #1a70d3');
    	
    	// class=changeable인 input 태그 값 변경 가능하도록 변경
    	$('#tr_' + tradeId + ' .changeable').attr('readonly', false);
    }
    
    // 취소 버튼 클릭 시 수정 가능 상태로 변경
    function cancelUpdate(tradeId) {
    	// 같은 주문번호의 행들을 모두 선택
    	
    	// 원래 정보로 되돌리기(reset)
    	
    	
    	// 수정 취소한 행의 스타일 원래대로 설정
    	$('#tr_' + tradeId).css('border', 'none');
    	
    	// 행 버튼 보이기/숨기기 상태 변경
    	$('#completeBtn_' + tradeId).hide();
    	$('#cancelBtn_' + tradeId).hide();
    	$('#updateBtn_' + tradeId).show();
    	
    	// 행의 모든 셀 readonly
    	$('#tr_' + tradeId + ' .changeable').attr('readonly', true);
    }
    
    function submitUpdate(tradeId) {
    	var currentRow = $('#updateBtn_' + tradeId).parent().parent();
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
    		url: "boupdate.do",
    		type: "post",
    		data: json,
    		success: function(data){
    			console.log("success : " + data);
    			if(data == "success") {
    				alert(tradeId + " 주문 정보 수정 성공");
    			} else {
    				alert("주문 정보 수정 실패");
    			}
    			
    			// 행 버튼 보이기/숨기기 상태 변경
    	    	$('#completeBtn_' + tradeId).hide();
    	    	$('#cancelBtn_' + tradeId).hide();
    	    	$('#updateBtn_' + tradeId).show();
    	    	
    	    	// 수정 취소한 행의 스타일 원래대로 설정
    	    	$('#tr_' + tradeId).css('border', 'none');
    	    	
    	    	// 행 버튼 보이기/숨기기 상태 변경
    	    	$('#completeBtn_' + tradeId).hide();
    	    	$('#cancelBtn_' + tradeId).hide();
    	    	$('#updateBtn_' + tradeId).show();
    	    	
    	    	// 행의 모든 셀 readonly
    	    	$('#tr_' + tradeId + ' .changeable').attr('readonly', true);
    			
    		},
    		error: function(jqXHR, textStatus, errorThrown){
    			console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
    		}
    	});
    }
    
    function deleteCheckedRow() {
    	// form 태그에 담아서 post 전송
    	const form = document.createElement('form'); // form 태그 생성
    	const url = 'bodelete.do';
        form.setAttribute('method', 'post'); // 전송 방식 결정 (get or post)
        form.setAttribute('action', url); // 전송할 url 지정
    	
    	$('#table_list').find('input[type="checkbox"]:checked').each(function() {
    	    const tradeId = $(this).val();
    		
    	    // input 태그 생성하여 데이터 담기
    	    const data = document.createElement('input');
        	data.setAttribute('type', 'hidden');
            data.setAttribute('name', 'tradeIDs'); // 데이터의 name
            data.setAttribute('value', tradeId); // 데이터의 value
            
         	// form 태그에 input 태그 넣기 
            form.appendChild(data);
    	});
    	
        // body에 form 태그 추가하고 submit 전송
        document.body.appendChild(form);
        form.submit();
    }
    
    function search() {
    	var search_type = $('#search_type').val();
    	var keyword = $('input[type=search]').val();
    	
    	if(keyword == '') {
    		alert('키워드를 입력해주세요.');
    		return false;
    	}
    	
    	// form 태그에 담아서 post 전송
    	const form = document.createElement('form'); // form 태그 생성
    	const url = 'bolistkw.do';
        form.setAttribute('method', 'post'); // 전송 방식 결정 (get or post)
        form.setAttribute('action', url); // 전송할 url 지정
    	
        const data1 = document.createElement('input');
        data1.setAttribute('type', 'hidden');
        data1.setAttribute('name', 'searchType'); // 데이터의 name
        data1.setAttribute('value', search_type); // 데이터의 value
        form.appendChild(data1);
        
        const data2 = document.createElement('input');
        data2.setAttribute('type', 'hidden');
        data2.setAttribute('name', 'keyword'); // 데이터의 name
        data2.setAttribute('value', keyword); // 데이터의 value
        form.appendChild(data2);
        
        // body에 form 태그 추가하고 submit 전송
        document.body.appendChild(form);
        form.submit();
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

                    <!--서치영역-->
                    <div class="search-container">
                        <form class="search-form">
                            <div class="select-search">
                                <div class="select-box">
                                    <div class="select-pan">
                                        <label for="sel_code"></label>
                                        <select name="searchType" id="search_type">
                                            <option value="book">도서명</option>
                                            <option value="bookStore">서점명</option>
                                            <option value="location">지역</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="search-box">
                                    <input type="search" placeholder="키워드를 입력하세요." class="search-box-text" value="${ keyword }" name="keyword">
                                    <button class="search-btn" onclick="search(); return false;">
                                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                    </button>
                                </div>
                            </div>

                            <div class="select-box">
                                <div class="select-pan">
                                    <label for="sel_code"></label>
                                    <select name="code" id="sel_code">
                                        <option value="all">상태</option>
                                        <option value="">주문접수</option>
                                        <option value="">입고요청</option>
                                        <option value="">출고대기</option>
                                    </select>
                                </div>
                            </div>

                            <div class="select-box">
                                <div class="select-pan-nemo">
                                    기간
                                </div>

                                <input type="date" class="select-date select-date-first" name="begin" value=${ begin }>
                                <input type="date" class="select-date select-date-second" name="end" value=${ end }>
								
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
                            <table class="contents-table" id="table_list">
                                <tr>
                                    <th>체크</th>
                                    <th>주문번호</th>
                                    <th>도서코드</th>
                                    <th>도서명</th>
                                    <th>지역</th>
                                    <th>서점명</th>
                                    <th>정가</th>
                                    <th>주문수량</th>
                                    <th>금액</th>
                                    <th>상태</th>
                                    <th>주문일시</th>
                                    <th>수정</th>
                                </tr>
                                <c:if test="${ !empty list }">
	                                <c:forEach items="${ list }" var="bookOrder" >
		                                <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1" id="tr_${ bookOrder.tradeId }">
		                                    <td class="td-50">
		                                        <input type="checkbox" name="check" value="" >
		                                    </td>
		                                    <td class="td-120">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="tradeId" id="tradeId-${ bookOrder.tradeId }" class="contents-input noline" value="${ bookOrder.tradeId }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="bookId" id="bookId-${ bookOrder.tradeId }" class="contents-input noline" value="${ bookOrder.bookId }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-200">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="bookName" id="bookName-${ bookOrder.tradeId }" class="contents-input noline" value="${ bookOrder.bookName }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="location" id="location-${ bookOrder.tradeId }" class="contents-input noline" value="강남" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="bookStoreName" id="bookStoreName-${ bookOrder.tradeId }" class="contents-input noline" value="${ bookOrder.bookStoreName }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-70">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="bookPrice" id="bookPrice-${ bookOrder.tradeId }" class="contents-input noline" value="${ bookOrder.bookPrice }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="orderQuantity" id="orderQuantity-${ bookOrder.tradeId }" class="contents-input noline" value="${ bookOrder.orderQuantity }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-120">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="totalPrice" id="totalPrice-${ bookOrder.tradeId }" class="contents-input noline" value="${ bookOrder.totalPrice }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-70">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="state" id="state-${ bookOrder.tradeId }" class="contents-input noline" value="${ bookOrder.state }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="orderDate" id="orderDate-${ bookOrder.tradeId }" class="contents-input noline" value="${ bookOrder.orderDate }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-70">
		                                        <input type="button" class="contents-input-btn noline" value="수정" id="updateBtn_${ bookOrder.tradeId }" onclick="onUpdate(${ bookOrder.tradeId }); return false;">
		                                        <input type="button" class="contents-input-btn noline" value="완료" id="completeBtn_${ bookOrder.tradeId }"  onclick="submitUpdate(${ bookOrder.tradeId }); return false;" style="display: none;">
		                                        <input type="button" class="contents-input-btn noline" value="취소" id="cancelBtn_${ bookOrder.tradeId }"  onclick="cancelUpdate(${ bookOrder.tradeId }); return false;" style="display: none;">
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