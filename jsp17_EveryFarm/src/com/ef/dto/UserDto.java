package com.ef.dto;

import java.sql.Date;

public class UserDto {
//	"id"	VARCHAR2(20)		PRIMARY KEY,
//	"pw"	VARCHAR2(20)		NULL,
//	"name"	VARCHAR2(20)		NULL,
//	"email"	VARCHAR2(30)		NULL,
//	"zonecode"	VARCHAR2(10)		NULL,
//	"addr"	VARCHAR2(100)		NULL,
//	"addretc"	VARCHAR2(100)		NULL,
//	"phone"	VARCHAR2(13)		NULL,
//	"grade"	NUMBER(3)		NULL,
//	"regdate"	DATE		NULL,
//	"dropout"	VARCHAR2(2)		NULL
	
	private String id;
	private String pw;
	private String name;
	private String email;
	private String zonecode;
	private String addr;
	private String addretc;
	private String phone;
	private int grade;
	private Date regdate;
	private String dropout;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZonecode() {
		return zonecode;
	}
	public void setZonecode(String zonecode) {
		this.zonecode = zonecode;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAddretc() {
		return addretc;
	}
	public void setAddretc(String addretc) {
		this.addretc = addretc;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getDropout() {
		return dropout;
	}
	public void setDropout(String dropout) {
		this.dropout = dropout;
	}
	
}
