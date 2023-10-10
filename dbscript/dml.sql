select * 
from (select rownum rnum, DEP_ID, BOARD_ID, EMP_ID, 
           BOARD_TITLE, BOARD_DETAIL, CREATE_DATE, 
           MODIFY_DATE, DELETE_DATE, VIEWS_NUM
      from (select * 
            from board
            order by BOARD_ID desc))
left join department using(DEP_ID)
left join employee using(EMP_ID)
where rnum >= 1 and rnum <= 10;

select *
from (select * 
      from board
      where emp_id = 1 and dep_id = 1 and board_id = 1)
left join department using(DEP_ID)
left join employee using(EMP_ID);

select *
from reply
where dep_id = 1 and board_id = 1;

insert into reply values(
1, 1, 1, 1, 1, 1, '댓글댓글댓글', sysdate, null, null);
commit;
