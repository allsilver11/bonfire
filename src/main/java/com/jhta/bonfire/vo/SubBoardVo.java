package com.jhta.bonfire.vo;

import java.sql.Date;

public class SubBoardVo {
    private int num;
    private String id;
    private String title;
    private String content;
    private Date adddate;
    private int recommend;
    private int hits;
    private String cname;

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAdddate() {
        return this.adddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }

    public int getrecommend() {
        return this.recommend;
    }

    public void setrecommend(int recommend) {
        this.recommend = recommend;
    }

    public int getHits() {
        return this.hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public String getCname() {
        return this.cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public SubBoardVo() {
    }

    public SubBoardVo(int num, String id, String title, String content, Date adddate, int recommend, int hits, String cname) {
        this.num = num;
        this.id = id;
        this.title = title;
        this.content = content;
        this.adddate = adddate;
        this.recommend = recommend;
        this.hits = hits;
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "{" +
            " num='" + getNum() + "'" +
            ", id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", adddate='" + getAdddate() + "'" +
            ", recommend='" + getrecommend() + "'" +
            ", hits='" + getHits() + "'" +
            ", cname='" + getCname() + "'" +
            "}";
    }


}
