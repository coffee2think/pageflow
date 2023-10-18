<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="board" value="${ requestScope.board }" />
<c:set var="depId" value="${ depId }" scope="session" />
<c:set var="replyList" value="${ requestScope.replyList }" />
<c:set var="loginCur" value="no" />
<c:if test="${ !empty loginMember }">
    <c:set var="loginCur" value="ok" />
</c:if>
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
<title></title>

<script type="text/javascript">
    const NOWPAGE = 1;
    const SUBPAGE = 2;
    const LNKPAGE = 1;
    
    $(function(){
        var workNotice = new Work_notice();
    	workNotice.buttonEvent();
        visibleReplyBtn();
    })

    function Work_notice() {
    }
    
    Work_notice.prototype = {
    	buttonEvent : function(){
    		
        	//보드 수정
        	$('.button-update').on('click', function(){
                if(confirm('수정 화면으로 이동하시겠습니까?')) {
                    location.href = 
                    'bdmoveupdate.do?boardId=${ board.boardId }&depId=${ board.depId }&empId=${ board.empId }&boardTitle=${ board.boardTitle }&boardDetail=${ board.boardDetail }&originFile=${ board.originFile }&renameFile=${ board.renameFile }';
                }
        	})
        	
        	//보드 삭제
        	$('.button-delete').on('click', function(){
        		if(confirm('정말 삭제하시겠습니까?')) {
                    location.href = 'bddelete.do?boardId=${ board.boardId }';//&depId=${ board.depId }&empId=${ board.empId }&boardTitle=${ board.boardTitle }&boardDetail=${ board.boardDetail }&originFile=${ board.originFile }&renameFile=${ board.renameFile }';
                }
        	})
        	
        	//게시판 댓글 뎁스0
            $('.reply-input-btn-depth0').on('click', function(){
                console.log('와 1번')
                let parent = $('.notice-reply-box-con');
                let box = $(this).parent().parent('.reply-input-box');
                ajaxInsertFirst(parent, box);
                
            })
        	
    	}
    }

    //버튼 이벤트
    function setReplyBtn(parent){
    	
    	//댓글 입력 팝업 보이기 버튼
        parent.find('.reply-btn').on('click', function(){
            clickReplyBtn($(this));
        })
        
        //댓글 입력버튼
        parent.find('.reply-input-btn').on('click', function(){
            let parent = $(this).parent().parent().parent('.notice-reply-con');
            replyInsert(parent);
        })

        //드롭다운 보이기 버튼
        parent.find('.reply-right-btn').on('click', function(){
            let dropdown = $(this).parent().parent('.notice-reply-con').find('.reply-dropdown');
            let vis = dropdown.css('display');

            if(vis == 'none') dropdown.show();
    	    else dropdown.hide();
        })

        //댓글 수정버튼
        parent.find('.reply-dropdown-btn').each(function(){

            $(this).on('click', function(){
                let parentBox = $(this).parent().parent().parent('.notice-reply-con');
                let dropdown = parentBox.find('.reply-dropdown');
                dropdown.hide();

                if($(this).hasClass('deletew')){
                    if(confirm('정말 삭제하시겠습니까?')) {
                        location.href = 'rydelete.do';
                    }
                }else{
                    if(confirm('수정하시겠습니까?')) {
                        let textbox = parentBox.find('.contents-notice-text');
                        textbox.attr('contenteditable', true);
                        textbox.focus();
                        
                        let downBox = parentBox.find('.contents-notice-down');
                        let fileUpDiv = parentBox.find('.file-btn-update-div');
                        fileUpDiv.show();
                        
                        textbox.on('focus', function() {
                            let input = $(this);
                            let text = input.html().trim();
                            //let text = input.html();
                            //console.log('text : ' + text);
                            input.html(text);
                            // 입력 상자의 내용을 선택하고 커서를 문장 끝으로 이동
                            //input[0].setSelectionRange(text.length, text.length);
                        });

                        let update_btn = parentBox.find('.reply-up-btn');
                        update_btn.show();
                        update_btn.on('click', function(){
                            $(this).hide();
                            parentBox.find('.file-btn-update-div').hide();
                            //ajax 사용
                            ajaxUpdate(parentBox);
                        })
                    }
                }

            })
        })

        //댓글 삭제버튼
        parent.find('.reply-report-btn').on('click', function(){
            let parentBox = $(this).parent().parent().parent('.notice-reply-con');
            let dropdown = $(this).parent().parent().parent('.notice-reply-con').find('.reply-dropdown');
            dropdown.hide();

        })

        //댓글 로그인 안했을때 

    }

    //댓글 업데이트
    function ajaxUpdate(parent){

        let obj = setId(parent);
        let textbox = parent.find('.contents-notice-text');
        let replyStr= textbox.html();
        let arr = replyStr.split('</em>');
        arr.shift();
        let replyDetail = arr.join(' ');
        let formData = new FormData();
        console.log('replyDetail : ' + replyDetail);
        if(replyDetail == '') replyDetail = '&nbsp;';
        formData.append('replyId', obj.replyId);
        formData.append('replyDetail', replyDetail);
        formData.append('originFile', parent.find('.origin-file-name').text());
        formData.append('upfile', $('#reply_' + obj.replyId + ' .file-btn-update')[0].files[0]);
        
		$.ajax({
    		url : 'ryupdate.do'
    		,type : 'post'
            ,processData : false
            ,contentType : false
    		,data : formData
    		,success : function(data) {
                //let str = JSON.stringify(data);
                //let adata = JSON.parse(str);

                console.log("data : " +data);
                let arr = data.split('#%');
                let modifyDate = arr[0];
                let fileName = arr[1];
    			console.log('arr : ' + arr);
                console.log('file_name : ' + fileName);
                textbox.attr('contenteditable', false);
                //parent.find('.reply-date span').text(modifyDate);

                if(adata.file_name != undefined && adata.file_name.length > 0){
                    parent.find('.contents-notice-down-box').show();
                    parent.find('.origin-file-name').text(adata.file_name);
                }
                
    		}
    		,error: function(request, status, errorData){
    			console.log("error : " + request + ", " + status + ", " + errorData);
    		}
    	})
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

    //아이디 지정
    function setId(parent){
    	
    	let data = {
            empId : Number(parent.attr('data-empid'))
   			,bundleId : Number(parent.attr('data-bundleid'))
           	,bundleId2 : Number(parent.attr('data-bundleid2'))
           	,parentId : Number(parent.attr('data-parentid'))
           	,depth : Number(parent.attr('data-depth'))
           	,replyId : Number(parent.attr('data-replyid'))
    	}
    	
		return data;
	}
    
    //내용 
    function changeDetail(obj){
        let replyDetail = obj.html();
        let arr = replyDetail.split('</em>');
        arr.shift();
        let replyDetail2 = arr.join(' ');
        if(replyDetail2 == '') replyDetail2 = '&nbsp;';
        return replyDetail2;
    }

  	//댓글 등록 버튼 클릭시
	function replyInsert(parent){
		
		//댓글 ajax 
		let obj = setId(parent);
    	let replyId, bundleId, bundleId2, parentId, depth;
        let empId = obj.empId;
    	let replyDetail = changeDetail(parent.find('.reply-input'));
        console.log('replyDetail2 : ' + replyDetail);
        
		//depth가 -1이 아닐때는 댓글의 댓글임
        bundleId = obj.bundleId;
        replyId = obj.replyId;
        
        if(obj.depth == 1) {
            bundleId2 = replyId;
            depth = 2;
        }else if(obj.depth == 2){
            bundleId2 = obj.bundleId2;
            depth = 3;
        }else if(obj.depth == 3){
            bundleId2 = obj.bundleId2;
            depth = 3;
        }else{
            //obj.depth가 -1일때
            bundleId2 = obj.bundleId2;
            depth = 1;
        }
        
        parentId = replyId;
        
        console.log('replyInsert - bundleId : ' + bundleId + ', bundleId2 : ' +
                bundleId2 + ', parentId : ' + parentId + ', depth : ' + depth + 
                    ', replyId : ' + replyId + ', replyDetail : ' + replyDetail);
        ajaxInsert(parent, empId, bundleId, bundleId2, parentId, depth, replyId, replyDetail);
		
	}
    
  	//댓글의 댓글 인서트
	function ajaxInsert(parent, empId, bundleId, bundleId2, parentId, depth, replyId, replyDetail){
		
		console.log('ajaxReplyInsert - bundleId : ' + bundleId + ', bundleId2 : ' +
				bundleId2 + ', parentId : ' + parentId + ', depth : ' + depth + 
				', replyId : ' + replyId + ', replyDetail : ' + replyDetail);
		
		let jsonData = {
			depId : ${ board.depId }
			,boardId : ${ board.boardId }
			,empId : empId
            ,empName : parent.find('.reply-ename').text()
			,bundleId : bundleId
			,bundleId2 : bundleId2
			,parentId : parentId
			,depth : depth
			,replyId : replyId
			,replyDetail : replyDetail	
		}

        let formData = new FormData();
        formData.append('depId', jsonData.depId);
        formData.append('boardId', jsonData.boardId);
        formData.append('empId', jsonData.empId);
        formData.append('bundleId', jsonData.bundleId);
        formData.append('bundleId2', jsonData.bundleId2);
        formData.append('parentId', jsonData.parentId);
        formData.append('depth', jsonData.depth);
        formData.append('replyId', jsonData.replyId);
        formData.append('replyDetail', jsonData.replyDetail);
        formData.append('upfile', $('#reply_' + jsonData.replyId + ' .file-btn')[0].files[0]);
        

		$.ajax({
    		url : 'ryinsert.do'
    		,type : 'post'
            ,processData : false
            ,contentType : false
    		,data : formData
    		,success : function(data) {
                // let str = JSON.stringify(data);
                // let adata = JSON.parse(str);

    			// console.log("reply_id : " +adata.reply_id);

    			// let ajaxData = {
                //     replyId : adata.reply_id
                //     ,empName : adata.emp_name
                //     ,profile : adata.profile
                //     ,createDate : adata.create_date
                //     ,originFile : adata.origin_file
                //     ,renameFile : adata.rename_file
                // }
    			console.log("data : " +data);
                let arr = data.split('#%');
                
    			let ajaxData = {
                    replyId : arr[0]
                    ,empName : arr[1]
                    ,profile : arr[2]
                    ,createDate : arr[3]
                    ,originFile : arr[4]
                    ,renameFile : arr[5]
                }
                parent.find('.reply-input-box').hide();
                parent.find('.reply-input').text('');
                parent.find('.file-btn').val('');
                parent.after(setTag(jsonData, ajaxData));
    			console.log('parent : ' + parent.attr('class'));
    			setReplyBtn($('#reply_' + ajaxData.replyId));
                viewReplyNum();
                visibleReplyBtn();
    		}
    		,error: function(request, status, errorData){
    			console.log("error : " + request + ", " + status + ", " + errorData);
    		}
    	})
	}

    //댓글 첫번째 입력
    function ajaxInsertFirst(parent, box){
        let replyDetail = changeDetail(box.find('.reply-input'));
        let formData = new FormData();
        let empId = ${ board.empId };
        formData.append('depId', ${ board.depId });
        formData.append('boardId', ${ board.boardId });
        formData.append('empId', empId);
        formData.append('replyDetail', replyDetail);
        formData.append('depth', -1);
        formData.append('upfile', $('.reply-input-box.depth1').find('.file-btn')[0].files[0]);

        $.ajax({
            url : 'ryinsertfirst.do'
            ,type : 'post'
            ,processData : false
            ,contentType : false
    		,data : formData
            ,success : function(data) {
                
                let arr = data.split('#%');
                console.log("arr : " +arr);
    			let ajaxData = {
                    replyId : arr[0]
                    ,empName : ''
                    ,profile : arr[2]
                    ,createDate : arr[3]
                    ,originFile : arr[4]
                    ,renameFile : arr[5]
                }

                let jsonData = {
                    empId : empId
                    ,empName : $('.notice-title-box .notice-profile-one').text()
                    ,bundleId : ajaxData.replyId
                    ,bundleId2 : ajaxData.replyId
                    ,parentId : ajaxData.replyId
                    ,replyDetail : replyDetail
                    ,depth : -1
                }

                $('.reply-input-box.depth1').find('.reply-input').html('');
                $('.reply-input-box.depth1').find('.reply-input').html('<em class="tag-text" contenteditable="false">@${ board.empName }</em>&nbsp;');
                $('.reply-input-box.depth1').find('.file-btn').val('');
                parent.prepend(setTag(jsonData, ajaxData));
                //console.log('#reply_'+ ajaxData.replyId);
                setReplyBtn($('#reply_' + ajaxData.replyId));
                viewReplyNum();
                visibleReplyBtn();
            },error: function(request, status, errorData){
                console.log("error : " + request + ", " + status + ", " + errorData);
            }
        })
    }
  	
    function viewReplyNum(){
        let num = Number($('.reply-title span').text());
        $('.reply-title span').text(num+1);
        
    }

    function visibleReplyBtn(){
        let log = '<c:out value="${ loginCur }" />';
        
        if(log == 'no') {
            $('.reply-btn').each(function(){
                $(this).hide();
            })
            $('.reply-input-box.depth1').hide();
            
            $('.button-update').hide();
            $('.button-delete').hide();
            $('.reply-right').each(function(){
                $(this).hide();
            })
        }else{
            $('.reply-btn').each(function(){
                $(this).show();
            })

            $('.reply-input-box.depth1').show();

            //만약 나라면
            let myId = Number('<c:out value="${ loginMember.empId }" />');
            let empId = Number('<c:out value="${ board.empId }" />');
            console.log('myId : ' + myId + '  empId : ' + empId);
            if(empId == myId){
                $('.button-update').show();
            }else{
                $('.button-update').hide();
            }

            $('.notice-reply-con').each(function(){
                let dataEmpId = Number($(this).attr('data-empid'));
                console.log('myId : ' + myId + '  dataEmpId : ' + dataEmpId);
                if(myId == dataEmpId){
                    $(this).find('.reply-right').show();
                }else{
                    $(this).find('.reply-right').hide();
                }
            })

        }
    }

  	function setTag(jsonData, ajaxData){
        let empName = '<c:out value="${ loginMember.empName }" />';//내이름 쓰기
        let parentEmpName = jsonData.empName;
        let originFileName = '';
        let renameFileName = '';
        let profile = (ajaxData.profile == undefined || ajaxData.profile == '') ? '' : decodeURIComponent(ajaxData.profile);

        if(ajaxData.originFile != undefined && ajaxData.originFile.length > 0) {
            originFileName = decodeURIComponent(ajaxData.originFile);
            renameFileName = ajaxData.renameFile;
        }
        
        let replyId = ajaxData.replyId;
        let createDate = ajaxData.createDate;
        
  		let el = `
  		<div class="notice-reply-con depth`+jsonData.depth+`" 
        	data-empid="`+jsonData.empId+`"
        	data-bundleid="`+jsonData.bundleId+`" 
        	data-bundleid2="`+jsonData.bundleId2+`" 
        	data-parentid="`+jsonData.parentId+`" 
        	data-depth="`+jsonData.depth+`" 
        	data-replyid="`+replyId+`"
        	id = "reply_`+replyId+`"
        >`
        if(jsonData.depth >= 1) {
            el += `<div class="depth2-icon">ㄴ</div>`;
        }
        
        el += 
            `
            <div class="contents-notice-line">
                <div class="notice-profile">
                    <img src="${ pageContext.servletContext.contextPath }/resources/images/profile.png">
                    <span>
                        <div class="reply-ename">`+empName+`</div>
                        <div class="reply-date">
                            <span>`+createDate+`</span>
                        </div>
                    </span>
                </div>
            </div>
            
            <div class="contents-notice-text">
                <em class="contents-notice-text-parent">@`+parentEmpName+`</em>
                `+jsonData.replyDetail+`
            </div>
            <div class="file-btn-update-div">
                <input type="file" class="file-btn-update" name="upfile2">
            </div>
            `
        
        if(originFileName == '') {
            el += `
            <div class="contents-notice-down-box">
                <a class="contents-notice-down" href="bddown.do?ofile=`+ originFileName +`&rfile=`+ renameFileName +`">
                    <img src="${ pageContext.servletContext.contextPath }/resources/images/side-icon-dep1-over.png">
                    <span class="origin-file-name">`+originFileName+`</span>
                    <img class="down-img" src="${ pageContext.servletContext.contextPath }/resources/images/down.png">
                </a>
            </div>
            `
        }else{
            el += `
            <div class="contents-notice-down-box show">
                <a class="contents-notice-down" href="bddown.do?ofile=`+ originFileName +`&rfile=`+ renameFileName +`">
                    <img src="${ pageContext.servletContext.contextPath }/resources/images/side-icon-dep1-over.png">
                    <span class="origin-file-name">`+originFileName+`</span>
                    <img class="down-img" src="${ pageContext.servletContext.contextPath }/resources/images/down.png">
                </a>
            </div>
            `
        }
        
        if(jsonData.depth != 3) {
            el += `
            <button class="reply-btn">
                답글
            </button>`;
        }
        
        el +=
            `
            <button class="reply-up-btn">수정</button>
			<div class="reply-right">
				<div class="reply-dropdown">
					<!--<button class="reply-dropdown-btn delete">삭제</button>-->
					<button class="reply-dropdown-btn update">수정</button>
				</div>
                <button class="reply-right-btn">
                    <img class="reply-right-btn-img" src="/pageflow/resources/images/right.png">
                </button>
            </div>`
            
        if(jsonData.depth != 3) {	
            el +=
            `
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
            </div>`
        }
        el +=
        `
        </div>`;
        
        return el;
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
                    
                    <!--서치영역-->
                    <div class="search-container noline">
                        <div class="search-form">
                            <c:import url="./work_search.jsp" />
                        </div>

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
                                                <div class="notice-profile-one">${ board.empName }</div>
                                                <div>
                                                    <span>${ board.createDate }</span>
                                                    <span>|</span>
                                                    <span class="bluetext">읽음 ${ board.viewsNum }</span>
                                                </div>
                                            </span>
                                        </div>
                                    </div>
                                    <c:url var="bdown" value="bddown.do">
                                        <c:param name="ofile" value="${ board.originFile }" />
                                        <c:param name="rfile" value="${ board.renameFile }" />
                                    </c:url>

                                    <c:if test="${ !empty board.originFile }">
                                    	
                                        <div class="contents-notice-down-box show">
                                            <a class="contents-notice-down" href="${ bdown }">
                                                <img src="${ pageContext.servletContext.contextPath }/resources/images/side-icon-dep1-over.png">
                                                <span class="origin-file-name">${ board.originFile }</span>
                                                <img class="down-img" src="${ pageContext.servletContext.contextPath }/resources/images/down.png">
                                            </a>
                                        </div>
                                    </c:if>

                                    <c:if test="${ empty board.originFile }">
                                    	
                                        <div class="contents-notice-down-box">
                                            <a class="contents-notice-down" href="${ bdown }">
                                                <img src="${ pageContext.servletContext.contextPath }/resources/images/side-icon-dep1-over.png">
                                                <span class="origin-file-name">${ board.originFile }</span>
                                                <img class="down-img" src="${ pageContext.servletContext.contextPath }/resources/images/down.png">
                                            </a>
                                        </div>
                                    </c:if>
                                </div>
                                
                                <div class="notice-contents-box">
                                    ${ board.boardDetail }
                                </div>
                                <div class="contents-notice-buttonbox">
									<div class="button-box">

		                                <a class="button-a button-update" href="#">
		                                    수정
		                                </a>

                                        <c:if test="${ replyList.size() <= 0 }">
                                            <a class="button-a button-delete" href="#">
                                                삭제
                                            </a>
                                        </c:if>
		                                
		                            </div>
		                        </div>
                                <!--notice-reply-box-->
                                <div class="notice-reply-box">
                                    <div class="notice-reply-title">
                                        <div class="reply-title">
                                            댓글 <span>${ replyList.size() }</span>
                                        </div>
                                    </div>
                                    <div class="notice-reply-box-con">
                                        <c:forEach var="r" items="${ replyList }">
                                            
                                            <div class="notice-reply-con depth${ r.depth }" 
                                                data-empid="${ r.empId }"
                                                data-bundleid="${ r.bundleId }" 
                                                data-bundleid2="${ r.bundleId2 }" 
                                                data-parentid="${ r.parentId }" 
                                                data-depth="${ r.depth }"
                                                data-replyid="${ r.replyId }"
                                                id = "reply_${ r.replyId }"
                                            >
                                                
                                                <c:if test="${ r.depth >= 1 }">
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
                                                    <em class="contents-notice-text-parent">@${ r.parentEmpName }</em>
                                                    ${ r.replyDetail }
                                                </div>
                                                <div  class="file-btn-update-div">
                                                    <input type="file" class="file-btn-update" name="upfile2">
                                                </div>

                                                <c:url var="bdown" value="bddown.do">
                                                    <c:param name="ofile" value="${ r.originFile }" />
                                                    <c:param name="rfile" value="${ r.renameFile }" />
                                                </c:url>

                                                <c:if test="${ !empty r.originFile }">
                                                    <div class="contents-notice-down-box show">
                                                        <a class="contents-notice-down" href="${ bdown }">
                                                            <img src="${ pageContext.servletContext.contextPath }/resources/images/side-icon-dep1-over.png">
                                                            <span class="origin-file-name">${ r.originFile }</span>
                                                            <img class="down-img" src="${ pageContext.servletContext.contextPath }/resources/images/down.png">
                                                        </a>
                                                    </div>
                                                </c:if>
                                                <c:if test="${ empty r.originFile }">
                                                    <div class="contents-notice-down-box">
                                                        <a class="contents-notice-down" href="${ bdown }">
                                                            <img src="${ pageContext.servletContext.contextPath }/resources/images/side-icon-dep1-over.png">
                                                            <span class="origin-file-name">${ r.originFile }</span>
                                                            <img class="down-img" src="${ pageContext.servletContext.contextPath }/resources/images/down.png">
                                                        </a>
                                                    </div>
                                                </c:if>
                                                        
                                                
                                                <c:if test="${ r.depth != 3 }">
                                                    <button class="reply-btn">
                                                        답글
                                                    </button>
                                                </c:if>
                                                
                                                <button class="reply-up-btn">수정</button>
                                                
                                                <div class="reply-right">
                                                    <div class="reply-dropdown">
                                                        <!--<button class="reply-dropdown-btn delete">삭제</button>-->
                                                        <button class="reply-dropdown-btn update">수정</button>
                                                    </div>
                                                    <button class="reply-right-btn">
                                                        <img class="reply-right-btn-img" src="/pageflow/resources/images/right.png">
                                                    </button>
                                                </div>
                                                
                                                <c:if test="${ r.depth != 3 }">
                                                
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
                                                </c:if>
                                            </div>
                                            
                                        </c:forEach>

                                        

                                        <script type="text/javascript">
                                            $('.notice-reply-con').each(function(){
                                                setReplyBtn($(this));
                                            })
                                        </script>
                                    </div>
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


                            <c:url var="bdlist_bottom" value="bdlistdate.do">
                                <c:param name="begin" value="${ begin }" />
                                <c:param name="end" value="${ end }" />
                                <c:param name="depId" value="${ depId }" />
                            </c:url>

                            <c:url var="bdlist_next" value="bdselect.do">
                                <c:param name="begin" value="${ begin }" />
                                <c:param name="end" value="${ end }" />
                                <c:param name="empId" value="${ board.empId }" />
                                <c:param name="boardId" value="${ board.boardId }" />
                                <c:param name="depId" value="${ depId }" />
                            </c:url>

                            <div class="button-box">
                                <a class="button-a button-list" style="width: 100px; height: 30px;" href="${ bdlist_bottom }">
                                    목록으로
                                </a>
                                <!--
                                <a class="button-a button-prev">
                                    ▲
                                </a>
                                <a class="button-a button-next">
                                    ▼
                                </a>
                                -->
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