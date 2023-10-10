<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<title>pageFlow</title>
<link rel="stylesheet" type="text/css" href="/pageflow/resources/css/main.css">
<script type="text/javascript" src="/pageflow/resources/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="/pageflow/resources/js/lib/chart.min.js"></script>
<script type="text/javascript" src="/pageflow/resources/js/page_info.js"></script>
<script type="text/javascript" src="/pageflow/resources/js/make_graph.js"></script>
<script>
    const NOWPAGE = 0;
    const SUBPAGE = 0;
    const LNKPAGE = 0;
    document.addEventListener("DOMContentLoaded", function(){
    	$('.main-container').addClass('all');
    	$('.main-side').hide();
    	
    });
</script>
</head>
<body>
	<body>
    <div id="container">
        
        <!--헤더-->
        <header class="main-header">
            <!--header-container-->
            <div class="header-container">
                <c:import url="./header.jsp" />
            </div>
            <!--header-container end-->
        </header>
        <!--헤더 end-->

        <main class="main-wrapper">
            
            <!--main-side-->
            <div class="main-side hidden">
                <div class="side-container">
                    <div class="side-title"></div>
                    <!-- 리스트 들어감 메인은 없음 -->
                </div>
            </div>
            <!--main-side end-->

            <!--main-container-->
            <div class="main-container">
                <!--내용-->
                <div class="main-contents-box main">
                    
                    <div class="mainbox-divide">
                        <div class="mainbox">

                            <div class="mainbox-con margin-bottom15">
                                <div class="main-header-bar">
                                    <div class="main-title-box main">
                                        <img src="./resources/images/header-icon.png">
                                        <span class="main-title">매출 현황</span>
                                    </div>
                                </div>
                                
                                <div class="mainbox-tablebox graph">
                                    <canvas id="myGraph" width="300" height="200">
                                    
                                    </canvas>
                                    <script>
                                        var make_graph = new Make_graph();
                                        make_graph.init('graph', 'myGraph');
                                    </script>
                                </div>
                            </div>

                            <div class="mainbox-con height-auto">
                                <div class="main-header-bar">
                                    <div class="main-title-box main">
                                        <img src="./resources/images/header-icon.png">
                                        <span class="main-title">공지 사항</span>
                                    </div>
                                </div>
                                
                                <div class="mainbox-tablebox">
                                    <ul class="mainbox-nlist">
                                        <a href="">
                                            <li>
                                                요즘 날씨가 많이 요상합니다.
                                                <span class="date">2023.09.01</span>
                                            </li>
                                        </a>
                                        <a href="">
                                            <li>
                                                근태관리 철저히 하세요.
                                                <span class="date">2023.09.11</span>
                                            </li> 
                                        </a>                                 
                                    </ul>
                                </div>
                            </div>

                        </div>
    
                        <div class="mainbox">
                            <div class="mainbox-con margin-bottom15">
                                <div class="main-header-bar">
                                    <div class="main-title-box main">
                                        <img src="./resources/images/header-icon.png">
                                        <span class="main-title">도서 트렌드</span>
                                    </div>
                                </div>
                                
                                <div class="mainbox-tablebox chart">
                                    <canvas id="myChart" width="200" height="200">
                                    
                                    </canvas>
                                    <script>
                                        make_graph.init('chart', 'myChart');
                                    </script>
                                </div>
                            </div>

                            <div class="mainbox-con height-auto">
                                <div class="main-header-bar">
                                    <div class="main-title-box main">
                                        <img src="./resources/images/header-icon.png">
                                        <span class="main-title">주문 요청</span>
                                    </div>
                                </div>
                                
                                <div class="mainbox-tablebox">
                                    <div class="mainbox-table">
                                        <table class="contents-table">
                                            <tr>
                                                <th>날짜</th>
                                                <th>품번</th>
                                                <th>품명</th>
                                                <th>거래처</th>
                                            </tr>
                                            
                                            <tr onclick="location.href='#'" class="cursor-pointer">
                                                <td class="td-70">
                                                    2023.09.11
                                                </td>
                                                <td class="td-50">
                                                    330923-1
                                                </td>
                                                <td class="td-150">
                                                    해리포터와 불
                                                </td>
                                                <td class="td-70">
                                                    교보문고
                                                </td>
                                            </tr>

                                            <tr onclick="location.href='#'" class="cursor-pointer">
                                                <td class="td-70">
                                                    2023.09.11
                                                </td>
                                                <td class="td-50">
                                                    330923-1
                                                </td>
                                                <td class="td-150">
                                                    간지의 제왕
                                                </td>
                                                <td class="td-70">
                                                    교보문고
                                                </td>
                                            </tr>

                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    

                </div>
                <!--내용 end-->

            </div>
            <!--main-container end-->
        </main>
        
	</div>
</body>
</html>