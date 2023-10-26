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
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/chart.min.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/page_info.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/make_graph.js"></script>
<script>
    const NOWPAGE = 0;
    const SUBPAGE = 0;
    const LNKPAGE = 0;
    document.addEventListener("DOMContentLoaded", function(){
    	$('.main-container').addClass('all');
    	$('.main-side').hide();

    });
</script>
<script type="text/javascript">
$(function(){
	// 주문 요청 ajax
	$.ajax({
		url: "booktop3.do",
		type: "post",
		dataType: "json",
		success: function(data){
			console.log("success : " + data);
			
			var str = JSON.stringify(data);
			
			var json = JSON.parse(str);
			
			values = "";
			for(var i in json.list){
				values += "<tr class='cursor-pointer'><a href='movebolist.do'>"
				       + "<td class='td-70'>"
				       + json.list[i].bdate
				       + "</td>"
				       + "<td class='td-50'>"
				       + json.list[i].orderId
				       + "</td>"
				       + "<td class='td-150'>"
				       + decodeURIComponent(json.list[i].bookName).replace(/\+/gi, " ") 
				       + "</td>"
				       + "<td class='td-70'>"
				       + decodeURIComponent(json.list[i].clientName).replace(/\+/gi, " ") 
				       + "</td>"
				       + "</a>"
				       + "</tr>";
			}
			$('#bookTopList').html($('#bookTopList').html() + values);
		},
		error: function(jqXHR, textStatus, errorThrown){
			console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
		}
	}); // ajax
	
	// 공지사항
	$.ajax({
		url: "ntop.do",
		type: "post",
		dataType: "json",
		success: function(data){
			console.log("success : " + data);

			var str = JSON.stringify(data);

			var json = JSON.parse(str);

			values = "";
			for(var i in json.nlist){
				values += "<li>"
				       + decodeURIComponent(json.nlist[i].noticeTitle).replace(/\+/gi, " ")
			           + "<span class='date'>"
					   + json.nlist[i].ndate
				 	   + "</span>"
			    	   + "</li>";
			}
			$('#newnotice').html($('#newnotice').html() + values);

		},
		error: function(jqXHR, textStatus, errorThrown){
			console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
		}
	});

    //그래프!!
    $.ajax({
		url: "maingraph.do",
		type: "post",
		dataType: "json",
		success: function(data){
			
			var str = JSON.stringify(data);
			var json = JSON.parse(str);
            var statsList = json.statsList;
            var numData = [];
            console.log("data : " + data);
            console.log("str : " + str);
            var ndata = {
                num_1 : 0
            };
            
            var ndata = {};

            for(var i=0; i<12; i++) {
                ndata['num_' + (i+1)] = 0;

                for(var j=0; j<statsList.length; j++) {
                    ndata['num_' + (i+1)] += Number(statsList[j]['salesMonth' + digit(i+1)]);
                }
                
            }
            //var ndatastr = JSON.stringify(ndata);
            var dataArray = [];
            for (var i = 1; i <= 12; i++) {
                var key = 'num_' + i;
                dataArray.push(ndata[key]);
            }
            console.log('dataArray : ' + dataArray);
            var make_graph1 = new Make_graph();
            make_graph1.init('graph', 'myGraph', dataArray);

		},
		error: function(jqXHR, textStatus, errorThrown){
			console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
		}
	});

    //차트
    $.ajax({
		url: "mainchart.do",
		type: "post",
		dataType: "json",
		success: function(data){
			
			var str = JSON.stringify(data);
			var json = JSON.parse(str);
            
            console.log('rankList : ' + json.rankList);
            
            var labelArr = [];
            var rankArr = [];
            var background = [];

           
            for(var i=0; i<json.rankList.length; i++) {
                labelArr.push(json.rankList[i].label);
                rankArr.push(json.rankList[i].rank);

                var RGB_1 = Math.floor(Math.random() * (255 + 1))
                var RGB_2 = Math.floor(Math.random() * (255 + 1))
                var RGB_3 = Math.floor(Math.random() * (255 + 1))
                var strRGBA = 'rgba(' + RGB_1 + ',' + RGB_2 + ',' + RGB_3 + ',0.5)'
                background.push(strRGBA);
            }

            var make_graph2 = new Make_graph();
            make_graph2.init('chart', 'myChart', {
                label : labelArr, 
                rank: rankArr,
                background : background

            });

		},
		error: function(jqXHR, textStatus, errorThrown){
			console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
		}
	});

	
}); // function

function digit(c) {
    var str = '';
    if(c < 10) str = '0' + c;
    else str = c;
    return str;
}
</script>
</head>
<body>
	<body>
    <div id="container">
        
        <!--헤더-->
        <header class="main-header">
            <!--header-container-->
            <div class="header-container">
                <c:import url="/WEB-INF/views/common/header.jsp" />
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
								    <canvas id="myGraph" width="300" height="200"></canvas>
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
                                    <a href="nlist.do" class="mainbox-table-a">
                                        <ul class="mainbox-nlist" id="newnotice">
                                        </ul>
                                    </a>
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
                                    <canvas id="myChart" width="200" height="200"></canvas>
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
                                        <a href="movebolist.do" class="mainbox-table-a">
                                            <table class="contents-table" id="bookTopList">
                                                <tr>
                                                    <th>날짜</th>
                                                    <th>품번</th>
                                                    <th>품명</th>
                                                    <th>거래처</th>
                                                </tr>

                                            </table>
                                        </a>
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