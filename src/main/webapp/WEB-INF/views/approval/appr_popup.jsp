<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
            })
            
            $('.search-btn-pop').each(function(){
                $(this).on('click', function(){
                    
                })
            })
            
        }
    }

    function setApprovalPopup(jdata){

        console.log('jdata.dep_name : ' + jdata.dep_name);

        $('.modal-title').text(jdata.drafter_name + '님의 ' + changeDraftType(jdata.draft_type) + '신청서입니다.');
        $('.modal-number').text('기안서 번호 : ' + jdata.appr_id);

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

        let t1 = 'o', t2 = '', t3 = '', t4 = '', t5 = '';
        if(jdata.detailType == 'annual') t1 = 'o';

        let el = `
        <td>종류</td>
        <td>연차(`+t1+`)</td>
        <td>월차(`+t2+`)</td>
        <td>보건(`+t3+`)</td>
        <td>특별(`+t4+`)</td>
        <td>공가(`+t5+`)</td>
        `
        $('.approval-type').append(el);

        makeApprovalLine(jdata);//라인생성
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

    function makeApprovalLine(jdata){

        let el = ``;
        let empData = [];
        for(let i=0; i<4; i++) {
            if(jdata['emp_name'+(i+1)] != null && jdata['emp_name'+(i+1)] != undefined) {
                empData.push({
                    emp_id : jdata['emp_id'+(i+1)]
                    ,emp_name : jdata['emp_name'+(i+1)]
                    ,pos_name : jdata['pos_name'+(i+1)]
                    ,date : jdata['stamp_date'+(i+1)]
                });
            }
        }

        //결재 도장 첫번째 tr
        el += `
        <tr>
            <td rowspan="3" class="td-50">결재</td>`

        console.log('jdata.emp_name4 : ' + jdata.emp_name4)
        for(let i=0; i<empData.length; i++) {
            if(i == 0){
                el += `<td class="td-100">기안자</td>`;
            }else{
                el += `<td class="td-100">`+empData[i].pos_name+`</td>`;
            }
        }
        el += `
        </tr>`

        let stamp = '결재';
        el += `
        <tr>`
        //결재 도장 두번째 tr
        for(let i=0; i<empData.length; i++) {
            el += `<td class="ht-60">`+stamp+`</td>`;
        }
        el += `
        </tr>`

        let buttonvis = false;

        el += `
        <tr>`
        //결재 도장 세번째 tr
        for(let i=0; i<empData.length; i++) {
            el += `<td class="font-10">`+empData[i].date+`</td>`;
            console.log('empData[i].emp_id : ' + empData[i].emp_id + '  myEmpId : ' + myEmpId);
            if(empData[i].emp_id == myEmpId) buttonvis = true;
        }
        el += `
        </tr>`
        
        $('#aprovalLine_table').append(el);

        $('.modal-table').css({'width' : empData.length*100 + 'px'});

        
        if(buttonvis) {
            el = `
            <input type="button" class="contents-input-btn big noline" id="btn_aproval" value="결재">&nbsp;&nbsp; 
            <input type="button" class="contents-input-btn big noline" id="btn_companion" value="반려">
            `
            $('.modal-aproval-btnbox').append(el);
        }
        
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