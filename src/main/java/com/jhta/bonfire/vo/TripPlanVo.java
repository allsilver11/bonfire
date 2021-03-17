package com.jhta.bonfire.vo;

public class TripPlanVo {
    private String idx;
    private String id;
    private String placeid;
    private String triporder;

    public String getIdx() {
        return this.idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaceid() {
        return this.placeid;
    }

    public void setPlaceid(String placeid) {
        this.placeid = placeid;
    }

    public String getTriporder() {
        return this.triporder;
    }

    public void setTriporder(String triporder) {
        this.triporder = triporder;
    }

    public TripPlanVo() {
    }

    public TripPlanVo(String idx, String id, String placeid, String triporder) {
        this.idx = idx;
        this.id = id;
        this.placeid = placeid;
        this.triporder = triporder;
    }

}
