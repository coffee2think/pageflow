<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="refundList" value="${ requestScope.refundList }" />
<c:set var="keyword" value="${ requestScope.keyword }" />
<c:set var="searchType" value="${ requestScope.searchType }" />
<c:set var="firstType" value="${ requestScope.firstType }" />
<c:set var="begin" value="${ requestScope.begin }" />
<c:set var="end" value="${ requestScope.end }" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/inventory_update.js"></script>
<script>
    const NOWPAGE = 4;
    const SUBPAGE = 4;
    const LNKPAGE = 1;
</script>
<title></title>
<script type="text/javascript"> 
	 $(document).ready(function(){
		 $('#btn_delete').click(function() {
				var jarr = new Array();
		        var scb = $('.selectedItems:checked');
		        var scbValues = scb.map(function() {
		            return $(this).val();
		        }).get();

		        var job = { scbkey : scbValues };
		        jarr.push(job);
		        
		        console.log("jarr : " + JSON.stringify(jarr));
		        if (scbValues.length === 0) {
		            alert('선택된 항목이 없습니다.');
		        } else {
		            $.ajax({
		                url: 'redelect.do',
		                type: 'post',
		                data: JSON.stringify(jarr), // 데이터를 JSON 문자열로 변환
		                contentType: 'application/json; charset=utf-8', // 컨텐츠 타입 설정
		                success: function() {
		                    alert('선택한 입고내역이 삭제되었습니다.');
		                    location.reload();
		                },
		                error: function() {
		                    alert('삭제 실패! 관리자에게 문의하세요.');
		                }
		            });
		        }
		    });
		});
	
	var curinput = "";
	$(function(){
		// 서치 버튼 클릭
		$('.search-button').on('click', function(){
			searchKey();
		})
		// 엔터 누르고 난뒤
		$('.search-box-text').on('keyup', function(key){
			if(key.keyCode==13){
				searchKey();
			}
		});
		
		var type = '<c:out value="${ searchType }" />'
		console.log('type : ' + type);
		
	})
	
	function searchKey(){

    	var url = 'refselectkeyword.do?';
            url += 'keyword=' + $('.search-box-text').val();
            url += '&searchType='+ $('#sel_code option:selected').val();
            
            location.href = url;
	}
	 
	function searchByDate() {
	    	var begin = $('#begin_startDate').val();
	    	var end = $('#end_startDate').val();
	    	
	    	console.log('begin : ' + begin);
	    	console.log('end : ' + end);
	    	
	    	var url = 'refunddate.do?';
	    	url += 'begin=' + begin;
	    	url += '&end=' + end;
	        
	    	location.href = url;
	    }
	

	$(function(){
		$('#table_list').find('input[name=refundNum]').each(function(){
			$(this).change(function(){
				var tr = $(this).parent().parent().parent();
				var bookPrice = tr.find('input[name=bookPrice]').val();
				var refundNum = tr.find('input[name=refundNum]').val();
				var refundAmount = bookPrice * refundNum;
				/* tr.find('input[name=storePrice]').addClass,('.changeable'); */
				tr.find('input[name=refundAmount]').val(refundAmount);

				console.log("bookPrice : " + bookPrice);
				console.log("refundNum : " + refundNum);
				console.log("refundAmount : " + refundAmount);
				
			});
		});
	});

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
                            <div class="select-search">
                                <div class="select-box">
                                    <div class="select-pan">
                                        <label for="sel_code"></label>
                                        <select name="code" id="sel_code">
                                            <option value="bookId">도서코드</option>
                                            <option value="bookName">도서명</option>
                                            <option value="clientName">서점명</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="search-box">
                                    <button class="search-btn">
                                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                    </button>
                                    <c:if test="${ empty keyword }">
										<input type="text" placeholder="키워드를 입력하세요." class="search-box-text" > 
									</c:if>
										<c:if test="${ !empty keyword }">
										<input type="text" placeholder="키워드를 입력하세요." class="search-box-text" value="${ keyword }"> 
									</c:if>
                                </div>
                            </div>
                            <div class="select-box">
                                <div class="select-pan-nemo">
                                    반품일자
                                </div>

                              <c:choose> 
					            <c:when test="${ !empty firstType and firstType eq 'first' }">
					                <input type="date" class="select-date select-date-first" id="begin_startDate">
					                <input type="date" class="select-date select-date-second" id="end_startDate">
					            </c:when> 
				            
					            <c:otherwise>
					                <input type="date" class="select-date select-date-first" id="begin_startDate" value="${ begin }">
					                <input type="date" class="select-date select-date-second" id="end_startDate" value="${ end }">
					            </c:otherwise> 
							        </c:choose> 
							        
						        <c:set var="today_" value="<%= new java.util.Date() %>" />
						        <fmt:formatDate var="today" value="${ today_ }" pattern="yyyy-MM-dd" />
						
						        <c:set var="weekago_" value="<%= java.util.Date.from(java.time.LocalDate.now().minusWeeks(1).plusDays(1).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()) %>" />
						        <fmt:formatDate var="weekago" value="${ weekago_ }" pattern="yyyy-MM-dd" />
						
		
						        <c:set var="monthago_" value="<%= java.util.Date.from(java.time.LocalDate.now().minusMonths(1).plusDays(1).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()) %>" />
						        <fmt:formatDate var="monthago" value="${ monthago_ }" pattern="yyyy-MM-dd" />
						
						        <c:url var="searchWeekUrl" value="refunddate.do">
						            <c:param name="begin" value="${ weekago }" />
						            <c:param name="end" value="${ today }" />
						            <c:param name="dateType" value="startDate" />
						        </c:url>
						
						        <c:url var="searchMonthUrl" value="refunddate.do">
						            <c:param name="begin" value="${ monthago }" />
						            <c:param name="end" value="${ today }" />
						            <c:param name="dateType" value="startDate" />
						        </c:url>
						
						        <input type="button" name="week" class="select-pan-btn" value="일주일" onclick="javascript: location.href='${ searchWeekUrl }'">
						        <input type="button" name="month" class="select-pan-btn" value="한달" onclick="javascript: location.href='${ searchMonthUrl }'">
						        <input type="button" name="search" class="select-pan-btn" value="검색" onclick="searchByDate('startDate'); return false;">
                            </div>

                        <div class="paging-box">
                            <!-- 페이징 -->
                            <c:import url="/WEB-INF/views/common/paging.jsp" />
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
                            <table class="contents-table inven" id="table_list">
                                <tr>
                                    <th>체크</th>
                                    <th>도서코드</th>
                                    <th>도서명</th>
                                    <th>반품서점</th>
                                    <th>반품상태</th>
                                    <th>반품일자</th>
                                    <th>정가</th>
                                    <th>반품부수</th>                                 
                                    <th>반품금액</th>
                                    <th>비고</th>
                                    <th>수정</th>
                                </tr>
                                <c:set var="totalRefundNum" value="0"/>
                                <c:set var="totalRefundPrice" value="0"/>
                                <c:if test="${ !empty refundList }">
	                    			<c:forEach var = "ref" items="${ refundList }">
		                                <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1" id="tr_${ ref.refundId }">
		                                    <td class="td-50">
		                                         <input type="checkbox" class="selectedItems" name="refundId" value="${ ref.refundId }">       
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="bookId" class="contents-input noline" value="${ ref.bookId }" readonly>
		                                       		<input type="hidden" name="clientId" value="${ ref.clientId }">
													 <input type="hidden" name="empId" value="${ ref.empId }">
													 <input type="hidden" name="empName" value="${ ref.empName }">
		                                        </div>
		                                    </td>
		                                    <td class="td-250">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="bookName" class="contents-input noline" value="${ ref.bookName }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="clientName" class="contents-input noline" value="${ ref.clientName }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-120">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="refundState" class="contents-input noline changeable" value="${ ref.refundState }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-70">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="refundDate" class="contents-input noline" value="${ ref.refundDate }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-100">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="bookPrice" class="contents-input noline" value="${ ref.bookPrice }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-70">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="refundNum" class="contents-input noline changeable" value="${ ref.refundNum }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-120">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="refundAmount" class="contents-input noline" value="${ ref.refundAmount }" readonly>
		                                        </div>
		                                    </td>
		                                       <td class="td-120">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="remark" class="contents-input noline changeable" value="${ ref.remark }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-70">
		                                        <input type="button" class="contents-input-btn noline" value="수정" id="updateBtn_${ ref.refundId }" onclick="onUpdate(${ ref.refundId }); return false;">
												<input type="button" class="contents-input-btn noline" value="완료" id="completeBtn_${ ref.refundId }" onclick="submitUpdate(${ ref.refundId }, 'refupdate.do'); return false;" style="display: none;"> 
												<input type="button" class="contents-input-btn noline" value="취소" id="cancelBtn_${ ref.refundId }" onclick="cancelUpdate(${ ref.refundId }); return false;" style="display: none;">
		                                    </td>
		                                </tr>
		                            <c:set var="totalRefundNum" value="${ totalRefundNum + ref.refundNum }"/>
		                             <c:set var="totalRefundPrice" value="${ totalRefundPrice + ref.refundAmount }"/>
									</c:forEach>
								</c:if>
                                <!--합계-->
                                <fmt:formatNumber var="totalRefundNum" value="${ totalRefundNum }" type="number"/>
								<fmt:formatNumber var="totalRefundPrice" value="${ totalRefundPrice }" type="number"/>
                                <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1 sum">
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>합계</td>
                                    <td>${ totalRefundNum }</td>
                                    <td>${ totalRefundPrice }</td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <!--합계end-->

                            </table>
                        </div>
                    </div>
                    <!--컨텐츠영역 end-->

                </div>
                <!--내용 end-->

                
                 <div class="submit-box">
                    <button class="contents-input-btn big noline" id="btn_delete">선택삭제</button> 
                </div>
                
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