DROP TABLE MDT;
DROP SEQUENCE MDTSEQ;

CREATE SEQUENCE MDTSEQ;
CREATE TABLE MDT(
	NUM NUMBER PRIMARY KEY,
	NAME VARCHAR2(100) NOT NULL,
	TITLE VARCHAR2(500) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	REGDATE DATE NOT NULL
);
INSERT INTO MDT VALUES(MDTSEQ.NEXTVAL, '관리자', '테스트', '테스트 중입니다.', SYSDATE);

SELECT NUM, NAME, TITLE, CONTENT, REGDATE FROM MDT ORDER BY NUM DESC;

