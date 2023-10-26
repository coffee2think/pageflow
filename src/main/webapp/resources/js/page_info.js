


var MENU_INFO = [
    {
        title : '업무관리'
        ,link : 'bdlist.do'
        ,branch : [
            {
                stitle : '공지사항'
                ,slink : 'nlist.do'
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
                ,slink : 'mnlist.do'
                ,sbranch : [
                    {mtitle : '사원등록', mlink : 'mnlist.do'},
                    {mtitle : '권한설정', mlink : 'mnauthority.do'}
                ]
            }
        ]
    }
    ,
    {
        title : '인쇄관리'
        ,link : 'polist.do'
        ,branch : [
            {
                stitle : '발주관리'
                ,slink : 'polist.do'
                ,sbranch : [
                    {mtitle : '발주현황', mlink : 'polist.do'},
                    {mtitle : '발주등록', mlink : 'poinsert.do'}
                ]
            },
            {
                stitle : '정산관리'
                ,slink : 'pclist.do'
                ,sbranch : [
                    {mtitle : '정산현황', mlink : 'pclist.do'}
                ]
            }
        ]
    }
    ,
    {
        title : '출판관리'
        ,link : 'ctrlist.do'
        ,branch : [
            {
                stitle : '계약관리'
                ,slink : 'ctrlist.do'
                ,sbranch : [
                    {mtitle : '계약현황', mlink : 'ctrlist.do'},
                    {mtitle : '계약등록', mlink : 'movectrinsert.do'}
                ]
            },
            {
                stitle : '편집관리'
                ,slink : 'edlist.do'
                ,sbranch : [
                    {mtitle : '편집현황', mlink : 'edlist.do'},
                    {mtitle : '편집등록', mlink : 'moveedinsert.do'}
                ]
            },
            {
                stitle : '도서관리'
                ,slink : 'bklist.do'
                ,sbranch : [
                    {mtitle : '도서현황', mlink : 'bklist.do'},
                    {mtitle : '도서등록', mlink : 'movebkinsert.do'}
                ]
            },
            {
                stitle : '작가관리'
                ,slink : 'wtlist.do'
                ,sbranch : [
                    {mtitle : '작가현황', mlink : 'wtlist.do'},
                    {mtitle : '작가등록', mlink : 'movewtinsert.do'}
                ]
            }
        ]

    },
    {
         title : '재고관리'
        ,link : 'inventorylist.do'
        ,branch : [
         {
                stitle : '재고관리'
                ,slink : 'inventorylist.do'
                ,sbranch : [
                    {mtitle : '재고현황', mlink : 'inventorylist.do'}
                ]
            },
            {
                stitle : '입고관리'
                ,slink : 'storelist.do'
                ,sbranch : [
                    {mtitle : '입고현황', mlink : 'storelist.do'},
                    {mtitle : '입고입력', mlink : 'storeinput.do'}
                ]
            },
            {
                stitle : '출고관리'
                ,slink : 'releaselist.do'
                ,sbranch : [
                    {mtitle : '출고현황', mlink : 'releaselist.do'},
                    {mtitle : '출고등록', mlink : 'releaseinput.do'}
                ]
            },
            {
                stitle : '반품관리'
                ,slink : 'refundlist.do'
                ,sbranch : [
                    {mtitle : '반품현황', mlink : 'refundlist.do'},
                    {mtitle : '반품입력', mlink : 'refundinput.do'}
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
                    {mtitle : '주문입력', mlink : 'moveboinput.do'}
                ]
            },
            {
                stitle : '판매관리'
                ,slink : 'movesales.do'
                ,sbranch : [
                    {mtitle : '판매현황', mlink : 'movesales.do'},
                    {mtitle : '판매입력', mlink : 'movessinput.do'}
                ]
            },
            {
                stitle : '거래처'
                ,slink : 'moveclient.do'
                ,sbranch : [
                    {mtitle : '거래처현황', mlink : 'moveclient.do'},
                    {mtitle : '거래처등록', mlink : 'moveclinput.do'}
                ]
            },
            {
                stitle : '매출관리'
                ,slink : 'movestats.do'
                ,sbranch : [
                    {mtitle : '매출통계', mlink : 'movestats.do'},
                    {mtitle : '도서트렌드분석', mlink : 'moverank.do'}
                ]
            }
        ]
    }
    ,
    {
        title : '전자결재'
        ,link : 'aplist.do?apType=my'
        ,branch : [
            {
                stitle : '내결재관리'
                ,slink : 'aplist.do?apType=my'
                ,sbranch : [
                    {mtitle : '상신한 기안서', mlink : 'aplist.do?apType=my'},
                    {mtitle : '결재할 기안서', mlink : 'aplist.do?apType=ap'}
                ]
            },
            {
                stitle : '기안서통합관리'
                ,slink : 'aplist.do?apType=all'
                ,sbranch : [
                ]
            },
            {
                stitle : '기안서작성'
                ,slink : 'apmoveinsert.do'
                ,sbranch : [
                ]
            },
            {
                stitle : '결재라인'
                ,slink : 'allinelist.do'
                ,sbranch : [
                ]
            }
        ]
    }
    ,
    {
        title : '마이페이지'
        ,link : 'javascript:goMyPage()'
        ,branch : [
            {
                stitle : '마이페이지'
                ,slink : 'javascript:goMyPage()'
                ,sbranch : [
                    {mtitle : '내정보 보기', mlink : 'javascript:goMyPage()'},
                    /*{mtitle : '쪽지함', mlink : 'movemsgbox.do'}*/
                ]
            }
        ]
    }
    
]