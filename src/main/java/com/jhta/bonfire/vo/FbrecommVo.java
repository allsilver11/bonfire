package com.jhta.bonfire.vo;

import java.sql.Date;

public class FbrecommVo {

	private int num;
	private String id;
	private int value;
	private Date adddate;
	
	public FbrecommVo() {}

	public FbrecommVo(int num, String id, int value, Date adddate) {
		super();
		this.num = num;
		this.id = id;
		this.value = value;
		this.adddate = adddate;
	}


	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Date getAdddate() {
		return adddate;
	}

	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}

}
