package com.jhta.bonfire.vo;

public class QhitsVo {
	private int num;
	private String id;
	public QhitsVo() {}
	public QhitsVo(int num, String id) {
		super();
		this.num = num;
		this.id = id;
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
	
}
