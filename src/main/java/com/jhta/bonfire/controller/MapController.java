package com.jhta.bonfire.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.jhta.bonfire.service.LocalMapService;
import com.jhta.bonfire.service.TripPlanService;
import com.jhta.bonfire.util.CommonUtil;
import com.jhta.bonfire.vo.TripPlanVo;
import com.jhta.bonfire.vo.geoJsonVo;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MapController {
    @Autowired private LocalMapService mapService;
    @Autowired private TripPlanService planService;
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @RequestMapping(value = {"/map"})
    public String tourData(
        @RequestParam Optional<Integer> routeidx
        ,Model model
    ){
        routeidx.ifPresent(idx->{
            // List<geoJsonVo> savedlist = planService.getPlanMapByIdx(idx);
            // JSONArray json = new JSONArray();
            // json.addAll(savedlist);
            model.addAttribute("idx", idx);
        });
        return "map/map";
    }

    @RequestMapping(value = {"/map/api/addmap"}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public HashMap<String, Object> addmap(
        @RequestBody geoJsonVo vo
    ) {
        logger.info("vo", vo);
        HashMap<String, Object> map = new HashMap<>();
        boolean result=true;
        try{
            mapService.addMap(vo);
            logger.info("saved : ", vo);
        } catch (DuplicateKeyException|NullPointerException e) {
            logger.info("samemap, skip "+e);
            result=!result;
        }
        map.put("success", result);
        return map;
    }

    @RequestMapping(value={"/map/api/addroutes"}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public HashMap<String, Object> addroutes(
        @RequestBody List<geoJsonVo> addedMarkers
        , @AuthenticationPrincipal Authentication authentication
    ) {
        HashMap<String, Object> map = new HashMap<>();
        if (authentication==null) {
            map.put("success", false);
            map.put("reason", "user not found");
            map.put("idx", null);
        } else {
            String id = authentication.getName();
            List<TripPlanVo> plans = new ArrayList<>();
            if (CommonUtil.isNotEmpty(addedMarkers)) {
                for (int i = 0; i < addedMarkers.size(); i++) {
                    String placeid = (String) addedMarkers.get(i).getProperties().get("id");
                    plans.add(new TripPlanVo("", id, placeid, i+""));
                }
                int idx = planService.addPlan(plans);
                map.put("success", true);
                map.put("idx", idx);
                map.put("reason", "green");
            } else {
                map.put("success", false);
                map.put("reason", "plan is empty");
                map.put("idx", null);
            }
        }
        return map;
    }

    @RequestMapping(value={"/map/api/myroutes"}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public JSONObject myroutes(
        @AuthenticationPrincipal Authentication authentication
    ) {
        Optional<String> id = Optional.ofNullable(authentication.getName());
        List<geoJsonVo> list=new ArrayList<>();
        List<Integer> listidx = new ArrayList<>();
        id.ifPresent(user->{
            list.addAll(planService.getPlanByUser(user));
            listidx.addAll(planService.getIdxByUser(user));
        });
        // JSONObject metadata = new JSONObject();
        ArrayList<HashMap<Object, Object>> metadata=new ArrayList<>();
        for (Integer idx : listidx) {
            HashMap<Object, Object> idxroutemap = new HashMap<>();
            StringBuilder sb = new StringBuilder();
            List<geoJsonVo> idxlist=new ArrayList<>();
            idxlist.addAll(planService.getPlanMapByIdx(idx));
            for (geoJsonVo vo : idxlist) {
                sb.append(vo.getProperties().get("place_name")+"->");
            }
            sb.delete(sb.length()-2, sb.length());
            idxroutemap.put("idx", idx);
            idxroutemap.put("route", sb.toString());
            metadata.add(idxroutemap);
        }
        JSONObject json = new JSONObject();
        json.put("features", list);
        json.put("metadata", metadata);

        return json;
    }

    @RequestMapping(value = {"/map/api/routes/{idx}"}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public List<geoJsonVo> getroutes(
        @PathVariable int idx
    ) {
        return planService.getPlanMapByIdx(idx);
    }

    @RequestMapping(value={"/map/api/delete/{idx}"}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public JSONObject deleteroute(
        @PathVariable int idx
        , @AuthenticationPrincipal Authentication authentication
    ) {
        JSONObject json = new JSONObject();
        Optional<String> username = Optional.ofNullable(authentication.getName());
        username.ifPresent(id->{
            int result = planService.removePlan(idx, id);
            json.put("success", (result>0)?true:false);
            json.put("result", result);
            json.put("idx", idx);
        });
        return json;
    }
}
