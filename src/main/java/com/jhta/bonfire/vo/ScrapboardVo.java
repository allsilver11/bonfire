package com.jhta.bonfire.vo;

import java.sql.Date;

public class ScrapboardVo {
	private String id;
	private int num;
	private Date adddate;
	
	public ScrapboardVo() {}
	
	public ScrapboardVo(String id, int num, Date adddate) {
		super();
		this.id = id;
		this.num = num;
		this.adddate = adddate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Date getAdddate() {
		return adddate;
	}
	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}
	
}
