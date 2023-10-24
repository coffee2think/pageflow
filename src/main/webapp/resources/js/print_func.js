var temp = {};

// 수정 버튼 클릭 시 수정 가능 상태로 변경
function onUpdate(id) {
    // 행 버튼 보이기/숨기기 상태 변경
    $('#completeBtn_' + id).show();
    $('#cancelBtn_' + id).show();
    $('#updateBtn_' + id).hide();
    
    // 수정 중인 행의 스타일 변경
    $('#tr_' + id).css('border', 'solid 3px #1a70d3');
    
    // class=changeable인 input 태그 값 변경 가능하도록 변경
    $('#tr_' + id + ' .changeable').attr('readonly', false);
    
    // 기존정보 temp에 임시 보관
    var currentRow = $('#updateBtn_' + id).parent().parent();
    var len = currentRow.children('td').length;
    currentRow.children('td').each(function(index) {
        // 양끝열(체크박스와 수정버튼 열)은 건너뜀
        if(index == 0 || index == len - 1) {
            return; 
        }
        
        // input 태그 정보 temp에 보관
        var input = $(this).find('input')[0];
        temp[input.name + '_' + id] = input.value;
    });
}

// 수정상태 이전 스타일로 되돌리기
function offUpdate(id) {
    // 행 버튼 보이기/숨기기 상태 변경
    $('#completeBtn_' + id).hide();
    $('#cancelBtn_' + id).hide();
    $('#updateBtn_' + id).show();
    
    // 수정 취소한 행의 스타일 원래대로 설정
    $('#tr_' + id).css('border', 'none');
    
    // 수정 취소한 행의 모든 열 readonly 설정
    $('#tr_' + id + ' .changeable').attr('readonly', true);
}

// 취소 버튼 클릭 시 수정 가능 상태로 변경
function cancelUpdate(id) {
    // 같은 주문번호의 행들을 모두 선택
    
    // 수정 이전 스타일로 되돌리기
    offUpdate(id);
    
    // 값을 원래 정보로 되돌리기(reset)
    var currentRow = $('#cancelBtn_' + id).parent().parent();
    var len = currentRow.children('td').length;
    currentRow.children('td').each(function(index) {
        // 양끝열(체크박스와 수정버튼 열)은 건너뜀
        if(index == 0 || index == len - 1) {
            return; 
        }
        
        // temp에서 정보 꺼내서 대입
        var input = $(this).find('input')[0];
        input.value = temp[input.name + '_' + id];
        delete temp[input.name + '_' + id]; // temp에서 정보 삭제
    });
}

function submitUpdate(id, url) {
    var currentRow = $('#completeBtn_' + id).parent().parent();
    var json = {};
    
    // 서버로 전송할 값 json에 담기
    var len = currentRow.children('td').length;
    currentRow.find('input').each(function(index) {
        // 양끝열(체크박스와 수정버튼 열)은 건너뜀
        if(index == 0 || index >= len - 1) {
            return; 
        }
        
        // endDate에 값이 들어있지 않다면 담지 않고 건너뜀
        if($(this).attr('name') == 'endDate' && $(this).val() == '') {
			return;
		}
        
        // 콤마를 삭제한 value 값이 숫자라면 숫자로 파싱
        if(!isNaN($(this).val().replace(/,/g, ''))) {
			$(this).val(Number($(this).val()));
		}
        
        json[$(this).attr('name')] = $(this).val();
    });
    
    // ajax로 update 요청 보내기
    $.ajax({
        url: url,
        type: "post",
        data: json,
        success: function(data){
            console.log("success : " + data);
            if(data == "success") {
                alert(id + "번 정보 수정 성공");
            } else {
                alert("정보 수정 실패");
            }
            
            // 수정 이전 스타일로 되돌리기
            offUpdate(id);
        },
        error: function(jqXHR, textStatus, errorThrown){
            console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
        }
    });
}

// 선택된 행의 정보 삭제 요청(post 전송)
function deleteCheckedRow(url) {
    // form 태그에 담아서 post 전송
    const form = document.createElement('form'); // form 태그 생성
    form.setAttribute('method', 'post'); // 전송 방식 결정 (get or post)
    form.setAttribute('action', url); // 전송할 url 지정
    
    var checked = $('#table_list').find('input[type="checkbox"]:checked');
    if(checked.length == 0) {
        alert('삭제할 행을 체크해주세요.');
        return false;
    }
    checked.each(function() {
        const id = $(this).val();
        
        // input 태그 생성하여 데이터 담기
        const data = document.createElement('input');
        data.setAttribute('type', 'hidden');
        data.setAttribute('name', 'IDs'); // 데이터의 name
        data.setAttribute('value', id); // 데이터의 value
        
        // form 태그에 input 태그 넣기 
        form.appendChild(data);
    });
    
    // body에 form 태그 추가하고 submit 전송
    document.body.appendChild(form);
    form.submit();
}

function searchByDate(url) {
	var begin = $('#begin').val();
	var end = $('#end').val();
	
	url += '?begin=' + begin;
	url += '&end=' + end;
	
	location.href = url;
}

function searchKeyword(url) {
    var searchType = $('#search_type').val();
    var keyword = $('input[type=search]').val();
    
    // 입력된 값이 없다면 검색 요청을 취소함
    if(keyword == '') {
        alert('키워드를 입력해주세요.');
        return false;
    }
    
    // form 태그에 담아서 post 전송
    const form = document.createElement('form'); // form 태그 생성
    form.setAttribute('method', 'post'); // 전송 방식 결정 (get or post)
    form.setAttribute('action', url); // 전송할 url 지정
    
    const data1 = document.createElement('input');
    data1.setAttribute('type', 'hidden');
    data1.setAttribute('name', 'searchType'); // 데이터의 name
    data1.setAttribute('value', searchType); // 데이터의 value
    form.appendChild(data1);
    
    const data2 = document.createElement('input');
    data2.setAttribute('type', 'hidden');
    data2.setAttribute('name', 'keyword'); // 데이터의 name
    data2.setAttribute('value', keyword); // 데이터의 value
    form.appendChild(data2);
    
    // body에 form 태그 추가하고 submit 전송
    document.body.appendChild(form);
    form.submit();
}
// 수량 * 단가 = 총액 계산
function calculateAmount() {
    var rows = $('#table_list').find('tr');
    rows.each(function() {
        var row = $(this);
        var quantity = row.find('input[name="quantity"]').val();
        var price = row.find('input[name="price"]').val();
        if (quantity !== undefined && price !== undefined) {
            var amount = quantity * price;
            if (!isNaN(amount)) {
                row.find('input[name="amount"]').val(amount);
            }
        }
    });
}
