package com.jhta.bonfire.vo;

public class LocalMapVo {
    private String type;
    private String g_type;
    private String g_coordinateslng;
    private String g_coordinatesLat;
    private String p_address_name;
    private String p_category_group_code;
    private String p_category_group_name;
    private String p_category_name;
    private String p_id;
    private String p_phone;
    private String p_place_name;
    private String p_place_url;
    private String p_road_address_name;

    public LocalMapVo(String type, String g_type, String g_coordinateslng, String g_coordinatesLat, String p_address_name, String p_category_group_code, String p_category_group_name, String p_category_name, String p_id, String p_phone, String p_place_name, String p_place_url, String p_road_address_name) {
        this.type = type;
        this.g_type = g_type;
        this.g_coordinateslng = g_coordinateslng;
        this.g_coordinatesLat = g_coordinatesLat;
        this.p_address_name = p_address_name;
        this.p_category_group_code = p_category_group_code;
        this.p_category_group_name = p_category_group_name;
        this.p_category_name = p_category_name;
        this.p_id = p_id;
        this.p_phone = p_phone;
        this.p_place_name = p_place_name;
        this.p_place_url = p_place_url;
        this.p_road_address_name = p_road_address_name;
    }

    public LocalMapVo() {
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getG_type() {
        return this.g_type;
    }

    public void setG_type(String g_type) {
        this.g_type = g_type;
    }

    public String getG_coordinateslng() {
        return this.g_coordinateslng;
    }

    public void setG_coordinateslng(String g_coordinateslng) {
        this.g_coordinateslng = g_coordinateslng;
    }

    public String getG_coordinatesLat() {
        return this.g_coordinatesLat;
    }

    public void setG_coordinatesLat(String g_coordinatesLat) {
        this.g_coordinatesLat = g_coordinatesLat;
    }

    public String getP_address_name() {
        return this.p_address_name;
    }

    public void setP_address_name(String p_address_name) {
        this.p_address_name = p_address_name;
    }

    public String getP_category_group_code() {
        return this.p_category_group_code;
    }

    public void setP_category_group_code(String p_category_group_code) {
        this.p_category_group_code = p_category_group_code;
    }

    public String getP_category_group_name() {
        return this.p_category_group_name;
    }

    public void setP_category_group_name(String p_category_group_name) {
        this.p_category_group_name = p_category_group_name;
    }

    public String getP_category_name() {
        return this.p_category_name;
    }

    public void setP_category_name(String p_category_name) {
        this.p_category_name = p_category_name;
    }

    public String getP_id() {
        return this.p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_phone() {
        return this.p_phone;
    }

    public void setP_phone(String p_phone) {
        this.p_phone = p_phone;
    }

    public String getP_place_name() {
        return this.p_place_name;
    }

    public void setP_place_name(String p_place_name) {
        this.p_place_name = p_place_name;
    }

    public String getP_place_url() {
        return this.p_place_url;
    }

    public void setP_place_url(String p_place_url) {
        this.p_place_url = p_place_url;
    }

    public String getP_road_address_name() {
        return this.p_road_address_name;
    }

    public void setP_road_address_name(String p_road_address_name) {
        this.p_road_address_name = p_road_address_name;
    }

}
