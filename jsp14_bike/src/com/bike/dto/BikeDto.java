package com.bike.dto;

public class BikeDto {
//	ADDR_GU VARCHAR2(4000),
//	CONTENT_ID NUMBER PRIMARY KEY,	--int
//	CONTENT_NUM VARCHAR2(4000),
//	NEW_ADDR VARCHAR2(4000),
//	CRADLE_COUNT NUMBER,			--int
//	LONGITUDE NUMBER,				--double
//	LATITUDE NUMBER					--double
	
	private String addr_gu;
	private int content_id;
	private String content_num;
	private String new_addr;
	private int cradle_count;
	private double longitude;
	private double latitude;
	
	public BikeDto() {
		super();
	}
	
	public BikeDto(String addr_gu, int content_id	, String content_num, String new_addr, int cradle_count,
			double longitude, double latitude) {
		super();
		this.addr_gu = addr_gu;
		this.content_id = content_id;
		this.content_num = content_num;
		this.new_addr = new_addr;
		this.cradle_count = cradle_count;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public String getAddr_gu() {
		return addr_gu;
	}
	public void setAddr_gu(String addr_gu) {
		this.addr_gu = addr_gu;
	}
	public int getContent_id() {
		return content_id;
	}
	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}
	public String getContent_num() {
		return content_num;
	}
	public void setContent_num(String content_num) {
		this.content_num = content_num;
	}
	public String getNew_addr() {
		return new_addr;
	}
	public void setNew_addr(String new_addr) {
		this.new_addr = new_addr;
	}
	public int getCradle_count() {
		return cradle_count;
	}
	public void setCradle_count(int cradle_count) {
		this.cradle_count = cradle_count;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
}
