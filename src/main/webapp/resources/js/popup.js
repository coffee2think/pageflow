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
		});
		
		$('.search-btn-pop').each(function(){
			$(this).on('click', function(){
				_this.insertVal();
			})
		});
    }
    ,
    insertVal : function(){
		//console.log('curinput : ' + curinput);
		//setList();
	}
	,
	showPopup:function(type){
		popup_type = type;
		
		// 팝업창 테이블 초기화
		_this.initTable();
		
		// 팝업창 보이기 설정
		$('.modal-pop-area').show();
    	$('.modal-pop-box').show();
	}
	,
	initTable: function() {
		// popup_type에 따른 값 지정
		popup_title = {
			'book': '도서검색',
			'printoffice': '인쇄소검색',
			'bookstore': '서점검색',
			'storage': '창고검색',
			'employee': '직원검색',
            'writer': '작가검색',
			'book_order': '주문검색',
            'saveLine' : '결재라인검색',
            'lineE': '직원검색',
            'department': '부서검색'
		};
		
		popup_thead = {
			'book': ['', '도서코드', '도서명', '정가', '재고현황'],
			'printoffice': ['', '거래처코드', '인쇄소명', '담당자', '연락처'],
			'bookstore': ['', '거래처코드', '서점명', '담당자', '연락처'],
			'storage': ['', '거래처코드', '창고명', '담당자', '연락처'],
			'employee': ['', '직원번호', '담당부서', '직원명', '직책'],
            'writer': ['', '작가번호', '작가명', '연락처', '이메일'],
			'book_order': ['', '주문번호', '서점명', '도서명', '주문수량'],
            'saveLine' : ['', '결재라인번호', '결재라인이름', '결재자1', '결재자2', '결재자3', '결재자4'],
            'lineE': ['', '직원번호', '담당부서', '직원명', '직책'],
            'department': ['', '부서코드', '부서명', '', '']
		};
		
		popup_selectbox = {
			'book': {'bookName': '도서명', 'bookId': '도서코드'},
			'printoffice': {'clientName': '인쇄소명', 'clientId': '거래처코드'},
			'bookstore': {'clientName': '서점명', 'clientId': '거래처코드'},
			'storage': {'clientName': '창고명', 'clientId': '거래처코드'},
			'employee': {'empName': '직원명', 'depName': '부서'},
            'writer': {'writerName': '작가명'},
			'book_order': {'clientName': '서점명', 'orderDate': '주문일'},
            'saveLine': {'savelineId': '결재라인번호', 'savelineName': '결재라인이름'},
            'lineE': {'empName': '직원명', 'depName': '부서'},
			'department': {'depName': '부서명'}
		};
		
		// 검색바 초기화
		$('#sel_code').find('option').each(function(){
			$(this).attr('selected', false);
		});
		$('#sel_code').find('option:eq(0)').attr('selected', true);
		$('input[type=search]').val('');
		
		var option = $('#sel_code').val();
		if(option.toLowerCase().includes('date')) {
			$('.modal-pop-search .search-box input[type=search]').hide();
			$('.modal-pop-search .search-box input[type=date]').show();
			$('span.wave').show();
		} else {
			$('.modal-pop-search .search-box input[type=search]').show();
			$('.modal-pop-search .search-box input[type=date]').hide();
			$('span.wave').hide();
		}
		
		// 창닫기 시 조회된 테이블 정보 삭제
		const table = $('#popup_table');
		const trList = table.find('tr');
		trList.each(function(index) {
			if(index > 0) {
				trList[index].remove();
			}
		});
		
		// 제목행 초기화
		const thead = table.find('thead');
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

        $('.modal-pop-search').css('visibility', 'visible');


        //-----결재라인 검색만 예외-----
        if(popup_type == 'saveLine') {
            var sendData = {};
		    tr.append('<td></td>');
		    tr.append('<td></td>');
            $('.modal-pop-search').css('visibility', 'hidden');

            sendData['empId'] = $('#hiddenEmpId').val();
            sendData['searchType'] = 'savelineId';
            sendData['keyword'] = '';

            $.ajax({
                url: 'popuplinesearch.do',
                type: 'post',
                data: sendData,
                dataType: 'json',
                success: function(result) {
                    // object => string
                    var jsonStr = JSON.stringify(result);
                    // string => parsing : json object
                    var json = JSON.parse(jsonStr);
                    json_global = json;
                    
                    console.log(json);
                    
                    // json 객체 안의 list를 하나씩 꺼내서 새로운 행으로 추가 처리
                    // 기존 행 정보 삭제
                    const trList = $('#popup_table').find('tr');
                    trList.each(function(index) {
                        if(index > 0) {
                            trList[index].remove();
                        }
                    });
        
                    const table = $('#popup_table');
                    const tbody = table.find('tbody');
                    approval_global = {
                        idArr : []
                        ,approver : []
                    }
                    //결재라인 검색은 이중포문
                    for(var i in json.list) {
                        approval_global.approver[i] = []
                        approval_global.idArr.push(json.list[i][0].savelineId)
                        var jdata = [];
                        for(var j in json.list[i]) {
                            console.log('json.list[i][j].approverName : ' + json.list[i][j].approverName)
                            jdata.push(json.list[i][j].approverName);
                            if(json.list[i][j].approverName != undefined){
                                approval_global.approver[i].push(json.list[i][j].approverName);
                            }
                        }
                        //console.log('i : ' + i)
                        tbody.append('<tr onclick=""  class="cursor-pointer">');
                        const tr = tbody.find('tr:last');
                        
                        tr.append('<td><input type="radio" name="radio" onchange="selectIndex(' + i + ');"></td>');
                        
                        const list = json.list[i];
                        
                        tr.append('<td>' + decodeURIComponent(list[0].savelineId).replace(/\+/gi, ' ') + '</td>');
                        tr.append('<td>' + decodeURIComponent((list[0].lineName == null) ? '' : list[0].lineName).replace(/\+/gi, ' ') + '</td>');
                        tr.append('<td>' + decodeURIComponent(setTextUndefined(jdata[0])).replace(/\+/gi, ' ') + '</td>');
                        tr.append('<td>' + decodeURIComponent(setTextUndefined(jdata[1])).replace(/\+/gi, ' ') + '</td>');
                        tr.append('<td>' + decodeURIComponent(setTextUndefined(jdata[2])).replace(/\+/gi, ' ') + '</td>');
                        tr.append('<td>' + decodeURIComponent(setTextUndefined(jdata[3])).replace(/\+/gi, ' ') + '</td>');
                    }
                },
                error: function(request, status, errorData) {
                    console.log("error code : " + request.status);
                    console.log("Message : " + request.responseText);
                    console.log("Error : " + errorData);
                }
            });
        }
        //-----결재라인 검색만 예외 end------
		
		// 팝업창 제목 초기화
		$('.modal-pop-title > span').text(popup_title[popup_type]);
		
		// select 박스 초기화
        console.log('!!!!!!!!!!popup_type : ' + popup_type);
		var selectbox = popup_selectbox[popup_type];
        console.log('!!!!!!!!!!selectbox : ' + selectbox);
		var keys = Object.keys(selectbox);
        console.log('!!!!!!!!!!keys : ' + keys);
		$('#sel_code').empty();
		for(i = 0; i < keys.length; i++) {
            console.log('!!!!!!!!!!selectbox[keys[i]] : ' + selectbox[keys[i]]);
			$('#sel_code').append('<option value="' + keys[i] + '">' + selectbox[keys[i]] + '</option>');
		}
        
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