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
	console.log("id : " + id + "url : " + url);
    var currentRow = $('#completeBtn_' + id).parent().parent();
    var json = {};

    // 서버로 전송할 값 json에 담기
    var len = currentRow.children('td').length;
    currentRow.find('input').each(function(index) {
        // 양끝열(체크박스와 수정버튼 열)은 건너뜀
         console.log($(this).attr('name'));
        if(index == 0 || index >= len) {
          
            //return;
            
        }

        // 콤마를 삭제한 value 값이 숫자라면 숫자로 파싱
        if(!isNaN($(this).val().replace(/,/g, ''))) {
			$(this).val(Number($(this).val()));
		}

        json[$(this).attr('name')] = $(this).val();

    });

    console.log("json : " + JSON.stringify(json));
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