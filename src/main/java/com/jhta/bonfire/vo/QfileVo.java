package com.jhta.bonfire.vo;

public class QfileVo {
	private int idx;
	private int num;
	private String savefilename;
	private String orgfilename;
	
	public QfileVo() {}

	public QfileVo(int idx, int num, String savefilename, String orgfilename) {
		super();
		this.idx = idx;
		this.num = num;
		this.savefilename = savefilename;
		this.orgfilename = orgfilename;
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
	
}
