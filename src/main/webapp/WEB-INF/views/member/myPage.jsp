<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/notice.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script>
    const NOWPAGE = 7;
    const SUBPAGE = 1;
    const LNKPAGE = 1;
  
</script>
<title>마이페이지</title>
</head>
<body>
	<header class="main-header">
		<!--header-container-->
		<div class="header-container">
			<!-- 헤더 들어감 -->
			<c:import url="../common/header.jsp" />
		</div>
		<!--header-container end-->
	</header>

	<!--main-side-->
	<div class="main-side">
		<div class="side-container">
			<div class="side-title"></div>
			<div class="side-icon-box">
				<%-- <a
					href="${ pageContext.servletContext.contextPath }/views/work/notice_input.jsp"
					class="side-write-btn margin-bottom-0">글쓰기</a> --%>
			</div>

			<!-- 리스트 들어감 -->
			<c:import url="../common/side.jsp" />
		</div>
	</div>
	<!--main-side end-->

	<h1 align="center">마이페이지</h1>
	<br>
	<!-- 사진파일 첨부시 enctype="multipart/form-date" 속성 추가함 -->
	<form action="movemyupdate.do" id="enrollForm" method="post"
		onsubmit="return validate();">
		<table id="outer" align="center" width="700" cellspacing="5"
			cellpadding="0">

			<tr>
				<th colspan="3">내 정보</th>
				<td rowspan="7" width="100" align="center" valign="middle">
					<div id="myphoto"
						style="margin: 0; width: 150px; height: 160px; padding: 0; border: 1px solid navy;">
						<img src="/pageflow/resources/images/employeeprofile.png"
							id="photo"
							style="width: 150px; height: 160px; border: 1px solid navy; color: bule; display: block;"
							alt="사진을 드래그 드롭하세요."><br>
					</div>
				</td>
			<tr>
				<th width="120">사번</th>
				<td><input type="input" name="empId" id="empid" value="${ loginMember.empId }" readonly>
			</tr>
			<%-- <tr>
				<th>암호</th>
				<td><input type="password" name="empPwd" id="empPwd" value="${ loginMember.empPwd }" readonly></td>
			</tr>
			<tr>
				<th>암호확인</th>
				<td><input type="password" name="empPwd2" id="empPwd2" value="${ loginMember.empPwd }" readonly></td>
			</tr> --%>
			<tr>
				<th>변경일</th>
				<td><input type="date" id="empPwdUpdate" value="${ loginMember.empPwdUpdate }" readonly></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="input" name="empName" value="${ loginMember.empName }" readonly></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="input" name="phone" value="${ loginMember.phone }" readonly></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="input" name="empBirth" value="${ loginMember.empBirth }" readonly></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="input" name="email" value="${ loginMember.email }" readonly></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="input" name="address" value="${ loginMember.address }" readonly></td>
			</tr>
			<tr>
				<th>직급</th>
				<td><input type="input" name="jobName" value="${ loginMember.jobName }" readonly></td>
			</tr>
			<tr>
				<th>직책</th>
				<td><input name="input" name="posName" value="${ loginMember.posName }" readonly></td>
			</tr>
			<tr>
				<th>부서</th>
				<td><input type="input" name="depName" value="${ loginMember.depName }" readonly></td>
			</tr>
			<tr>
				<th>입사일</th>
				<td><input type="date" name="enrollDate" value="${ loginMember.enrollDate }" readonly></td>
			</tr>

			<tr>
				<th colspan="3"><input type="submit" value="수정페이지로 이동">
		</table>
	</form>
</body>
</html>