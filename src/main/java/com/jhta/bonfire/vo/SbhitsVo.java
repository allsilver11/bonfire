package com.jhta.bonfire.vo;

public class SbhitsVo {
    private int num;
    private String id;

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

    public SbhitsVo() {
    }

    public SbhitsVo(int num, String id) {
        this.num = num;
        this.id = id;
    }
}
