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
    const LNKPAGE = 2;
    
    
    function resiterPage() {
    	if(confirm('직원등록 페이지로 이동하시겠습니까?')) {
        window.location.href = 'empmoveinsert.do'; 
    	}
    }
    
    function listPage() {
    	
        window.location.href = 'mnauthority.do'; 
    	
    }
    
    function onUpdate(empId) {
        // empId를 기반으로 수정 페이지의 URL을 생성합니다.
        //var updatePageUrl = '/updatePage?id=' + empId;
        
        // 수정 페이지로 이동합니다.
        if(confirm('직원수정 페이지로 이동하시겠습니까?')){
        	  window.location.href = 'empmoveupdate.do?empId=' + empId;
        }
      
    }
    
    function changeLogin(element){
    	//checkbox 의 체크 상태가 변경된(change) input 태그의 name 속성값에서 empId 를 분리 추출함
    	var empId = element.name.substring(8);
    	console.log("empId : " + empId);
    	
    	if(element.checked == true ){
    		//alert("로그인 허용을 체크함");
    		location.href = "${ pageContext.servletContext.contextPath }/loginok.do?empId=" + empId + "&loginOk=Y";
    	}else{
    		//alert("로그인 제한을 체크함");
    		location.href = "${ pageContext.servletContext.contextPath }/loginok.do?empId=" + empId + "&loginOk=N";
    	}
    }
    
    function changeAdmin(element){
    	//checkbox 의 체크 상태가 변경된(change) input 태그의 name 속성값에서 empId 를 분리 추출함
    	var empId = element.name.substring(8);
    	console.log("empId : " + empId);
    	
    	if(element.checked == true ){
    		// 체크한 경우
    		console.log("관리자 허용 안 함을  체크함");
    		location.href = "${ pageContext.servletContext.contextPath }/adminyn.do?empId=" + empId + "&adminYN=Y";
    	}else{
    		// 체크안한 경우
    		console.log("관리자 허용을 체크함");
    		location.href = "${ pageContext.servletContext.contextPath }/adminyn.do?empId=" + empId + "&adminYN=N";
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

.search-box {
	margin: 15px;
}

</style>
<title>직원 등록</title>
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
					<form class="search-form" action="authsearch.do" method="post">
					<input type="hidden" name="searchType" value="deptpos">
						<div class="select-search">
							<div class="search-box">
								<label for="deptkeyword">부서명</label>
								<button class="search-btn">
									<img class="search-image"
										src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
								</button>
								<input type="search" placeholder="부서명을 입력하세요." name="keyword"
									class="search-box-text" value="" id="deptkeyword">
							</div>

							<div class="search-box">
								<label for="jobkeyword">직책</label>
								<button class="search-btn" type="submit">
									<img class="search-image"
										src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
								</button>
								<input type="search" placeholder="직책을 입력하세요." name="keyword"
									class="search-box-text" value="" id="jobkeyword"> <input
									type="submit" value="검색">
							</div>
						</div>
					</form>
				</div>

				<!--서치영역 end-->


				<!--내용-->
				<div class="main-contents-box">

					<div class="contents-container sort-row">
						<div class="contents-box">
							<table class="contents-table">
								<tr>
									<th>사번</th>
									<th>이름</th>
									<th>직급</th>
									<th>직책</th>
									<th>부서</th>
									<th>관리자</th>
									<th>로그인허용</th>
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
										<c:if test="${emp.adminYN eq 'Y' }">
											 <input type="checkbox" name="adminyn_${emp.empId }"
														value="Y" onchange="changeAdmin(this);" checked> 
										</c:if>
										<c:if test="${emp.adminYN eq 'N' }">
											 <input type="checkbox" name="adminyn_${emp.empId }"
											  value="Y" onchange="changeAdmin(this);" > 
										</c:if>
												</div>
											</td>

										<td class="td-100">
											<div class="contents-input-div">
										<c:if test="${emp.loginOk eq 'Y' }">
											 <input type="checkbox" name="loginok_${emp.empId }"
														value="Y" onchange="changeLogin(this);" checked> 
										</c:if>
										<c:if test="${emp.loginOk eq 'N' }">
											 <input type="checkbox" name="loginok_${emp.empId }"
											  value="Y" onchange="changeLogin(this);" > 
										</c:if>
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
								<button onclick="listPage()" class="my-button" id="listBtn">목록</button>
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