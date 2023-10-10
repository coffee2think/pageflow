
document.addEventListener("DOMContentLoaded", function(){
    var side = new Side();
	side.init();
	side.buttonEvent();
});

function Side(){
	
}

Side.prototype = {
    init : function() {
        var el = ``;
        var info = MENU_INFO[NOWPAGE-1];
        //타이틀
        $('.side-title').text(info.title);
        var title_str = info.branch[SUBPAGE-1].stitle;
        if(info.branch[SUBPAGE-1].sbranch[LNKPAGE-1] != undefined) {
			title_str = title_str + ' - ' + info.branch[SUBPAGE-1].sbranch[LNKPAGE-1].mtitle;
		}
        $('.main-title').text(title_str);
        
        //사이드메뉴
        el = `
        <div class="side-list-box">
            <ul class="side-list">
        `;
        var active = '';

        for(var i=0; i<info.branch.length; i++){
            active = (i == SUBPAGE-1) ? 'active': '';
            el += `
            <li class="side-list-btn depth_`+1+` `+active+`">
                <a href="`+info.branch[i].slink+`" id="sideBtn_`+(i+1)+`">
                    <img src="/page/resources/images/side-icon-dep`+1+`.png">
                    <span>`+info.branch[i].stitle+`</span>
                </a>
            </li>
            `

            for(var j=0; j<info.branch[i].sbranch.length; j++){
                active = (i == SUBPAGE-1 && j == LNKPAGE-1) ? 'active': '';
                el += `
                <li class="side-list-btn depth_`+2+` `+active+`">
                    <a href="`+info.branch[i].sbranch[j].mlink+`" id="sideSubBtn_`+(i+1)+`_`+(j+1)+`">
                        <img src="/page/resources/images/side-icon-dep`+2+`.png">
                        <span>`+info.branch[i].sbranch[j].mtitle+`</span>
                    </a>
                </li>
                `
            }
        }

        el += `
            </ul>
        </div>
        `

        $('.side-body-box').append(el);
    }
    ,
    buttonEvent : function(){
        //헤더의 폴드, 언폴드 버튼
        $('.header-left-btn').on('click', function(){
            var vis = $('.main-side').css('display');
    
            if(vis == 'none') {
                $(this).removeClass('right');
                $('.main-container').removeClass('all');
                $('.main-side').show();
            }else{
                $(this).addClass('right');
                $('.main-container').addClass('all');
                $('.main-side').hide();
            }
        })

        //검색창 나오게 안나오게
        $('#search_visible_btn').on('click', function(){
            var vis = $('.search-form').css('display');

            if(vis == 'none') {
                $(this).removeClass('open');
                $('.search-form').show();
            }else{
                $(this).addClass('open');
                $('.search-form').hide();
            }
        })
    }
}