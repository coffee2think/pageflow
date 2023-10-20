<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/popup.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery-ui-zoom.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.ui.touch-punch.min.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/popup.js"></script>
<script>

let index_global, json_global;
let rowIndex_popup;

function register() {
	popup_inputData = {
			'book': ['bookId', 'bookName', 'bookPrice'],
			'printoffice': ['clientId', 'clientName', ''],
			'bookstore': ['clientId', 'clientName'],
			'storage': ['clientId', 'clientName'],
			'book_order': ['orderId', 'bookId', 'bookName', 'clientId', 'clientName', 'orderDate', 'bookPrice', 'orderQuantity']
	};
	
	$('.modal-pop-close').parent().parent('.modal-pop-box').hide();
	$('.modal-pop-close').parent().parent().parent('.modal-pop-area').hide();
	
	list = json_global.list[index_global];
	inputData = popup_inputData[popup_type];
	for(i = 0; i < inputData.length; i++) {
		$('input[name=' + inputData[i] + ']').eq(rowIndex_popup).val(decodeURIComponent(list[inputData[i]]).replace(/\+/gi, ' '));
		console.log($('input[name=' + inputData[i] + ']').eq(rowIndex_popup));
		console.log(decodeURIComponent(list[inputData[i]]).replace(/\+/gi, ' '));
	}
	
	
	//$('input[name=bookId]').eq(rowIndex_popup).val(json_global.list[index_global].bookId);
	//$('input[name=bookName]').eq(rowIndex_popup).val(decodeURIComponent(json_global.list[index_global].bookName).replace(/\+/gi, ' '));
	//$('input[name=bookPrice]').eq(rowIndex_popup).val(json_global.list[index_global].bookPrice);
	
	// 정보 등록 후 글로벌변수 초기화
	index_global = null;
	json_global = null;
	rowIndex_popup = null;
}

function registerPrintOffice() {
	$('.modal-pop-close').parent().parent('.modal-pop-box').hide();
	$('.modal-pop-close').parent().parent().parent('.modal-pop-area').hide();
	
	$('input[name=printId]').eq(rowIndex_popup).val(json_global.list[index_global].clientId);
	$('input[name=clientName]').eq(rowIndex_popup).val(decodeURIComponent(json_global.list[index_global].clientName).replace(/\+/gi, ' '));
	
	console.log(json_global.list[index_global]);
}

function selectIndex(index) {
	console.log('selected index : ' + index);
	index_global = index;
}

function selectList() {	
	var url = {
			'book': 'popupBook.do',
			'printoffice': 'popupPrintOffice.do',
			'bookstore': 'popupBookStore.do',
			'storage': 'popupStorage.do',
			'book_order': 'popupBookOrder.do'
	};
	
	$.ajax({
		url: url[popup_type],
		type: 'post',
		data: {
			searchType: $('.modal-pop-box').find('select').val(),
			keyword: $('.modal-pop-box .search-box input').val()
		},
		dataType: 'json',
		success: function(result) {
			// object => string
			var jsonStr = JSON.stringify(result);
			// string => parsing : json object
			var json = JSON.parse(jsonStr);
			json_global = json;
			
			console.log(json);
			
			// json 객체 안의 list를 하나씩 꺼내서 새로운 행으로 추가 처리
			// 기존 행 정보 삭제
			const trList = $('#popup_table').find('tr');
			trList.each(function(index) {
				if(index > 0) {
					trList[index].remove();
				}
			});
			
			// 행 생성
			for(var i in json.list) {
				const table = document.getElementById('popup_table');
				const newRow = table.insertRow(parseInt(i) + 1);
				
				const newCell1 = newRow.insertCell(0); // 체크버튼
				const newCell2 = newRow.insertCell(1); // No
				const newCell3 = newRow.insertCell(2); // 도서코드
				const newCell4 = newRow.insertCell(3); // 도서명
				const newCell5 = newRow.insertCell(4); // 재고현황
				
				newCell1.innerHTML = '<td><input type="radio" name="radio" onchange="selectIndex(' + i + ')" id="tr_' + i + '"></td>';
				newCell2.innerHTML = '<td>' + (parseInt(i) + 1) + '</td>';
				newCell3.innerHTML = '<td>' + json.list[i].bookId + '</td>';
				newCell4.innerHTML = '<td>' + decodeURIComponent(json.list[i].bookName).replace(/\+/gi, ' ') + '</td>';
				newCell5.innerHTML = '<td>' + json.list[i].stock + '</td></tr>';
			}
		},
		error: function(request, status, errorData) {
			console.log("error code : " + request.status);
			console.log("Message : " + request.responseText);
			console.log("Error : " + errorData);
		}
	});
}

function selectPrintOffice() {	
	$.ajax({
		url: 'popupPrintOffice.do',
		type: 'post',
		data: {
			searchType: $('#printoffice').find('select').val(),
			keyword: $('#printoffice .search-box input').val()
		},
		dataType: 'json',
		success: function(result) {
			// object => string
			var jsonStr = JSON.stringify(result);
			// string => parsing : json object
			var json = JSON.parse(jsonStr);
			json_global = json;
			
			console.log(json);
			
			// json 객체 안의 list를 하나씩 꺼내서 새로운 행으로 추가 처리
			// 기존 행 정보 삭제
			const trList = $('#table_list_printoffice').find('tr');
			trList.each(function(index) {
				if(index > 0) {
					trList[index].remove();
				}
			});
			
			// 행 생성
			for(var i in json.list) {
				const table = document.getElementById('table_list_printoffice');
				const newRow = table.insertRow(parseInt(i) + 1);
				
				const newCell1 = newRow.insertCell(0); // 체크버튼
				const newCell2 = newRow.insertCell(1); // No
				const newCell3 = newRow.insertCell(2); // 거래처코드
				const newCell4 = newRow.insertCell(3); // 인쇄소명
				const newCell5 = newRow.insertCell(4); // 주소
				
				newCell1.innerHTML = '<td><input type="radio" name="radio" onchange="saveInfo(' + i + ')" id="tr_' + i + '"></td>';
				newCell2.innerHTML = '<td>' + (parseInt(i) + 1) + '</td>';
				newCell3.innerHTML = '<td>' + json.list[i].clientId + '</td>';
				newCell4.innerHTML = '<td>' + decodeURIComponent(json.list[i].clientName).replace(/\+/gi, ' ') + '</td>';
				newCell5.innerHTML = '<td>' + decodeURIComponent(json.list[i].clientAddress).replace(/\+/gi, ' ') + '</td></tr>';
			}
		},
		error: function(request, status, errorData) {
			console.log("error code : " + request.status);
			console.log("Message : " + request.responseText);
			console.log("Error : " + errorData);
		}
	});
}

</script>
<title></title>
</head>
<body>
	<!-- modal-pop-box -->
    <div class="modal-pop-box small pop-box-1">
        <div class="modal-pop-title">
            <span></span>
            <button class="modal-pop-close">
                <img src="${ pageContext.servletContext.contextPath }/resources/images/close.png">
            </button>
        </div>
        
        <!--modal-pop-->
        <div class="modal-pop">
            <div class="modal-pop-search">
                <div class="select-box">
                    <div class="select-pan">
                        <label for="sel_code"></label>
                        <select name="code" id="sel_code">
                            <option value="bookName">도서명</option>
                            <option value="bookId">도서코드</option>
                        </select>
                    </div>
                </div>
                <div class="search-box">
                    <button class="search-btn-pop" onclick="selectList();">
                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                    </button>
                    <input type="search" placeholder="키워드를 입력하세요." class="search-box-text" value="">
                </div>
            </div>

            <div class="modal-pan-center">
                <table class="contents-table" id="popup_table">
                    <thead>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </thead>
                    <tbody>
                        <tr onclick=""  class="cursor-pointer">
                            <td><input type="radio" name="radio" value=""></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </tbody>
                    
                </table>
            </div>

            <div class="modal-pan-bottom flex-center">
                <input type="button" class="contents-input-btn big noline" id="btn_register" value="등록" onclick="register();">
            </div>
        </div>
        <!--modal-pop end-->

    </div>
    <!-- modal-pop-box end -->
    
    <%-- <!-- modal-pop-box -->
    <div class="modal-pop-box small pop-box-1" id="printoffice">
        <div class="modal-pop-title">
            인쇄소검색
            <button class="modal-pop-close">
                <img src="${ pageContext.servletContext.contextPath }/resources/images/close.png">
            </button>
        </div>
        
        <!--modal-pop-->
        <div class="modal-pop">
            <div class="modal-pop-search">
                <div class="select-box">
                    <div class="select-pan">
                        <label for="sel_code"></label>
                        <select name="code" id="sel_code_printoffice">
                            <option value="clientName">인쇄소명</option>
                            <option value="clientId">거래처코드</option>
                        </select>
                    </div>
                </div>
                <div class="search-box">
                    <button class="search-btn-pop" onclick="selectList("+ type +");">
                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                    </button>
                    <input type="search" placeholder="키워드를 입력하세요." class="search-box-text" value="">
                </div>
            </div>

            <div class="modal-pan-center">
                <table class="contents-table" id="table_list_printoffice">
                    <thead>
                        <th></th>
                        <th>
                            No.
                        </th>
                        <th>
                            거래처코드
                        </th>
                        <th>
                            인쇄소명
                        </th>
                        <th>
                            주소
                        </th>
                    </thead>
                    <tbody>
                        <tr onclick=""  class="cursor-pointer">
                            <td><input type="checkbox" name="check" value=""></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </tbody>
                    
                </table>
            </div>

            <div class="modal-pan-bottom flex-center">
                <input type="button" class="contents-input-btn big noline" id="btn_register" value="등록" onclick="registerPrintOffice();">
            </div>
        </div>
        <!--modal-pop end-->

    </div>
    <!-- modal-pop-box end --> --%>
    
</body>
</html>