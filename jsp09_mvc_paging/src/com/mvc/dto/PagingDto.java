package com.mvc.dto;

public class PagingDto {
	
	// 게시글(실제 글 목록)
	// 페이지(게시글을 한 번에 보여주는 장소)
	// 블록(페이지를 한번에 보여주는 양 << 1 2 3 4 5 >> )
	
	private int totalrows; 		// 한 페이지의 총 게시글 수
	private int currentpage;	// 현재 보고 있는 페이지 (처음엔 보통 1)
	private int pagescale;		// 블록 크기 
	private int startpage;		// 시작페이지 ( 페이지 1-5일때 1)
	private int endpage;		// 마지막페이지 ( 페이지 1-5일때 5)
	private int pagegroup;		// 블록 번호
	private int totalpage;		// 총 페이지 수 ( 125/10 = 12.5 = 13페이지)
	
	private int to;
	private int from;
	
	public int getTotalrows() {
		return totalrows;
	}
	public void setTotalrows(int totalrows) {
		this.totalrows = totalrows;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public int getPagescale() {
		return pagescale;
	}
	public void setPagescale(int pagescale) {
		this.pagescale = pagescale;
	}
	public int getStartpage() {
		return startpage;
	}
	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}
	public int getEndpage() {
		return endpage;
	}
	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}
	public int getPagegroup() {
		return pagegroup;
	}
	public void setPagegroup(int pagegroup) {
		this.pagegroup = pagegroup;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	
}
