<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css"
	href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<link rel="stylesheet" type="text/css"
	href="${ pageContext.servletContext.contextPath }/resources/css/notice.css">
<script type="text/javascript"
	src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
function validate(){
   
   //암호와 암호확인이 일치하지 않는지 확인
   //var pwdValue = document.getElementById("userpwd").value;
   //var pwdValue2 = document.getElementById("userpwd2").value;
   //jQuery 선택자 사용으로 바꾼다면
   var pwdValue = $('#emppwd').val();
   var pwdValue2 = $('#emppwd2').val();
   
   if(pwdValue !== pwdValue2){
      alert("암호와 암호확인이 일치하지 않습니다. 다시 입력하세요.");
      document.getElementById("emppwd").value = "";
      document.getElementById("emppwd2").select();
      return false;  //전송 취소함
   }
   
   //아이디의 값 형식이 요구한 대로 작성되었는지 검사
   //암호의 값 형식이 요구한 대로 작성되었는지 검사
   //정규표현식(Regular Expression) 사용함
   
   return true;  //전송보냄
}
function dupIDCheck(){
   //사용 가능한 아이디인지 확인하는 함수 : ajax 기술 사용해야 함
   
   $.ajax({
      url: "idchk.do",
      type: "post",
      data: { userid: $('#empid').val() },
      success: function(data){
         console.log("success : " + data);
         if(data == "ok"){
            alert("사용 가능한 아이디입니다.");
            $('#userpwd').focus();
         }else{
            alert("이미 사용중인 아이디입니다.");
            $('#empid').select();
         }
      },
      error: function(jqXHR, textStatus, errorThrown){
         console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
      }
   });
   
   return false;  //버튼 클릭이벤트 취소 (submit 버튼에 클릭이벤트 전달을 막기 위함)
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
				<a
					href="${ pageContext.servletContext.contextPath }/views/work/notice_input.jsp"
					class="side-write-btn margin-bottom-0">글쓰기</a>
			</div>

			<!-- 리스트 들어감 -->
			<c:import url="../common/side.jsp" />
		</div>
	</div>
	<!--main-side end-->

	<h1 align="center">직원 정보 수정 페이지</h1>
	<br>
	<!-- 사진파일 첨부시 enctype="multipart/form-date" 속성 추가함 -->
	<form action="empinsert.do" id="enrollForm" method="post"
		onsubmit="return validate();">
		<table id="outer" align="center" width="700" cellspacing="5"
			cellpadding="0">

			<tr>
				<th colspan="3">직원 정보를 입력해 주세요. (* 표시는 필수입력 항목입니다.)</th>
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
					</c:url> <input type="file" name="file" id="photofile"> <input
					type="submit" value="Upload" name="submit"></td>
			<tr>
				<th width="120">사번</th>
				<td><input type="text" name="empId" id="empid" value="${employee.empId }" readonly>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="empName"  value="${employee.empName }" readonly></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="tel" name="phone" value="${employee.phone }"></td>
			</tr>

				<th>이메일</th>
				<td><input type="email" name="email" value="${employee.email }"></td>
			<tr>
				<th>주소</th>
				<td><input type="address" name="address" value="${employee.address}" required></td>
			<tr>
				<th>직급</th>
				<td><select name="jobId" value="${employee.jobId }">
						<option value="1">사원</option>
						<option value="2">대리</option>
						<option value="3">과장</option>
						<option value="4">차장</option>
						<option value="5">부장</option>
				</select></td>
			<tr>
				<th>직책</th>
				<td><select name="posId" value="${employee.posId }">
						<option value="1">직원</option>
						<option value="2">팀장</option>
						<option value="3">차장</option>
						<option value="4">실장</option></td>
			<tr>
				<th>부서</th>
				<td><select name="depId" value="${employee.depId }">
						<option value="2">기획</option>
						<option value="3">디자인</option>
						<option value="4">교정</option>
						<option value="1">개발</option></td>
			</tr>

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