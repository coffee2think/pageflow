<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<style type="text/css">
.msgbox-outer {
	border: 1px solid #9fc7ff;
	width: 500px;
	height: 500px;
}

.msgbox-header {
	border: 1px solid #9fc7ff;
	width: 500px;
	height: 50px;
	line-height: 50px;
	display: flex;
	align-items: center;
}

.msgbox-icon {
	margin: 5px;
}

.space-between {
	display: flex;
	justify-content: space-between;
}
</style>
<script>
    const NOWPAGE = 7;
    const SUBPAGE = 1;
    const LNKPAGE = 2;
    
    function deleteMsg() {
    	// post 방식으로 선택된 쪽지 삭제
    }
</script>
<title></title>
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
        <!--헤더 end-->

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
					<div class="msgbox-outer">
						<div class="msgbox-header">
							<img class="msgbox-icon" src="${ pageContext.servletContext.contextPath }/resources/images/msgbox-icon.png" width="40" height="40"><h1>쪽지함</h1>
						</div>
						<div class="space-between">
							<div class="left">
								<a href="#">받은 쪽지</a> | <a href="#">보낸 쪽지</a>
							</div>
							<div class="right">
								필터
								<select id="read_status">
									<option value="read">안 읽음</option>
									<option value="unread">읽음</option>
								</select>
							</div>
						</div>
						<div>
							<table class="contents-table">
								<tr>
									<th>보낸이</th>
									<th>제목</th>
									<th>받은날짜</th>
									<th>읽음여부</th>
									<th>삭제</th>
								</tr>
								<c:if test="${ !empty list }">
									<c:forEach items="${ list }" var="msg">
										<tr>
											<td>${ msg.senderName } ${ msg.senderPosName }</td>
											<td>${ msg.messTitle }</td>
											<td>${ msg.sendTime }</td>
											<td>
												<c:if test="${ msg.readChk == 'Y' }">
													읽음
												</c:if>
												<c:if test="${ msg.readChk != 'Y' }">
													안읽음
												</c:if>
											</td>
											<td>
												<a href="#">삭제</a>
											</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${ empty list }">
									<tr><td colspan="5">쪽지가 없습니다.</td></tr>
								</c:if>
								<tr>
								
								</tr>
							</table>
							<div class="space-between">
								<div class="delete-btn">
									<input type="button" id="btn_delete" value="삭제" onclick="deleteMsg();">
								</div>
								<div class="send-btn">
									<input type="button" id="btn_send" value="쪽지 보내기" onclick="javascript: location.href='moveWriteMsg.do'">
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