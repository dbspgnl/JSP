package com.mvc.dto;

import java.sql.Date;

public class MVCDto {

//	SEQ NUMBER PRIMARY KEY,
//	WRITER VARCHAR2(500) NOT NULL,
//	TITLE VARCHAR2(1000) NOT NULL,
//	CONTENT VARCHAR2(4000) NOT NULL,
//	REGDATE DATE NOT NULL
	
	private int seq;
	private String writer;
	private String title;
	private String content;
	private Date regdate;
	
	public MVCDto() {

	}

	public MVCDto(int seq, String writer, String title, String content, Date regdate) {
		this.seq = seq;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
	}
	// 글쓰기 :  writer, title, content
	public MVCDto(String writer, String title, String content) {
		this.writer = writer;
		this.title = title;
		this.content = content;
	}
	
	// 수정: seq, title, content
	public MVCDto(int seq, String title, String content) {
		this.seq = seq;
		this.title = title;
		this.content = content;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
