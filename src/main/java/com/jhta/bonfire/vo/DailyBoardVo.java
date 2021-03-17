package com.jhta.bonfire.vo;

import java.sql.Date;

public class DailyBoardVo {
	private int num;
	private String id;
	private String title;
	private String content;
	private Date adddate;
	
	public DailyBoardVo() {}

	public DailyBoardVo(int num, String id, String title, String content, Date adddate) {
		super();
		this.num = num;
		this.id = id;
		this.title = title;
		this.content = content;
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

	public Date getAdddate() {
		return adddate;
	}

	public void setAddate(Date adddate) {
		this.adddate = adddate;
	}
	
	
}
