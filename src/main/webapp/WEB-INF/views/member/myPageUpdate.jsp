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
<title>수정페이지</title>
<script type="text/javascript">
	function validate(){
	   
	   //암호와 암호확인이 일치하지 않는지 확인
	   //var pwdValue = document.getElementById("userpwd").value;
	   //var pwdValue2 = document.getElementById("userpwd2").value;
	   //jQuery 선택자 사용으로 바꾼다면
	   var pwdValue = $('#empPwd').val();
	   var pwdValue2 = $('#empPwd2').val();
	   
	   if(pwdValue !== pwdValue2){
	      alert("암호와 암호확인이 일치하지 않습니다. 다시 입력하세요.");
	      document.getElementById("empPwd").value = "";
	      document.getElementById("empPwd2").select();
	      return false;  //전송 취소함
	   }
	   return true;  //전송보냄
	}
	
	window.onload = function(){
		
	   //선택한 사진파일 이미지 미리보기 처리
	   var photofile = document.getElementById("photofile");
	   photofile.addEventListener('change', function(event){      
	      const files = event.currentTarget.files;
	       const file = files[0];
	       const myphoto = document.getElementById("photo");       
	       console.log(file.name);
	       
	       const reader = new FileReader();
	        reader.onload = (e) => {          
	          myphoto.setAttribute('src', e.target.result);
	          myphoto.setAttribute('data-file', file.name);
	        };
	        reader.readAsDataURL(file);    
	   });
	}
</script>
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

	<h1 align="center">수정페이지</h1>
	<br>
	<!-- 사진파일 첨부시 enctype="multipart/form-date" 속성 추가함 -->
	<form action="myupdate.do" id="enrollForm" method="post"
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
				<td><div class="select-pan-nemo">파일첨부</div> <c:url var="ndown"
						value="bfdown.do">
						<c:param name="ofile" value="${ notice.noticeOriginalFileName }" />
						<c:param name="rfile" value="${ notice.noticeRenameFileName }" />
					</c:url> <input type="file" name="file" id="photofile"> <input
					type="submit" value="Upload" name="submit"></td>
			<tr>
				<th width="120">사번</th>
				<td><input type="input" name="empId" id="empid" value="${ loginMember.empId }" readonly>
			</tr>
			<tr>
				<th>*암호</th>
				<td><input type="password" name="empPwd" id="empPwd" value="${ loginMember.empPwd }" required></td>
			</tr>
			<tr>
				<th>*암호확인</th>
				<td><input type="password" name="empPwd2" id="empPwd2" value="${ loginMember.empPwd }" required></td>
			</tr>
			<tr>
				<th>변경일</th>
				<td><input type="date" id="empPwdUpdate" value="${ loginMember.empPwdUpdate }" readonly></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="input" name="empName" value="${ loginMember.empName }" readonly></td>
			</tr>
			<tr>
				<th>*전화번호</th>
				<td><input type="input" name="phone" value="${ loginMember.phone }" required></td>
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
				<th>*주소</th>
				<td><input type="input" name="address" value="${ loginMember.address }" required></td>
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
				<th colspan="3"><input type="submit" value="수정하기">
					&nbsp; <input type="reset" value="수정취소"> &nbsp; <a
					href="main.do">시작페이지로 이동</a></th>
			</tr>
		</table>
	</form>
</body>
</html>