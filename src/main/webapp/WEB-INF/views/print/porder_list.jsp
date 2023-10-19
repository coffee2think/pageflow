<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css"
	href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<script type="text/javascript"
	src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script type="text/javascript">
    const NOWPAGE = 2;
    const SUBPAGE = 1;
    const LNKPAGE = 1;
    
$(function(){
	
	
	$('${ printOrder.printId }').click(function(){
		
		var inputs = $('.changeable input');
		
		
		inputs.each(function(){
			$(this).attr('readonly', false);
		});
		
		console.log("=========================확인============================");
		
		//ajax 사용
		$.ajax({
			url: "poupdate.do",
			type: "post",
			data: { 
				orderDate: $('#orderDate').val(), endDate: $('#endDate').val(),
				pubDate: $('#pubDate').val(), quantity: $('#quantity').val(), 
				price: $('#price').val(), amount: $('#amount').val(), state: $('#state').val()
				},
			
			success: function(data){
				if(data == "ok"){
					alert("수정되었습니다.");
				}
			}	
		});		//ajax 의 닫기태그
	});			//click 의 닫기태그
	
$('#cancel').click(function(){
		
		var inputs = document.getElementsByClassName('contents-input-div changeable');
		
		for(var i = 0; i< inputs.length; i++){
			inputs[i].readonly = true;
			}
	});	
})				//document.ready 의 닫기 태그    
</script>
<title></title>
<script type="text/javascript">
	$(document).ready(function(){
		$('#btn_delete').click(function(){
			const selectedCheckbox = $('input[name="check"]:checked');
			const selectedPrintOrderIds = [];
			
			selectedCheckbox.each(function(){
				var p_id = $(this).parent().parent().attr('id');
				selectedPrintOrderIds.push(p_id.split('_').pop());
				
				
			});
			console.log(selectedPrintOrderIds);
			if(selectedPrintOrderIds.length === 0){
				alert('선택된 항목이 없습니다.');
			} else{
				$.ajax({
					url: 'podelete.do',
					type: 'post',
					dataType: 'json',
					data: { selectedPrintOrderIds: selectedPrintOrderIds.join(',') },
					success: function(response){
						alert('선택한 인쇄소가 없습니다.');
						location.reload();
					},
					error: function(){
						alert('삭제 실패! 관리자에게 문의 하세요');
					}
				});
			}
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
						<img
							src="${ pageContext.servletContext.contextPath }/resources/images/header-icon.png">
						<span class="main-title"></span>
					</div>
					<button class="header-left-btn"></button>
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
										<label for="sel_code"></label> <select name="code"
											id="sel_code">
											<option value="">인쇄소코드</option>
											<option value="">인쇄소명</option>
											<option value="">도서코드</option>
											<option value="">도서명</option>
											<option value="">수량</option>
											<option value="">단가</option>
											<option value="">합계</option>
										</select>
									</div>
								</div>

								<div class="search-box">
									<button class="search-btn">
										<img class="search-image"
											src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
									</button>
									<input type="text" placeholder="키워드를 입력하세요."
										class="search-box-text" value="">
								</div>
							</div>

							<div class="select-box">
								<div class="select-pan">
									<label for="sel_code"></label> <select name="code" id="sel_code">
										<option value="all">인쇄소별</option>
										<option value="">거성인쇄</option>
										<option value="">JK인쇄</option>
									</select>
								</div>

								<div class="select-pan">
									<label for="sel_code"></label> <select name="code" id="sel_code">
										<option value="all">상태</option>
										<option value="">지급</option>
										<option value="">미지급</option>
									</select>
								</div>
							</div>

							<div class="select-box">
								<div class="select-pan-nemo">발주일</div>

								<input type="date" class="select-date select-date-first"
									name="begin" value=${ begin }> <input type="date"
									class="select-date select-date-second" name="end"
									value=${ end }>

								<c:set var="today_" value="<%=new java.util.Date()%>" />
								<fmt:formatDate var="today" value="${ today_ }"
									pattern="yyyy-MM-dd" />
								<c:set var="weekago_"
									value="<%=new java.util.Date(new java.util.Date().getTime() - 60 * 60 * 24 * 1000 * 6)%>" />
								<fmt:formatDate var="weekago" value="${ weekago_ }"
									pattern="yyyy-MM-dd" />
								<c:set var="monthago_"
									value="<%=new java.util.Date(new java.util.Date().getTime() - 60 * 60 * 24 * 1000 * 30)%>" />
								<fmt:formatDate var="monthago" value="${ monthago_ }"
									pattern="yyyy-MM-dd" />

								<c:url var="searchWeekUrl" value="polistdate.do">
									<c:param name="begin" value="${ weekago }" />
									<c:param name="end" value="${ today }" />
								</c:url>

								<c:url var="searchMonthUrl" value="polistdate.do">
									<c:param name="begin" value="${ monthago }" />
									<c:param name="end" value="${ today }" />
								</c:url>

								<input type="button" name="week" class="select-pan-btn"
									value="일주일"
									onclick="javascript: location.href='${ searchWeekUrl }'">
								<input type="button" name="month" class="select-pan-btn"
									value="한달"
									onclick="javascript: location.href='${ searchMonthUrl }'">
							</div>

							<div class="select-box">
								<div class="select-pan-nemo">마감일</div>

								<input type="date" class="select-date select-date-first"
									name="begin" value=${ begin }> <input type="date"
									class="select-date select-date-second" name="end"
									value=${ end }>

								<c:set var="today_" value="<%=new java.util.Date()%>" />
								<fmt:formatDate var="today" value="${ today_ }"
									pattern="yyyy-MM-dd" />
								<c:set var="weekago_"
									value="<%=new java.util.Date(new java.util.Date().getTime() - 60 * 60 * 24 * 1000 * 6)%>" />
								<fmt:formatDate var="weekago" value="${ weekago_ }"
									pattern="yyyy-MM-dd" />
								<c:set var="monthago_"
									value="<%=new java.util.Date(new java.util.Date().getTime() - 60 * 60 * 24 * 1000 * 30)%>" />
								<fmt:formatDate var="monthago" value="${ monthago_ }"
									pattern="yyyy-MM-dd" />

								<c:url var="searchWeekUrl" value="polistdate.do">
									<c:param name="begin" value="${ weekago }" />
									<c:param name="end" value="${ today }" />
								</c:url>

								<c:url var="searchMonthUrl" value="polistdate.do">
									<c:param name="begin" value="${ monthago }" />
									<c:param name="end" value="${ today }" />
								</c:url>

								<input type="button" name="week" class="select-pan-btn"
									value="일주일"> <input type="button" name="month"
									class="select-pan-btn" value="한달">
							</div>

							<div class="select-box">
								<div class="select-pan-nemo">출간일</div>

								<input type="date" class="select-date select-date-first"
									name="begin" value=${ begin }> <input type="date"
									class="select-date select-date-second" name="end"
									value=${ end }>

								<c:set var="today_" value="<%=new java.util.Date()%>" />
								<fmt:formatDate var="today" value="${ today_ }"
									pattern="yyyy-MM-dd" />
								<c:set var="weekago_"
									value="<%=new java.util.Date(new java.util.Date().getTime() - 60 * 60 * 24 * 1000 * 6)%>" />
								<fmt:formatDate var="weekago" value="${ weekago_ }"
									pattern="yyyy-MM-dd" />
								<c:set var="monthago_"
									value="<%=new java.util.Date(new java.util.Date().getTime() - 60 * 60 * 24 * 1000 * 30)%>" />
								<fmt:formatDate var="monthago" value="${ monthago_ }"
									pattern="yyyy-MM-dd" />

								<c:url var="searchWeekUrl" value="polistdate.do">
									<c:param name="begin" value="${ weekago }" />
									<c:param name="end" value="${ today }" />
								</c:url>

								<c:url var="searchMonthUrl" value="polistdate.do">
									<c:param name="begin" value="${ monthago }" />
									<c:param name="end" value="${ today }" />
								</c:url>

								<input type="button" name="week" class="select-pan-btn"
									value="일주일"> <input type="button" name="month"
									class="select-pan-btn" value="한달">
							</div>

						</form>

						<div class="paging-box">
							<!-- 페이징 -->
							<c:import url="../common/paging.jsp" />
						</div>

						<button class="search-visible-btn" id="search_visible_btn">
							<img class="search-close"
								src="${ pageContext.servletContext.contextPath }/resources/images/cursor_1.png">
							<img class="search-open"
								src="${ pageContext.servletContext.contextPath }/resources/images/cursor_2.png">
						</button>

					</div>
					<!--서치영역 end-->

					<!--컨텐츠영역-->
					<div class="contents-container sort-row">
						<div class="contents-box">
							<table class="contents-table">
								<tr>
									<th>체크</th>
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
									<th>수정</th>  
								</tr>
								<c:if test="${ !empty list }">
									<c:forEach items="${ list }" var="printOrder">
										<tr id="printtr_${ printOrder.orderId }" data-parent="1" data-num="1" data-depth="1"
											class="table-td-depth1">
											<td class="td-50"><input type="checkbox" name="check" value=""></td>
											<td class="td-100">
												<div class="contents-input-div">
													<input type="input" name=printId
														class="contents-input noline" value="${ printOrder.printId }" readonly>
												</div>
											</td>
											<td class="td-120">
												<div class="contents-input-div">
													<input type="input" name="clientName" 
													class="contents-input noline" value="${ printOrder.clientName }" readonly>
												</div>
											</td>
											<td class="td-100">
												<div class="contents-input-div changeable">
													<input type="input" name="orderDate" 
													class="contents-input noline" value="${ printOrder.orderDate }" readonly>
												</div>
											</td>
											<td class="td-100">
												<div class="contents-input-div changeable">
													<input type="input" name="endDate" 
													class="contents-input noline" value="${ printOrder.endDate }" readonly>
												</div>
											</td>
											<td class="td-100">
												<div class="contents-input-div changeable">
													<input type="input" name="pubDate"
														class="contents-input noline" value="${ printOrder.pubDate }" readonly>
												</div>
											</td>
											<td class="td-70">
												<div class="contents-input-div">
													<input type="input" name="bookId"
														class="contents-input noline" value="${ printOrder.bookId }" readonly>
												</div>
											</td>
											<td class="td-250">
												<div class="contents-input-div">
													<input type="input" name="bookName"
														class="contents-input noline" value="${ printOrder.bookName }" readonly>
												</div>
											</td>
											<td class="td-50">
												<div class="contents-input-div">
													<input type="input" name="unit" class="contents-input noline" value="${ printOrder.unit }"
														readonly>
												</div>
											</td>
											<td class="td-70">
												<div class="contents-input-div changeable">
													<input type="input" name="quantity"
														class="contents-input noline" value="${ printOrder.quantity }" readonly>
												</div>
											</td>
											<td class="td-100">
												<div class="contents-input-div changeable">
													<input type="input" name="price"
														class="contents-input noline" value="${ printOrder.price }" readonly>
												</div>
											</td>
											<td class="td-120">
												<div class="contents-input-div changeable">
													<input type="input" name="amount"
														class="contents-input noline" value="${ printOrder.amount }" readonly>
												</div>
											</td>
											<td class="td-50">
												<div class="contents-input-div changeable">
													<input type="input" name="state"
														class="contents-input noline" value="${ printOrder.state }" readonly>
												</div>
											</td>
											<td class="td-70">
												<input id="updateBtn_${ printOrder.printId }" type="button" name="update" class="contents-input-btn noline" 
												value="수정" onclick="onUpdate(${ printOrder.printId }); return false;">
												
												<input id="completeBtn_${ printOrder.printId }" type="button" name="update" class="contents-input-btn noline" 
												value="완료" onclick="submitUpdate(${ printOrder.printId }, 'poupdate.do'); return false;" style="display: none;">
												
												<input id="cancelBtn_${ printOrder.printId }" type="button" name="update" class="contents-input-btn noline" 
												value="취소" onclick="cancelUpdate(${ printOrder.printId }); return false;" style="display: none;">
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
					<button type="button" class="contents-input-btn big noline" id="btn_delete">선택삭제</button> 
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