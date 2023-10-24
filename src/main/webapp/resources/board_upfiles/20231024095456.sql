--게시글 탑앤로우
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

--게시글 하나
select *
from (select * 
      from board
      where emp_id = 1 and dep_id = 1 and board_id = 1)
left join department using(DEP_ID)
left join employee using(EMP_ID);

select *
from reply
where dep_id = 1 and board_id = 1;


--댓글 제한
select * 
from reply 
left join employee USING(EMP_ID) 
where BUNDLE_ID in    
     (select REPLY_ID 
      from (select rownum rnum, REPLY_ID, DEP_ID, BOARD_ID, EMP_ID, BUNDLE_ID
            PARENT_ID, DEPTH, REPLY_DETAIL, CREATE_DATE, MODIFY_DATE, DELETE_DATE
            from (select * from reply where BOARD_ID = 1 and DEP_ID = 1 
                  order by BUNDLE_ID desc, PARENT_ID asc, DEPTH asc, REPLY_ID desc) 
            ) 
      where rnum >= 1 and rnum <= 10
      ) 
order by BUNDLE_ID desc, PARENT_ID asc, DEPTH asc, REPLY_ID desc;


--댓글 무제한
select * 
from reply 
left join employee USING(EMP_ID) 
where BUNDLE_ID in    
     (select REPLY_ID 
      from (select rownum rnum, REPLY_ID, DEP_ID, BOARD_ID, EMP_ID, BUNDLE_ID, BUNDLE_ID2
            PARENT_ID, DEPTH, DEPTH2, REPLY_DETAIL, CREATE_DATE, MODIFY_DATE, DELETE_DATE
            from (select * from reply where BOARD_ID = 1 and DEP_ID = 1 ) 
            )
      )
order by BUNDLE_ID desc, DEPTH asc, BUNDLE_ID2 desc, DEPTH2 asc, REPLY_ID desc;

select EMP_ID, EMP_NAME
from reply 
left join employee USING(EMP_ID)
where REPLY_ID = 2;


select * 
		from (select rownum rnum, DEP_ID, BOARD_ID, EMP_ID, 
				   BOARD_TITLE, BOARD_DETAIL, CREATE_DATE, 
		           MODIFY_DATE, DELETE_DATE, VIEWS_NUM 
		      from (select * from board 
		           order by BOARD_ID desc)) 
		left join employee using(EMP_ID)
		where rnum >= 1 and rnum <= 10;

select max(BUNDLE_ID) from reply;

select * from reply
where REPLY_ID = (select max(REPLY_ID) from reply);


update upload_reply set   
UPLOAD_URL = '3.jpg', 
RENAME_URL = '20231017.jpg'
where REPLY_ID = 11;

update upload_board set 
UPLOAD_URL = '',
RENAME_URL = ''
where DEP_ID = 1 
and BOARD_ID = 1;

update board set
BOARD_TITLE = '',
BOARD_DETAIL = '',
MODIFY_DATE = sysdate
where DEP_ID = 1 
and BOARD_ID = 1;

--보드
select count(*) from board
WHERE CREATE_DATE >= SYSDATE - INTERVAL '7' DAY AND CREATE_DATE <= SYSDATE;


SELECT count(*) from board
WHERE CREATE_DATE BETWEEN TRUNC(SYSDATE) - 30 AND TRUNC(SYSDATE);



select * 
from (select rownum rnum, DEP_ID, BOARD_ID, EMP_ID, 
           BOARD_TITLE, BOARD_DETAIL, CREATE_DATE, 
           MODIFY_DATE, DELETE_DATE, VIEWS_NUM
      from (select * 
            from board
            where create_date between '2023/10/16' and (to_date('2023/10/17') + 1 - 1/86400)
            order by BOARD_ID desc))
left join department using(DEP_ID)
left join employee using(EMP_ID)
where rnum >= 1 and rnum <= 10
order by BOARD_ID desc;


select * 
from (select rownum rnum, DEP_ID, BOARD_ID, EMP_ID, 
           BOARD_TITLE, BOARD_DETAIL, CREATE_DATE, 
           MODIFY_DATE, DELETE_DATE, VIEWS_NUM
      from (select * 
            from board
            order by BOARD_ID desc))
left join department using(DEP_ID)
left join employee using(EMP_ID)
where EMP_ID = 1 and 
(rnum >= 1 and rnum <= 10);

select * 
from (select rownum rnum, DEP_ID, BOARD_ID, EMP_ID, 
           BOARD_TITLE, BOARD_DETAIL, CREATE_DATE, 
           MODIFY_DATE, DELETE_DATE, VIEWS_NUM
      from (select * 
            from board
            where create_date between '2023-10-01' and (to_date('2023-10-18') + 1 - 1/86400)
            order by BOARD_ID desc))
left join department using(DEP_ID)
left join employee using(EMP_ID)
where rnum >= 1 and rnum <= 10
order by BOARD_ID desc;


--보드
select * 
from (select rownum rnum, DEP_ID, BOARD_ID, EMP_ID, 
           BOARD_TITLE, BOARD_DETAIL, CREATE_DATE, 
           MODIFY_DATE, DELETE_DATE, VIEWS_NUM
      from (select * 
            from board
            where BOARD_TITLE like '%' || 'zx' || '%'
            order by BOARD_ID desc))
left join department using(DEP_ID)
where rnum >= 1 and rnum <= 10;

select * 
from (select rownum rnum, sub.*
      from (select * 
            from board
            join department using (DEP_ID)
            join employee using (EMP_ID)
            where emp_name like '%' || '홍' || '%'
            order by BOARD_ID desc) sub)
where rnum >= 1 and rnum <= 10;


SELECT count(*) from board
where BOARD_TITLE like '%' || 'zx' || '%';

--전자결재





--기안자 조인
select * 
from (select rownum rnum, sub.*
      from (select APPR_ID, DRAFTER, APPROVER, DRAFT_TYPE, LINE_ID,
            APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE, DRAFT_NAME,
            ED.emp_name AS drafter_name,
            EA.emp_name AS approver_name
            from approval A
            join draft using (DRAFT_TYPE)
            join employee ED on (A.DRAFTER = ED.emp_id)
            join employee EA on (A.approver = EA.emp_id)
            where drafter = 1
            order by APPR_ID desc) sub)
where rnum >= 1 and rnum <= 10
order by APPR_ID desc;


select * 
from (select rownum rnum, sub.*
      from (select * 
            from draft_annual
            where APPR_ID = 1
            and DRAFT_TYPE = 'annual'
            order by APPR_ID desc) sub)
where rnum >= 1 and rnum <= 10
order by APPR_ID desc;

--사람 조인
select * 
from (select rownum rnum, sub.*
      from (select APPR_ID, DRAFTER, APPROVER, DRAFT_TYPE, LINE_ID,
            APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE,
            ED.emp_name AS drafter_name,
            EA.emp_name AS approver_name
            from approval A
            join draft using (DRAFT_TYPE)
            join employee ED on (A.DRAFTER = ED.emp_id)
            join employee EA on (A.approver = EA.emp_id)
            where APPR_STATE = 'continue'
            order by APPR_ID desc) sub)
where rnum >= 1 and rnum <= 10
order by APPR_ID desc;


-- 기안자 카운트
select count(*)
from (select APPR_ID, DRAFTER, APPROVER, DRAFT_TYPE, LINE_ID,
      APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE,
      EA.emp_name AS approver_name
      from approval A
      join employee EA on (A.approver = EA.emp_id)
    )
where approver_name like '%' || '장' || '%';

select count(*)
from (select APPR_ID, DRAFTER, APPROVER, DRAFT_TYPE, LINE_ID,
      APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE,
      ED.emp_name AS drafter_name
      from approval A
      join employee ED on (A.DRAFTER = ED.emp_id)
    )
where drafter_name like '%' || '태' || '%';


-- 키워드 카운터
select count(*) from approval 
where APPR_STATE = 'continue';


--기안자 날짜
select * 
from (select rownum rnum, sub.*
      from (select APPR_ID, DRAFTER, APPROVER, DRAFT_TYPE, LINE_ID,
            APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE, DRAFT_NAME,
            ED.emp_name AS drafter_name,
            EA.emp_name AS approver_name
            from approval A
            join draft using (DRAFT_TYPE)
            join employee ED on (A.DRAFTER = ED.emp_id)
            join employee EA on (A.approver = EA.emp_id)
            where drafter = 1
            and APPR_DATE between '2023-10-01' and (to_date('2023-10-19') + 1 - 1/86400)
            order by APPR_ID desc) sub)
where rnum >= 1 and rnum <= 10
order by APPR_ID desc;

select count(*)
		from (select APPR_ID, DRAFTER, APPROVER, DRAFT_TYPE, LINE_ID,
		      APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE,
		      ED.emp_name AS drafter_name
		      from approval A
		      join employee ED on (A.DRAFTER = ED.emp_id)
		      where DRAFTER = 1
		    )
		where drafter_name like '%' || '장' || '%';
        
select count(*)
		from (select APPR_ID, DRAFTER, APPROVER, DRAFT_TYPE, LINE_ID,
		      APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE,
		      EA.emp_name AS approver_name
		      from approval A
		      join employee EA on (A.approver = EA.emp_id)
		      where DRAFTER = 1
		    )
		where approver_name like '%' || '장' || '%';
        




select * 
		from 
 (select rownum rnum, sub.*
		     
 from 
 (select APPR_ID, DRAFTER, APPROVER, DRAFT_TYPE, LINE_ID,
		            APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE,
		            ED.emp_name AS drafter_name,
		            EA.emp_name AS approver_name
		           
 from approval A
		            join draft using (DRAFT_TYPE)
		            join employee ED on (A.DRAFTER = ED.emp_id)
		            join employee EA on (A.approver = EA.emp_id)
		           
 where drafter = 1
		           
 and APPR_STATE = 'continue'
		            order by APPR_ID desc) sub)
		where rnum >= 1
 and rnum <= 1
		order by APPR_ID desc;
        
SELECT count(*) from approval
		where APPR_DATE between '2023-09-20' and (to_date('2023-10-19') + 1 - 1/86400);
        
        
select count(*)
from (select APPR_ID, DRAFTER, APPROVER, DRAFT_TYPE, LINE_ID,
      APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE,
      ED.emp_name AS drafter_name
      from approval A
      join employee ED on (A.DRAFTER = ED.emp_id)
    )
where drafter_name like '%' || '홍' || '%';
        
        
        
select * 
from (select rownum rnum, sub.*
      from (select APPR_ID, DRAFTER, APPROVER, DRAFT_TYPE, LINE_ID,
            APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE, DRAFT_NAME,
            ED.EMP_ID AS EMP_ID, ED.JOB_ID AS JOB_ID, ED.POS_ID AS POS_ID,
            ED.DEP_ID AS DEP_ID,
            ED.emp_name AS drafter_name,
            EA.emp_name AS approver_name
            from approval A
            join draft using (DRAFT_TYPE)
            join employee ED on (A.DRAFTER = ED.emp_id)
            join employee EA on (A.approver = EA.emp_id)
            where APPR_ID = 1) sub
        )
left join department using(dep_id)
left join job using(JOB_ID)
left join position using(pos_id);

select * from approvalline
		where line_id = 1;
        
select APPR_ID, DRAFTER, APPROVER, DRAFT_TYPE, LINE_ID,
        APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE, DRAFT_NAME,
        ED.EMP_ID AS EMP_ID, ED.JOB_ID AS JOB_ID, ED.POS_ID AS POS_ID,
        ED.DEP_ID AS DEP_ID,
        ED.emp_name AS drafter_name,
        EA.emp_name AS approver_name
from approvalline A
join e using (DRAFT_TYPE)
join employee ED on (A.DRAFTER = ED.emp_id)
join employee EA on (A.approver = EA.emp_id)
where APPR_ID = 1;



select * 
from (select rownum rnum, sub.*
      from (select APPR_ID, DRAFTER, DRAFT_TYPE, LINE_ID, SAVELINE_ID,
            APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE, DRAFT_NAME,
            ED.emp_name AS drafter_name
            from approval A
            join draft using (DRAFT_TYPE)
            join employee ED on (A.DRAFTER = ED.emp_id)
            where APPR_STATE = 'companion'
            order by APPR_ID desc) sub)
where rnum >= 1 and rnum <= 10
order by APPR_ID desc;






-- 결재자일때 
select count(*)
from approval A
where 3 in (select APPROVER_ID
           from approvalline
           where A.LINE_ID = LINE_ID
           and APPROVER_NAME like '%' || '장' || '%');


select * 
from (select rownum rnum, sub.*
      from (select APPR_ID, DRAFTER, DRAFT_TYPE, LINE_ID, SAVELINE_ID,
            APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE, DRAFT_NAME,
            ED.emp_name AS drafter_name
            from approval A
            join draft using (DRAFT_TYPE)
            join employee ED on (A.DRAFTER = ED.emp_id)
            order by APPR_ID desc) sub
        where 3 in (select APPROVER_ID
		           from approvalline
		           where sub.LINE_ID = LINE_ID
		           and APPROVER_NAME like '%' || '장' || '%'))
where rnum >= 1 and rnum <= 10
order by APPR_ID desc;

--결재자를 검색
select count(*)
from approval A
where 3 in (select APPROVER_ID
           from approvalline
           where A.LINE_ID = LINE_ID
           and APPROVER_NAME like '%' || '장' || '%');
           
--모든 결재자 검색
select count(*)
		from approval A
		where LINE_ID = (select LINE_ID
		           from approvalline
		           where APPROVER_NAME like '%' || '장' || '%');
    
select * 
from (select rownum rnum, sub.*
      from (select APPR_ID, DRAFTER, DRAFT_TYPE, LINE_ID, SAVELINE_ID,
            APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE, DRAFT_NAME,
            ED.emp_name AS drafter_name
            from approval A
            join draft using (DRAFT_TYPE)
            join employee ED on (A.DRAFTER = ED.emp_id)
            order by APPR_ID desc) sub
        where sub.LINE_ID = (select LINE_ID
		           from approvalline
		           where APPROVER_NAME like '%' || '장' || '%'))
where rnum >= 1 and rnum <= 10
order by APPR_ID desc;

--내가 결재자일때 검색
select count(*)
from approval A
where 3 in (select APPROVER_ID
		           from approvalline
		           where A.LINE_ID = LINE_ID);
                   
select * 
from (select rownum rnum, sub.*
      from (select APPR_ID, DRAFTER, DRAFT_TYPE, LINE_ID, SAVELINE_ID,
            APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE, DRAFT_NAME,
            ED.emp_name AS drafter_name
            from approval A
            join draft using (DRAFT_TYPE)
            join employee ED on (A.DRAFTER = ED.emp_id)
            order by APPR_ID desc) sub
        where 3 in (select APPROVER_ID
		           from approvalline
		           where sub.LINE_ID = LINE_ID))
where rnum >= 1 and rnum <= 10
order by APPR_ID desc;

--내가 결재자일때 날짜 검색
select count(*)
from approval A
where 3 in (select APPROVER_ID
                   from approvalline
                   where A.LINE_ID = LINE_ID)
and APPR_DATE between '2023-10-17' and (to_date('2023-10-20') + 1 - 1/86400);

select * 
from (select rownum rnum, sub.*
      from (select APPR_ID, DRAFTER, DRAFT_TYPE, LINE_ID, SAVELINE_ID,
            APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE, DRAFT_NAME,
            ED.emp_name AS drafter_name
            from approval A
            join draft using (DRAFT_TYPE)
            join employee ED on (A.DRAFTER = ED.emp_id)
            order by APPR_ID desc) sub
        where 3 in (select APPROVER_ID
		           from approvalline
		           where sub.LINE_ID = LINE_ID))
where rnum >= 1 and rnum <= 10
and APPR_DATE between '2023-10-17' and (to_date('2023-10-20') + 1 - 1/86400)
order by APPR_ID desc;

--내가 결재자일때 결재완료 검색
select * 
from (select rownum rnum, sub.*
      from (select APPR_ID, DRAFTER, DRAFT_TYPE, LINE_ID, SAVELINE_ID,
            APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE, DRAFT_NAME,
            ED.emp_name AS drafter_name
            from approval A
            join draft using (DRAFT_TYPE)
            join employee ED on (A.DRAFTER = ED.emp_id)
            order by APPR_ID desc) sub
        where 3 in (select APPROVER_ID
                   from approvalline
                   where sub.LINE_ID = LINE_ID)
        and APPR_STATE = 'continue')
where rnum >= 1 and rnum <= 10
order by APPR_ID desc;

select count(*)
from (select APPR_ID, DRAFTER, DRAFT_TYPE, LINE_ID, SAVELINE_ID,
      APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE,
      ED.emp_name AS drafter_name
      from approval A
      join employee ED on (A.DRAFTER = ED.emp_id)
      where 3 in (select APPROVER_ID
                           from approvalline
                           where A.LINE_ID = LINE_ID)
    )
where drafter_name like '%' || '홍' || '%';


select count(*)
from (select APPR_ID, DRAFTER, DRAFT_TYPE, LINE_ID, SAVELINE_ID,
      APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE,
      ED.emp_name AS drafter_name
      from approval A
      join employee ED on (A.DRAFTER = ED.emp_id)
    )
where drafter_name like '%' || '홍' || '%';


--aproval검색
select * 
from (select rownum rnum, sub.*
      from (select APPR_ID, DRAFTER, DRAFT_TYPE, LINE_ID, SAVELINE_ID,
            APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE, DRAFT_NAME,
            ED.EMP_ID AS EMP_ID, 
            ED.JOB_ID AS JOB_ID, 
            ED.POS_ID AS POS_ID,
            ED.DEP_ID AS DEP_ID,
            ED.emp_name AS drafter_name
            from approval A
            join draft using (DRAFT_TYPE)
            join employee ED on (A.DRAFTER = ED.emp_id)
            where APPR_ID = 1) sub
        )
left join department using(dep_id)
left join job using(JOB_ID)
left join position using(pos_id);


select * 
from (select rownum rnum, sub.*
      from (select APPR_ID, DRAFTER, DRAFT_TYPE, LINE_ID, SAVELINE_ID,
            APPR_STATE, APPR_DATE, RECEIPT_DATE, REJECTION_DATE, DRAFT_NAME,
            ED.EMP_ID AS EMP_ID, 
            ED.JOB_ID AS JOB_ID, 
            ED.POS_ID AS POS_ID,
            ED.DEP_ID AS DEP_ID,
            ED.emp_name AS drafter_name
            from approval A
            join draft using (DRAFT_TYPE)
            join employee ED on (A.DRAFTER = ED.emp_id)
            where DRAFTER = 1) sub
        )
left join department using(dep_id)
left join job using(JOB_ID)
left join position using(pos_id);


update approval
set APPR_STATE = 'complete'
where APPR_ID = 1;


select * 
from (select rownum rnum, sub.*
      from (select *
            from employee
            where emp_id = 3) sub
        )
left join department using(dep_id)
left join job using(JOB_ID)
left join position using(pos_id);



delete from upload_board
where board_id = 1;

delete from reply
where board_id = 1;

delete from board 
where board_id = 1;

commit;
delete
