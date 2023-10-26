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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.bundle.min.js"></script>
<script>
    const NOWPAGE = 5;
    const SUBPAGE = 4;
    const LNKPAGE = 2;
    
    var categories = [];
    var category_ratio = [];
    var background = [];
    
    $(function() {
    	$('.category').each(function() {
    		categories.push($(this).attr('name'));
    		category_ratio.push($(this).val());
    		
    		// Set backgroundColor 랜덤하게 값 추가 ( 투명도 30% )
    		var RGB_1 = Math.floor(Math.random() * (255 + 1))
    		var RGB_2 = Math.floor(Math.random() * (255 + 1))
    		var RGB_3 = Math.floor(Math.random() * (255 + 1))
    		var strRGBA = 'rgba(' + RGB_1 + ',' + RGB_2 + ',' + RGB_3 + ',0.3)'
    		background.push(strRGBA);
    	});
    	
    	console.log('categories : ' + categories);
    	console.log('ratio : ' + category_ratio);
    	
    	drawChart();
    });
    
    
    var chartData = {
		labels: categories,
		datasets: [{
			data: category_ratio,
			backgroundColor: background
		}] 
    }
    
    function drawChart() {
    	let ctx = document.getElementById('myChart').getContext('2d');
    	
    	window.pieChart = new Chart(ctx, {
    		type: 'doughnut',
    		data: chartData,
    		options: {
    			responsive: false,
    			legend: {
    				position: 'bottom' 
    			}
    		}
    	});
    }
</script>
<title>도서 트렌드 분석</title>
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
                            <div class="contents-title contents-title-chart">
								<!-- 카테고리별 원형 차트 -->
								<c:if test="${ !empty map }">
									<c:forEach items="${ map }" var="category">
										<input type="hidden" class="category" name="${ category.key }" value="${ category.value }">
									</c:forEach>
								</c:if>
								
								
                            </div>
							<div style="display: flex; ">
	                            <div class="" id="chart">
	                                <canvas id="myChart" width="500" height="500" style="margin: 50px;">
	                                </canvas>
	                            </div>
	                            <!-- 카테고리별 원형 차트 end -->
	
	                            <!-- 랭킹 테이블 -->
								<div>	
									<table class="contents-table sales" id="table_list">
										<tr>
											<th>순위</th>
											<th>도서명</th>
											<!-- <th>ISBN</th> -->
											<th>카테고리</th>
											<th>책 타입</th>
										</tr>
										<c:if test="${ !empty list }">
											<c:forEach items="${ list }" var="book">
												<tr>
													<td>${ book.rank }</td>
													<td>${ book.bookName }</td>
													<%-- <td>${ book.isbn }</td> --%>
													<td>${ book.category }</td>
													<td>${ book.bookType }</td>
												</tr>
											</c:forEach>
										</c:if>
									</table>
								</div>
								<!-- 랭킹 테이블 end -->
							</div>
                        </div>
                    </div>
                    <!--컨텐츠영역 end-->

                </div>
                <!--내용 end-->

                <!--
                <div class="submit-box">
                    <input type="button" class="contents-input-btn big noline" id="btn_delete" value="선택삭제">
                </div>
                -->
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