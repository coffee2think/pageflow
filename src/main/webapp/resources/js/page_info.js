


var MENU_INFO = [
    {
        title : '업무관리'
        ,link : 'bdlist.do'
        ,branch : [
            {
                stitle : '공지사항'
                ,slink : 'notice_list.do'
                ,sbranch : [
                ]
            },
            {
                stitle : '업무게시판'
                ,slink : 'bdlist.do'
                ,sbranch : [
                    {mtitle : '개발팀', mlink : 'bdlist.do'}
                ]
            },
            {
                stitle : '관리자'
                ,slink : ''
                ,sbranch : [
                    {mtitle : '사원등록', mlink : ''},
                    {mtitle : '사원정보수정', mlink : ''},
                    {mtitle : '권한설정', mlink : ''}
                ]
            }
        ]
    }
    ,
    {
        title : '인쇄관리'
        ,link : '/page/views/print/print_list.jsp'
        ,branch : [
            {
                stitle : '인쇄의뢰'
                ,slink : '/page/views/print/print_list.jsp'
                ,sbranch : [
                    {mtitle : '인쇄의뢰현황', mlink : '/page/views/print/print_list.jsp'},
                    {mtitle : '인쇄의뢰등록', mlink : '/page/views/print/print_input.jsp'}
                ]
            },
            {
                stitle : '발주관리'
                ,slink : '/page/views/print/porder_list.jsp'
                ,sbranch : [
                    {mtitle : '발주현황', mlink : '/page/views/print/porder_list.jsp'},
                    {mtitle : '발주등록', mlink : '/page/views/print/porder_input.jsp'}
                ]
            },
            {
                stitle : '정산관리'
                ,slink : '/page/views/print/calc_list.jsp'
                ,sbranch : [
                    {mtitle : '정산현황', mlink : '/page/views/print/calc_list.jsp'},
                    {mtitle : '정산등록', mlink : '/page/views/print/calc_input.jsp'}
                ]
            }
        ]
    }
    ,
    {
        title : '출판관리'
        ,link : '/page/views/publish/contr_list.jsp'
        ,branch : [
            {
                stitle : '계약관리'
                ,slink : '/page/views/publish/contr_list.jsp'
                ,sbranch : [
                    {mtitle : '계약현황', mlink : '/page/views/publish/contr_list.jsp'},
                    {mtitle : '계약등록', mlink : '/page/views/publish/contr_input.jsp'}
                ]
            },
            {
                stitle : '도서관리'
                ,slink : '/page/views/publish/book_list.jsp'
                ,sbranch : [
                    {mtitle : '도서현황', mlink : '/page/views/publish/book_list.jsp'},
                    {mtitle : '도서등록', mlink : '/page/views/publish/book_input.jsp'}
                ]
            },
            {
                stitle : '편집관리'
                ,slink : '/page/views/publish/edit_list.jsp'
                ,sbranch : [
                    {mtitle : '편집현황', mlink : '/page/views/publish/edit_list.jsp'},
                    {mtitle : '편집등록', mlink : '/page/views/publish/edit_input.jsp'}
                ]
            }
        ]
    },
    {
        title : '재고관리'
        ,link : '/page/views/inventory/store_list.jsp'
        ,branch : [
            {
                stitle : '입고관리'
                ,slink : '/page/views/inventory/store_list.jsp'
                ,sbranch : [
                    {mtitle : '입고현황', mlink : '/page/views/inventory/store_list.jsp'},
                    {mtitle : '입고입력', mlink : '/page/views/inventory/store_input.jsp'}
                ]
            },
            {
                stitle : '출고관리'
                ,slink : '/page/views/inventory/release_list.jsp'
                ,sbranch : [
                    {mtitle : '출고현황', mlink : '/page/views/inventory/release_list.jsp'},
                    {mtitle : '출고등록', mlink : '/page/views/inventory/release_input.jsp'}
                ]
            },
            {
                stitle : '반품관리'
                ,slink : '/page/views/inventory/refund_list.jsp'
                ,sbranch : [
                    {mtitle : '반품현황', mlink : '/page/views/inventory/refund_list.jsp'},
                    {mtitle : '반품입력', mlink : '/page/views/inventory/refund_input.jsp'}
                ]
            },
            {
                stitle : '재고관리'
                ,slink : '/page/views/inventory/inven_list.jsp'
                ,sbranch : [
                    {mtitle : '재고현황', mlink : '/page/views/inventory/inven_list.jsp'}
                ]
            }
        ]
    }
    ,
    {
        title : '영업관리'
        ,link : 'movebolist.do'
        ,branch : [
            {
                stitle : '주문관리'
                ,slink : 'movebolist.do'
                ,sbranch : [
                    {mtitle : '주문현황', mlink : 'movebolist.do'},
                    {mtitle : '주문입력', mlink : 'boinsert.do'}
                ]
            },
            {
                stitle : '판매관리'
                ,slink : 'salesList.do'
                ,sbranch : [
                    {mtitle : '판매현황', mlink : 'salesList.do'},
                    {mtitle : '판매입력', mlink : 'salesInput.do'}
                ]
            },
            {
                stitle : '거래처'
                ,slink : 'clientList.do'
                ,sbranch : [
                    {mtitle : '거래처현황', mlink : 'clientList.do'},
                    {mtitle : '거래처등록', mlink : 'clientInput.do'}
                ]
            },
            {
                stitle : '매출관리'
                ,slink : '/page/views/sales/income_list.jsp'
                ,sbranch : [
                    {mtitle : '매출통계', mlink : '/page/views/sales/income_list.jsp'},
                    {mtitle : '도서트렌드분석', mlink : '/page/views/sales/trend_list.jsp'}
                ]
            }
        ]
    }
    ,
    {
        title : '전자결재'
        ,link : '/page/views/approval/appr_list.jsp'
        ,branch : [
            {
                stitle : '내결재관리'
                ,slink : '/page/views/approval/appr_list.jsp'
                ,sbranch : [
                ]
            },
            {
                stitle : '기안서통합관리'
                ,slink : '/page/views/approval/appr_listall.jsp'
                ,sbranch : [
                ]
            },
            {
                stitle : '기안서작성'
                ,slink : '/page/views/approval/appr_input.jsp'
                ,sbranch : [
                ]
            },
            {
                stitle : '결재라인'
                ,slink : '/page/views/approval/appr_line.jsp'
                ,sbranch : [
                ]
            }
        ]
    }
]