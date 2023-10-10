
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