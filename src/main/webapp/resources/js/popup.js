var popup;
var popup_type;

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
				_this.insertVal();
			})
		})
		
		
		
    }
    ,
    insertVal : function(){
		console.log('curinput : ' + curinput);
		//setList();
	}
	,
	showPopup:function(type){
		popup_type = type;
		
		// 팝업창 테이블 초기화
		_this.initTable();
		
		// 팝업창 보이기 설정
		$('.modal-pop-area').show();
    	/*$('.modal-pop-box').each(function(){
			$(this).hide();
		})*/
		
    	$('.modal-pop-box').show();
	}
	,
	initTable: function() {
		// 검색바 초기화
		$('#sel_code').children('option').each(function(){
			$(this).attr('selected', false);
		});
		$('#sel_code').children('option:eq(0)').attr('selected', true);
		$('input[type=search]').val('');
		
		// 창닫기 시 조회된 테이블 정보 삭제
		const table = $('#popup_table');
		const trList = table.find('tr');
		trList.each(function(index) {
			if(index > 0) {
				trList[index].remove();
			}
		});
		
		// 제목행 초기화
		popup_thead = {
			'book': ['', 'No.', '도서코드', '도서명', '재고현황'],
			'printoffice': ['', 'No.', '거래처코드', '인쇄소명', '연락처'],
			'bookstore': ['', 'No.', '거래처코드', '서점명', '연락처'],
			'storage': ['', 'No.', '거래처코드', '창고명', '연락처'],
			'book_order': ['', 'No.', '주문번호', '도서명', '주문수량']
		};
		
		const thead = table.children('thead');
		thead.find('th').each(function(index) {
			$(this).text(popup_thead[popup_type][index]);
		});
		
		// 빈행 추가
		table.append('<tr onclick=""  class="cursor-pointer">');
		const tr = table.find('tr:last');
		tr.append('<td><input type="radio" name="radio" value=""></td>');
		tr.append('<td></td>');
		tr.append('<td></td>');
		tr.append('<td></td>');
		tr.append('<td></td>');
		
		// 팝업창 제목 초기화
    	popup_title = {
			'book': '도서검색',
			'printoffice': '인쇄소검색',
			'bookstore': '서점검색',
			'storage': '창고검색',
			'book_order': '주문검색'
		};
		$('.modal-pop-title > span').text(popup_title[popup_type]);
	}
    ,
    append:function(data){
        var str = `
        <tr data-parent="${sad}" data-num="${sad}" data-depth="${sad}" class="table-td-depth1">
                                        
            <td class="td-50">
                <div class="contents-check-div">
                    <button class="contents-input-plus">
                        <img src="/page/resources/images/plus.png">
                    </button>
                    <button class="contents-input-minus">
                        <img src="/page/resources/images/minus.png">
                    </button>
                </div>
            </td>
            <td class="td-120">
                <div class="contents-input-div input-search">
                    <button class="input-search-btn">
                        <img class="search-image" src="/page/resources/images/search_btn.png">
                    </button>
                    <input type="input" name="no" class="contents-input" value="`+data.pop1+`">
                </div>
            </td>
            <td class="td-120">
                <div class="contents-input-div input-search">
                    <button class="input-search-btn">
                        <img class="search-image" src="/page/resources/images/search_btn.png">
                    </button>
                    <input type="input" name="code" class="contents-input" value="">
                </div>
            </td>
            <td class="td-200">
                <div class="contents-input-div input-search">
                    <button class="input-search-btn">
                        <img class="search-image" src="/page/resources/images/search_btn.png">
                    </button>
                    <input type="input" name="name" class="contents-input" value="">
                </div>
            </td>
            <td class="td-70">
                <div class="contents-input-div">
                    <input type="input" name="location" class="contents-input">
                </div>
            </td>
            <td class="td-100">
                <div class="contents-input-div input-search">
                    <button class="input-search-btn">
                        <img class="search-image" src="/page/resources/images/search_btn.png">
                    </button>
                    <input type="input" name="store" class="contents-input" value="">
                </div>
            </td>
            <td class="td-120">
                <div class="contents-input-div">
                    <input type="date" name="date" class="select-date small">
                </div>
            </td>
            <td class="td-100">
                <div class="contents-input-div">
                    <input type="number" name="price" class="contents-input">
                </div>
            </td>
            <td class="td-70">
                <div class="contents-input-div">
                    <input type="number" name="quantity" class="contents-input">
                </div>
            </td>
            <td class="td-120">
                <div class="contents-input-div">
                    <input type="number" name="amount" class="contents-input" value="">
                </div>
            </td>
            <td class="td-120">
                <div class="contents-input-div">
                    <input type="number" name="collection" class="contents-input" value="">
                </div>
            </td>
            <td class="td-120">
                <div class="contents-input-div">
                    <input type="number" name="balance" class="contents-input" value="">
                </div>
            </td>
        </tr>
        `
        $('.contents-table').append(str);
    }
}