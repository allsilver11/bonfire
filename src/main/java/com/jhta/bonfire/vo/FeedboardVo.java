package com.jhta.bonfire.vo;

import java.sql.Date;

public class FeedboardVo {
	private int num;
	private String id;
	private String title;
	private String content;
	private String thumbnail;
	private int recommend;
	private int hits;
	private int scrap;
	private int ispost;
	private Date postdate;
	private Date adddate;
	private String cname;
	
	public FeedboardVo() {}

	public FeedboardVo(int num, String id, String title, String content, String thumbnail, int recommend, int hits,
			int scrap, int ispost, Date postdate, Date adddate, String cname) {
		super();
		this.num = num;
		this.id = id;
		this.title = title;
		this.content = content;
		this.thumbnail = thumbnail;
		this.recommend = recommend;
		this.hits = hits;
		this.scrap = scrap;
		this.ispost = ispost;
		this.postdate = postdate;
		this.adddate = adddate;
		this.cname = cname;
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

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getScrap() {
		return scrap;
	}

	public void setScrap(int scrap) {
		this.scrap = scrap;
	}

	public int getIspost() {
		return ispost;
	}

	public void setIspost(int ispost) {
		this.ispost = ispost;
	}

	public Date getPostdate() {
		return postdate;
	}

	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}

	public Date getAdddate() {
		return adddate;
	}

	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
}
