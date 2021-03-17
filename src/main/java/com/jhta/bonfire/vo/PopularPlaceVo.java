package com.jhta.bonfire.vo;

public class PopularPlaceVo {
	private String cname;
	private int cnamecnt;
	public PopularPlaceVo() {
		super();
	}
	public PopularPlaceVo(String cname, int cnamecnt) {
		super();
		this.cname = cname;
		this.cnamecnt = cnamecnt;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getCnamecnt() {
		return cnamecnt;
	}
	public void setCnamecnt(int cnamecnt) {
		this.cnamecnt = cnamecnt;
	}
	
}
