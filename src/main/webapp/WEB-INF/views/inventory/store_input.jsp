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
    const NOWPAGE = 4;
    const SUBPAGE = 2;
    const LNKPAGE = 2;
    let client_type = 'bookstore';
</script>
<title></title>
<script type="text/javascript">
	function addRow() {
		const uniqueId = "row_" + (new Date()).getTime();

		$("#input_table").append(
		`<tr id="`+uniqueId+`" data-parent="1" data-num="1" data-depth="1" class="table-td-depth1 content-row">       
           <td class="td-50">
               <div class="contents-check-div">
                   <button class="contents-input-plus" onclick="addRow(`+uniqueId+`);">
                       <img src="${ pageContext.servletContext.contextPath }/resources/images/plus.png">
                   </button>
                   <button class="contents-input-minus" onclick="minusRow(`+uniqueId+`);">
                       <img src="${ pageContext.servletContext.contextPath }/resources/images/minus.png">
                   </button>
               </div>
           </td>
           <td class="td-100">
               <div class="contents-input-div input-search">
                   <button class="input-search-btn">
                       <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                   </button>
                   <input type="input" name="bookId" class="contents-input">
               </div>
           </td>
           <td class="td-200">
               <div class="contents-input-div input-search">
                   <button class="input-search-btn">
                       <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                   </button>
                   <input type="input" name="bookName" class="contents-input">
               </div>
           </td>
           <td class="td-100">
               <div class="contents-input-div input-search">
                   <button class="input-search-btn">
                       <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                   </button>
                   <input type="hidden" name="clientId" value="">
                   <input type="input" name="clientName" class="contents-input">
               </div>
           </td>
           <td class="td-100">
               <div class="contents-input-div input-search">
                   <button class="input-search-btn">
                       <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                   </button>
                   <input type="hidden" name="empId">
                   <input type="input" name="empName" class="contents-input">
               </div>
           </td>
           <td class="td-70">
               <div class="contents-input-div">
                   <input type="date" name="storeDate" class="select-date small">
               </div>
           </td>
           <td class="td-70">
               <div class="contents-input-div">
                   <input type="input" id="storeNumInput_`+uniqueId+`" name="storeNum" class="contents-input" onchange="calcTotalPrice(`+uniqueId+`); return false;">
               </div>
           </td>
           <td class="td-70">
               <div class="contents-input-div">
                   <input type="input" id="bookPriceInput_`+uniqueId+`" name="bookPrice" class="contents-input" onchange="calcTotalPrice(1); return false;">
               </div>
           </td>
           <td class="td-100">
               <div class="contents-input-div">
                   <input type="input" id="storePrice_`+uniqueId+`" name="storePrice" class="contents-input" readonly>
               </div>
           </td>
       </tr>`
			);
		initPopupBtn();
	}
	function minusRow(rowId) {
	    $("#" + rowId).remove();
	    initPopupBtn();
	}
	
	function calcTotalPrice(uniqueId) {
		console.log("uniqueId : " + uniqueId)
		var bookPrice = Number($('#bookPriceInput_'+uniqueId).val());
		var storeNum = Number($('#storeNumInput_'+uniqueId).val()); // .val()을 쓰면 String 이됨!! Number 안에 작성해줌!!
		
		var storePrice = Number($('#storePrice_'+uniqueId).val(bookPrice * storeNum));
		
		console.log("bookPrice : " + bookPrice);
		console.log("storeNum : " + storeNum);
		console.log("storePrice : " + storePrice);
	}

</script>

</head>
<body>
	<div id="container">
        
        <!--헤더-->
        <header class="main-header">
            <!--header-container-->
            <div class="header-container">
                <!-- 헤더 들어감 -->
                <c:import url="/WEB-INF/views/common/header.jsp" />
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
                    <c:import url="/WEB-INF/views/common/side.jsp" />
                </div>
            </div>
            <!--main-side end-->

            <!--main-container-->
            <div class="main-container">

                <!--form-->
                <!-- <form class="input-form" action="/comi/partyi" method="post" enctype="multipart/form-data">-->
                <form class="input-form" action="stoinsert.do" method="post">
                    <!--main-header-bar-->
                	<input type="hidden" name="empId" value="${ loginMember.empId }">
                	<input type="hidden" name="empName" value="${ loginMember.empName }">
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
                                        <th>입고창고</th>
                                        <th>인수자</th>
                                        <th>입고일자</th>
                                        <th>입고부수</th>
                                        <th>정가</th>
                                        <th>입고금액</th>
                                    </tr>
                                    <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1 content-row" id="row_1">       
                                        <td class="td-50">
                                            <div class="contents-check-div">
                                                <button class="contents-input-plus" onclick="addRow(); return false">
                                                    <img src="${ pageContext.servletContext.contextPath }/resources/images/plus.png">
                                                </button>
                                                <button class="contents-input-minus" onclick="minusRow(); return false">
                                                    <img src="${ pageContext.servletContext.contextPath }/resources/images/minus.png">
                                                </button>
                                            </div>
                                        </td>
                                        <td class="td-100">
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <input type="input" name="bookId" class="contents-input">
                                            </div>
                                        </td>
                                        <td class="td-200">
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <input type="input" name="bookName" class="contents-input">
                                            </div>
                                        </td>
                                        <td class="td-100">
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <input type="hidden" name="clientId" value="">
                                                <input type="input" name="clientName" class="contents-input">
                                            </div>
                                        </td>
                                        <td class="td-100">
                                            <div class="contents-input-div input-search">
                                                <button class="input-search-btn">
                                                    <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                                </button>
                                                <input type="hidden" name="empId">
                                                <input type="input" name="empName" class="contents-input">
                                            </div>
                                        </td>
                                        <td class="td-70">
                                            <div class="contents-input-div">
                                                <input type="date" name="storeDate" class="select-date small">
                                            </div>
                                        </td>
                                        <td class="td-70">
                                            <div class="contents-input-div">
                                                <input type="input" id="storeNumInput_row_1" name="storeNum" class="contents-input" onchange="calcTotalPrice('row_1'); return false;">
                                            </div>
                                        </td>
                                        <td class="td-70">
                                            <div class="contents-input-div">
                                                <input type="input" id="bookPriceInput_row_1" name="bookPrice" class="contents-input" onchange="calcTotalPrice('row_1'); return false;">
                                            </div>
                                        </td>
                                        <td class="td-100">
                                            <div class="contents-input-div">
                                                <input type="input" id="storePrice_1" name="storePrice" class="contents-input" readonly>
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
                <c:import url="/WEB-INF/views/common/popup.jsp" />
            </div>
            <!--modal-pop-area end-->

        </main>
        
    </div>
</body>
</html>