DROP TABLE MBP_USER

CREATE TABLE MBP_USER(	
	userID VARCHAR2(20), 
	userPassword VARCHAR2(64),
	userEmail VARCHAR2(50),
	userEmailHash VARCHAR2(64),
	userEmailChecked NUMBER(2) CONSTRAINT userEmailChecked_BOOLEAN CHECK(userEmailChecked IN (0,1))
);

SELECT * FROM MBP_USER;