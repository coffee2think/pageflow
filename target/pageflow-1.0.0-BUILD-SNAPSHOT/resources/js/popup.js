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
		
		$('.search-btn').each(function(){
			$(this).on('click', function(){
				_this.insertVal();
			})
		})
		
    }
    ,
    insertVal : function(){
		console.log('curinput : ' + curinput);
		setList();
	}
	,
	showPopup:function(){
		$('.modal-pop-area').show();
    	$('.modal-pop-box').show();
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