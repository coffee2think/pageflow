<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="rowNum" value="1" />

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
    const LNKPAGE = 2;
    
    function addRow(currentIndex) {
		// table element 찾기
		const table = document.getElementById('input_table');

		// 새 행(Row) 추가 (테이블 중간에)
		const newRow = table.insertRow(currentIndex + 1);

		// 새 행(Row)에 Cell 추가
		const newCell1 = newRow.insertCell(0);
		newCell1.setAttribute('class', 'td-50');
		const newCell2 = newRow.insertCell(1);
		newCell2.setAttribute('class', 'td-120');
		const newCell3 = newRow.insertCell(2);
		newCell3.setAttribute('class', 'td-200');
		const newCell4 = newRow.insertCell(3);
		newCell4.setAttribute('class', 'td-100');
		const newCell5 = newRow.insertCell(4);
		newCell5.setAttribute('class', 'td-100');
		const newCell6 = newRow.insertCell(5);
		newCell6.setAttribute('class', 'td-70');
		const newCell7 = newRow.insertCell(6);
		newCell7.setAttribute('class', 'td-120');
		const newCell8 = newRow.insertCell(7);
		newCell8.setAttribute('class', 'td-120');

		// Cell에 텍스트 추가
		newCell1.innerHTML = '<div class="contents-check-div">'
						+ '<button class="contents-input-plus" onclick="addRow(' + ( currentIndex + 1 ) + '); return false;">'
						+ '<img src="${ pageContext.servletContext.contextPath }/resources/images/plus.png">'
						+ '</button>'
						+ '<button class="contents-input-minus" onclick="removeRow(' + ( currentIndex + 1 ) + '); return false;">'
						+ '<img src="${ pageContext.servletContext.contextPath }/resources/images/minus.png">'
						+ '</button>'
						+ '</div>';
		
		newCell2.innerHTML = '<div class="contents-input-div input-search">'
						+ '<button class="input-search-btn">'
						+ '<img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">'
						+ '</button>'
						+ '<input type="input" name="bookId" class="contents-input" value="">'
						+ '</div>';
		
		newCell3.innerHTML = '<div class="contents-input-div input-search">'
						+ '<button class="input-search-btn">'
						+ '<img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">'
						+ '</button>'
						+ '<input type="input" name="bookName" class="contents-input" value="">'
						+ '</div>';
		
		newCell4.innerHTML = '<div class="contents-input-div input-search">'
						+ '<button class="input-search-btn">'
						+ '<img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">'
						+ '</button>'
						+ '<input type="input" name="clientId" class="contents-input" value="">'
						+ '<input type="input" name="clientName" class="contents-input" value="">'
						+ '</div>';
		newCell5.innerHTML = '<div class="contents-input-div">'
						+ '<input type="number" name="bookPrice" class="contents-input">'
						+ '</div>';
		
		newCell6.innerHTML = '<div class="contents-input-div">'
						+ '<input type="number" name="orderQuantity" class="contents-input">'
						+ '</div>';
		
		newCell7.innerHTML = '<div class="contents-input-div">'
						+ '<input type="number" name="totalPrice" class="contents-input" value="">'
						+ '</div>';

		newCell8.innerHTML = '<div class="contents-input-div">'
						+ '<input type="input" name="state" class="contents-input" value="">'
						+ '</div>';
		
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
				+ '<button class="contents-input-plus" onclick="addRow(' + i + '); return false;">'
				+ '<img src="${ pageContext.servletContext.contextPath }/resources/images/plus.png">'
				+ '</button> '
				+ '<button class="contents-input-minus" onclick="removeRow(' + i + '); return false;">'
				+ '<img src="${ pageContext.servletContext.contextPath }/resources/images/minus.png">'
				+ '</button>'
				+ '</div>';
		} //for
		
	} //initRowIndex
	 
	function calcTotalPrice(currentIndex) {
		const table = document.getElementById('input_table');
		
		var row = table.rows[currentIndex];
		var bookPrice = row.cells[4].children[0].children[0].value;
		var orderQuantity = row.cells[5].children[0].children[0].value;
		
		var totalPrice = row.cells[6];
		totalPrice.children[0].children[0].value = bookPrice * orderQuantity;
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

                <!--form-->
                <!-- <form class="input-form" action="/comi/partyi" method="post" enctype="multipart/form-data">-->
                <form class="input-form" action="boinsert.do" method="post">
                	<%-- <input type="hidden" name="empId" value="${ loginMember.empId }"> --%>
                	<input type="hidden" name="empId" value="1"> <!-- 하드코딩 -->
                	<%-- <input type="hidden" name="empName" value="${ loginMember.empName }"> --%>
                	<input type="hidden" name="empName" value="홍길동"> <!-- 하드코딩 -->
                	
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
                                <table class="contents-table" id="input_table" border="1">
                                    <tr>
                                        <th></th>
                                        <th>도서코드</th>
                                        <th>도서명</th>
                                        <th>서점명</th>
                                        <th>정가</th>
                                        <th>주문수량</th>
                                        <th>금액</th>
                                        <th>상태</th>
                                    </tr>
                                    <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1">
                                        <td class="td-50">
                                            <div class="contents-check-div">
                                                <button class="contents-input-plus" onclick="addRow(1); return false;">
                                                    <img src="${ pageContext.servletContext.contextPath }/resources/images/plus.png">
                                                </button>
                                                <button class="contents-input-minus" onclick="removeRow(1); return false;">
                                                    <img src="${ pageContext.servletContext.contextPath }/resources/images/minus.png">
                                                </button>
                                            </div>
                                        </td>
                                        <td class="td-120">
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <input type="input" name="bookId" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td class="td-200">
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <input type="input" name="bookName" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td class="td-100">
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <!-- <input type="hidden" name="clientId" value=""> -->
                                                <input type="hidden" name="clientId" value="1"> <!-- 하드코딩(팝업창에서 정보를 가져와야함) -->
                                                <input type="input" name="clientName" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td class="td-100">
                                            <div class="contents-input-div">
                                                <input type="number" name="bookPrice" class="contents-input" value="" onchange="calcTotalPrice(1); return false;">
                                            </div>
                                        </td>
                                        <td class="td-70">
                                            <div class="contents-input-div">
                                                <input type="number" name="orderQuantity" class="contents-input" value="" onchange="calcTotalPrice(1); return false;">
                                            </div>
                                        </td>
                                        <td class="td-120">
                                            <div class="contents-input-div">
                                                <input type="number" name="totalPrice" class="contents-input" value="0" readonly>
                                            </div>
                                        </td>
                                        <td class="td-120">
                                            <div class="contents-input-div">
                                                <input type="input" name="state" class="contents-input" value="">
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
                        <input type="submit" class="contents-input-btn big noline" id="btn_save" value="등록">
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