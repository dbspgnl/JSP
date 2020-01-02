package com.my.dto;

import java.util.Date;

public class MyDto {

//	--번호, 작성자, 제목, 내용, 작성일
//	CREATE TABLE MYBOARD(
//		MYNO NUMBER PRIMARY KEY,
//		MYNAME VARCHAR2(1000) NOT NULL,
//		MYTITLE VARCHAR2(2000) NOT NULL,
//		MYCONTENT VARCHAR2(4000) NOT NULL,
//		MYDATE DATE NOT NULL
	
	private int myno;
	private String myname;
	private String mytitle;
	private String mycontent;
	private Date mydate;
	
	public MyDto() {
		
	}
	public MyDto(int myno, String myname, String mytitle, 
			String mycontent, Date mydate) {
		this.myno = myno;
		this.myname = myname;
		this.mytitle = mytitle;
		this.mycontent = mycontent;
		this.mydate = mydate;
	}
	public int getMyno() {
		return myno;
	}
	public void setMyno(int myno) {
		this.myno = myno;
	}
	public String getMyname() {
		return myname;
	}
	public void setMyname(String myname) {
		this.myname = myname;
	}
	public String getMytitle() {
		return mytitle;
	}
	public void setMytitle(String mytitle) {
		this.mytitle = mytitle;
	}
	public String getMycontent() {
		return mycontent;
	}
	public void setMycontent(String mycontent) {
		this.mycontent = mycontent;
	}
	public Date getMydate() {
		return mydate;
	}
	public void setMydate(Date mydate) {
		this.mydate = mydate;
	}
	
}
