package com.jhta.bonfire.vo;

import java.sql.Date;

public class AuthorRegVo {
	private int num;
	private String id;
	private Date adddate;
	private String pres;
	private String theme;
	private String intro;
	
	public AuthorRegVo() {}

	public AuthorRegVo(int num, String id, Date adddate, String pres, String theme, String intro) {
		super();
		this.num = num;
		this.id = id;
		this.adddate = adddate;
		this.pres = pres;
		this.theme = theme;
		this.intro = intro;
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

	public Date getAdddate() {
		return adddate;
	}

	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}

	public String getPres() {
		return pres;
	}

	public void setPres(String pres) {
		this.pres = pres;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Override
	public String toString() {
		return "AuthorRegVo [num=" + num + ", id=" + id + ", adddate=" + adddate + ", pres=" + pres + ", theme=" + theme
				+ ", intro=" + intro + "]";
	}
	
	
}
