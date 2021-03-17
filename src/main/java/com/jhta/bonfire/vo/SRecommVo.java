package com.jhta.bonfire.vo;

import java.sql.Date;

public class SRecommVo {
    private int num;
    private String id;
    private int value;
    private Date adddate;


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

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getAdddate() {
        return this.adddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }

    public SRecommVo() {
    }

    public SRecommVo(int num, String id, int value, Date adddate) {
        this.num = num;
        this.id = id;
        this.value = value;
        this.adddate = adddate;
    }
    
}
