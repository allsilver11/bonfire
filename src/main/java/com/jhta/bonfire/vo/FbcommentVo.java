package com.jhta.bonfire.vo;

import java.sql.Date;

public class FbcommentVo {

	private int idx;
	private int num;
	private String id;
	private String content;
	private Date adddate;
	
	public FbcommentVo() {}

	public FbcommentVo(int idx, int num, String id, String content, Date adddate) {
		super();
		this.idx = idx;
		this.num = num;
		this.id = id;
		this.content = content;
		this.adddate = adddate;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getAdddate() {
		return adddate;
	}

	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}
	
}
