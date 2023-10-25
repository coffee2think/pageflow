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
	
}); // function
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
                                
                                <div class="mainbox-tablebox graph width-65vw" id="myChart">
								    <canvas id="myChart" width="300" height="200"></canvas>
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
                                    <ul class="mainbox-nlist" id="newnotice">
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
                                        <table class="contents-table" id="bookTopList">
                                            <tr>
                                                <th>날짜</th>
                                                <th>품번</th>
                                                <th>품명</th>
                                                <th>거래처</th>
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