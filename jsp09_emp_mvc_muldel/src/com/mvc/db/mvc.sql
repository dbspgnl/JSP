DROP SEQUENCE MVCSEQ;
DROP TABLE MVCBOARD;

CREATE SEQUENCE MVCSEQ;
CREATE TABLE MVCBOARD(
	SEQ NUMBER PRIMARY KEY,
	WRITER VARCHAR2(500) NOT NULL,
	TITLE VARCHAR2(1000) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	REGDATE DATE NOT NULL
);

INSERT INTO MVCBOARD VALUES(MVCSEQ.NEXTVAL, '관리자', 'TEST제목', 'TEST내용', SYSDATE);
INSERT INTO MVCBOARD VALUES(MVCSEQ.NEXTVAL, '사용자', 'TEST제목', 'TEST내용', SYSDATE);



SELECT EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO FROM EMP ORDER BY EMPNO DESC;

SELECT * FROM EMP;

