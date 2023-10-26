<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="empId" value="-1"/>
<c:if test="${ !empty loginMember }">
    <c:set var="empId" value="${ loginMember.empId }"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="initial-scale=1.0,maximum-scale=3.0,minimum-scale=1.0,width=device-width,minimal-ui">
<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/header.css">
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/page_info.js"></script>
<script type="text/javascript">

    let empId = Number('<c:out value="${ empId }" />');

    document.addEventListener("DOMContentLoaded", function(){
        var header = new Header();
        header.init();

        
        console.log('header empId : ' + empId);
        //로그인 안했을때 나의 결재페이지 안보이게
        
        if(empId == -1) {
            //전자결재 로그인 안했을 시
            $('#header_sep_'+5).hide();
            $('#header_sep_'+6).hide();

            $('#headerTable_'+6).hide();
            $('#header_sep_'+7).hide();
            
            $('#headerTable_'+7).hide();
            $('#header_sep_'+8).hide();
        }else{
            console.log('=========empId : ' + empId);
            $('#headerBtn_'+6).attr('href', 'aplist.do?apType=my&empId='+empId);
        }

    });

    function Header(){
        
    }

    Header.prototype = {
        init : function(){
            
            var el = ``;
            for(var i=0; i<MENU_INFO.length; i++){
                el += `
                <div class="header-table-div" id="headerTable_`+(i+1)+`">
                    <a class="header-btn" id="headerBtn_`+(i+1)+`" href="`+MENU_INFO[i].link+`">`
                    +MENU_INFO[i].title+
                    `</a>
                </div>`;
                
                if(i != MENU_INFO.length-1) {
                    el += `<span class="header-sep" id="header_sep_`+(i+1)+`"></span>`;
                }
            }
            console.log('===NOWPAGE : ' + NOWPAGE);
            $('.header-table').append(el);

            $('#headerBtn_'+NOWPAGE).addClass('active');
            
            $('.header-right').on('click', function(){
                
            })

            if(empId != -1) {
                $('#headerBtn_7').attr('href', 'movemypage.do?empId='+empId);
            }
        }
    }

</script>
<title></title>
</head>
<body>
	<a class="header-left" href="${ pageContext.servletContext.contextPath }/main.do">
        <img src="${ pageContext.servletContext.contextPath }/resources/images/logo.png">
    </a>

    <div class="header-table">
    	
    </div>

    <div class="header-right">
    <c:if test="${ empty sessionScope.loginMember }">
    	<button class="header-right-btn">
    		<img src="${ pageContext.servletContext.contextPath }/resources/images/profile.png">
    	</button>
    	 <a class="header-right-a" href="loginPage.do" >
        	로그인
        </a>
    </c:if>
 
    <c:if test="${ !empty sessionScope.loginMember }">
    	${ loginMember.empName }님
    	<a href="logout.do">로그아웃</a>
    	<a href="movemypage.do?empId=${ empId }">마이페이지</a>
    </c:if>
   
    </div>
</body>
</html>