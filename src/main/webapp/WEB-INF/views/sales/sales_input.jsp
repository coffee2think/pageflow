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
    const SUBPAGE = 2;
    const LNKPAGE = 2;
    
    let curinput;
	let client_type = 'bookstore';
    
	function addRow(currentIndex) {
    	if($('#input_table').find('tr').length > 10) {
    		alert('더이상 추가할 수 없습니다.');
            return;
    	}
    	
    	// table element 찾기
		const table = document.getElementById('input_table');

		// 새 행(Row) 추가 (테이블 중간에)
		const newRow = table.insertRow(currentIndex + 1);

        // empId 전송용 input
        const input_empId = document.createElement('input');
        input_empId.setAttribute('type', 'hidden');
        input_empId.setAttribute('name', 'empId');
        input_empId.setAttribute('value', '${ loginMember.empId }');
        newRow.appendChild(input_empId);

		// 새 행(Row)에 Cell 추가
		var newCell = new Array(11);
		for(i = 0; i < newCell.length; i++) {
			newCell[i] = newRow.insertCell(i);
			// newCell[i].setAttribute('class', 'td-?');
		}
		
		// Cell에 텍스트 추가
		newCell[0].innerHTML = `
					<div class="contents-check-div">
			            <button class="contents-input-plus" onclick="addRow(` + (currentIndex + 1) + `); return false;">
			                <img src="${ pageContext.servletContext.contextPath }/resources/images/plus.png">
			            </button>
			            <button class="contents-input-minus" onclick="removeRow(` + (currentIndex + 1) + `); return false;">
			                <img src="${ pageContext.servletContext.contextPath }/resources/images/minus.png">
			            </button>
			        </div>
					`;
		
		newCell[1].innerHTML = `
					<div class="contents-input-div input-search">
			            <button class="input-search-btn">
			                <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
			            </button>
			            <input type="input" name="orderId" class="contents-input" value="">
			        </div>
					`;
		
		newCell[2].innerHTML = `
					<div class="contents-input-div input-search">
			            <button class="input-search-btn">
			                <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
			            </button>
			            <input type="input" name="bookId" class="contents-input" value="">
			        </div>
					`;
		
		newCell[3].innerHTML = `
					<div class="contents-input-div input-search">
			            <button class="input-search-btn">
			                <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
			            </button>
			            <input type="input" name="bookName" class="contents-input" value="">
			        </div>
					`;
		
		newCell[4].innerHTML = `
					<div class="contents-input-div input-search">
			            <button class="input-search-btn">
			                <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
			            </button>
			            <input type="input" name="clientName" class="contents-input" value="">
			        </div>
					`;
		
		newCell[5].innerHTML = `
					<div class="contents-input-div">
			            <input type="date" name="orderDate" class="select-date small">
			        </div>
					`;
		
		newCell[6].innerHTML = `
					<div class="contents-input-div">
			            <input type="number" name="bookPrice" class="contents-input">
			        </div>
					`;

		newCell[7].innerHTML = `
					<div class="contents-input-div">
			            <input type="number" name="orderQuantity" class="contents-input">
			        </div>
					`;
		
		newCell[8].innerHTML = `
					<div class="contents-input-div">
			            <input type="number" name="totalPrice" class="contents-input" value="">
			        </div>
					`;
		newCell[9].innerHTML = `
					<div class="contents-input-div">
			            <input type="number" name="collectedAmount" class="contents-input" value="">
			        </div>
					`;
		newCell[10].innerHTML = `
					<div class="contents-input-div">
			            <input type="number" name="balance" class="contents-input" value="">
			        </div>
					`;
		
		initRowIndex();
	}
    
    function removeRow(currentIndex) {
    	var trList = $('#input_table').find('tr');
    	if(trList.length > 2) {
	    	trList.eq(currentIndex).remove();
	    	initRowIndex();
    	}
    }
    
    // 행 추가 삭제를 위한 함수 인덱스 초기화
	function initRowIndex() {
		var table = document.getElementById('input_table');
		var rowList = table.rows;
		
		for (i = 1; i < rowList.length; i++) {
			var row = rowList[i];
			
			row.cells[0].innerHTML = '<div class="contents-check-div">'
				+ '<button class="contents-input-plus" onclick="addRow(' + ( i ) + '); return false;">'
				+ '<img src="${ pageContext.servletContext.contextPath }/resources/images/plus.png">'
				+ '</button>'
				+ '<button class="contents-input-minus" onclick="removeRow(' + ( i ) + '); return false;">'
				+ '<img src="${ pageContext.servletContext.contextPath }/resources/images/minus.png">'
				+ '</button>'
				+ '</div>';
		} //for
		
		// 팝업창 버튼 초기화 (함수 출처 : popup.jsp)
		initPopupBtn();
		
	} //initRowIndex
    
</script>
<title>판매 입력</title>
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
                <form class="input-form" action="ssinsert.do" method="post">
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
                                <table class="contents-table" id="input_table">
                                    <tr>
                                        <th class="td-50"></th>
                                        <th class="td-120">주문번호</th>
                                        <th class="td-120">도서코드</th>
                                        <th class="td-200">도서명</th>
                                        <th class="td-100">서점명</th>
                                        <th class="td-120">주문일시</th>
                                        <th class="td-100">정가</th>
                                        <th class="td-70">주문수량</th>
                                        <th class="td-120">금액</th>
                                        <th class="td-120">수금액</th>
                                        <th class="td-120">잔액</th>
                                    </tr>
                                    
                                    <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1">
                                        
                                        <td>
                                            <div class="contents-check-div">
                                                <button class="contents-input-plus" onclick="addRow(1); return false;">
                                                    <img src="${ pageContext.servletContext.contextPath }/resources/images/plus.png">
                                                </button>
                                                <button class="contents-input-minus" onclick="removeRow(1); return false;">
                                                    <img src="${ pageContext.servletContext.contextPath }/resources/images/minus.png">
                                                </button>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <input type="input" name="orderId" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td>
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <input type="input" name="bookId" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td>
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <input type="input" name="bookName" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td>
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <input type="input" name="clientName" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td>
                                            <div class="contents-input-div">
                                                <input type="date" name="orderDate" class="select-date small">
                                            </div>
                                        </td>
                                        <td>
                                            <div class="contents-input-div">
                                                <input type="number" name="bookPrice" class="contents-input">
                                            </div>
                                        </td>
                                        <td>
                                            <div class="contents-input-div">
                                                <input type="number" name="orderQuantity" class="contents-input">
                                            </div>
                                        </td>
                                        <td>
                                            <div class="contents-input-div">
                                                <input type="number" name="totalPrice" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td>
                                            <div class="contents-input-div">
                                                <input type="number" name="collectedAmount" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td>
                                            <div class="contents-input-div">
                                                <input type="number" name="balance" class="contents-input" value="">
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