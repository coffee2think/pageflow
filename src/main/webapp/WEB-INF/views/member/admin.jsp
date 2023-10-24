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
    const SUBPAGE = 3;
    const LNKPAGE = 1;
    
    
    function resiterPage() {
    	if(confirm('직원등록 페이지로 이동하시겠습니까?')) {
        window.location.href = 'empmoveinsert.do'; 
    	}
    }
    
    function onUpdate(empId) {
        // empId를 기반으로 수정 페이지의 URL을 생성합니다.
        //var updatePageUrl = '/updatePage?id=' + empId;
        
        // 수정 페이지로 이동합니다.
        if(confirm('직원수정 페이지로 이동하시겠습니까?')){
        	  window.location.href = 'empmoveupdate.do?empId=' + empId;
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
				<c:import url="/WEB-INF/views/common/header.jsp" />
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
			<div class="main-container ">

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
				<!--서치영역-->

				<!--서치영역-->

				<div class="search-container noline">
					<form class="search-form" action="msearch.do" method="post">

						<div class="select-search">
							<div class="select-box">
								<div class="select-pan">
									<label for="sel_code"></label> <select name="searchType"
										id="sel_code">
										<option selected enabled="false">==항목선택==</option>
										<option value="emp">직원 이름</option>
										<option value="dept">부서명</option>
									</select>
								</div>
							</div>

							<div class="search-box">
								<button class="search-btn" type="submit">
									<img class="search-image"
										src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
								</button>
								<input type="search" placeholder="키워드를 입력하세요." name="keyword"
									class="search-box-text" value="" id="nkeyword">
							</div>
						</div>
					</form>
				</div>

				<!--서치영역 end-->


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
									<th>직급</th>
									<th>직책</th>
									<th>부서</th>
									<th>수정</th>

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
														class="contents-input noline" value="${  emp.jobName }"
														readonly>
												</div>
											</td>

											<td class="td-100">
												<div class="contents-input-div">
													<input type="inpute" name="posId"
														class="contents-input noline" value="${  emp.posName }"
														readonly>
												</div>
											</td>
											<td class="td-100">
												<div class="contents-input-div">
													<input type="input" name="depId"
														class="contents-input noline" value="${ emp.depName }"
														readonly>
												</div>
											</td>

											<td class="td-100">
												<div class="contents-input-div">
													<input type="button" class="contents-input-btn noline"
														value="수정" id="updateBtn_${ emp.empId }"
														onclick="onUpdate(${ emp.empId }); return false;">
												</div>
											</td>

										</tr>
									</c:forEach>
								</c:if>

							</table>
							<!-- 페이징 -->
							<div class="paging-box">
								<c:import url="../common/paging.jsp" />
							</div>
							<div>
								<button onclick="resiterPage()" class="my-button"
									id="openModalBtn">직원 등록</button>
							</div>
						</div>
					</div>

				</div>
			</div>

		</main>

		<!-- 직원 등록 버튼 -->


		<!--컨텐츠영역 end-->





	</div>



</body>
</html>