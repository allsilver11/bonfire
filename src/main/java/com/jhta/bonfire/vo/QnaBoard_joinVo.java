package com.jhta.bonfire.vo;

import java.util.Date;

public class QnaBoard_joinVo {
	private int num;
	private String id;
	private String title;
	private String content;
	private Date adddate;
	private int hits;
	private String comments;
	private String cname;
	private String pwd;
	
	private int fidx;
	private int fnum;
	private String savefilename;
	private String orgfilename;
	
	private int hnum;
	private String hid;
	
	public QnaBoard_joinVo() {}

	public QnaBoard_joinVo(int num, String id, String title, String content, Date adddate, int hits, String comments,
			String cname, String pwd, int fidx, int fnum, String savefilename, String orgfilename, int hnum,
			String hid) {
		super();
		this.num = num;
		this.id = id;
		this.title = title;
		this.content = content;
		this.adddate = adddate;
		this.hits = hits;
		this.comments = comments;
		this.cname = cname;
		this.pwd = pwd;
		this.fidx = fidx;
		this.fnum = fnum;
		this.savefilename = savefilename;
		this.orgfilename = orgfilename;
		this.hnum = hnum;
		this.hid = hid;
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

	public int getFidx() {
		return fidx;
	}

	public void setFidx(int fidx) {
		this.fidx = fidx;
	}

	public int getFnum() {
		return fnum;
	}

	public void setFnum(int fnum) {
		this.fnum = fnum;
	}

	public String getSavefilename() {
		return savefilename;
	}

	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}

	public String getOrgfilename() {
		return orgfilename;
	}

	public void setOrgfilename(String orgfilename) {
		this.orgfilename = orgfilename;
	}

	public int getHnum() {
		return hnum;
	}

	public void setHnum(int hnum) {
		this.hnum = hnum;
	}

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}
	
	
	
}
