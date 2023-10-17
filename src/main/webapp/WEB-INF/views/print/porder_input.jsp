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
    const NOWPAGE = 2;
    const SUBPAGE = 2;
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
		newCell3.setAttribute('class', 'td-150');
		const newCell4 = newRow.insertCell(3);
		newCell4.setAttribute('class', 'td-120');
		const newCell5 = newRow.insertCell(4);
		newCell5.setAttribute('class', 'td-120');
		const newCell6 = newRow.insertCell(5);
		newCell6.setAttribute('class', 'td-120');
		const newCell7 = newRow.insertCell(6);
		newCell7.setAttribute('class', 'td-100');
		const newCell8 = newRow.insertCell(7);
		newCell8.setAttribute('class', 'td-200');
		const newCell9 = newRow.insertCell(8);
		newCell9.setAttribute('class', 'td-70');
		const newCell10 = newRow.insertCell(9);
		newCell10.setAttribute('class', 'td-70');
		const newCell11 = newRow.insertCell(10);
		newCell11.setAttribute('class', 'td-100');
		const newCell12 = newRow.insertCell(11);
		newCell12.setAttribute('class', 'td-100');
		const newCell13 = newRow.insertCell(12);
		newCell13.setAttribute('class', 'td-100');

		// Cell에 텍스트 추가
		newCell1.innerHTML = '<div class="contents-input-div input-search">'
       					+ '<button class="input-search-btn">'
        				+ '<img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">'
    					+ '</button>'
    					+ '<input type="input" name="printId" class="contents-input" value="">'
						+ '</div>';
		
		newCell2.innerHTML = '<div class="contents-input-div input-search">'
						+ '<button class="input-search-btn">'
						+ '<img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">'
						+ '</button>'
						+ '<input type="input" name="printId" class="contents-input" value="">'
						+ '</div>';
						 
		newCell3.innerHTML = '<div class="contents-input-div input-search">'
				 		+ '<button class="input-search-btn">'
					 	+ ' <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">'
				 	 	+ '</button>'
					 	+ '<input type="input" name="clientName" class="contents-input" value="">'
				 		+ '</div>';
		
		newCell4.innerHTML = '<div class="contents-input-div">'
       					+ '<input type="date" name="orderDate" class="select-date small">'
       					+ '</div>';
       					
		newCell5.innerHTML = '<div class="contents-input-div">'
				        + '<input type="date" name="endDate" class="select-date small">'
				        + '</div>';
		
		newCell6.innerHTML =  '<div class="contents-input-div">'
				        + '<input type="date" name="pubDate" class="select-date small">'
				        + '</div>';
		
		newCell7.innerHTML = '<div class="contents-input-div input-search">'
				        + '<button class="input-search-btn">'
				        + '<img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">'
				   		+ '</button>'
			    		+ '<input type="input" name="bookId" class="contents-input" value="">'
						+ '</div>';

		newCell8.innerHTML = '<div class="contents-input-div input-search">'
				        + '<button class="input-search-btn">'
				        + '<img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">'
				    	+ '</button>'
				    	+ '<input type="input" name="bookName" class="contents-input" value="">'
				    	+ '</div>';
			
		newCell9.innerHTML =  '<div class="contents-input-div">'
				        + '<input type="number" name="unit" class="contents-input">'
				        + '</div>';
			
			
		newCell10.innerHTML =  '<div class="contents-input-div">'
				        + '<input type="number" name="quantity" class="contents-input" value="">'
				        + '</div>';
			
			
		newCell11.innerHTML =  '<div class="contents-input-div">'
				        +'<input type="number" name="price" class="contents-input" value="">'
				        + '</div>';
			
			
		newCell12.innerHTML =  '<div class="contents-input-div">'
				        + '<input type="number" name="amount" class="contents-input" value="">'
				        + '</div>';
		
			
		newCell13.innerHTML = 	 '<div class="contents-input-div">'
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
                <form class="input-form" action="poinsert.do" method="post">
                <input type="hidden" name="empName" class="select-date small">
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
                                        <th></th>
                                        <th>거래처코드</th>
                                        <th>인쇄소</th>
                                        <th>발주일</th>
                                        <th>마감일</th>
                                        <th>출간일</th>
                                        <th>도서코드</th>
                                        <th>도서명</th>
                                        <th>단위</th>
                                        <th>수량</th>
                                        <th>단가</th>
                                        <th>합계</th>
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
                                                <input type="input" name="printId" class="contents-input" value="${ printOrder.printId }">
                                            </div>
                                        </td>
                                        <td class="td-150">
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <input type="input" name="clientName" class="contents-input" value="${ printOrder.clientName }">
                                            </div>
                                        </td>
                                        <td class="td-120">
                                            <div class="contents-input-div">
                                                <input type="date" name="orderDate" class="select-date small">
                                            </div>
                                        </td>
                                        <td class="td-120">
                                            <div class="contents-input-div">
                                                <input type="date" name="endDate" class="select-date small">
                                            </div>
                                        </td>
                                        <td class="td-120">
                                            <div class="contents-input-div">
                                                <input type="date" name="pubDate" class="select-date small">
                                            </div>
                                        </td>
                                        <td class="td-100">
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <input type="input" name="bookId" class="contents-input" value="${ printOrder.bookId }">
                                            </div>
                                        </td>
                                        <td class="td-200">
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <input type="input" name="bookName" class="contents-input" value="${ printOrder.bookName }">
                                            </div>
                                        </td>
                                        <td class="td-70">
                                            <div class="contents-input-div">
                                                <input type="number" name="unit" class="contents-input" value="${ printOrder.unit }">
                                            </div>
                                        </td>
                                        <td class="td-70">
                                            <div class="contents-input-div">
                                                <input type="number" name="quantity" class="contents-input" value="${ printOrder.quantity }">
                                            </div>
                                        </td>
                                        <td class="td-100">
                                            <div class="contents-input-div">
                                                <input type="number" name="price" class="contents-input" value="${ printOrder.price }">
                                            </div>
                                        </td>
                                        <td class="td-100">
                                            <div class="contents-input-div">
                                                <input type="number" name="amount" class="contents-input" value="${ printOrder.amount }">
                                            </div>
                                        </td>
                                        <td class="td-50">
                                            <div class="contents-input-div">
                                                <input type="input" name="state" class="contents-input" value="${ printOrder.state }">
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

