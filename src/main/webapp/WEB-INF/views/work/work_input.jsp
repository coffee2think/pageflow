<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="depId" value="${ requestScope.depId }" scope="session" />
<c:set var="begin" value="${ requestScope.begin }" scope="session" />
<c:set var="end" value="${ requestScope.end }" scope="session" />
<c:set var="keyword" value="${ requestScope.keyword }" scope="session" />
<c:set var="searchType" value="${ requestScope.searchType }" scope="session" />
<c:set var="firstType" value="${ requestScope.firstType }" scope="session" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/notice.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script type="text/javascript">
    const NOWPAGE = 1;
    const SUBPAGE = 2;
    const LNKPAGE = 1;
    
    
    $(function(){
        const boardDetail = '<c:out value="${ board.boardDetail }" />';
        console.log('boardDetail : ' + boardDetail);
        var decodStr = decodeURIComponent(boardDetail);
        console.log('decodStr : ' + decodStr);
        var decodedString = decodeHtmlEntities(String(decodStr));
        $('#content_input').html(decodedString);

    })

    function decodeHtmlEntities(input) {
        //var input = 'ㅁㄴㅇ&lt;div&gt;ㄴㅇ&lt;/div&gt;&lt;div&gt;ㄴ&lt;/div&gt;&lt;div&gt;ㅇ&lt;/div&gt;&lt;div&gt;ㄴㅇ&lt;/div&gt;&lt;div&gt;&lt;br&gt;&lt;/div&gt;';
        //var decodedString = input.replace(/&lt;/g, '<').replace(/&gt;/g, '>');
        input = input.replace(/&lt;/g, '<');
        input = input.replace(/&gt;/g, '>');
        console.log('input : ' + input)
        return input;
    }

    function convertTagsToNewlines(input) {
        // 사용자 정의 정규 표현식을 사용하여 <div> 태그와 <br> 태그를 개행 문자 \n으로 변경
        var regex = /<div>/gi;
        input = input.replace(regex, '\n');
        
        regex = /<\/div>/gi;
        input = input.replace(regex, '');
        
        regex = /<br>/gi;
        input = input.replace(regex, '\n');

        return input;
    }
    function inputSubmit(){
        let str = $('.content-input').html();
    	$('#appendTextArea').val(str);
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
                                글쓰기
                            </div>

                            <div class="select-box">
                                <div class="select-pan-nemo">
                                    게시판
                                </div>

                                <div class="select-pan">
                                    <label for="sel_code"></label>
                                    <select name="code" id="sel_code">
                                        <option value="development">개발팀</option>
                                    </select>
                                </div>
                            </div>
                            
							<c:if test="${ empty board }">
                            <form id="myForm" action="bdinsert.do" method="post" onsubmit="inputSubmit();" enctype="multipart/form-data">
                                <input type="hidden" name="empId" value="${ loginMember.empId }">
                            <!-- <form id="myForm" action="bdinsert.do" method="post" enctype="multipart/form-data"></form> -->
                            </c:if>
                            
                            <c:if test="${ !empty board }">
                            <form id="myForm" action="bdupdate.do" method="post" onsubmit="inputSubmit();" enctype="multipart/form-data">
                            	<input type="hidden" name="boardId" value="${ board.boardId }">
                            	<input type="hidden" name="empId" value="${ board.empId }">
                            	<input type="hidden" name="depId" value="${ board.depId }">
                            	
                            	<input type="hidden" name="originFile" value="${ board.originFile }">
                            	<input type="hidden" name="renameFile" value="${ board.renameFile }">

                                <input type="hidden" name="begin" value="${ begin }">
                            	<input type="hidden" name="end" value="${ end }">
                            </c:if>
                            	
                            	<input type="hidden" name="depId" value="${ depId }">
                                <div class="select-box">
                                    <div class="select-pan-nemo">
                                        제목
                                    </div>
                                    <c:if test="${ empty board }">
	                                    <div class="select-pan">
	                                        <input type="text" name="boardTitle" class="input-box-input noline" placeholder="제목을 입력하세요.">
	                                    </div>
                                    </c:if>
                                    <c:if test="${ !empty board and !empty board.boardTitle }">
	                                    <div class="select-pan">
	                                        <input type="text" name="boardTitle" class="input-box-input noline" value="${ board.boardTitle }">
	                                    </div>
                                    </c:if>
                                </div>

                                <div class="select-box">
                                    <div class="select-pan-nemo">
                                        파일첨부
                                    </div>
									
                                    <c:choose> 
                                        <c:when test="${ !empty board and !empty board.originFile }">
                                            <c:url var="bdown" value="bddown.do">
                                                <c:param name="ofile" value="${ board.originFile }" />
                                                <c:param name="rfile" value="${ board.renameFile }" />
                                            </c:url>
                                            <div class="contents-notice-down-box show">
                                                <a class="contents-notice-down" href="${ bdown }">
                                                    <img src="${ pageContext.servletContext.contextPath }/resources/images/side-icon-dep1-over.png">
                                                    <span class="origin-file-name">${ board.originFile }</span>
                                                    <img class="down-img" src="${ pageContext.servletContext.contextPath }/resources/images/down.png">
                                                </a>
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
										
                                    </div>
                                </div>
								
								
                                <div class="content-input-btn-box">
                                	<c:if test="${ empty board }">
                                    	<input type="submit" class="contents-input-btn big noline" id="btn_insert" value="입력">
                                	</c:if>
                                	<c:if test="${ !empty board }">
                                    	<input type="submit" class="contents-input-btn big noline" id="btn_insert" value="수정">
                                	</c:if>
                                </div>
	                            
                                <textarea name="boardDetail" id="appendTextArea"></textarea>
                            </form>
                        </div>
                    </div>
                    <!--컨텐츠영역 end-->
					
                </div>
                <!--내용 end-->
				
                <!--
                <div class="submit-box">
                    <input type="button" class="contents-input-btn big noline" id="btn_write" value="글쓰기">
                </div>
                -->
            </div>
            <!--main-container end-->
        </main>
        
    </div>
</body>
</html>