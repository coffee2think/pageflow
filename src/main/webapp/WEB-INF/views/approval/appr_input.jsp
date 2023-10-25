<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="nowdays_" value="<%= java.util.Date.from(java.time.LocalDate.now().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()) %>" />
<fmt:formatDate var="nowdays" value="${ nowdays_ }" pattern="yyyy년 MM월 dd일" />

<c:set var="apprId" value="-1" />
<c:if test="${ !empty approval.apprId }">
    <c:set var="apprId" value="${ approval.apprId }" />
</c:if>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/main.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<style>
    #btn_notclick {
        width: 80px;
        height: 25px;
        line-height: 1px;
    }
</style>
<script type="text/javascript">
    const NOWPAGE = 6;
    const SUBPAGE = 3;
    const LNKPAGE = 1;
    const pageType = '<c:out value="${ pageType }" />'


    $(function(){
        $('.search-btn-appr').on('click', function(){
            return false;
        })

        if(pageType == 'update'){
            const apprId = '<c:out value="${ approval.apprId }" />'
            const originFile = '<c:out value="${ approval.originFile }" />'
            const renameFile = '<c:out value="${ approval.renameFile }" />'
            const lineId = '<c:out value="${ approval.lineId }" />'
            const savelineId = '<c:out value="${ approval.savelineId }" />'
            const title = '<c:out value="${ approval.title }" />'
            const detail = '<c:out value="${ approval.detail }" />'
            const emergency = '<c:out value="${ approval.emergency }" />'
            const detailType = '<c:out value="${ approval.detailType }" />'
            const startDate = '<c:out value="${ approval.startDate }" />'
            const endDate = '<c:out value="${ approval.endDate }" />'
            $('.input-form').attr('action', 'apupdate.do');
            let sendData = {};

            console.log('apprId : ' + apprId);
            sendData['apprId'] = apprId;
            sendData['lineId'] = lineId;

            $.ajax({
                url: 'allinemy.do',
                type: 'post',
                data: sendData,
                dataType: 'json',
                success: function(result) {
                    var jsonStr = JSON.stringify(result);
                    var approvalLine = JSON.parse(jsonStr).list;
                    console.log('approvalLine : ' + approvalLine); 
                    console.log('jsonStr : ' + jsonStr);

                    if(savelineId == -1) {
                        //결재라인이 없을때
                        count_global = approvalLine.length;
                        
                        for(var i in approvalLine) {
                            const list = approvalLine[i];
                            i = Number(i);
                            console.log('list : ' + list);

                            var a_str = list.approver_id + '#%' + decode(list.approver_name) + '#%' 
                                        + ((list.depName == undefined) ? '개발팀' : decode(list.depName)) + '#%' + decode(list.pos_name);

                            //$('#approver_' + (i+1)).val(a_str);
                            $('#approver_' + (i+1)).val(a_str);
                            console.log('i : ' + i + '  a_str : ' + a_str);
                            console.log('i : ' + i + '  val : ' + $('#approver_' + (i+1)).val());
                            var el = `
                            <span class="appr-box" id="appr_box_`+(i+1)+`">
                                `+decode(list.approver_name)+`
                                <button type="button" class="appr-box-close" id="appr_close_`+(i+1)+`">x</button>
                            </span>
                            `
                            $('#search_approver').append(el);

                            $('#appr_close_' + (i+1)).on('click', function(){
                                var id = $(this).attr('id').split('_').pop();
                                $('#approver_' + id).val('');
                                $(this).remove();
                                $('#appr_box_' + id).remove();
                                count_global --;
                            })
                            
                        }
                        console.log('=========???=========');
                        //서치버튼 클릭할때 초기화
                        

                        //결재라인 삭제
                        $('#search_line').empty();
                        $('#save_line').val('-1');
                        

                    }else{
                        //결재라인 있을때
                        $('#save_line').val(savelineId);
                        $('#search_line').empty();
                        for(var i in approvalLine) {
                            var el = `
                            <span class="appr-box">`+approvalLine[i].approver_name+`</span>
                            `
                            $('#search_line').append(el);
                            
                        }
                        console.log('=========================================')
                        //결재자 전부 삭제
                        for(var i=0; i<4; i++) {
                            $('#approver_'+(i+1)).val('');
                        }
                        $('#approval_line').val(lineId);//update는 lineId저장
                        $('#search_approver').empty();
                        count_global = 0;
                    }

                },
                error: function(request, status, errorData) {
                    console.log("error code : " + request.status);
                    console.log("Message : " + request.responseText);
                    console.log("Error : " + errorData);
                }
            });

            $('input[name="title"]').val(title);
            $('.detail_area').html(detail);
            $('input[name="emergency"]').val(emergency);
            $('input[name="startDate"]').val(startDate);
            $('input[name="endDate"]').val(endDate);
            $('#btn_save').val('수정');

            $('input[name="detailType"]').each(function(){
                console.log('$(this).val() : ' + $(this).val() + '  detailType : ' + detailType);
                if($(this).val() == detailType){
                    $(this).prop( 'checked' , true );
                }
            })

            if(originFile != undefined && originFile != null && originFile != '') {
                let el =`
                <div class="contents-update-down-box show">
                    <a class="contents-notice-down" href="apdown.do?ofile=`+originFile+`&rfile=`+renameFile+`">
                        <img src="/pageflow/resources/images/side-icon-dep1-over.png">
                        <span class="origin-file-name">`+decodeURIComponent(originFile)+`</span>
                        <img class="down-img" src="/pageflow/resources/images/down.png">
                    </a>
                </div>`

                $('.input-down-box').append(el);
            }

            console.log('<c:out value="${ approval }" />');
        }



        $('.detail-radio').each(function(){

        })


        initPopupBtn();
        enabledBtn('show');

        
    })
    function inputSubmit(){
    	$('#appendTextArea').val($('.detail_area').text());
    }

    function enabledBtn(vis){
        if(vis == 'show') {
            $('#btn_save').show();
            $('#btn_notclick').hide();
        }else{
            $('#btn_save').hide();
            $('#btn_notclick').show();
        }
        
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
                    <!--!!!form!!!-->
                    <form class="input-form" action="apinsert.do" method="post" onsubmit="inputSubmit();" enctype="multipart/form-data">
                        <input type="hidden" name="apprId" value="${ apprId }">
                        <input type="hidden" name="drafter" value="${ empId }">
                        <input type="hidden" name="draftType" value="annual">
                        <input type="hidden" name="depName" value="${ employee.depName }">
                        <input type="hidden" name="posName" value="${ employee.posName }">
                        <input type="hidden" name="drafterName" value="${ employee.empName }">
                        <input type="hidden" id="approval_line" name="lineId" placeholder="결재할라인" class="search-box-text" value="-1">
                    <!--서치영역-->
                    <div class="search-container">
                        <div class="search-form">

                            <div class="select-box">
                                <div class="select-pan">
                                    <label for="sel_code_my"></label>
                                    <select name="code" id="sel_code_my">
                                        <option value="yoncha" selected>연차신청서</option>
                                    </select>
                                </div>

                            </div>
                            
                            <div class="input-box">
                                <div class="select-pan-nemo width-70">
                                    제목
                                </div>

                                <input type="text" name="title" class="input-box-input noline">
                            </div>

                            <div class="input-box">
                                <div class="select-pan-nemo width-70">
                                    결재라인
                                </div>

                                <div class="search-box-all">
                                    <div class="search-box">
                                        <button type="button" class="input-search-btn search-btn-appr">
                                            <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                        </button>
                                        
                                        <input type="hidden" id="save_line" name="savelineId" placeholder="결재라인" class="search-box-text" value="-1">
                                        <div class="search-box-text search-box-text-div" id="search_line"></div>
                                    </div>
                                    
                                    <div class="search-box">
                                        <button type="button" class="input-search-btn search-btn-appr">
                                            <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                            
                                        </button>
                                        <input type="hidden" id="save_appr" name="lineE" placeholder="결재자" class="search-box-text" value="">
                                        <input type="hidden" id="approver_1" name="approver_1" value="">
                                        <input type="hidden" id="approver_2" name="approver_2" value="">
                                        <input type="hidden" id="approver_3" name="approver_3" value="">
                                        <input type="hidden" id="approver_4" name="approver_4" value="">
                                        <div class="search-box-text search-box-text-div" id="search_approver"></div>
                                    </div>
                                    <!--
                                    <div class="search-box">
                                        <button class="search-btn-appr">
                                            <img class="search-image" src="${ pageContext.servletContext.contextPath }/resources/images/search_btn.png">
                                        </button>
                                        <input type="text" placeholder="참조자" class="search-box-text" value="">
                                    </div>
                                    -->
                                </div>
                                
                            </div>
                            
                            <div class="input-box">
                                
                                <div class="select-pan-nemo width-70">
                                    첨부
                                </div>
                                <div class="input-down-box"></div>
                                <input type="file" name="upfile" class="input-file">
                            </div>

                        </div>
                    </div>
                    <!--서치영역 end-->

                    <!--컨텐츠영역-->
                    <div class="contents-container sort-row">
                        <div class="contents-box aproval">
                            <div class="contents-title"><span>◆</span> 연차신청서</div>
                            <table class="contents-table aproval">
                                
                                <tbody>
                                    <tr>
                                        <td>소속</td>
                                        <td colspan="5" class="approval-td emphtext">${ employee.depName }</td>
                                    </tr>
                                    <tr>
                                        <td>성명</td>
                                        <td colspan="5" class="approval-td emphtext">${ employee.empName }</td>
                                        <input type="hidden" id="hiddenEmpId" name="empId" value="${ empId }">
                                    </tr>
                                    <tr>
                                        <td>직위</td>
                                        <td colspan="5" class="approval-td emphtext">${ employee.posName }</td>
                                    </tr>
                                    <tr>
                                        <td>종류</td>
                                        <td>연차( <input type="radio" name="detailType" value="annual" id="detailType_day" class="detail-radio"> )</td>
                                        <td>월차( <input type="radio" name="detailType" value="month" id="detailType_month" class="detail-radio"> )</td>
                                        <td>보건( <input type="radio" name="detailType" value="health" id="detailType_health" class="detail-radio"> )</td>
                                        <td>특별( <input type="radio" name="detailType" value="special" id="detailType_special" class="detail-radio"> )</td>
                                        <td>공가( <input type="radio" name="detailType" value="official" id="detailType_oficial" class="detail-radio"> )</td>
                                    </tr>
                                    <tr>
                                        <td>사유</td>
                                        <td colspan="5">
                                            <div class="detail_area" contenteditable="true">

                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>기간</td>
                                        <td colspan="5">
                                            시작날짜 <input type="date" name="startDate" class=""> &nbsp;&nbsp;&nbsp;~&nbsp;&nbsp;&nbsp; 
                                            종료날짜 <input type="date" name="endDate" class="">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>비상연락망</td>
                                        <td colspan="5"><input type="tel" name="emergency" class="detail_area detail_emergency" ></td>
                                    </tr>
                                    <tr class="height-200">
                                        <td colspan="6">
                                            <div class="table-empty-box">
                                                <div>
                                                    위와 같이 휴가를 신청하오니 승인하여 주시기 바랍니다.
                                                </div>
                                                
                                                <div>
                                                    ${ nowdays }
                                                </div>
                                                <div>
                                                    <b>소속 : ${ employee.depName }</b> 
                                                </div>
                                                <div>
                                                    <b>성명 : ${ employee.empName }</b>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                                
                            </table>
                        </div>
                        <textarea name="detail" id="appendTextArea"></textarea>
                    </div>
                    <!--컨텐츠영역 end-->
                    
                </div>
                <!--내용 end-->

                <div class="submit-box">
                    <input type="submit" class="contents-input-btn big noline" id="btn_save" value="저장">
                    <button type="button" class="contents-input-btn big noline notclicked" id="btn_notclick" >저장</button>
                </div>
                </form>
                <!--form end-->
            </div>
            <!--main-container end-->
            
            <!--modal-pop-area-->
            <div class="modal-pop-area">
                <!-- 팝업 들어감 -->
                <c:import url="../common/popup.jsp" />
            </div>
            <!--modal-pop-area end-->
            
        </main>
        
    </div>
</body>
</html>