<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css"
	href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<link rel="stylesheet" type="text/css"
	href="${ pageContext.servletContext.contextPath }/resources/css/notice.css">
<script type="text/javascript"
	src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<title></title>
<script>
    const NOWPAGE = 1;
    const SUBPAGE = 1;
    const LNKPAGE = 1;
    
    function resiterPage() {
    	if(confirm('직원등록 페이지로 이동하시겠습니까?')) {
        window.location.href = 'enrollemp.do'; 
    	}
    }
    
</script>

<style>
/* 기본 버튼 스타일 */
.my-button {
	display: inline-block;
	margin: 20px;
	padding: 10px 40px;
	font-size: 16px;
	text-decoration: none;
	border: 2px solid #3498db;
	color: #3498db;
	border-radius: 5px;
	transition: all 0.3s ease;
}

/* 마우스 호버 효과 */
.my-button:hover {
	background-color: #3498db;
	color: white;
}

/* .popup {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .popup-content {
            background-color: #fefefe;
            margin: 10% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            text-align: center;
        } */
        
        
</style>

 

	<title>직원 등록 모달 창</title>
    

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

		<main class="main-wrapper">

			<!--main-side-->
			<div class="main-side">
				<div class="side-container">
					<div class="side-title"></div>
					<div class="side-icon-box">
						<a
							href="${ pageContext.servletContext.contextPath }/views/work/notice_input.jsp"
							class="side-write-btn margin-bottom-0">글쓰기</a>
					</div>

					<!-- 리스트 들어감 -->
					<c:import url="../common/side.jsp" />
				</div>
			</div>
			<!--main-side end-->

			<!--main-container-->
			<div class="main-container notice">

				<!--main-header-bar-->
				<div class="main-header-bar">
					<div class="main-title-box">
						<img
							src="${ pageContext.servletContext.contextPath }/resources/images/header-icon.png">
						<span class="main-title"> </span>
					</div>
					<button class="header-left-btn"></button>
				</div>
				<!--main-header-bar end-->
				<!--서치영역-->


				<!--내용-->
				<div class="main-contents-box">
				
				<h1 align="center">전체 직원 관리 페이지</h1>
				<h2 align="center">현재 직원 수 : ${requestScope.list.size() } 명</h2>

					<div class="contents-container sort-row">
						<div class="contents-box">
							<table class="contents-table">
								<tr>
									<th>사번</th>
									<th>이름</th>
									<th>전화번호</th>
									<th>생일</th>
									<th>이메일</th>
									<th>주소</th>
									<th>입사일</th>
									<th>직급번호</th>
									<th>직책번호</th>
									<th>부서번호</th>
								
								</tr>
								<c:if test="${ !empty list }">
									<c:forEach items="${ list }" var="emp">

										<tr data-parent="1" data-num="1" data-depth="1"
											class="table-td-depth1" id="tr_${ emp.empId }">

											<td class="td-250"><input type="input" name="empId"
												class="contents-input noline" value="${ emp.empId }">
											</td>
											<td class="td-100"><input type="input" name="empName"
												class="contents-input noline" value="${ emp.empName }">
											</td>
											<td class="td-100"><input type="input" name="phone"
												class="contents-input noline" value="${ emp.phone }">

											</td>
											<td class="td-100"><input type="input" name="empBirth"
												class="contents-input noline" value="${ emp.empBirth }">

											</td>
											<td class="td-100"><input type="input" name="email"
												class="contents-input noline" value="${ emp.email }">

											</td>
											<td class="td-100">
												<div class="contents-input-div">
													<input type="input" name="address"
														class="contents-input noline" value="${ emp.address }">
												</div>
											</td>
											<td class="td-100"><input type="input" name="enrollDate"
												class="contents-input noline" value="${ emp.enrollDate }"
												readonly></td>
											<td class="td-100">
												<div class="contents-input-div">
													<input type="inpute" name="jobId"
														class="contents-input noline" value="${  emp.jobId }"
														readonly>
												</div>
											</td>

											<td class="td-100">
												<div class="contents-input-div">
													<input type="inpute" name="posId"
														class="contents-input noline" value="${  emp.posId }"
														readonly>
												</div>
											</td>
											<td class="td-100">
												<div class="contents-input-div">
													<input type="input" name="depId"
														class="contents-input noline" value="${ emp.depId }"
														readonly>
												</div>
											</td>
											
											</td>
											
											</td>

										</tr>
									</c:forEach>
								</c:if>

							</table>
						</div>
					</div>

					
					
					 <!-- 직원 등록 버튼 -->
   		 
		
   
	<button onclick="resiterPage()" class="my-button" id="openModalBtn">직원 등록</button>
>
					
					
					
		

					<!--컨텐츠영역 end-->
</body>
</html>