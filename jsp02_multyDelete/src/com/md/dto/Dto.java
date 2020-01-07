package com.md.dto;

import java.sql.Date;

public class Dto {

//	NUM NUMBER PRIMARY KEY,
//	NAME VARCHAR2(100) NOT NULL,
//	TITLE VARCHAR2(500) NOT NULL,
//	CONTENT VARCHAR2(4000) NOT NULL,
//	REGDATE DATE NOT NULL
	
	private int num;
	private String name;
	private String title;
	private String content;
	private Date date;

	public Dto() {
		
	}
	
	public Dto(int num, String name, String title, String content, Date date) {
		super();
		this.num = num;
		this.name = name;
		this.title = title;
		this.content = content;
		this.date = date;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
