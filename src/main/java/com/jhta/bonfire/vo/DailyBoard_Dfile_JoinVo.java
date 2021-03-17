package com.jhta.bonfire.vo;

import java.sql.Date;

public class DailyBoard_Dfile_JoinVo {
	private int num;
	private String id;
	private String title;
	private String content;
	private Date adddate;
	
	private int idx;
	private String saveFileName;
	private String orgFileName;
	
	public DailyBoard_Dfile_JoinVo() {}

	public DailyBoard_Dfile_JoinVo(int num, String id, String title, String content, Date adddate, int idx,
			String saveFileName, String orgFileName) {
		super();
		this.num = num;
		this.id = id;
		this.title = title;
		this.content = content;
		this.adddate = adddate;
		this.idx = idx;
		this.saveFileName = saveFileName;
		this.orgFileName = orgFileName;
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

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getOrgFileName() {
		return orgFileName;
	}

	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}
	
	
}
