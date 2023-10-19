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
    var popup;
    document.addEventListener("DOMContentLoaded", function(){
        popup = new Popup();
        popup.init();
    });

    function Popup(){
        
    }

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
                홍길동님의 연차신청서입니다.
            </div>
            <div class="modal-number">
                890-12
            </div>

            <div class="modal-aproval">
                <table class="contents-table modal-table">
                    <thead>
                        <tr>
                            <td rowspan="3" class="td-50">결재</td>
                            <td class="td-100">기안자</td>
                            <td class="td-100">팀장</td>
                        </tr>
                        <tr>
                            <td class="ht-60"></td>
                            <td class="ht-60"></td>
                        </tr>
                        <tr>
                            <td class="font-10">2003/08/28</td>
                            <td class="font-10"></td>
                        </tr>
                    </thead>
                </table>
            </div>
            
            <div class="modal-aproval modal-line">
                <table class="contents-table aproval">
                        
                    <tbody>
                        <tr>
                            <td>소속</td>
                            <td colspan="5"></td>
                        </tr>
                        <tr>
                            <td>성명</td>
                            <td colspan="5"></td>
                        </tr>
                        <tr>
                            <td>직위</td>
                            <td colspan="5"></td>
                        </tr>
                        <tr>
                            <td>종류</td>
                            <td>연차()</td>
                            <td>월차()</td>
                            <td>보건()</td>
                            <td>특별()</td>
                            <td>공가()</td>
                        </tr>
                        <tr>
                            <td>사유</td>
                            <td colspan="5"></td>
                        </tr>
                        <tr>
                            <td>기간</td>
                            <td colspan="5"></td>
                        </tr>
                        <tr>
                            <td>비상연락망</td>
                            <td colspan="5"></td>
                        </tr>
                        <tr class="height-200">
                            <td colspan="6">
                                <div class="table-empty-box">
                                    <div>
                                        위와 같이 휴가를 신청하오니 승인하여 주시기 바랍니다.
                                    </div>
                                    <div>
                                        20 년
                                        월
                                        일
                                    </div>
                                    <div>
                                        <b>소속 : </b> 
                                    </div>
                                    <div>
                                        <b>성명 : </b>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                    
                </table>
            </div>

            <div class="modal-aproval flex-center">
                <input type="button" class="contents-input-btn big noline" id="btn_aproval" value="결재">&nbsp;&nbsp; 
                <input type="button" class="contents-input-btn big noline" id="btn_companion" value="반려">
            </div>

        </div>
        <!--modal-pop end-->

    </div>
    
</body>
</html>