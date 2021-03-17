package com.jhta.bonfire.vo;

public class CatsVo {
    private String cname;
    private String cwhichboard;

    public CatsVo() {
    }

    public CatsVo(String cname, String cwhichboard) {
        this.cname = cname;
        this.cwhichboard = cwhichboard;
    }

    public String getCname() {
        return this.cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCwhichboard() {
        return this.cwhichboard;
    }

    public void setCwhichboard(String cwhichboard) {
        this.cwhichboard = cwhichboard;
    }

}
