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
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/notice.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<title></title>
<script>
	const NOWPAGE = 1;
	const SUBPAGE = 1;
	const LNKPAGE = 1;

	$(function() {
		// div 영역의 공지 내용 textarea에 담기
		$('#btn_update').on('click', function() {
			$('#appendTextArea').val($('.content-input').text());
			$('#upnotice').submit();
		});
     });
	
	function inputSubmit() {
    	$('#btn_update').click();
	}
</script>
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

                    <c:if test="${ !empty loginMember }">
                        <c:import url="../common/side_icon.jsp" />
                    </c:if>

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
                        <img src="${ pageContext.servletContext.contextPath }/resources/images/header-icon.png">
                        <span class="main-title"></span>
                    </div>
                    <button class="header-left-btn">
                    </button>
                </div>
                <!--main-header-bar end-->

                <!--내용-->
                <div class="main-contents-box normal">
					
                    <!--컨텐츠영역-->
                    <div class="contents-container sort-row">
                        <div class="contents-box notice">

                            <div class="contents-title notice-tit">
                                공지글쓰기
                            </div>
                            
                            <form id="myForm" action="noupdate.do" method="post" onsubmit="inputSubmit();" enctype="multipart/form-data">
                            	<input type="hidden" name="noticeId" value="${ notice.noticeId }">
                            	<input type="hidden" name="empId" value="${ notice.empId }">
                            	
                                <div class="select-box">
                                    <div class="select-pan-nemo">
                                        제목
                                    </div>
									<div class="select-pan">
										<input type="text" name="noticeTitle" class="input-box-input noline" value="${ notice.noticeTitle }">
									</div>
                                </div>

                                <div class="select-box">
                                    <div class="select-pan-nemo">
                                        파일첨부
                                    </div>
									
                                    <c:choose> 
                                        <c:when test="${ !empty notice.noticeOriginalFileName }">
                                            <c:url var="ndown" value="nfdown.do">
                                                <c:param name="noticeOriginalFileName" value="${ notice.noticeOriginalFileName }" />
                                                <c:param name="noticeRenameFileName" value="${ notice.noticeRenameFileName }" />
                                            </c:url>
                                            <div class="contents-notice-down-box show">
                                                <a class="contents-notice-down" href="${ ndown }">
                                                    <img src="${ pageContext.servletContext.contextPath }/resources/images/side-icon-dep1-over.png">
                                                    <span class="origin-file-name">${ notice.noticeOriginalFileName }</span>
                                                    <img class="down-img" src="${ pageContext.servletContext.contextPath }/resources/images/down.png">
                                                </a>
                                            </div>
                                            <div>
												<input class="margin-left20px" type="checkbox" name="deleteFlag" value="Y"> 파일삭제
											</div>
                                            <div class="select-pan margin-left20px">
                                                <input type="file" name="upfile" class="select-file">
                                            </div>
                                        </c:when> 
                                        
                                        <c:otherwise>
                                            <div class="select-pan">
                                                <input type="file" name="upfile" class="select-file">
                                            </div>
                                        </c:otherwise> 
                                    </c:choose> 
                                </div>

                                <div class="content-input-area height-long">
                                    <div id="content_input" class="content-input" contenteditable="true">
										${ notice.noticeDetail }
                                    </div>
                                </div>
								
                                <div class="content-input-btn-box">
									<input type="submit" class="contents-input-btn big noline" id="btn_update" value="수정">
                                </div>
	                            
                                <textarea name="noticeDetail" id="appendTextArea"></textarea>
                            </form>
                        </div>
                    </div>
                    <!--컨텐츠영역 end-->
                
				</div>
                <!--내용 end-->
				
            </div>
            <!--main-container end-->
        </main>
        
    </div>
</body>
</html>