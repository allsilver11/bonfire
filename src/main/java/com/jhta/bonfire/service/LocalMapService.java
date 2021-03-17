package com.jhta.bonfire.service;

import com.jhta.bonfire.dao.LocalMapDao;
import com.jhta.bonfire.util.CommonUtil;
import com.jhta.bonfire.vo.geoJsonVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalMapService {
    @Autowired private LocalMapDao dao;

    public int addMap(geoJsonVo vo){
        return dao.addMap(CommonUtil.fromGeoJSON(vo));
    }
    public geoJsonVo getById(String propertiesid){
        return CommonUtil.toGeoJSON(dao.getById(propertiesid));
    }
}
