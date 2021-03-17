package com.jhta.bonfire.vo;

public class SearchAuthorVo {
	private String id;
	private String nickname;
	private String bfilename;
	private String pfilename;
	private String authority;
	public SearchAuthorVo() {
		super();
	}
	public SearchAuthorVo(String id, String nickname, String bfilename, String pfilename, String authority) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.bfilename = bfilename;
		this.pfilename = pfilename;
		this.authority = authority;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBfilename() {
		return bfilename;
	}
	public void setBfilename(String bfilename) {
		this.bfilename = bfilename;
	}
	public String getPfilename() {
		return pfilename;
	}
	public void setPfilename(String pfilename) {
		this.pfilename = pfilename;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
}
