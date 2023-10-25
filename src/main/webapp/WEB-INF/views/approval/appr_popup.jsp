<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="nowdays_" value="<%= java.util.Date.from(java.time.LocalDate.now().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()) %>" />
<fmt:formatDate var="nowdays" value="${ nowdays_ }" pattern="yyyy-MM-dd" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/popup.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery-ui-zoom.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.ui.touch-punch.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
    let popup;
    const myEmpId = '<c:out value="${ loginMember.empId }" />';

    $(function(){
        popup = new Popup();
        popup.init();
    })
    
    function Popup(){}

    Popup.prototype = {
        init : function() {
            _this = this;
            var isDragging = false;
            var offsetX, offsetY;

            $('.modal-pop-title').draggable({
                start: function (e, ui) {
                    isDragging = true;
                    offsetX = e.clientX - $(this).offset().left;
                    offsetY = e.clientY - $(this).offset().top + 52;
                }
                ,
                drag: function (e, ui) {
                    if (!isDragging) return;
                    var left = e.clientX - offsetX;
                    var top = e.clientY - offsetY;

                    $(this).parent('.modal-pop-box').css({
                        "left": left + "px",
                        "top": top + "px"
                    });
                }
                ,
                stop: function (e, ui) {
                    isDragging = false;
                }
            })
            
            $('.modal-pop-close').on('click', function(){
                $(this).parent().parent('.modal-pop-box').hide();
                $(this).parent().parent().parent('.modal-pop-area').hide();
                closePopup();
            })
            
            $('.search-btn-pop').each(function(){
                $(this).on('click', function(){
                    
                })
            })
            
        }
    }

    function setApprovalPopup(json){

        let jdata = json.approval;
        let lineList = json.list;

        console.log('jdata.dep_name : ' + jdata.dep_name);
        console.log('lineList : ' + lineList);

        $('.modal-title').text(jdata.drafter_name + '님의 ' + changeDraftType(jdata.draft_type) + '신청서입니다.');
        $('.modal-number').text('기안서 번호 : ' + jdata.appr_id);

        if(jdata.origin_file != undefined && jdata.origin_file != null && jdata.origin_file != '') {
            el =`
            <div class="contents-notice-down-box show">
                <a class="contents-notice-down" href="apdown.do?ofile=`+jdata.origin_file+`&rfile=`+jdata.rename_file+`">
                    <img src="/pageflow/resources/images/side-icon-dep1-over.png">
                    <span class="origin-file-name">`+decodeURIComponent(jdata.origin_file)+`</span>
                    <img class="down-img" src="/pageflow/resources/images/down.png">
                </a>
            </div>`

            $('.modal-down').append(el);
        }
        

        $('.approval-depName').each(function(){
            $(this).text(jdata.dep_name);
        })
        $('.approval-empName').each(function(){
            $(this).text(jdata.drafter_name);
        })

        $('.approval-job').text(jdata.job_name);
        $('.approval-detail').text(jdata.detail);
        $('.approval-duration').text(jdata.start_date + ' ~ ' + jdata.end_date);
        $('.approval-emergency').text(jdata.emergency);
        $('.approval-date').text(jdata.appr_date);

        let t1 = '', t2 = '', t3 = '', t4 = '', t5 = '';
        if(jdata.detailType == 'annual') t1 = 'o';
        else if(jdata.detailType == 'month') t2 = 'o';
        else if(jdata.detailType == 'health') t3 = 'o';
        else if(jdata.detailType == 'special') t4 = 'o';
        else if(jdata.detailType == 'official') t5 = 'o';

        el = `
            <td>종류</td>
            <td>연차(`+t1+`)</td>
            <td>월차(`+t2+`)</td>
            <td>보건(`+t3+`)</td>
            <td>특별(`+t4+`)</td>
            <td>공가(`+t5+`)</td>
            `
            $('.approval-type').append(el);
            makeApprovalLine(lineList, jdata);//라인생성
        isFirst = true;
    }

    function changeDraftType(draftType){
        let draftStr = '';
        switch (draftType) {
            case 'amount':
                draftStr = '지출결의서'
                break;
            default:
                draftStr = '연차'
                break;
        }

        return draftStr;
    }

    function makeApprovalLine(lineList, jdata){

        let el = ``;

        //결재 도장 첫번째 tr
        el += `
        <tr>
            <td rowspan="3" class="td-50">결재</td>
            <td class="td-100">기안자</td>`

        for(let i=0; i<lineList.length; i++) {
            el += `<td class="td-100">`+lineList[i].pos_name+`</td>`;
        }
        el += `
        </tr>`
        let buttonvis = false;
        let myLineId;
        let stamp = '';
        el += `
        <tr>
            <td class="ht-60"><div class="table-stamp">결재</div></td>`
        //결재 도장 두번째 tr
        for(let i=0; i<lineList.length; i++) {

            if(lineList[i].stamp_check == 'Y') stamp = '<div class="table-stamp">결재</div>';
            else if(lineList[i].stamp_check == 'N') stamp = '<div class="table-stamp">반려</div>';
            else  stamp = '<div class="table-stamp"></div>';
            el += `<td class="ht-60" id="myTag_`+lineList[i].approver_id+`">`+stamp+`</td>`;

            if(lineList[i].approver_id == myEmpId && lineList[i].stamp_check == 'E') {
                buttonvis = true;
                myLineId = i;
            }
        }
        el += `
        </tr>`


        el += `
        <tr>
            <td class="font-10">`+jdata.appr_date+`</td>`
        //결재 도장 세번째 tr
        for(let i=0; i<lineList.length; i++) {

            el += `<td class="font-10" id="myDate_`+lineList[i].approver_id+`">`+lineList[i].stamp_date+`</td>`;
            console.log('lineList[i].approver_id : ' + lineList[i].approver_id + '  myEmpId : ' + myEmpId);
            
        }
        el += `
        </tr>`
        
        $('#aprovalLine_table').append(el);

        $('.modal-table').css({'width' : (lineList.length+1)*100 + 'px'});

        
        if(buttonvis) {
            el = `
            <input type="button" class="contents-input-btn big noline" id="btn_aproval" value="결재">&nbsp;&nbsp; 
            <input type="button" class="contents-input-btn big noline" id="btn_companion" value="반려">
            `
            
            $('.modal-aproval-btnbox').append(el);

            $('.contents-input-btn').each(function(){
                $(this).on('click', function(){
                    let id = $(this).attr('id');
                    let str = '', type = '';
                    

                    if(id == 'btn_aproval') {
                        //결재
                        str = '결재하시겠습니까?';
                        type = 'Y';

                    }else {
                        //반려
                        str = '반려하시겠습니까?';
                        type = 'N';
                    }

                    if(confirm(str)) {
                        sendApproval(lineList[myLineId], jdata, type);
                    }
                         
                })
            })
            
        }
        
    }

    function sendApproval(list, jdata, type){

        $.ajax({
        url: 'apsendapp.do',
        type: 'post',
        data: {
            apprId : jdata.appr_id
            ,lineId : list.line_id
            ,approverId : list.approver_id
            ,stampCheck : type
        },
        success: function(data){
            $('.contents-input-btn').each(function(){
                    $(this).hide();
            })
            if(data == 'ok') {
                let stamp = (type == 'Y') ? '결재' : '반려';
                $('#myTag_' + list.approver_id).empty();
                $('#myTag_' + list.approver_id).append('<div class="table-stamp">'+stamp+'</div>');
                $('#myDate_' + list.approver_id).text(${ nowdays });
            }
            
        },
        error: function(jqXHR, textStatus, errorThrown){
            console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
            alert('결재 검색이 되지 않습니다.');
        }
    });
    }

    function closePopup(){
        location.href = 
        'aplist.do?apType='+appr_param+'&empId='+${empId};
        return;

        $('#aprovalLine_table').empty();
        $('.modal-aproval-btnbox').empty();
        $('.approval-type').empty();
    }

</script>
</head>
<body>
    <div class="modal-pop-box big" id="approval_pop_box">
        <div class="modal-pop-title">
            전자결재
            <button class="modal-pop-close">
                <img src="./resources/images/close.png">
            </button>
        </div>
        
        <!--modal-pop-->
        <div class="modal-pop">

            <div class="modal-title">
                
            </div>
            <div class="modal-number">
                
            </div>

            <div class="modal-aproval">
                <table class="contents-table modal-table" id="aprovalLine_table">
                    <thead>
                        
                    </thead>
                </table>
            </div>

            <div class="modal-down">
            </div>
            
            <div class="modal-aproval modal-line">
                <table class="contents-table aproval">
                        
                    <tbody>
                        <tr>
                            <td>소속</td>
                            <td colspan="5" class="approval-td emphtext approval-depName"></td>
                        </tr>
                        <tr>
                            <td>성명</td>
                            <td colspan="5" class="approval-td emphtext approval-empName"></td>
                        </tr>
                        <tr>
                            <td>직위</td>
                            <td colspan="5" class="approval-td emphtext approval-job"></td>
                        </tr>
                        <tr class="approval-type">
                            
                        </tr>
                        <tr>
                            <td>사유</td>
                            <td colspan="5" class="approval-td emphtext approval-detail"></td>
                        </tr>
                        <tr>
                            <td>기간</td>
                            <td colspan="5" class="approval-td emphtext approval-duration"></td>
                        </tr>
                        <tr>
                            <td>비상연락망</td>
                            <td colspan="5" class="approval-td emphtext approval-emergency"></td>
                        </tr>
                        <tr class="height-200">
                            <td colspan="6">
                                <div class="table-empty-box">
                                    <div>
                                        위와 같이 휴가를 신청하오니 승인하여 주시기 바랍니다.
                                    </div>
                                    <div class="approval-date">
                                        20 년
                                        월
                                        일
                                    </div>
                                    <div>
                                        <b>소속 : </b>
                                        <span class="emphtext approval-depName"></span>
                                    </div>
                                    <div>
                                        <b>성명 : </b>
                                        <span class="emphtext approval-empName"></span>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                    
                </table>
            </div>

            <div class="modal-aproval flex-center modal-aproval-btnbox">
                
            </div>

        </div>
        <!--modal-pop end-->

    </div>
    
</body>
</html>