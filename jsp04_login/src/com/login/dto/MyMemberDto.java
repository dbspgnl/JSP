package com.login.dto;

public class MyMemberDto {
//	MYNO NUMBER NOT NULL,				--PK
//	MYID VARCHAR2(500) NOT NULL,		--UNIQUE
//	MYPW VARCHAR2(500) NOT NULL,
//	MYNAME VARCHAR2(500) NOT NULL,
//	MYADDR VARCHAR2(4000) NOT NULL,
//	MYPHONE VARCHAR2(20) NOT NULL,		--UNIQUE
//	MYEMAIL VARCHAR2(100) NOT NULL,		--UNIQUE
//	MYENABLED VARCHAR2(2) NOT NULL,
//	MYROLE VARCHAR2(100) NOT NULL,
	private int myno;
	private String myid;
	private String mypw;
	private String myname;
	private String myaddr;
	private String myphone;
	private String myemail;
	private String myenabled;
	private String myrole;
	
	public MyMemberDto() {
		
	}
	
	public MyMemberDto(int myno, String myid, String mypw, String myname, String myaddr, String myphone, String myemail,
			String myenabled, String myrole) {
		super();
		this.myno = myno;
		this.myid = myid;
		this.mypw = mypw;
		this.myname = myname;
		this.myaddr = myaddr;
		this.myphone = myphone;
		this.myemail = myemail;
		this.myenabled = myenabled;
		this.myrole = myrole;
	}

	public int getMyno() {
		return myno;
	}

	public void setMyno(int myno) {
		this.myno = myno;
	}

	public String getMyid() {
		return myid;
	}

	public void setMyid(String myid) {
		this.myid = myid;
	}

	public String getMypw() {
		return mypw;
	}

	public void setMypw(String mypw) {
		this.mypw = mypw;
	}

	public String getMyname() {
		return myname;
	}

	public void setMyname(String myname) {
		this.myname = myname;
	}

	public String getMyaddr() {
		return myaddr;
	}

	public void setMyaddr(String myaddr) {
		this.myaddr = myaddr;
	}

	public String getMyphone() {
		return myphone;
	}

	public void setMyphone(String myphone) {
		this.myphone = myphone;
	}

	public String getMyemail() {
		return myemail;
	}

	public void setMyemail(String myemail) {
		this.myemail = myemail;
	}

	public String getMyenabled() {
		return myenabled;
	}

	public void setMyenabled(String myenabled) {
		this.myenabled = myenabled;
	}

	public String getMyrole() {
		return myrole;
	}

	public void setMyrole(String myrole) {
		this.myrole = myrole;
	}
	
}
