<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="board" value="${ requestScope.board }" />
<c:set var="replyList" value="${ requestScope.replyList }" />

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

<script type="text/javascript">
    const NOWPAGE = 1;
    const SUBPAGE = 2;
    const LNKPAGE = 1;
    
    document.addEventListener("DOMContentLoaded", function(){
    	var workNotice = new Work_notice();
    	workNotice.buttonEvent();
    })
	
    function Work_notice() {
    }
    
    Work_notice.prototype = {
    	buttonEvent : function(){
    		//등록순, 최신순 버튼
    		$('.reply-sort-btn').each(function(){
        		$(this).on('click', function(){
        			
        		})
        	})
        	
        	//보드 수정
        	$('.button-update').on('click', function(){
        		
        	})
        	
        	//보드 삭제
        	$('.button-update').on('click', function(){
        		
        	})
        	
        	//게시판 댓글 뎁스0
        	$('.reply-input-btn-depth0').on('click', function(){
        		location.href = 'nlist.do';
        	})
        	
        	
    	}
    }
    
    function setReplyBtn(){
    	//댓글 입력 팝업 보이기 버튼
        $('.reply-btn').each(function(){
            $(this).on('click', function(){
            	clickReplyBtn($(this));
            })
        })
        
        //댓글 입력버튼
        $('.reply-input-btn').each(function(){
        	$(this).on('click', function(){
        		
        		if($(this).parent().parent('.reply-input-box').hasClass('depth1')){
        			
        		}else{
        			let parent = $(this).parent().parent().parent('.notice-reply-con');
                	replyInsert(parent);
        		}
        		
        	})
        })
    }
    
    function setId(parent){
    	
    	let data = {
   			bundleId : parent.attr('data-bundleId')
           	,bundleId2 : parent.attr('data-bundleId2')
           	,parentId : parent.attr('data-parentId')
           	,depth : parent.attr('data-depth')
           	,depth2 : parent.attr('data-depth2')
           	,replyId : parent.attr('data-replyId')
    	}
    	
		return data;
	}
    
  	//댓글 창 보이기
	function clickReplyBtn(thisObj) {
		let parent = thisObj.parent('.notice-reply-con');
    	let data = setId(parent);
    	console.log('data.replyId : ' + data.replyId);
    	let reply = $('#reply_'+data.replyId);
    	let inputBox = reply.find('.reply-input-box');
    	let vis = inputBox.css('display');
    	
    	if(vis == 'none') inputBox.show();
    	else inputBox.hide();
    	
    	inputBox.find('.reply-input').html('<em class="tag-text" contenteditable="false">@' + reply.find('.reply-ename').text() + '</em>&nbsp;');
	}
    
  	//댓글 등록 버튼 클릭시
	function replyInsert(parent){
		
		//댓글 ajax 
		let obj = setId(parent);
    	let replyId, bundleId, bundleId2, parentId, depth, depth2;
    	let replyDetail = parent.find('.reply-input').text();
		
		if(obj.depth == -1){
			//bundlId는 depth가 -1일때는 최상위
			//번들아이디를 ajax로 받아와야 함
			replyId = obj.replyId;
			bundleId = replyId;
			bundleId2 = bundleId;
			parentId = bundleId;
			depth = -1;
			depth2 = -1;
			
			ajaxInsert2(bundleId, bundleId2, parentId, depth, 
					depth2, replyId, replyDetail);
			
		}else{
			//depth가 -1이 아닐때는 댓글의 댓글임
			bundleId = obj.bundleId;
			replyId = obj.replyId;
			if(depth == 1) {
				bundleId2 = replyId;
			}else {
				bundleId2 = obj.bundleId2;
			}
			
			parentId = replyId;
			depth = 1;
			depth2 = (depth2 < 3) ? obj.depth2 + 1 : obj.depth2;
			console.log('replyInsert - bundleId : ' + bundleId + ', bundleId2 : ' +
					bundleId2 + ', parentId : ' + parentId + ', depth : ' + depth + 
					', depth2 : ' + depth2 + ', replyId : ' + replyId + 
					', replyDetail : ' + replyDetail);
			ajaxInsert(bundleId, bundleId2, parentId, depth, 
					depth2, replyId, replyDetail);
			
		}
		
	}
    
  	//댓글 인서트
	function ajaxInsert(bundleId, bundleId2, parentId, depth, 
			depth2, replyId, replyDetail){
		
		console.log('ajaxReplyInsert - bundleId : ' + bundleId + ', bundleId2 : ' +
				bundleId2 + ', parentId : ' + parentId + ', depth : ' + depth + 
				', depth2 : ' + depth2 + ', replyId : ' + replyId + 
				', replyDetail : ' + replyDetail);
		
		$.ajax({
    		url : 'ryinsert.do'
    		,type : 'post'
    		,data : {
    			defId : ${ board.depId }
    			,empId : 1
    			,bundleId : bundleId
    			,bundleId2 : bundleId2
    			,parentId : parentId
    			,depth : depth
    			,depth2 : depth2
    			,replyId : replyId
    			,replyDetail : replyDetail
    		},
    		success : function(data) {
    			console.log("success : " + data);
    			//$('.reply-input-btn')
    			
    		},
    		error: function(jqXHR, textStatus, errorThrown){
    			console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
    		}
    	})
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
                    <div class="side-icon-box">
                        <a href="bdmoveinsert.do?depId=${ board.depId }" class="side-write-btn">글쓰기</a>
                        <div class="side-icon-menu">
                            <button class="side-icon-btn" id="sideBtn_new">
                                <span class="side-icon">3</span>
                                <span>최신글</span>
                            </button>
                            <button class="side-icon-btn" id="sideBtn_my">
                                <span class="side-icon">
                                    <img src="${ pageContext.servletContext.contextPath }/resources/images/my_1.png">
                                </span>
                                <span>내 게시글</span>
                            </button>
                        </div>
                    </div>

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

                    <!--서치영역-->
                    <div class="search-container noline">
                        <form class="search-form">

                            <div class="select-search">
                                <div class="select-box">
                                    <div class="select-pan">
                                        <label for="sel_code"></label>
                                        <select name="code" id="sel_code">
                                            <option value="">내용</option>
                                            <option value="">작성자</option>
                                            <option value="">댓글</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="search-box">
                                    <button class="search-btn">
                                        <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                    </button>
                                    <input type="text" placeholder="키워드를 입력하세요." class="search-box-text" value="">
                                </div>
                            </div>

                            <div class="select-box">
                                <div class="select-pan-nemo">
                                    작성날짜
                                </div>

                                <input type="date" class="select-date select-date-first">
                                <input type="date" class="select-date select-date-second">

                                <input type="button" name="week" class="select-pan-btn" value="일주일">
                                <input type="button" name="month" class="select-pan-btn" value="한달">
                            </div>

                        </form>

                        <button class="search-visible-btn" id="search_visible_btn">
                            <img class="search-close" src="${ pageContext.servletContext.contextPath }/resources/images/cursor_1.png">
                            <img class="search-open" src="${ pageContext.servletContext.contextPath }/resources/images/cursor_2.png">
                        </button>

                    </div>
                    <!--서치영역 end-->

                    <!--컨텐츠영역-->
                    <div class="contents-container sort-row">
                        <div class="contents-box notice" data-num="">

                            <div class="contents-title notice-tit small">
                                ${ board.depName } 게시판
                            </div>

                            <!--contents-notice-->
                            <div class="contents-notice notice">

                                <div class="notice-title-box">
                                    <div class="contents-notice-title big">
                                        ${ board.boardTitle }
                                    </div>
                                    <div class="contents-notice-line">
                                        <div class="notice-profile">
                                            <img src="${ pageContext.servletContext.contextPath }/resources/images/profile.png">
                                            <span>
                                                <div>${ board.empName }</div>
                                                <div>
                                                    <span>${ board.createDate }</span>
                                                    <span>|</span>
                                                    <span class="bluetext">읽음 ${ board.viewsNum }</span>
                                                </div>
                                            </span>
                                        </div>
                                    </div>
                                    
                                </div>
                                
                                <div class="notice-contents-box">
                                    ${ board.boardDetail }
                                </div>
                                <div class="contents-notice-buttonbox">
									<div class="button-box">
		                                <a class="button-a button-update" href="#">
		                                    수정
		                                </a>
		                                <a class="button-a button-delete" href="#">
		                                    삭제
		                                </a>
		                            </div>
		                        </div>
                                <!--notice-reply-box-->
                                <div class="notice-reply-box">
                                    <div class="notice-reply-title">
                                        <div class="reply-title">
                                            댓글 <span>${ replyList.size() }</span>
                                        </div>
                                        <div class="reply-sort">
                                            <button class="reply-sort-btn">
                                                <img src="${ pageContext.servletContext.contextPath }/resources/images/chk.png">
                                                <span>등록순</span>
                                            </button>
                                            <button class="reply-sort-btn">
                                                <img class="display-none" src="${ pageContext.servletContext.contextPath }/resources/images/chk.png">
                                                <span>최신순</span>
                                            </button>
                                        </div>
                                    </div>
                                    
                                    <c:forEach var="r" items="${ replyList }">
                                    	
                                        <div class="notice-reply-con depth${ r.depth2 }" 
                                        	data-empId="${ r.empId }"
                                        	data-bundleId="${ r.bundleId }" 
                                        	data-bundleId2="${ r.bundleId2 }" 
                                        	data-parentId="${ r.parentId }" 
                                        	data-depth="${ r.depth }" 
                                        	data-depth2="${ r.depth2 }" 
                                        	data-replyId="${ r.replyId }"
                                        	id = "reply_${ r.replyId }"
                                        >
                                            
                                            <c:if test="${ r.depth2 >= 1 }">
                                            	<div class="depth2-icon">ㄴ</div>
                                            </c:if>
                                            
                                            <div class="contents-notice-line">
                                                <div class="notice-profile">
                                                    <img src="${ pageContext.servletContext.contextPath }/resources/images/profile.png">
                                                    <span>
                                                        <div class="reply-ename">${ r.empName }</div>
                                                        <div class="reply-date">
                                                            <span>${ r.createDate }</span>
                                                        </div>
                                                    </span>
                                                </div>
                                            </div>
                                            
                                            <div class="contents-notice-text">
                                                <span class="contents-notice-text-parent">@${ r.parentEmpName }</span>
                                                ${ r.replyDetail }
                                            </div>
                                            
    										
		
                                            <a class="contents-notice-down" href="#">
                                                <img src="${ pageContext.servletContext.contextPath }/resources/images/side-icon-dep1-over.png">
                                                <span>product_manage2.zip</span>
                                                <img class="down-img" src="${ pageContext.servletContext.contextPath }/resources/images/down.png">
                                            </a>
                                            
                                            <c:if test="${ r.depth2 != 3 }">
	                                            <button class="reply-btn">
	                                                답글
	                                            </button>
    										</c:if>
    										
    										<div class="reply-right">
												<div class="reply-dropdown">
													<button class="reply-delete-btn">삭제</button>
													<button class="reply-report-btn">신고</button>
												</div>
						                        <button class="reply-right-btn">
						                            <img class="reply-right-btn-img" src="/pageflow/resources/images/right.png">
						                        </button>
						                    </div>
											
	                                        <div class="reply-input-box depth2">
	                                            <!-- <div class="depth2-icon-reply">ㄴ</div> -->
	                                            <div class="reply-input-area">
	                                                <div class="reply-input" contenteditable="true">
	                                                    
	                                                </div>
	                                            </div>
	                                            <div class="reply-input-btn-box">
	                                                <input type="file" class="file-btn" name="upfile">
	                                                <input type="button" class="contents-input-btn reply-input-btn big noline" value="입력">
	                                            </div>
	                                        </div>
                                        </div>
                                    </c:forEach>
									<script type="text/javascript">
										setReplyBtn();
									</script>
                                </div>
                                <!--notice-reply-box end-->

                                <div class="reply-input-box depth1">
                                    <div class="reply-input-area">
                                        <div class="reply-input" contenteditable="true">
											<em class="tag-text" contenteditable="false">@${ board.empName }</em>&nbsp;
                                        </div>
                                    </div>
                                    <div class="reply-input-btn-box">
                                        <input type="file" class="file-btn" name="upfile">
                                        <input type="button" class="contents-input-btn reply-input-btn-depth0 big noline" value="입력">
                                    </div>
                                </div>

                            </div>
                            <!--contents-notice end-->

                            <div class="button-box">
                                <a class="button-a button-list">
                                    목록
                                </a>
                                <a class="button-a button-prev">
                                    ▲
                                </a>
                                <a class="button-a button-next">
                                    ▼
                                </a>
                                <a class="button-a button-top" href="#body-top">
                                    위로
                                </a>
                            </div>

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