package com.jhta.bonfire.vo;

import java.sql.Date;

public class FeedboardJoinScrapboardVo {
	private int num;
	private String writer;
	private String title;
	private String content;
	private String thumbnail;
	private int recommend;
	private int hits;
	private int scrap;
	private Date postdate;
	private String cname;
	private String id;
	private Date adddate;
	
	public FeedboardJoinScrapboardVo() {}

	public FeedboardJoinScrapboardVo(int num, String writer, String title, String content, String thumbnail,
			int recommend, int hits, int scrap, Date postdate, String cname, String id, Date adddate) {
		super();
		this.num = num;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.thumbnail = thumbnail;
		this.recommend = recommend;
		this.hits = hits;
		this.scrap = scrap;
		this.postdate = postdate;
		this.cname = cname;
		this.id = id;
		this.adddate = adddate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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

	public Date getPostdate() {
		return postdate;
	}

	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
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
	
}
