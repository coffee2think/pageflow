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
/* Content Styles */
h1 {
	text-align: center;
	margin-top: 20px;
}

/* Form Styles */
table {
	border-collapse: collapse;
	width: 100%;
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
input[type="submit"], input[type="reset"] {
	background-color: #007bff;
	color: white;
	border: none;
	cursor: pointer;
	margin-top: 10px;
	display: inline-block;
	margin: 0 10px;
	padding: 10px 20px;
}

/* Reset Button Styles */
input[type="reset"] {
	background-color: #f2f2f2;
	color: black;
	border: 1px solid black;
	cursor: pointer;
}
</style>
<script type="text/javascript"
	src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script>
    const NOWPAGE = 7;
    const SUBPAGE = 1;
    const LNKPAGE = 1;
    
    function validate(){
    	//암호확인의 포커스가 사라질 때 암호와 암호확인 값 일치하는지 체크
    	var pwd1 = document.getElementById("empPwd").value;
    	var pwd2 = document.getElementById("empPwd2").value;
    	
    	if (pwd1 === "" || pwd2 === "") {
    		alert("비밀번호를 입력하지 않으셨습니다.\n다시 수정하세요.");
    		return false;
    	}
    	
    	if(pwd1 !== pwd2) {
    		alert("암호와 암호 확인이 일치하지 않습니다.\n다시 수정하세요.");
    		document.getElementById("empPwd2").value = "";		//암호확인의 값 지움
    		document.getElementById("empPwd2").focus();
    		return false;
    	}
    	
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
<title>수정페이지</title>
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

				<!--내용-->
                <div class="main-contents-box">
					<h1 align="center">수정페이지</h1>
					<br>
					<!-- 사진파일 첨부시 enctype="multipart/form-date" 속성 추가함 -->
					<form action="myupdate.do?empId=${ employee.empId }" id="enrollForm" method="post" enctype="multipart/form-data" onsubmit="return validate();">
						<input type="hidden" name="origin_emppwd" value="${ employee.empPwd }">
						<input type="hidden" name="profile" value="${employee.profile }">
						<table id="outer" align="center" width="700" cellspacing="5" cellpadding="0">
							<tr>
								<th colspan="3">내 정보</th>
								<td rowspan="7" width="100" align="center" valign="middle">
									<div id="myphoto" style="margin: 0; width: 150px; height: 160px; padding: 0; border: 1px solid navy;">
										<img src="/pageflow/resources/member_upfiles/${employee.profile }" id="photo" style="width: 150px; height: 160px; border: 1px solid navy; color: bule; display: block;" alt="사진을 드래그 드롭하세요."><br>
										<div class="select-pan-nemo" style="width: 150px;">파일첨부</div>
										<input type="file" name="upfile" id="photofile" style="width: 150px;">
									</div>
								</td>
							</tr>
							<tr>
								<th width="120">사번</th>
								<td>${employee.empId}</td>
								<%-- <td><input type="text" name="empId" id="empid" value="${ employee.empId }" readonly> --%>
							</tr>
							<tr>
								<th>이름</th>
								<td>${employee.empName}</td>
								<%-- <td><input type="text" name="empName" value="${ employee.empName }" readonly></td> --%>
							</tr>
							<tr>
								<th>*암호</th>
								<td><input type="password" name="empPwd" id="empPwd"></td>
							</tr>
							<tr>
								<th>*암호확인</th>
								<td><input type="password" name="empPwd2" id="empPwd2"></td>
							</tr>
							<tr>
								<th>*전화번호</th>
								<td><input type="text" name="phone" value="${ employee.phone }"></td>
							</tr>
							<tr>
								<th>*주소</th>
								<td><input type="text" name="address"
									value="${ employee.address }" style="width: 250px;"></td>
							</tr>
							<tr>
								<th colspan="3"><input type="submit" value="수정하기"> <input
									type="reset" value="수정취소"> <br> <a href="main.do">시작페이지로
										이동</a></th>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</main>
	</div>
</body>
</html>