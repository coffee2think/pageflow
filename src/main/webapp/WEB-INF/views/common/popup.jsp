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

document.addEventListener("DOMContentLoaded", function(){
	initPopupBtn();
});

function initPopupBtn() {
	$('.input-search-btn').on('click', function() {
		var input = $(this).parent().children('input').eq(0);
		var popup_type = input.attr('name');
		if(popup_type.includes('book')) {
			popup_type = 'book';
		}
		
		if(popup_type.includes('client')) {
			popup_type = client_type;
		}
		
		if(popup_type.includes('emp')) {
			popup_type = 'employee';
		}
		
		console.log('popup_type : ' + popup_type);
		
		popup.showPopup(popup_type);
		
		curinput = $(this).parent('.input-search').find('input[type=input]');
		return false;
	});
}

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
	
	const list = json_global.list[index_global];
	const inputData = popup_inputData[popup_type];
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

function search() {	
	var url = {
			'book': 'popupBook.do',
			'printoffice': 'popupPrintOffice.do',
			'bookstore': 'popupBookStore.do',
			'storage': 'popupStorage.do',
			'book_order': 'popupBookOrder.do',
			'employee': 'popupEmployee.do'
	};
	
	var table_column = {
			'book': ['', '', 'bookId', 'bookName', 'stock'],
			'printoffice': ['', '', 'clientId', 'clientName', 'clientContact'],
			'bookstore': ['', '', 'clientId', 'clientName', 'clientContact'],
			'storage': ['', '', 'clientId', 'clientName', 'clientContact'],
			'book_order': ['', '', 'orderId', 'bookName', 'orderQuantity'],
			'employee': ['', '', 'empId', 'empName', 'depName']
	};
	
	$.ajax({
		url: url[popup_type],
		type: 'post',
		data: {
			searchType: $('.modal-pop-box').find('select').val(),
			keyword: $('.modal-pop-box .search-box input[type=search]').val(),
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
				const table = $('#popup_table');
				const tbody = table.find('tbody');
				
				tbody.append('<tr onclick=""  class="cursor-pointer">');
				const tr = tbody.find('tr:last');
				
				tr.append('<td><input type="radio" name="radio" onchange="selectIndex(' + i + ');"></td>');
				tr.append('<td>' + (parseInt(i) + 1) + '</td>');
				
				const list = json.list[i];
				const columnNames = table_column[popup_type];
				tr.append('<td>' + decodeURIComponent(list[columnNames[2]]).replace(/\+/gi, ' ') + '</td>');
				tr.append('<td>' + decodeURIComponent(list[columnNames[3]]).replace(/\+/gi, ' ') + '</td>');
				tr.append('<td>' + decodeURIComponent(list[columnNames[4]]).replace(/\+/gi, ' ') + '</td>');
			}
			
			// 페이징 초기화
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
<body style="overflow-X: hidden;">
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
                    <button class="search-btn-pop" onclick="search();">
                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                    </button>
                    <input type="search" placeholder="키워드를 입력하세요." class="search-box-text" value="" onkeyup="if(event.keyCode == 13){ search(); }">
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
</body>
</html>