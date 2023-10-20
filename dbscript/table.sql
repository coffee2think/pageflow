/* 직원 */
DROP TABLE employee 
	CASCADE CONSTRAINTS;

/* 직급 */
DROP TABLE job 
	CASCADE CONSTRAINTS;

/* 직책 */
DROP TABLE position 
	CASCADE CONSTRAINTS;

/* 부서 */
DROP TABLE department 
	CASCADE CONSTRAINTS;

/* 업무 게시판 */
DROP TABLE board 
	CASCADE CONSTRAINTS;

/* 업무 게시판 댓글 */
DROP TABLE reply 
	CASCADE CONSTRAINTS;

/* 첨부파일 댓글 */
DROP TABLE upload_reply 
	CASCADE CONSTRAINTS;

/* 쪽지함 */
DROP TABLE message 
	CASCADE CONSTRAINTS;

/* 전자결재-기안서 */
DROP TABLE approval 
	CASCADE CONSTRAINTS;

/* 전자결재 라인 */
DROP TABLE aprovalline 
	CASCADE CONSTRAINTS;

/* 기안서 내용(연차) */
DROP TABLE draft_annual 
	CASCADE CONSTRAINTS;

/* 참조 쪽지 업무 게시판 */
DROP TABLE reference_board 
	CASCADE CONSTRAINTS;

/* 도서 */
DROP TABLE book 
	CASCADE CONSTRAINTS;

/* 편집관리 */
DROP TABLE edit 
	CASCADE CONSTRAINTS;

/* 계약관리 */
DROP TABLE contract 
	CASCADE CONSTRAINTS;

/* 작가 */
DROP TABLE writer 
	CASCADE CONSTRAINTS;

/* 공지사항 */
DROP TABLE notice 
	CASCADE CONSTRAINTS;

/* 발주/정산관리 */
DROP TABLE print_order 
	CASCADE CONSTRAINTS;

/* 재고관리 */
DROP TABLE inventory 
	CASCADE CONSTRAINTS;

/* 주문관리 */
DROP TABLE book_order 
	CASCADE CONSTRAINTS;

/* 도서 판매 랭킹 */
DROP TABLE rank 
	CASCADE CONSTRAINTS;

/* 거래처(서점, 창고, 인쇄소) */
DROP TABLE client 
	CASCADE CONSTRAINTS;

/* 기안서 종류 */
DROP TABLE draft 
	CASCADE CONSTRAINTS;

/* 반품관리 */
DROP TABLE refund 
	CASCADE CONSTRAINTS;

/* 입/출고관리 */
DROP TABLE store 
	CASCADE CONSTRAINTS;

/* 첨부파일 업무 게시판 */
DROP TABLE upload_board 
	CASCADE CONSTRAINTS;

/* 참조 결재 쪽지  */
DROP TABLE reference_message 
	CASCADE CONSTRAINTS;

/* 참조 공지사항 쪽지  */
DROP TABLE reference_notice 
	CASCADE CONSTRAINTS;

/* 권한 레벨 */
DROP TABLE authority_level 
	CASCADE CONSTRAINTS;

/* 권한 그룹 */
DROP TABLE authority 
	CASCADE CONSTRAINTS;

/* 직원 */
CREATE TABLE employee (
	emp_id NUMBER NOT NULL, /* 직원 번호 */
	emp_pwd VARCHAR2(60) NOT NULL, /* 비밀번호 */
	emp_pwd_update DATE DEFAULT sysdate NOT NULL, /* 비밀번호 업데이트 날짜 */
	emp_name VARCHAR2(30) NOT NULL, /* 이름 */
	phone VARCHAR2(30) NOT NULL, /* 휴대폰 번호 */
	emp_birth VARCHAR2(13) NOT NULL, /* 생년월일 */
	email VARCHAR2(40) NOT NULL, /* 이메일 */
	address VARCHAR2(255) NOT NULL, /* 주소 */
	salary NUMBER DEFAULT 0 NOT NULL, /* 급여 */
	profile VARCHAR2(255), /* 프로필 사진 url */
	enroll_date DATE DEFAULT sysdate NOT NULL, /* 입사 일자 */
	modify_date DATE, /* 수정 일자 */
	leave_date DATE, /* 퇴사 일자 */
	last_date DATE DEFAULT sysdate NOT NULL, /* 마지막 접속일 */
	jop_id NUMBER NOT NULL, /* 직급 번호 */
	pos_id NUMBER, /* 직책 번호 */
	dep_id NUMBER, /* 부서 번호 */
	login_ok CHAR(1) DEFAULT 'Y' NOT NULL, /* 로그인 가능여부 */
	admin_yn CHAR(1) DEFAULT 'N' NOT NULL /* 관리자 여부 */
);

COMMENT ON TABLE employee IS '직원';

COMMENT ON COLUMN employee.emp_id IS '직원 번호';

COMMENT ON COLUMN employee.emp_pwd IS '비밀번호';

COMMENT ON COLUMN employee.emp_pwd_update IS '비밀번호 업데이트 날짜';

COMMENT ON COLUMN employee.emp_name IS '이름';

COMMENT ON COLUMN employee.phone IS '휴대폰 번호';

COMMENT ON COLUMN employee.emp_birth IS '생년월일';

COMMENT ON COLUMN employee.email IS '이메일';

COMMENT ON COLUMN employee.address IS '주소';

COMMENT ON COLUMN employee.salary IS '급여';

COMMENT ON COLUMN employee.profile IS '프로필 사진 url';

COMMENT ON COLUMN employee.enroll_date IS '입사 일자';

COMMENT ON COLUMN employee.modify_date IS '수정 일자';

COMMENT ON COLUMN employee.leave_date IS '퇴사 일자';

COMMENT ON COLUMN employee.last_date IS '마지막 접속일';

COMMENT ON COLUMN employee.jop_id IS '직급 번호';

COMMENT ON COLUMN employee.pos_id IS '직책 번호';

COMMENT ON COLUMN employee.dep_id IS '부서 번호';

COMMENT ON COLUMN employee.login_ok IS '로그인 가능여부';

COMMENT ON COLUMN employee.admin_yn IS '관리자 여부';

ALTER TABLE employee
	ADD
		CONSTRAINT PK_employee
		PRIMARY KEY (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 직급 */
CREATE TABLE job (
	jop_id NUMBER NOT NULL, /* 직급 번호 */
	job_name VARCHAR2(30) NOT NULL /* 직급 이름 */
);

COMMENT ON TABLE job IS '직급';

COMMENT ON COLUMN job.jop_id IS '직급 번호';

COMMENT ON COLUMN job.job_name IS '직급 이름';

ALTER TABLE job
	ADD
		CONSTRAINT PK_job
		PRIMARY KEY (
			jop_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 직책 */
CREATE TABLE position (
	pos_id NUMBER NOT NULL, /* 직책 번호 */
	pos_name VARCHAR2(30) NOT NULL /* 직책 이름 */
);

COMMENT ON TABLE position IS '직책';

COMMENT ON COLUMN position.pos_id IS '직책 번호';

COMMENT ON COLUMN position.pos_name IS '직책 이름';

ALTER TABLE position
	ADD
		CONSTRAINT PK_position
		PRIMARY KEY (
			pos_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 부서 */
CREATE TABLE department (
	dep_id NUMBER NOT NULL, /* 부서 번호 */
	dep_name VARCHAR2(30) NOT NULL /* 부서 이름 */
);

COMMENT ON TABLE department IS '부서';

COMMENT ON COLUMN department.dep_id IS '부서 번호';

COMMENT ON COLUMN department.dep_name IS '부서 이름';

ALTER TABLE department
	ADD
		CONSTRAINT PK_department
		PRIMARY KEY (
			dep_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 업무 게시판 */
CREATE TABLE board (
	dep_id NUMBER NOT NULL, /* 부서 번호 */
	board_id NUMBER NOT NULL, /* 게시글 번호 */
	emp_id NUMBER NOT NULL, /* 직원 번호 */
	board_title VARCHAR2(1000) NOT NULL, /* 게시글 제목 */
	board_detail VARCHAR2(1000) NOT NULL, /* 게시글 내용 */
	create_date DATE DEFAULT sysdate NOT NULL, /* 게시글 작성 일자 */
	modify_date DATE, /* 게시글 수정 일자 */
	delete_date DATE, /* 게시글 삭제 일자 */
	views_num NUMBER DEFAULT 0 NOT NULL /* 게시글 조회수 */
);

COMMENT ON TABLE board IS '업무 게시판';

COMMENT ON COLUMN board.dep_id IS '부서 번호';

COMMENT ON COLUMN board.board_id IS '게시글 번호';

COMMENT ON COLUMN board.emp_id IS '직원 번호';

COMMENT ON COLUMN board.board_title IS '게시글 제목';

COMMENT ON COLUMN board.board_detail IS '게시글 내용';

COMMENT ON COLUMN board.create_date IS '게시글 작성 일자';

COMMENT ON COLUMN board.modify_date IS '게시글 수정 일자';

COMMENT ON COLUMN board.delete_date IS '게시글 삭제 일자';

COMMENT ON COLUMN board.views_num IS '게시글 조회수';

ALTER TABLE board
	ADD
		CONSTRAINT PK_board
		PRIMARY KEY (
			dep_id,
			board_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 업무 게시판 댓글 */
CREATE TABLE reply (
	reply_id NUMBER NOT NULL, /* 댓글 번호 */
	dep_id NUMBER NOT NULL, /* 부서 번호 */
	board_id NUMBER NOT NULL, /* 게시글 번호 */
	emp_id NUMBER NOT NULL, /* 작성자 번호 */
	bundle_id NUMBER NOT NULL, /* 묶음 댓글 번호 */
    bundle_id2 NUMBER NOT NULL, /* 묶음 댓글 번호2 */
	parent_id NUMBER NOT NULL, /* 부모 댓글 번호 */
	depth NUMBER NOT NULL, /* 깊이 */
	reply_detail VARCHAR2(1000) NOT NULL, /* 댓글 내용 */
	create_date DATE DEFAULT sysdate NOT NULL, /* 댓글 작성 일자 */
	modify_date DATE, /* 댓글 수정 일자 */
	delete_date DATE /* 댓글 삭제 일자 */
);

COMMENT ON TABLE reply IS '업무 게시판 댓글';

COMMENT ON COLUMN reply.reply_id IS '댓글 번호';

COMMENT ON COLUMN reply.dep_id IS '부서 번호';

COMMENT ON COLUMN reply.board_id IS '게시글 번호';

COMMENT ON COLUMN reply.emp_id IS '작성자 번호';

COMMENT ON COLUMN reply.bundle_id IS '묶음 댓글 번호';
COMMENT ON COLUMN reply.bundle_id2 IS '묶음 댓글 번호2';

COMMENT ON COLUMN reply.parent_id IS '부모 댓글 번호';
COMMENT ON COLUMN reply.depth IS '깊이';

COMMENT ON COLUMN reply.reply_detail IS '댓글 내용';

COMMENT ON COLUMN reply.create_date IS '댓글 작성 일자';

COMMENT ON COLUMN reply.modify_date IS '댓글 수정 일자';

COMMENT ON COLUMN reply.delete_date IS '댓글 삭제 일자';

ALTER TABLE reply
	ADD
		CONSTRAINT PK_reply
		PRIMARY KEY (
			reply_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 첨부파일 댓글 */
CREATE TABLE upload_reply (
	upload_id NUMBER NOT NULL, /* 첨부파일 번호 */
	reply_id NUMBER NOT NULL, /* 댓글 번호 */
	upload_url VARCHAR2(255) NOT NULL, /* 원본파일명 */
	rename_url VARCHAR2(255) /* 변경파일명 */
);

COMMENT ON TABLE upload_reply IS '첨부파일 댓글';

COMMENT ON COLUMN upload_reply.upload_id IS '첨부파일 번호';

COMMENT ON COLUMN upload_reply.reply_id IS '댓글 번호';

COMMENT ON COLUMN upload_reply.upload_url IS '원본파일명';

COMMENT ON COLUMN upload_reply.rename_url IS '변경파일명';

ALTER TABLE upload_reply
	ADD
		CONSTRAINT PK_upload_reply
		PRIMARY KEY (
			upload_id,
			reply_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 쪽지함 */
CREATE TABLE message (
	mess_id NUMBER NOT NULL, /* 쪽지 번호 */
	sender_id NUMBER NOT NULL, /* 보낸사람(직원 번호) */
	receiver_id NUMBER NOT NULL, /* 받는사람(직원 번호) */
	send_time DATE DEFAULT sysdate NOT NULL, /* 쪽지 보낸 시간 */
	mess_title VARCHAR2(500) NOT NULL, /* 쪽지 제목 */
	mess_detail VARCHAR2(1000) NOT NULL, /* 쪽지 내용 */
	read_chk CHAR(1) DEFAULT 'N' NOT NULL /* 읽음 여부 */
);

COMMENT ON TABLE message IS '쪽지함';

COMMENT ON COLUMN message.mess_id IS '쪽지 번호';

COMMENT ON COLUMN message.sender_id IS '보낸사람(직원 번호)';

COMMENT ON COLUMN message.receiver_id IS '받는사람(직원 번호)';

COMMENT ON COLUMN message.send_time IS '쪽지 보낸 시간';

COMMENT ON COLUMN message.mess_title IS '쪽지 제목';

COMMENT ON COLUMN message.mess_detail IS '쪽지 내용';

COMMENT ON COLUMN message.read_chk IS '읽음 여부';

ALTER TABLE message
	ADD
		CONSTRAINT PK_message
		PRIMARY KEY (
			mess_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 전자결재-기안서 */
CREATE TABLE approval (
	appr_id NUMBER NOT NULL, /* 전자결재 번호 */
	drafter NUMBER NOT NULL, /* 직원 번호(기안자) */
	approver NUMBER NOT NULL, /* 직원 번호(결재자) */
	draft_type VARCHAR2(30) NOT NULL, /* 기안서 종류 */
	line_id NUMBER, /* 결재라인 번호 */
	appr_state VARCHAR2(30), /* 진행 상태 */
	appr_date DATE DEFAULT sysdate NOT NULL, /* 기안 일자 */
	receipt_date DATE DEFAULT sysdate, /* 상신 일자 */
	rejection_date DATE DEFAULT sysdate /* 반려 일자 */
);

COMMENT ON TABLE approval IS '전자결재-기안서';

COMMENT ON COLUMN approval.appr_id IS '전자결재 번호';

COMMENT ON COLUMN approval.drafter IS '직원 번호(기안자)';

COMMENT ON COLUMN approval.approver IS '직원 번호(결재자)';

COMMENT ON COLUMN approval.draft_type IS '기안서 종류';

COMMENT ON COLUMN approval.line_id IS '결재라인 번호';

COMMENT ON COLUMN approval.appr_state IS '진행 상태';

COMMENT ON COLUMN approval.appr_date IS '기안 일자';

COMMENT ON COLUMN approval.receipt_date IS '상신 일자';

COMMENT ON COLUMN approval.rejection_date IS '반려 일자';

ALTER TABLE approval
	ADD
		CONSTRAINT PK_approval
		PRIMARY KEY (
			appr_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 전자결재 라인 */
CREATE TABLE aprovalline (
	line_id NUMBER NOT NULL, /* 결재라인 번호 */
	emp_id5 NUMBER NOT NULL, /* 직원 번호(기안자) */
	line_name VARCHAR2(30), /* 결재라인 이름 */
	emp_id NUMBER, /* 직원 번호(결재자 1) */
	emp_id2 NUMBER, /* 직원 번호(결재자 2) */
	emp_id3 NUMBER, /* 직원 번호(결재자 3) */
	emp_id4 NUMBER /* 직원 번호(결재자 4) */
);

COMMENT ON TABLE aprovalline IS '전자결재 라인';

COMMENT ON COLUMN aprovalline.line_id IS '결재라인 번호';

COMMENT ON COLUMN aprovalline.emp_id5 IS '직원 번호(기안자)';

COMMENT ON COLUMN aprovalline.line_name IS '결재라인 이름';

COMMENT ON COLUMN aprovalline.emp_id IS '직원 번호(결재자 1)';

COMMENT ON COLUMN aprovalline.emp_id2 IS '직원 번호(결재자 2)';

COMMENT ON COLUMN aprovalline.emp_id3 IS '직원 번호(결재자 3)';

COMMENT ON COLUMN aprovalline.emp_id4 IS '직원 번호(결재자 4)';

ALTER TABLE aprovalline
	ADD
		CONSTRAINT PK_aprovalline
		PRIMARY KEY (
			line_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 기안서 내용(연차) */
CREATE TABLE draft_annual (
	appr_id NUMBER NOT NULL, /* 전자결재 번호 */
	draft_type VARCHAR2(30) NOT NULL, /* 기안서 종류 */
	detail VARCHAR2(1000) NOT NULL, /* 사유 */
	emergency VARCHAR2(30) NOT NULL, /* 비상연락망 */
	start_date DATE DEFAULT sysdate NOT NULL, /* 시작 일자 */
	end_date DATE DEFAULT sysdate NOT NULL /* 종료 일자 */
);

COMMENT ON TABLE draft_annual IS '기안서 내용(연차)';

COMMENT ON COLUMN draft_annual.appr_id IS '전자결재 번호';

COMMENT ON COLUMN draft_annual.draft_type IS '기안서 종류';

COMMENT ON COLUMN draft_annual.detail IS '사유';

COMMENT ON COLUMN draft_annual.emergency IS '비상연락망';

COMMENT ON COLUMN draft_annual.start_date IS '시작 일자';

COMMENT ON COLUMN draft_annual.end_date IS '종료 일자';

ALTER TABLE draft_annual
	ADD
		CONSTRAINT PK_draft_annual
		PRIMARY KEY (
			appr_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 참조 쪽지 업무 게시판 */
CREATE TABLE reference_board (
	ref_id NUMBER NOT NULL, /* 참조 번호 */
	emp_id NUMBER NOT NULL, /* 직원 번호 */
	dep_id NUMBER NOT NULL, /* 부서 번호 */
	board_id NUMBER NOT NULL, /* 게시글 번호 */
	read_chk CHAR(1) DEFAULT 'N' NOT NULL /* 읽음여부 */
);

COMMENT ON TABLE reference_board IS '참조 쪽지 업무 게시판';

COMMENT ON COLUMN reference_board.ref_id IS '참조 번호';

COMMENT ON COLUMN reference_board.emp_id IS '직원 번호';

COMMENT ON COLUMN reference_board.dep_id IS '부서 번호';

COMMENT ON COLUMN reference_board.board_id IS '게시글 번호';

COMMENT ON COLUMN reference_board.read_chk IS '읽음여부';

ALTER TABLE reference_board
	ADD
		CONSTRAINT PK_reference_board
		PRIMARY KEY (
			ref_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 도서 */
CREATE TABLE book (
	book_id NUMBER NOT NULL, /* 도서 번호 */
	writer_id NUMBER NOT NULL, /* 작가 번호 */
	book_name VARCHAR2(300) NOT NULL, /* 도서명 */
	writer_name VARCHAR2(30) NOT NULL, /* 저/역자 */
	book_price_s NUMBER NOT NULL, /* 입고가 */
	book_price_r NUMBER, /* 출고가 */
	book_price NUMBER NOT NULL, /* 정가 */
	category VARCHAR2(255) NOT NULL, /* 카테고리 */
	isbn VARCHAR2(30) NOT NULL, /* 국제표준 도서 번호 */
	book_state VARCHAR2(30) NOT NULL, /* 도서 상태 */
	engraving VARCHAR2(30) NOT NULL, /* 판쇄 */
	pub_date DATE NOT NULL, /* 도서 발행 일자 */
	reg_date DATE DEFAULT sysdate NOT NULL /* 도서 등록 일자 */
);

COMMENT ON TABLE book IS '도서';

COMMENT ON COLUMN book.book_id IS '도서 번호';

COMMENT ON COLUMN book.writer_id IS '작가 번호';

COMMENT ON COLUMN book.book_name IS '도서명';

COMMENT ON COLUMN book.writer_name IS '저/역자';

COMMENT ON COLUMN book.book_price_s IS '입고가';

COMMENT ON COLUMN book.book_price_r IS '출고가';

COMMENT ON COLUMN book.book_price IS '정가';

COMMENT ON COLUMN book.category IS '카테고리';

COMMENT ON COLUMN book.isbn IS '국제표준 도서 번호';

COMMENT ON COLUMN book.book_state IS '도서 상태';

COMMENT ON COLUMN book.engraving IS '판쇄';

COMMENT ON COLUMN book.pub_date IS '도서 발행 일자';

COMMENT ON COLUMN book.reg_date IS '도서 등록 일자';

ALTER TABLE book
	ADD
		CONSTRAINT PK_book
		PRIMARY KEY (
			book_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 편집관리 */
CREATE TABLE edit (
	edit_id NUMBER NOT NULL, /* 편집 번호 */
	dep_id NUMBER NOT NULL, /* 부서 번호 */
	emp_id NUMBER, /* 직원 번호(담당자) */
	emp_name VARCHAR2(30), /* 직원 이름(담당자) */
	book_name VARCHAR2(300) NOT NULL, /* 도서명 */
	edit_state VARCHAR2(30) NOT NULL, /* 편집 진행상태 */
	start_date DATE DEFAULT sysdate, /* 편집 시작 일자 */
	end_date DATE /* 편집 마감 일자 */
);

COMMENT ON TABLE edit IS '편집관리';

COMMENT ON COLUMN edit.edit_id IS '편집 번호';

COMMENT ON COLUMN edit.dep_id IS '부서 번호';

COMMENT ON COLUMN edit.emp_id IS '직원 번호(담당자)';

COMMENT ON COLUMN edit.emp_name IS '직원 이름(담당자)';

COMMENT ON COLUMN edit.book_name IS '도서명';

COMMENT ON COLUMN edit.edit_state IS '편집 진행상태';

COMMENT ON COLUMN edit.start_date IS '편집 시작 일자';

COMMENT ON COLUMN edit.end_date IS '편집 마감 일자';

ALTER TABLE edit
	ADD
		CONSTRAINT PK_edit
		PRIMARY KEY (
			edit_id,
            dep_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 계약관리 */
CREATE TABLE contract (
	contr_id NUMBER NOT NULL, /* 계약 번호 */
	emp_id NUMBER NOT NULL, /* 직원 번호(담당자) */
	writer_id NUMBER NOT NULL, /* 작가 번호 */
	book_name VARCHAR2(300) NOT NULL, /* 도서명 */
	emp_name VARCHAR2(30) NOT NULL, /* 직원 이름(담당자) */
	contr_date DATE DEFAULT sysdate, /* 계약 일자 */
	contr_doc VARCHAR2(255), /* 계약 문서url */
	contr_state VARCHAR2(30) DEFAULT 'start' NOT NULL, /* 계약 진행상태 */
);

COMMENT ON TABLE contract IS '계약관리';

COMMENT ON COLUMN contract.contr_id IS '계약 번호';

COMMENT ON COLUMN contract.emp_id IS '직원 번호(담당자)';

COMMENT ON COLUMN contract.writer_id IS '작가 번호';

COMMENT ON COLUMN contract.book_name IS '도서명';

COMMENT ON COLUMN contract.emp_name IS '직원 이름(담당자)';

COMMENT ON COLUMN contract.contr_date IS '계약 일자';

COMMENT ON COLUMN contract.contr_doc IS '계약 문서url';

COMMENT ON COLUMN contract.contr_state IS '계약 진행상태';

ALTER TABLE contract
	ADD
		CONSTRAINT PK_contract
		PRIMARY KEY (
			contr_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 작가 */
CREATE TABLE writer (
   writer_id NUMBER NOT NULL, /* 작가 번호 */
   writer_name VARCHAR2(30) NOT NULL, /* 작가명 */
   phone VARCHAR2(30) NOT NULL, /* 연락처 */
   writer_birth VARCHAR2(13) NOT NULL, /* 생년월일 */
   email VARCHAR2(40) NOT NULL, /* 이메일 */
   address VARCHAR2(255), /* 주소 */
   bank VARCHAR2(30) NOT NULL, /* 은행명 */
   account VARCHAR2(20) NOT NULL /* 계좌번호 */
);

COMMENT ON TABLE writer IS '작가';

COMMENT ON COLUMN writer.writer_id IS '작가 번호';

COMMENT ON COLUMN writer.writer_name IS '작가명';

COMMENT ON COLUMN writer.phone IS '연락처';

COMMENT ON COLUMN writer.writer_birth IS '생년월일';

COMMENT ON COLUMN writer.email IS '이메일';

COMMENT ON COLUMN writer.address IS '주소';

COMMENT ON COLUMN writer.bank IS '은행명';

COMMENT ON COLUMN writer.account IS '계좌번호';

ALTER TABLE writer
	ADD
		CONSTRAINT PK_writer
		PRIMARY KEY (
			writer_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 공지사항 */
CREATE TABLE notice (
	notice_id NUMBER NOT NULL, /* 공지사항 번호 */
	emp_id NUMBER NOT NULL, /* 직원 번호(작성자) */
	notice_title VARCHAR2(1000) NOT NULL, /* 공지사항 제목 */
	notice_detail VARCHAR2(1000) NOT NULL, /* 공지사항 내용 */
	create_date DATE DEFAULT sysdate NOT NULL, /* 공지사항 작성 일자 */
	modify_date DATE, /* 공지사항 수정 일자 */
	delete_date DATE, /* 공지사항 삭제 일자 */
	classify VARCHAR2(30) DEFAULT '전체' NOT NULL, /* 공지 분류 */
	importance CHAR(1) DEFAULT 'N' NOT NULL /* 중요도 */
);

COMMENT ON TABLE notice IS '공지사항';

COMMENT ON COLUMN notice.notice_id IS '공지사항 번호';

COMMENT ON COLUMN notice.emp_id IS '직원 번호(작성자)';

COMMENT ON COLUMN notice.notice_title IS '공지사항 제목';

COMMENT ON COLUMN notice.notice_detail IS '공지사항 내용';

COMMENT ON COLUMN notice.create_date IS '공지사항 작성 일자';

COMMENT ON COLUMN notice.modify_date IS '공지사항 수정 일자';

COMMENT ON COLUMN notice.delete_date IS '공지사항 삭제 일자';

COMMENT ON COLUMN notice.classify IS '공지 분류';

COMMENT ON COLUMN notice.importance IS '중요도';

ALTER TABLE notice
	ADD
		CONSTRAINT PK_notice
		PRIMARY KEY (
			notice_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 발주/정산관리 */
CREATE TABLE print_order (
	order_id NUMBER NOT NULL, /* 발주 번호 */
	book_id NUMBER NOT NULL, /* 도서 번호 */
	print_id NUMBER NOT NULL, /* 인쇄소 번호 */
	emp_id NUMBER NOT NULL, /* 직원 번호(담당자) */
	emp_name VARCHAR2(30) NOT NULL, /* 직원 이름(담당자) */
	classify VARCHAR2(20), /* 구분(인쇄의뢰, 발주, 정산) */
	unit NUMBER NOT NULL, /* 단위 */
	quantity NUMBER NOT NULL, /* 수량 */
	price NUMBER NOT NULL, /* 단가 */
	amount NUMBER NOT NULL, /* 합계 */
	state VARCHAR2(30), /* 상태 */
	order_date DATE DEFAULT sysdate NOT NULL, /* 발주 일자 */
	end_date DATE, /* 마감 일자 */
	pub_date DATE /* 출간 일자 */
);

COMMENT ON TABLE print_order IS '발주/정산관리';

COMMENT ON COLUMN print_order.order_id IS '발주 번호';

COMMENT ON COLUMN print_order.book_id IS '도서 번호';

COMMENT ON COLUMN print_order.print_id IS '인쇄소 번호';

COMMENT ON COLUMN print_order.emp_id IS '직원 번호(담당자)';

COMMENT ON COLUMN print_order.emp_name IS '직원 이름(담당자)';

COMMENT ON COLUMN print_order.classify IS '구분(인쇄의뢰, 발주, 정산)';

COMMENT ON COLUMN print_order.unit IS '단위';

COMMENT ON COLUMN print_order.quantity IS '수량';

COMMENT ON COLUMN print_order.price IS '단가';

COMMENT ON COLUMN print_order.amount IS '합계';

COMMENT ON COLUMN print_order.state IS '상태';

COMMENT ON COLUMN print_order.order_date IS '발주 일자';

COMMENT ON COLUMN print_order.end_date IS '마감 일자';

COMMENT ON COLUMN print_order.pub_date IS '출간 일자';

ALTER TABLE print_order
	ADD
		CONSTRAINT PK_print_order
		PRIMARY KEY (
			order_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 재고관리 */
CREATE TABLE inventory (
	inven_id NUMBER NOT NULL, /* 재고 번호 */
	book_id NUMBER NOT NULL, /* 도서 번호 */
	storage_id NUMBER NOT NULL, /* 창고 번호 */
	store_id NUMBER, /* 입/출고 번호 */
	refund_id NUMBER, /* 반품 번호 */
	prev_inven_id NUMBER NOT NULL, /* 이전 재고 번호 */
	increase NUMBER NOT NULL, /* 증감 */
	curr_inven NUMBER NOT NULL, /* 현재 재고 */
	inven_date DATE DEFAULT sysdate NOT NULL, /* 재고 측정 일자 */
	classify VARCHAR2(20), /* 재고 구분 */
	remark VARCHAR2(1000) /* 비고 */
);

COMMENT ON TABLE inventory IS '재고관리';

COMMENT ON COLUMN inventory.inven_id IS '재고 번호';

COMMENT ON COLUMN inventory.book_id IS '도서 번호';

COMMENT ON COLUMN inventory.storage_id IS '창고 번호';

COMMENT ON COLUMN inventory.store_id IS '입/출고 번호';

COMMENT ON COLUMN inventory.refund_id IS '반품 번호';

COMMENT ON COLUMN inventory.prev_inven_id IS '이전 재고 번호';

COMMENT ON COLUMN inventory.increase IS '증감';

COMMENT ON COLUMN inventory.curr_inven IS '현재 재고';

COMMENT ON COLUMN inventory.inven_date IS '재고 측정 일자';

COMMENT ON COLUMN inventory.classify IS '재고 구분';

COMMENT ON COLUMN inventory.remark IS '비고';

ALTER TABLE inventory
	ADD
		CONSTRAINT PK_inventory
		PRIMARY KEY (
			inven_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 도서주문 */
CREATE TABLE book_order (
	order_id NUMBER NOT NULL, /* 주문번호 */
	book_id NUMBER NOT NULL, /* 도서번호 */
	client_id NUMBER NOT NULL, /* 거래처 번호 */
	emp_id NUMBER NOT NULL, /* 직원 번호(담당자) */
	order_quantity NUMBER NOT NULL, /* 주문수량 */
	order_date DATE DEFAULT sysdate NOT NULL, /* 주문일시 */
	modify_date DATE, /* 수정일시 */
	state VARCHAR2(30) NOT NULL /* 상태 */
);

COMMENT ON TABLE book_order IS '도서주문';

COMMENT ON COLUMN book_order.order_id IS '주문번호';

COMMENT ON COLUMN book_order.book_id IS '도서번호';

COMMENT ON COLUMN book_order.client_id IS '거래처 번호';

COMMENT ON COLUMN book_order.emp_id IS '직원 번호(담당자)';

COMMENT ON COLUMN book_order.order_quantity IS '주문수량';

COMMENT ON COLUMN book_order.order_date IS '주문일시';

COMMENT ON COLUMN book_order.modify_date IS '수정일시';

COMMENT ON COLUMN book_order.state IS '상태';

ALTER TABLE book_order
	ADD
		CONSTRAINT PK_book_order
		PRIMARY KEY (
			order_id, book_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 판매 */
CREATE TABLE sales (
	sales_id NUMBER NOT NULL, /* 판매번호 */
	order_id NUMBER NOT NULL, /* 주문번호 */
	book_id NUMBER NOT NULL, /* 도서번호 */
	collected_amount NUMBER DEFAULT 0 NOT NULL, /* 수금액 */
	collect_date DATE DEFAULT sysdate NOT NULL /* 수금일시 */
);

COMMENT ON TABLE sales IS '판매';

COMMENT ON COLUMN sales.sales_id IS '판매번호';

COMMENT ON COLUMN sales.order_id IS '주문번호';

COMMENT ON COLUMN sales.book_id IS '도서번호';

COMMENT ON COLUMN sales.collected_amount IS '수금액';

COMMENT ON COLUMN sales.collect_date IS '수금일시';

ALTER TABLE sales
	ADD
		CONSTRAINT PK_sales
		PRIMARY KEY (
			sales_id
		);

/* 도서 판매 랭킹 */
CREATE TABLE rank (
	rank NUMBER NOT NULL, /* 순위 */
	book_name VARCHAR2(300) NOT NULL, /* 도서명 */
	writer VARCHAR2(30), /* 저자 */
	publisher VARCHAR2(100) NOT NULL, /* 출판사 */
	category VARCHAR2(100) NOT NULL, /* 카테고리 */
	book_type VARCHAR2(30) NOT NULL /* 책유형 */
);

COMMENT ON TABLE rank IS '도서 판매 랭킹';

COMMENT ON COLUMN rank.rank IS '순위';

COMMENT ON COLUMN rank.book_name IS '도서명';

COMMENT ON COLUMN rank.writer IS '저자';

COMMENT ON COLUMN rank.publisher IS '출판사';

COMMENT ON COLUMN rank.category IS '카테고리';

COMMENT ON COLUMN rank.book_type IS '책유형';

ALTER TABLE rank
	ADD
		CONSTRAINT PK_rank
		PRIMARY KEY (
			rank
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 거래처(서점, 창고, 인쇄소) */
CREATE TABLE client (
	client_id NUMBER NOT NULL, /* 거래처 번호 */
	category VARCHAR2(20) NOT NULL, /* 거래처구분 */
	client_name VARCHAR2(100) NOT NULL, /* 거래처명 */
	client_address VARCHAR2(255) NOT NULL, /* 거래처 주소 */
	CLIENT_CONTACT VARCHAR2(30) NOT NULL, /* 거래처 연락처 */
	eid VARCHAR2(12) NOT NULL, /* 사업자등록번호 */
	client_url VARCHAR2(255), /* 거래처 홈페이지 */
	manager VARCHAR2(30), /* 거래처 담당자 */
	manager_contact VARCHAR2(30), /* 거래처 담당자 연락처 */
	manager_email VARCHAR2(40), /* 거래처 담당자 이메일 */
	start_date DATE DEFAULT sysdate NOT NULL, /* 거래시작일 */
	end_date DATE /* 거래종료일 */
);

COMMENT ON TABLE client IS '거래처(서점, 창고, 인쇄소)';

COMMENT ON COLUMN client.client_id IS '거래처 번호';

COMMENT ON COLUMN client.category IS '거래처구분';

COMMENT ON COLUMN client.client_name IS '거래처명';

COMMENT ON COLUMN client.client_address IS '거래처 주소';

COMMENT ON COLUMN client.CLIENT_CONTACT IS '거래처 연락처';

COMMENT ON COLUMN client.eid IS '사업자등록번호';

COMMENT ON COLUMN client.client_url IS '거래처 홈페이지';

COMMENT ON COLUMN client.manager IS '거래처 담당자';

COMMENT ON COLUMN client.manager_contact IS '거래처 담당자 연락처';

COMMENT ON COLUMN client.manager_email IS '거래처 담당자 이메일';

COMMENT ON COLUMN client.start_date IS '거래시작일';

COMMENT ON COLUMN client.end_date IS '거래종료일';

ALTER TABLE client
	ADD
		CONSTRAINT PK_client
		PRIMARY KEY (
			client_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 기안서 종류 */
CREATE TABLE draft (
	draft_type VARCHAR2(30) NOT NULL, /* 기안서 종류 */
	draft_name VARCHAR2(20) /* 기안서 이름 */
);

COMMENT ON TABLE draft IS '기안서 종류';

COMMENT ON COLUMN draft.draft_type IS '기안서 종류';

COMMENT ON COLUMN draft.draft_name IS '기안서 이름';

ALTER TABLE draft
	ADD
		CONSTRAINT PK_draft
		PRIMARY KEY (
			draft_type
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 반품관리 */
CREATE TABLE refund (
	refund_id NUMBER NOT NULL, /* 반품 번호 */
	book_id NUMBER NOT NULL, /* 도서 번호 */
	client_id NUMBER NOT NULL, /* 거래처 번호 */
	emp_id NUMBER NOT NULL, /* 직원 번호(담당자) */
	emp_name VARCHAR2(30) NOT NULL, /* 직원 이름(담당자) */
	refund_num NUMBER NOT NULL, /* 반품 부수 */
	refund_date DATE DEFAULT sysdate NOT NULL, /* 반품 일자 */
	refund_amount NUMBER NOT NULL, /* 반품 금액 */
	refund_state VARCHAR2(30) NOT NULL, /* 반품 상태 */
	remark VARCHAR2(1000) /* 비고 */
);

COMMENT ON TABLE refund IS '반품관리';

COMMENT ON COLUMN refund.refund_id IS '반품 번호';

COMMENT ON COLUMN refund.book_id IS '도서 번호';

COMMENT ON COLUMN refund.client_id IS '거래처 번호';

COMMENT ON COLUMN refund.emp_id IS '직원 번호(담당자)';

COMMENT ON COLUMN refund.emp_name IS '직원 이름(담당자)';

COMMENT ON COLUMN refund.refund_num IS '반품 부수';

COMMENT ON COLUMN refund.refund_date IS '반품 일자';

COMMENT ON COLUMN refund.refund_amount IS '반품 금액';

COMMENT ON COLUMN refund.refund_state IS '반품 상태';

COMMENT ON COLUMN refund.remark IS '비고';

ALTER TABLE refund
	ADD
		CONSTRAINT PK_refund
		PRIMARY KEY (
			refund_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 입/출고관리 */
CREATE TABLE store (
	store_id NUMBER NOT NULL, /* 입/출고 번호 */
	book_id NUMBER NOT NULL, /* 도서 번호 */
	storage_id NUMBER NOT NULL, /* 창고 번호 */
	emp_id NUMBER NOT NULL, /* 직원 번호(담당자) */
	emp_name VARCHAR2(30) NOT NULL, /* 직원 이름(담당자) */
	store_num NUMBER NOT NULL, /* 입/출고 부수 */
	store_price NUMBER NOT NULL, /* 입/출고가 */
	store_date DATE DEFAULT sysdate NOT NULL, /* 입/출고 일자 */
	classify VARCHAR2(20) NOT NULL /* 구분 */
);

COMMENT ON TABLE store IS '입/출고관리';

COMMENT ON COLUMN store.store_id IS '입/출고 번호';

COMMENT ON COLUMN store.book_id IS '도서 번호';

COMMENT ON COLUMN store.storage_id IS '창고 번호';

COMMENT ON COLUMN store.emp_id IS '직원 번호(담당자)';

COMMENT ON COLUMN store.emp_name IS '직원 이름(담당자)';

COMMENT ON COLUMN store.store_num IS '입/출고 부수';

COMMENT ON COLUMN store.store_price IS '입/출고가';

COMMENT ON COLUMN store.store_date IS '입/출고 일자';

COMMENT ON COLUMN store.classify IS '구분';

ALTER TABLE store
	ADD
		CONSTRAINT PK_store
		PRIMARY KEY (
			store_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 첨부파일 업무 게시판 */
CREATE TABLE upload_board (
	upload_id NUMBER NOT NULL, /* 첨부파일 번호 */
	dep_id NUMBER NOT NULL, /* 부서 번호 */
	board_id NUMBER NOT NULL, /* 게시글 번호 */
	upload_url VARCHAR2(255) NOT NULL, /* 원본파일명 */
	rename_url VARCHAR2(255) /* 변경파일명 */
);

COMMENT ON TABLE upload_board IS '첨부파일 업무 게시판';

COMMENT ON COLUMN upload_board.upload_id IS '첨부파일 번호';

COMMENT ON COLUMN upload_board.dep_id IS '부서 번호';

COMMENT ON COLUMN upload_board.board_id IS '게시글 번호';

COMMENT ON COLUMN upload_board.upload_url IS '원본파일명';

COMMENT ON COLUMN upload_board.rename_url IS '변경파일명';

ALTER TABLE upload_board
	ADD
		CONSTRAINT PK_upload_board
		PRIMARY KEY (
			upload_id,
			dep_id,
			board_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 참조 결재 쪽지  */
CREATE TABLE reference_message (
	emp_id NUMBER NOT NULL, /* 직원 번호 */
	appr_id NUMBER NOT NULL, /* 전자결재 번호 */
	read_chk CHAR(1) DEFAULT 'N' NOT NULL /* 읽음여부 */
);

COMMENT ON TABLE reference_message IS '참조 결재 쪽지 ';

COMMENT ON COLUMN reference_message.emp_id IS '직원 번호';

COMMENT ON COLUMN reference_message.appr_id IS '전자결재 번호';

COMMENT ON COLUMN reference_message.read_chk IS '읽음여부';

ALTER TABLE reference_message
	ADD
		CONSTRAINT PK_reference_message
		PRIMARY KEY (
			emp_id,
			appr_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 참조 공지사항 쪽지  */
CREATE TABLE reference_notice (
	ref_id NUMBER NOT NULL, /* 참조 번호 */
	emp_id NUMBER NOT NULL, /* 직원 번호 */
	notice_id NUMBER NOT NULL, /* 공지사항 번호 */
	read_chk CHAR(1) DEFAULT 'N' NOT NULL /* 읽음여부 */
);

COMMENT ON TABLE reference_notice IS '참조 공지사항 쪽지 ';

COMMENT ON COLUMN reference_notice.ref_id IS '참조 번호';

COMMENT ON COLUMN reference_notice.emp_id IS '직원 번호';

COMMENT ON COLUMN reference_notice.notice_id IS '공지사항 번호';

COMMENT ON COLUMN reference_notice.read_chk IS '읽음여부';

ALTER TABLE reference_notice
	ADD
		CONSTRAINT PK_reference_notice
		PRIMARY KEY (
			ref_id,
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 권한 레벨 */
CREATE TABLE authority_level (
	auth_level VARCHAR2(10) NOT NULL, /* 권한 레벨 */
	department_name VARCHAR2(30) NOT NULL /* 부서명 */
);

COMMENT ON TABLE authority_level IS '권한 레벨';

COMMENT ON COLUMN authority_level.auth_level IS '권한 레벨';

COMMENT ON COLUMN authority_level.department_name IS '부서명';

ALTER TABLE authority_level
	ADD
		CONSTRAINT PK_authority_level
		PRIMARY KEY (
			auth_level
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

/* 권한 그룹 */
CREATE TABLE authority (
	jop_id NUMBER NOT NULL, /* 직급 번호 */
	dep_id NUMBER NOT NULL, /* 부서 번호 */
	auth_level VARCHAR2(10) NOT NULL /* 권한 레벨 */
);

COMMENT ON TABLE authority IS '권한 그룹';

COMMENT ON COLUMN authority.jop_id IS '직급 번호';

COMMENT ON COLUMN authority.dep_id IS '부서 번호';

COMMENT ON COLUMN authority.auth_level IS '권한 레벨';

ALTER TABLE employee
	ADD
		CONSTRAINT FK_department_TO_employee
		FOREIGN KEY (
			dep_id
		)
		REFERENCES department (
			dep_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE employee
	ADD
		CONSTRAINT FK_position_TO_employee
		FOREIGN KEY (
			pos_id
		)
		REFERENCES position (
			pos_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE employee
	ADD
		CONSTRAINT FK_job_TO_employee
		FOREIGN KEY (
			jop_id
		)
		REFERENCES job (
			jop_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE board
	ADD
		CONSTRAINT FK_department_TO_board
		FOREIGN KEY (
			dep_id
		)
		REFERENCES department (
			dep_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE board
	ADD
		CONSTRAINT FK_employee_TO_board
		FOREIGN KEY (
			emp_id
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE reply
	ADD
		CONSTRAINT FK_board_TO_reply
		FOREIGN KEY (
			dep_id,
			board_id
		)
		REFERENCES board (
			dep_id,
			board_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;
/*
ALTER TABLE reply
	ADD
		CONSTRAINT FK_reply_TO_reply
		FOREIGN KEY (
			parent_id
		)
		REFERENCES reply (
			reply_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;
*/
ALTER TABLE reply
	ADD
		CONSTRAINT FK_employee_TO_reply
		FOREIGN KEY (
			emp_id
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE upload_reply
	ADD
		CONSTRAINT FK_reply_TO_upload_reply
		FOREIGN KEY (
			reply_id
		)
		REFERENCES reply (
			reply_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE message
	ADD
		CONSTRAINT FK_employee_TO_message
		FOREIGN KEY (
			sender_id
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE message
	ADD
		CONSTRAINT FK_employee_TO_message2
		FOREIGN KEY (
			receiver_id
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE approval
	ADD
		CONSTRAINT FK_employee_TO_approval
		FOREIGN KEY (
			drafter
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE approval
	ADD
		CONSTRAINT FK_employee_TO_approval2
		FOREIGN KEY (
			approver
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE approval
	ADD
		CONSTRAINT FK_draft_TO_approval
		FOREIGN KEY (
			draft_type
		)
		REFERENCES draft (
			draft_type
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE approval
	ADD
		CONSTRAINT FK_aprovalline_TO_approval
		FOREIGN KEY (
			line_id
		)
		REFERENCES aprovalline (
			line_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE aprovalline
	ADD
		CONSTRAINT FK_employee_TO_aprovalline
		FOREIGN KEY (
			emp_id
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE aprovalline
	ADD
		CONSTRAINT FK_employee_TO_aprovalline2
		FOREIGN KEY (
			emp_id2
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE aprovalline
	ADD
		CONSTRAINT FK_employee_TO_aprovalline3
		FOREIGN KEY (
			emp_id3
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE aprovalline
	ADD
		CONSTRAINT FK_employee_TO_aprovalline4
		FOREIGN KEY (
			emp_id4
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE aprovalline
	ADD
		CONSTRAINT FK_employee_TO_aprovalline5
		FOREIGN KEY (
			emp_id5
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE draft_annual
	ADD
		CONSTRAINT FK_approval_TO_draft_annual
		FOREIGN KEY (
			appr_id
		)
		REFERENCES approval (
			appr_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE draft_annual
	ADD
		CONSTRAINT FK_draft_TO_draft_annual
		FOREIGN KEY (
			draft_type
		)
		REFERENCES draft (
			draft_type
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE reference_board
	ADD
		CONSTRAINT FK_board_TO_reference_board
		FOREIGN KEY (
			dep_id,
			board_id
		)
		REFERENCES board (
			dep_id,
			board_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE reference_board
	ADD
		CONSTRAINT FK_employee_TO_reference_board
		FOREIGN KEY (
			emp_id
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE book
	ADD
		CONSTRAINT FK_writer_TO_book
		FOREIGN KEY (
			writer_id
		)
		REFERENCES writer (
			writer_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE edit
	ADD
		CONSTRAINT FK_employee_TO_edit
		FOREIGN KEY (
			emp_id
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE edit
	ADD
		CONSTRAINT FK_department_TO_edit
		FOREIGN KEY (
			dep_id
		)
		REFERENCES department (
			dep_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE contract
	ADD
		CONSTRAINT FK_employee_TO_contract
		FOREIGN KEY (
			emp_id
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE contract
	ADD
		CONSTRAINT FK_writer_TO_contract
		FOREIGN KEY (
			writer_id
		)
		REFERENCES writer (
			writer_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE notice
	ADD
		CONSTRAINT FK_employee_TO_notice
		FOREIGN KEY (
			emp_id
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE print_order
	ADD
		CONSTRAINT FK_book_TO_print_order
		FOREIGN KEY (
			book_id
		)
		REFERENCES book (
			book_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE print_order
	ADD
		CONSTRAINT FK_employee_TO_print_order
		FOREIGN KEY (
			emp_id
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE print_order
	ADD
		CONSTRAINT FK_client_TO_print_order
		FOREIGN KEY (
			print_id
		)
		REFERENCES client (
			client_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE inventory
	ADD
		CONSTRAINT FK_book_TO_inventory
		FOREIGN KEY (
			book_id
		)
		REFERENCES book (
			book_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE inventory
	ADD
		CONSTRAINT FK_client_TO_inventory
		FOREIGN KEY (
			storage_id
		)
		REFERENCES client (
			client_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE inventory
	ADD
		CONSTRAINT FK_store_TO_inventory
		FOREIGN KEY (
			store_id
		)
		REFERENCES store (
			store_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE inventory
	ADD
		CONSTRAINT FK_inventory_TO_inventory
		FOREIGN KEY (
			prev_inven_id
		)
		REFERENCES inventory (
			inven_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE inventory
	ADD
		CONSTRAINT FK_refund_TO_inventory
		FOREIGN KEY (
			refund_id
		)
		REFERENCES refund (
			refund_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE book_order
	ADD
		CONSTRAINT FK_client_TO_book_order
		FOREIGN KEY (
			client_id
		)
		REFERENCES client (
			client_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE book_order
	ADD
		CONSTRAINT FK_employee_TO_book_order
		FOREIGN KEY (
			emp_id
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE book_order
	ADD
		CONSTRAINT FK_book_TO_book_order
		FOREIGN KEY (
			book_id
		)
		REFERENCES book (
			book_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE refund
	ADD
		CONSTRAINT FK_book_TO_refund
		FOREIGN KEY (
			book_id
		)
		REFERENCES book (
			book_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE refund
	ADD
		CONSTRAINT FK_employee_TO_refund
		FOREIGN KEY (
			emp_id
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE refund
	ADD
		CONSTRAINT FK_client_TO_refund
		FOREIGN KEY (
			client_id
		)
		REFERENCES client (
			client_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE store
	ADD
		CONSTRAINT FK_client_TO_store
		FOREIGN KEY (
			storage_id
		)
		REFERENCES client (
			client_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE store
	ADD
		CONSTRAINT FK_employee_TO_store
		FOREIGN KEY (
			emp_id
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE store
	ADD
		CONSTRAINT FK_book_TO_store
		FOREIGN KEY (
			book_id
		)
		REFERENCES book (
			book_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE upload_board
	ADD
		CONSTRAINT FK_board_TO_upload_board
		FOREIGN KEY (
			dep_id,
			board_id
		)
		REFERENCES board (
			dep_id,
			board_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE reference_message
	ADD
		CONSTRAINT FK_employee_TO_reference_message
		FOREIGN KEY (
			emp_id
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE reference_message
	ADD
		CONSTRAINT FK_approval_TO_reference_message
		FOREIGN KEY (
			appr_id
		)
		REFERENCES approval (
			appr_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE reference_notice
	ADD
		CONSTRAINT FK_employee_TO_reference_notice
		FOREIGN KEY (
			emp_id
		)
		REFERENCES employee (
			emp_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE reference_notice
	ADD
		CONSTRAINT FK_notice_TO_reference_notice
		FOREIGN KEY (
			notice_id
		)
		REFERENCES notice (
			notice_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE authority
	ADD
		CONSTRAINT FK_job_TO_authority
		FOREIGN KEY (
			jop_id
		)
		REFERENCES job (
			jop_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE authority
	ADD
		CONSTRAINT FK_department_TO_authority
		FOREIGN KEY (
			dep_id
		)
		REFERENCES department (
			dep_id
		)
		NOT DEFERRABLE
		INITIALLY IMMEDIATE
		ENABLE
		VALIDATE;

ALTER TABLE sales
	ADD
		CONSTRAINT FK_book_order_TO_sales
		FOREIGN KEY (
			order_id,
			book_id
		)
		REFERENCES book_order (
			order_id,
			book_id
		);

ALTER TABLE notice
	ADD (notice_readcount number);
    
ALTER TABLE notice
	ADD (NOTICE_ORIGINAL_FILENAME VARCHAR2(255));

ALTER TABLE notice
	ADD (NOTICE_RENAME_FILENAME VARCHAR2(255));
    
ALTER TABLE print_order
	modify (UNIT VARCHAR2(20));   

alter table contract
modify contr_state default '진행중';

alter table edit
modify start_date default null;

alter table book
modify book_state default '정상';

alter table contract 
ADD (category VARCHAR2(30));

ALTER TABLE book
MODIFY BOOK_PRICE_S number NULL;

ALTER TABLE book
MODIFY PUB_DATE date NULL;

ALTER TABLE draft_annual
ADD (title VARCHAR2(500) NULL );

alter TABLE book
MODIFY PUB_DATE date default sysdate;
