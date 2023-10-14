<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/login.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script>
    const NOWPAGE = 0;
    const SUBPAGE = 0;
    const LNKPAGE = 0;
</script>
<title></title>
</head>
<body>
	<div id="container">
        
        <main class="main-wrapper login">
            
            <div class="login-box">
                <form action="login.do" method="post">
                    <a class="login-logo" href="main.do">
                        <img src="${ pageContext.servletContext.contextPath }/resources/images/logo.png">
                    </a>

                    <div class="login-nemo login-name margin-top30px">
                        <input type="text" id="empid" placeholder="사번을 입력하세요." name="empId" required>
                        <i class='bx bxs-user' name='bx'></i>
                    </div>

                    <div class="login-nemo login-name">
                        <input type="password" id="emppwd" placeholder="비밀번호를 입력하세요." name="empPwd" required>
                        <i class='bx bxs-lock-alt' name="bx"></i>
                    </div>

                    <input class="login-btn" type="submit" name="login" value="로그인">
                </form>
            </div>

            <div class="margin-top10px">
                <a class="find-btn find-btn-id" href="#">사번찾기</a>
                <span>/</span>
                <a class="find-btn find-btn-pwd" href="#">비번찾기</a>
            </div>

        </main>
        
    </div>
</body>
</html>