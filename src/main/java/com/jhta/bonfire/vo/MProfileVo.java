package com.jhta.bonfire.vo;

public class MProfileVo {
	private String id;
	private String nickname;
	private String intro;
	private int follow;
	private int follower;
	private String bfilename;
	private String pfilename;
	public MProfileVo() {
		super();
	}
	public MProfileVo(String id, String nickname, String intro, int follow, int follower, String bfilename,
			String pfilename) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.intro = intro;
		this.follow = follow;
		this.follower = follower;
		this.bfilename = bfilename;
		this.pfilename = pfilename;
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
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public int getFollow() {
		return follow;
	}
	public void setFollow(int follow) {
		this.follow = follow;
	}
	public int getFollower() {
		return follower;
	}
	public void setFollower(int follower) {
		this.follower = follower;
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
	
}
