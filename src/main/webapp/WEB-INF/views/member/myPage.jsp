<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css"
	href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<style>
    .main-contents-box {
        padding-top: 0px;
    }

/* Page Styles */
body {
	background-color: white;
	margin: 0;
	padding: 0;
}

/* Content Styles */
h1 {
	text-align: center;
	margin-top: 20px;
}

/* Form Styles */
table {
    border-collapse: collapse;
    width: 700px;
    max-width: 700px;
    margin: 0 auto;
}

th {
	border: 1px solid #ccc;
	padding: 10px;
	text-align: center;
	background-color: #f2f2f2;
}

td {
	border: 1px solid #ccc;
	padding: 10px;
	text-align: left;
}

/* Photo Styles */
#myphoto {
	width: 150px;
	height: 160px;
	border: 2px solid navy;
	text-align: center;
	padding: 10px;
	margin: 0 auto;
}

#photo {
	width: 100%;
	height: 100%;
	border: 1px solid navy;
	display: block;
}

/* Submit Button Styles */
input[type="submit"] {
	background-color: #007bff;
	color: white;
	padding: 10px 20px;
	border: none;
	cursor: pointer;
	margin-top: 10px;
	display: block;
	margin: 0 auto;
}
</style>
<script type="text/javascript"
	src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script>
    const NOWPAGE = 7;
    const SUBPAGE = 1;
    const LNKPAGE = 1;
    
    function goMyPage() {
        var empId = ${employee.empId};

        var myPageUrl = 'movemypage.do?empId=' + empId;
        
    	window.location.href = myPageUrl;
    }
    
</script>
<title>마이페이지</title>
</head>
<body>
    <div id="container">
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

                <div>
                    <h1 align="center">마이페이지</h1>
                </div>
                <!--내용-->
                <div class="main-contents-box">
                    <!-- 사진파일 첨부시 enctype="multipart/form-date" 속성 추가함 -->
                    <form action="movemyupdate.do?empId=${ employee.empId }" id="enrollForm" method="post" onsubmit="return validate();">
                        <table id="outer" align="center" width="700" cellspacing="5" cellpadding="0">
                            <tr>
                                <th colspan="3">내 정보</th>
                                <td rowspan="7" width="100" align="center" valign="middle">
                                    <div id="myphoto" style="margin: 0; width: 150px; height: 160px; padding: 0; border: 1px solid navy;">
                                    	<c:choose>
										    <c:when test="${empty employee.profile}">
										        <img src="/pageflow/resources/images/profile.png" id="imageElement" style="width: 150px; height: 160px; border: 1px solid navy; color: blue; display: block;">
										    </c:when>
										    <c:otherwise>
										        <img src="/pageflow/resources/member_upfiles/${employee.profile}" id="photo" style="width: 150px; height: 160px; border: 1px solid navy; color: blue; display: block;">
										    </c:otherwise>
										</c:choose>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th width="120">사번</th>
                                <td>${employee.empId}</td>
                            </tr>
                            <tr>
                                <th>이름</th>
                                <td>${employee.empName}</td>
                            </tr>
                            <tr>
                                <th>전화번호</th>
                                <td>${employee.phone}</td>
                            </tr>
                            <tr>
                                <th>생년월일</th>
                                <td>${employee.empBirth}</td>
                            </tr>
                            <tr>
                                <th>이메일</th>
                                <td>${employee.email}</td>
                            </tr>
                            <tr>
                                <th>주소</th>
                                <td>${employee.address}</td>
                            </tr>
                            <tr>
                                <th>직급</th>
                                <td>${employee.jobName}</td>
                            </tr>
                            <tr>
                                <th>직책</th>
                                <td>${employee.posName}</td>
                            </tr>
                            <tr>
                                <th>부서</th>
                                <td>${employee.depName}</td>
                            </tr>
                            <tr>
                                <th>입사일</th>
                                <td>${employee.enrollDate}</td>
                            </tr>
                            <tr>
                                <th colspan="3"><input type="submit" value="수정페이지로 이동"></th>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </main>
    </div>
</body>
</html>