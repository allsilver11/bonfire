package com.jhta.bonfire.vo;

public class DfileVo {
	private int idx;
	private int num;
	private String saveFileName;
	private String orgFileName;
	
	public DfileVo() {}

	public DfileVo(int idx, int num, String saveFileName, String orgFileName) {
		super();
		this.idx = idx;
		this.num = num;
		this.saveFileName = saveFileName;
		this.orgFileName = orgFileName;
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
