package com.jhta.bonfire.vo;

import java.util.Date;

public class QnaBoardVo {
	private int num;
	private String id;
	private String title;
	private String content;
	private Date adddate;
	private int hits;
	private String comments;
	private String cname;
	private String pwd;
	private int pin;
	private int secret;
	
	public QnaBoardVo() {}

	public QnaBoardVo(int num, String id, String title, String content, Date adddate, int hits,
			String comments, String cname, String pwd, int pin, int secret) {
		super();
		this.num = num;
		this.id = id;
		this.title = title;
		this.content = content;
		this.pwd = pwd;
		this.adddate = adddate;
		this.hits = hits;
		this.comments = comments;
		this.cname = cname;
		this.pwd = pwd;
		this.pin = pin;
		this.secret = secret;
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

	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public int getSecret() {
		return secret;
	}

	public void setSecret(int secret) {
		this.secret = secret;
	}
	
}
