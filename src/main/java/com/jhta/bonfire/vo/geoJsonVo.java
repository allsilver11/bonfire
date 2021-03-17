package com.jhta.bonfire.vo;

import java.util.HashMap;
import java.util.Map;

public class geoJsonVo {
    private String type;
    private Map<String, Object> geometry= new HashMap<>();
    private Map<String, Object> properties=new HashMap<>();

    public geoJsonVo() {
    }

    public geoJsonVo(String type, Map<String,Object> geometry, Map<String,Object> properties) {
        this.type = type;
        this.geometry = geometry;
        this.properties = properties;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String,Object> getGeometry() {
        return this.geometry;
    }

    public void setGeometry(Map<String,Object> geometry) {
        this.geometry = geometry;
    }

    public Map<String,Object> getProperties() {
        return this.properties;
    }

    public void setProperties(Map<String,Object> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "{" +
            " type='" + getType() + "'" +
            ", geometry='" + getGeometry() + "'" +
            ", properties='" + getProperties() + "'" +
            "}";
    }
    
}
