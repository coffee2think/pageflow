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
function selectBook() {	
	$.ajax({
		url: 'popupbook.do',
		type: 'post',
		data: {
			code: $('#book').find('select').val(),
			keyword: $('#book .search-box input').val()
		},
		dataType: 'json',
		success: function(result) {
			console.log('result : ' + result);
			
			// object => string
			var jsonStr = JSON.stringify(result);
			// string => parsing : json object
			var json = JSON.parse(jsonStr);
			
			// json 객체 안의 list를 하나씩 꺼내서 새로운 행으로 추가 처리
			const table = $('#book table');
			for(var i in json.list) {
				const newRow = table.insertRow(i + 1);
				
				const newCell0 = newRow.insertCell(0);
				const newCell1 = newRow.insertCell(1);
				const newCell2 = newRow.insertCell(2);
				const newCell3 = newRow.insertCell(3);
				const newCell4 = newRow.insertCell(4);
				
				newCell0.innerHTML = ;
				newCell0.innerHTML = ;
				newCell0.innerHTML = ;
				newCell0.innerHTML = ;
				newCell0.innerHTML = ;
				
				output += "<tr><td>" + json.list[i].noticeNo
					+ "</td><td>" + decodeURIComponent(jsonObj.list[i].noticeTitle).replace(/\+/gi, ' ')
					+ "</td><td>" + jsonObj.list[i].noticeWriter
					+ "</td><td>" + jsonObj.list[i].noticeDate
					+ "</td></tr>";
			}
			
			console.log(output);
			
			// 테이블에 기록 처리
			$('#tblist').html(output);
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
    <div class="modal-pop-box small pop-box-1" id="book">
        <div class="modal-pop-title">
            도서검색
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
                            <option value="name">도서명</option>
                            <option value="code">도서코드</option>
                        </select>
                    </div>
                </div>
                <div class="search-box">
                    <button class="search-btn-pop">
                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                    </button>
                    <input type="search" placeholder="키워드를 입력하세요." class="search-box-text" value="">
                </div>
            </div>

            <div class="modal-pan-center">
                <table class="contents-table">
                    <thead>
                        <th></th>
                        <th>
                            No.
                        </th>
                        <th>
                            도서코드
                        </th>
                        <th>
                            도서명
                        </th>
                        <th>
                            재고현황
                        </th>
                    </thead>
                    <tbody>
                        <tr onclick=""  class="cursor-pointer">
                            <td>
                                <input type="checkbox" name="check" value="" >
                            </td>
                            <td>1</td>
                            <td>10102</td>
                            <td>이것이 자바다</td>
                            <td>140</td>
                        </tr>
                    </tbody>
                    
                </table>
            </div>

            <div class="modal-pan-bottom flex-center">
                <input type="button" class="contents-input-btn big noline" id="btn_register" value="등록">
            </div>
        </div>
        <!--modal-pop end-->

    </div>
    <!-- modal-pop-box end -->

    <!-- modal-pop-box -->
    <div class="modal-pop-box small pop-box-1" id="printoffice">
        <div class="modal-pop-title">
            인쇄소 검색
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
                            <option value="name">인쇄소명</option>
                            <option value="code">인쇄소코드</option>
                        </select>
                    </div>
                </div>
                <div class="search-box">
                    <button class="search-btn-pop">
                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                    </button>
                    <input type="text" placeholder="키워드를 입력하세요." class="search-box-text" value="">
                </div>
            </div>

            <div class="modal-pan-center">
                <table class="contents-table">
                    <thead>
                        <th></th>
                        <th>
                            No.
                        </th>
                        <th>
                            인쇄소코드
                        </th>
                        <th>
                            인쇄소명
                        </th>
                    </thead>
                    <tbody>
                        <tr onclick=""  class="cursor-pointer">
                            <td>
                                <input type="checkbox" name="check" value="" >
                            </td>
                            <td>1</td>
                            <td>AK-1213</td>
                            <td>거성인쇄</td>
                        </tr>
                    </tbody>
                    
                </table>
            </div>
            
            <div class="modal-pan-bottom flex-center">
                <input type="button" class="contents-input-btn big noline" id="btn_register" value="등록">
            </div>
            
        </div>
        <!--modal-pop end-->

    </div>
    <!-- modal-pop-box end -->
</body>
</html>