<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
document.addEventListener("DOMContentLoaded", function(){
    var header = new Header();
	header.init();
});

function Header(){
    
}

Header.prototype = {
    init : function(){
		
        var el = ``;
        for(var i=0; i<MENU_INFO.length; i++){
            el += `
            <div class="header-table-div">
                <a class="header-btn" id="headerBtn_`+(i+1)+`" href="`+MENU_INFO[i].link+`">`
                +MENU_INFO[i].title+
                `</a>
            </div>`;

            if(i != MENU_INFO.length-1) {
                el += `<span class="header-sep"></span>`;
            }
        }

        $('.header-table').append(el);

        $('#headerBtn_'+NOWPAGE).addClass('active');
        
        $('.header-right').on('click', function(){
			
		})
    }
}

</script>
<title></title>
</head>
<body>
	<a class="header-left" href="${ pageContext.servletContext.contextPath }/main.jsp">
        <img src="${ pageContext.servletContext.contextPath }/resources/images/logo.png">
    </a>

    <div class="header-table">
    	
    </div>

    <div class="header-right">
    	<button class="header-right-btn">
    		<img src="${ pageContext.servletContext.contextPath }/resources/images/profile.png">
        	<span>홍길동</span>
    	</button>
    	
        <a class="header-right-a" href="${ pageContext.servletContext.contextPath }/views/member/login.jsp">
        	로그인
        </a>
    </div>
</body>
</html>