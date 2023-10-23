//결재라인 
$(function(){
    
})


//체크 버튼 4개만 클릭 가능하게
function checkBtnEvent(){
    var maxChecked = 4;

    $("input[type='checkbox']").change(function() {
        console.log('?????')
        var checkedCheckboxes = $("input[type='checkbox']:checked");

        if (checkedCheckboxes.length > maxChecked) {
            $(this).prop("checked", false); // Prevent checking more than 4 checkboxes
        }
    });
}

//라인 마이너스
function makeMinusSaveLine(){
    let count = Number($('.my-line-body tr').last().attr('data-num'));
    $('#mySaveLine_'+count).remove();
}

//라인 플러스
function makePlusSaveLine(){
    let count = Number($('.my-line-body tr').last().attr('data-num'));
    let cnt = count + 1;
    let el = `
    <tr data-parent="1" data-num="`+cnt+`" data-depth="1" class="table-td-depth1" id="mySaveLine_`+cnt+`">
        <td>
            <div class="contents-check-div">
                <button class="contents-input-plus" onclick="makePlusSaveLine(); return false;">
                    <img src="/pageflow/resources/images/plus.png">
                </button>
                <button class="contents-input-minus" onclick="makeMinusSaveLine(); return false;">
                    <img src="/pageflow/resources/images/minus.png">
                </button>
                <input type="hidden" name="empId" value="">
            </div>
        </td>
        <td>
            <div class="contents-check-div">
                <input type="checkbox" name="check" value="">
            </div>
        </td>
        <td>
            <div class="contents-input-div input-search">
                <button class="input-search-btn">
                    <img class="search-image" src="/pageflow/resources/images/search_btn.png">
                </button>
                <input type="input" name="empName" class="contents-input" value="">
            </div>
        </td>
        <td>
            <div class="contents-input-div input-readonly">
                <input type="input" name="posName" class="contents-input" value="" readonly>
            </div>
        </td>
        <td>
            <div class="contents-input-div input-readonly">
                <input type="input" name="depName" class="contents-input nocalc-witdh" value="" readonly>
            </div>
        </td>
        <td>
            <div class="contents-input-div">
                <input type="input" name="order" class="contents-input" value="`+cnt+`">
            </div>
        </td>
    </tr>
    `

    $('.my-line-body').append(el);
    checkBtnEvent();
    initPopupBtn();
}

//나의 결재라인 추가
function makeApprSaveLine(data){
    let lineName = '';
    let approver_1 = '', approver_2 = '', approver_3 = '', approver_4 = '';

    let el = `
    <tr>`;
    
    for(let i=0; i<data.length; i++) {
        if(i == 0){
            lineName = data[i].lineName;
            approver_1 = data[i].approverName;
        }
        else if (i == 1) approver_2 = data[i].approverName;
        else if (i == 2) approver_3 = data[i].approverName;
        else if (i == 3) approver_4 = data[i].approverName;
    } 
    el += `
        <td class="td-150">
            `+lineName+`
        </td>
        <td class="td-100">
            `+approver_1+`
        </td>
        <td class="td-100">
            `+approver_2+`
        </td>
        <td class="td-100">
            `+approver_3+`
        </td>
        <td class="td-100">
            `+approver_4+`
        </td>
        <td class="td-100">
            <input type="button" name="delete" class="contents-input-btn noline" value="삭제">
        </td>
    </tr>
    `
    $('.my-approval-body').append(el);
    
}
