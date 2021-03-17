package com.jhta.bonfire.service;

import java.util.ArrayList;
import java.util.List;

import com.jhta.bonfire.dao.TripPlanDao;
import com.jhta.bonfire.util.CommonUtil;
import com.jhta.bonfire.vo.LocalMapVo;
import com.jhta.bonfire.vo.TripPlanVo;
import com.jhta.bonfire.vo.geoJsonVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripPlanService {
    @Autowired private TripPlanDao dao;
    /**
     * @param List<TripPlanVo>
     * @return idx
     */
    public int addPlan(List<TripPlanVo> vo) {
        int idx = getIndex(); idx++;
        for (TripPlanVo tripPlanVo : vo) {tripPlanVo.setIdx(idx+"");}
        dao.addPlan(vo);
        return idx;
    }
    public int getIndex() {return dao.getIndex();}
    public List<Integer> getIdxByUser(String id) {return dao.getIdxByUser(id);}
    public List<geoJsonVo> getPlanByUser(String id) {
        List<LocalMapVo> maps = dao.getPlanMapByUser(id);
        List<geoJsonVo> result = new ArrayList<>();
        for (LocalMapVo localMapVo : maps) {result.add(CommonUtil.toGeoJSON(localMapVo));}
        return result;
    }

    public List<TripPlanVo> getPlanIdxByIdx(int idx) {return dao.getPlanIdxByIdx(idx);}
    public List<geoJsonVo> getPlanMapByIdx(int idx) {
        List<LocalMapVo> maps = dao.getPlanMapByIdx(idx);
        List<geoJsonVo> result = new ArrayList<>();
        for (LocalMapVo localMapVo : maps) {result.add(CommonUtil.toGeoJSON(localMapVo));}
        return result;
    }
    public int removePlan(int idx, String id) {
        return dao.removePlan(new TripPlanVo(idx+"", id, "", ""));
    }
}