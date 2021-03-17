package com.jhta.bonfire.vo;

public class PopularArticleRecommVo {
	private int num;
	private String id;
	private String nickname;
	private String title;
	private int recommend;
	private String thumbnail;
	public PopularArticleRecommVo() {
		super();
	}
	public PopularArticleRecommVo(int num, String id, String nickname, String title, int recommend, String thumbnail) {
		super();
		this.num = num;
		this.id = id;
		this.nickname = nickname;
		this.title = title;
		this.recommend = recommend;
		this.thumbnail = thumbnail;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
}
