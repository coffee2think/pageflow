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
    const NOWPAGE = 3;
    const SUBPAGE = 1;
    const LNKPAGE = 2;
    
    let curinput;

    document.addEventListener("DOMContentLoaded", function(){
    	$('.input-search-btn').on('click', function() {
    		var type = $(this).attr('class').split(' ')[1];
    		popup.showPopup(type);
    		curinput = $(this).parent('.input-search').find('input[type=input]');
    		return false;
    	})
    }); 
    
    function addRow(currentIndex) {
    	if($('#ctrinsert_table').find('tr').length >= 10) {
    		return;
    	}
    	
    	// table element 찾기
		const table = document.getElementById('ctrinsert_table');

		// 새 행(Row) 추가 (테이블 중간에)
		const newRow = table.insertRow(currentIndex + 1);

		// 새 행(Row)에 Cell 추가
		const newCell1 = newRow.insertCell(0);
		newCell1.setAttribute('class', 'td-50');
		const newCell2 = newRow.insertCell(1);
		newCell2.setAttribute('class', 'td-70');
		const newCell3 = newRow.insertCell(2);
		newCell3.setAttribute('class', 'td-70');
		const newCell4 = newRow.insertCell(3);
		newCell4.setAttribute('class', 'td-70');
		const newCell5 = newRow.insertCell(4);
		newCell5.setAttribute('class', 'td-70');
		
		// Cell에 텍스트 추가
		newCell1.innerHTML = '<div class="contents-check-div">'
						+ '<button class="contents-input-plus" onclick="addRow(' + ( currentIndex + 1 ) + '); return false;">'
						+ '<img src="${ pageContext.servletContext.contextPath }/resources/images/plus.png">'
						+ '</button> '
						+ '<button class="contents-input-minus" onclick="removeRow(' + ( currentIndex + 1 ) + '); return false;">'
						+ '<img src="${ pageContext.servletContext.contextPath }/resources/images/minus.png">'
						+ '</button>'
						+ '</div>';

		newCell2.innerHTML = '<div class="contents-input-div input-search">'
						+ '<button class="input-search-btn">'
						+ '<img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">'
						+ ' </button>'
						+ '<input type="input" name="empId" class="contents-input" value="">'
						+ '</div>';
		
		newCell3.innerHTML = '<div class="contents-input-div input-search">'
						+ '<button class="input-search-btn">'
						+ '<img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">'
						+ ' </button>'
						+ '<input type="input" name="writerId" class="contents-input" value="">'
						+ '</div>';

		newCell4.innerHTML = '<div class="contents-input-div">'
						+ '<input type="input" name="bookName" class="contents-input" value="">'
						+ '</div>';
						
		newCell5.innerHTML = '<div class="contents-input-div">'
						+ '<input type="input" name="category" class="contents-input" value="">'
						+ '</div>';

		initRowIndex();
	}
    
    function removeRow(currentIndex) {
    	var trList = $('#ctrinsert_table').find('tr');
    	if(trList.length > 2) {
	    	trList.eq(currentIndex).remove();
	    	initRowIndex();
    	}
    }
    
 	// 행 추가 삭제를 위한 함수 인덱스 초기화
	function initRowIndex() {
		var table = document.getElementById('ctrinsert_table');
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
</script>
<title>계약등록</title>
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
                <form class="input-form" action="ctrinsert.do" method="post">
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
                                <table class="contents-table" id="ctrinsert_table" border="1">
                                    <tr>
                                        <th></th>
                                        <th>직원번호</th>
                                        <th>작가번호</th>
                                        <th>도서명</th>
                                        <th>카테고리</th>
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
                                        <td class="td-70">
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <input type="input" name="empId" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td class="td-70">
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <input type="input" name="writerId" class="contents-input" value="">
                                            </div>
                                        </td>
                                        <td class="td-70">
                                            <div class="contents-input-div">
                                                <input type="input" name="bookName" class="contents-input">
                                            </div>
                                        </td>
                                        <td class="td-70">
                                            <div class="contents-input-div">
                                                <input type="input" name="category" class="contents-input" value="">
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