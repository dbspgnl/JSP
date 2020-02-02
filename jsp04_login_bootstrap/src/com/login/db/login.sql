DROP SEQUENCE MYMEMBERSEQ;
DROP TABLE MYMEMBER;

CREATE SEQUENCE MYMEMBERSEQ;
--멤버번호(), 아이디, 패스워드, 이름, 주소, 전화버호, 이메일, 
--가입여부(Y-가입/N-탈퇴), 등급(ADMIN, USER..)
CREATE TABLE MYMEMBER(
	MYNO NUMBER NOT NULL,				--PK
	MYID VARCHAR2(500) NOT NULL,		--UNIQUE
	MYPW VARCHAR2(500) NOT NULL,
	MYNAME VARCHAR2(500) NOT NULL,
	MYADDR VARCHAR2(4000) NOT NULL,
	MYPHONE VARCHAR2(20) NOT NULL,		--UNIQUE
	MYEMAIL VARCHAR2(100) NOT NULL,		--UNIQUE
	MYENABLED VARCHAR2(2) NOT NULL,
	MYROLE VARCHAR2(100) NOT NULL,
	
	CONSTRAINT MYMEMBER_PK PRIMARY KEY (MYNO),
	CONSTRAINT MYMEMBER_UQ_ID UNIQUE(MYID),
	CONSTRAINT MYMEMBER_UQ_PHONE UNIQUE(MYPHONE),
	CONSTRAINT MYMEMBER_UQ_EMAIL UNIQUE(MYEMAIL),
	CONSTRAINT MYMEMBER_CHK_ENABLED CHECK(MYENABLED IN('Y','N'))
);

INSERT INTO MYMEMBER VALUES
(MYMEMBERSEQ.NEXTVAL,'admin','1234','관리자','서울시 강남구','010-1234-5678','ADMIN@ADMIN.COM','Y','ADMIN');

INSERT INTO MYMEMBER VALUES
(MYMEMBERSEQ.NEXTVAL,'user','1234','사용자','서울시 종로구','010-1111-2222','TEST@ADMIN.COM','Y','USER');

SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE FROM MYMEMBER;