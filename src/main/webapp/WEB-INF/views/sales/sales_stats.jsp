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
    const NOWPAGE = 5;
    const SUBPAGE = 4;
    const LNKPAGE = 1;

    $(function() {
        const table = $('#table_list');
        var monthly_sum = Array.from({length: 12}, () => 0);
        console.log('monthly_sum : ' + monthly_sum);

        // 컨텐츠 영역 합계
        const trList = table.find('tr.stats-data');
        trList.each(function() {
            var sum = 0;
            var month = 0;

            $(this).find('input.monthly-sales').each(function() {
                sum += Number($(this).val());
                monthly_sum[month] += Number($(this).val());
                month += 1;
            });

            $(this).find('input[name=sum]').val(sum);
        });

        // 집계 영역 합계
        const totalSumRow = table.find('tr.row-total-sum');
        var total_sum = 0;
        totalSumRow.find('td.monthly-total').each(function(index) {
            $(this).text(monthly_sum[index]);
            total_sum += monthly_sum[index];
        });
        $('#total_sum').text(total_sum);
    }); // document ready
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
                <div class="main-contents-box normal">

                    <!--서치영역-->
                    <div class="search-container">
                        <form class="search-form">
                            <div class="select-search">
                                <div class="select-box">
                                    <div class="select-pan">
                                        <label for="sel_code"></label>
                                        <select name="code" id="sel_code">
                                            <option value="">도서코드</option>
                                            <option value="">도서명</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="search-box">
                                    <button class="search-btn">
                                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                    </button>
                                    <input type="search" placeholder="키워드를 입력하세요." class="search-box-text" value="">
                                </div>
                            </div>

                            <div class="select-box">
                                <div class="select-pan">
                                    <label for="sel_code"></label>
                                    <select name="code" id="sel_code">
                                        <option value="all">매출년도</option>
                                        <option value="">2023년</option>
                                        <option value="">2022년</option>
                                        <option value="">2021년</option>
                                    </select>
                                </div>
                                
                                <div class="select-pan">
                                    <label for="start_month"></label>
                                    <select name="startMonth" id="start_month">
                                        <option value="all">매출월</option>
                                        <option value="jan">1월</option>
                                        <option value="feb">2월</option>
                                        <option value="mar">3월</option>
                                        <option value="apr">4월</option>
                                        <option value="may">5월</option>
                                        <option value="jun">6월</option>
                                        <option value="jul">7월</option>
                                        <option value="aug">8월</option>
                                        <option value="sep">9월</option>
                                        <option value="oct">10월</option>
                                        <option value="nov">11월</option>
                                        <option value="dec">12월</option>
                                    </select>
                                </div>

								<div>
									기간 지정
									<input type="date" class="select-date select-date-first" name="begin" value=${ begin }>
                                	<input type="date" class="select-date select-date-second" name="end" value=${ end }>
								</div>
                                
								<input type="button" class="contents-input-btn big noline" id="btn_chart" value="차트보기" onclick="chart">
                            </div>

                        </form>

                        <button class="search-visible-btn" id="search_visible_btn">
                            <img class="search-close" src="${ pageContext.servletContext.contextPath }/resources/images/cursor_1.png">
                            <img class="search-open" src="${ pageContext.servletContext.contextPath }/resources/images/cursor_2.png">
                        </button>

                    </div>
                    <!--서치영역 end-->

                    <!--컨텐츠영역-->
                    <div class="contents-container sort-row">
                        <div class="contents-box">
                            <table class="contents-table" id="table_list">
                                <tr>
                                    <th>도서코드</th>
                                    <th>도서명</th>
                                    <th>1월</th>
                                    <th>2월</th>
                                    <th>3월</th>
                                    <th>4월</th>
                                    <th>5월</th>
                                    <th>6월</th>
                                    <th>7월</th>
                                    <th>8월</th>
                                    <th>9월</th>
                                    <th>10월</th>
                                    <th>11월</th>
                                    <th>12월</th>
                                    <th>합계</th>
                                </tr>
                                <c:if test="${ !empty list }">
                                	<c:forEach items="${ list }" var="stats">
		                                <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1 stats-data">
		                                    <td class="td-70">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="bookId" class="contents-input noline" value="${ stats.bookId }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-200">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="bookName" class="contents-input noline" value="${ stats.bookName }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="m01" class="contents-input noline monthly-sales m01" value="${ stats.salesMonth01 }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="m02" class="contents-input noline monthly-sales m02" value="${ stats.salesMonth02 }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="m03" class="contents-input noline monthly-sales m03" value="${ stats.salesMonth03 }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="m04" class="contents-input noline monthly-sales m04" value="${ stats.salesMonth04 }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="m05" class="contents-input noline monthly-sales m05" value="${ stats.salesMonth05 }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="m06" class="contents-input noline monthly-sales m06" value="${ stats.salesMonth06 }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="m07" class="contents-input noline monthly-sales m07" value="${ stats.salesMonth07 }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="m08" class="contents-input noline monthly-sales m08" value="${ stats.salesMonth08 }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="m09" class="contents-input noline monthly-sales m09" value="${ stats.salesMonth09 }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="m10" class="contents-input noline monthly-sales m10" value="${ stats.salesMonth10 }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="m11" class="contents-input noline monthly-sales m11" value="${ stats.salesMonth11 }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-50">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="m12" class="contents-input noline monthly-sales m12" value="${ stats.salesMonth12 }" readonly>
		                                        </div>
		                                    </td>
		                                    <td class="td-70">
		                                        <div class="contents-input-div">
		                                            <input type="input" name="sum" class="contents-input noline" value="" readonly>
		                                        </div>
		                                    </td>
		                                </tr>
	                                </c:forEach>
                                </c:if>

                                <!--합계-->
                                <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1 sum row-total-sum">
                                    <td></td>
                                    <td>전체합계</td>
                                    <td class="monthly-total"></td>
                                    <td class="monthly-total"></td>
                                    <td class="monthly-total"></td>
                                    <td class="monthly-total"></td>
                                    <td class="monthly-total"></td>
                                    <td class="monthly-total"></td>
                                    <td class="monthly-total"></td>
                                    <td class="monthly-total"></td>
                                    <td class="monthly-total"></td>
                                    <td class="monthly-total"></td>
                                    <td class="monthly-total"></td>
                                    <td class="monthly-total"></td>
                                    <td id="total_sum"></td>
                                </tr>
                                <!--합계end-->

                                <!--출고금액-->
                                <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1 sum">
                                    <td></td>
                                    <td>출고금액</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <!--출고금액end-->

                                <!--반입금액-->
                                <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1 sum">
                                    <td></td>
                                    <td>반입금액</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <!--반입금액end-->

                                <!--순출고금액-->
                                <tr data-parent="1" data-num="1" data-depth="1" class="table-td-depth1 sum">
                                    <td></td>
                                    <td>순출고금액</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <!--순출고금액end-->

                            </table>

                            <div class="mainbox-tablebox graph width-65vw">
                                <canvas id="myGraph" width="300" height="200">
                                
                                </canvas>
                                <script>
                                    var make_graph = new Make_graph();
                                    make_graph.init('graph', 'myGraph');
                                </script>
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