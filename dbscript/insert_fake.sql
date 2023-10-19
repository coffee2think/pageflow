
insert into employee 
values(1, 1234, sysdate, '홍길동', '010-1234-1234', sysdate, 'donki@naver.com',
'서울시 동작구 상도동', 100000, null, default, null, null, default, 1, null, null, 
default, default);

insert into employee 
values(2, 1234, sysdate, '김태히', '010-9999-9999', sysdate, 'kth@naver.com',
'서울시 영등포구 여의도동', 100000, null, default, null, null, default, 1, null, null, 
default, default);

insert into employee 
values(3, 1234, sysdate, '장덩근', '010-2222-1234', sysdate, 'jdg@naver.com',
'서울시 구로구 구로동', 100000, null, default, null, null, default, 1, null, null, 
default, default);

insert into employee 
values(4, 1234, sysdate, '원빈곤', '010-2222-1234', sysdate, 'www@naver.com',
'경기도 광명시 철산동', 400000, null, default, null, null, default, 1, null, null, 
default, default);

insert into employee 
values(5, 1234, sysdate, '강동온', '010-2222-1234', sysdate, 'gtd@naver.com',
'경기도 분당구 ', 300000, null, default, null, null, default, 1, null, null, 
default, default);

insert into board 
values(1, 1, 1, '첫번째 글', '첫번째 보드의 글이다', default, null, null, 0);

insert into board 
values(1, 2, 1, '두번째 글', '첫번째 보드의 글이다', default, null, null, 0);

insert into board 
values(1, 3, 1, '세번째 글', '첫번째 보드의 글이다', default, null, null, 0);

insert into job values(1, '부장');

insert into department values(1, '개발팀');

insert into reply values(
1,	1,	1,	1,	1,	1,	1,	-1, '댓글댓글댓글1-1', sysdate, null, null);

insert into reply values(
2,	1,	1,	2,	2,	2,	2,	-1,	'댓글댓글댓글2-2', sysdate, null, null);

insert into reply values(
3,	1,	1,	3,	2,	3,	2,	1,	'댓글댓글댓글2-2-1-3', sysdate, null, null);

insert into reply values(
4,	1,	1,	4,	2,	3,	3,	2,	'댓글댓글댓글2-3-2-4', sysdate, null, null);

insert into reply values(
5,	1,	1,	5,	2,	5,	2,	1,	'댓글댓글댓글2-2-1-5', sysdate, null, null);

insert into reply values(
6,	1,	1,	1,	6,	1,	6,	-1,	'댓글댓글댓글6-6', sysdate, null, null);

insert into reply values(
7,	1,	1,	1,	2,	3,	4,	3,	'댓글댓글댓글2-4-3-7', sysdate, null, null);

insert into reply values(
8,	1,	1,	2,	2,	5,	5,	2,	'댓글댓글댓글2-5-2-8', sysdate, null, null);

insert into reply values(
9,	1,	1,	4,	2,	9,	2,	2,	'댓글댓글댓글2-2-2-9', sysdate, null, null);

insert into reply values(
10,	1,	1,	5,	2,	10, 2,	2,	'댓글댓글댓글2-2-2-10', sysdate, null, null);
commit;


insert into board 
values (DEP_ID, (select max(BOARD_ID) + 1 from board), 
        EMP_ID, BOARD_TITLE, BOARD_DETAIL, 
        CREATE_DATE, MODIFY_DATE,  
        DELETE_DATE, VIEWS_NUM)
        

insert into reply values
    (select max(REPLY_ID) + 1 from reply), DEP_ID, BOARD_ID, EMP_ID,
     BUNDLE_ID, BUNDLE_ID2, PARENT_ID, DEPTH, DEPTH2,
     REPLY_DETAIL, CREATE_DATE, MODIFY_DATE, DELETE_DATE);

commit;

insert into reply values
	    ((select max(REPLY_ID) + 1 from reply), 1, 1, 1,
	     6, 6, 6, 1, 2,
	     'sadasdasdasd', default, null, null);
rollback;

insert into upload_board 
values (1, 1, 1, 'icon.png', '202310140305.png');

insert into upload_reply 
values (1, 1, 'mascot.png', '202310140306.png');


insert into draft
values ('annual', '연차신청서');

insert into approval
values (1, 1, 3, 'annual', null, 'draft', sysdate, null, null);
commit;


insert into draft_annual 
values (1, 'annual', '연차를 신청합니다. 병원방문', '010-1234-1234', '2023-10-19', '2023-10-23');
commit;
