package com.jhta.bonfire.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import com.jhta.bonfire.service.SubBoardService;
import com.jhta.bonfire.util.CommonUtil;
import com.jhta.bonfire.util.PageUtil;
import com.jhta.bonfire.vo.SCommDetail;
import com.jhta.bonfire.vo.SCommentVo;
import com.jhta.bonfire.vo.SRecommVo;
import com.jhta.bonfire.vo.SbhitsVo;
import com.jhta.bonfire.vo.SubBoardVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SubBoardController {
    @Autowired private SubBoardService service;
    @Autowired private ServletContext sc;
    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = { "/board/{cname}/list/{page}", "/board/{cname}/list/" })
    public String getList(@PathVariable Optional<Integer> page, @PathVariable String cname, Model model,
            @RequestParam(required = false, defaultValue = "10") int listSize,
            @RequestParam(required = false, defaultValue = "10") int pageSize, String keyword, String... field) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("cname", cname.equals("all") ? null : cname);
        if (CommonUtil.isNotEmpty(keyword, field)) {
            map.put("fields", field);
            map.put("keyword", keyword);
        }
        // ---count용 검색 제한 설정---
        // map.put("startRow", pu.getStartRow());
        // map.put("endRow", pu.getEndRow());

        PageUtil pu = new PageUtil(page.orElse(1), listSize, pageSize, service.count(map));
        map.put("startRow", pu.getStartRow());
        map.put("endRow", pu.getEndRow());
        logger.debug("keyword : " + keyword);

        model.addAttribute("page", page.orElse(1));
        model.addAttribute("fields", field);
        model.addAttribute("keyword", keyword);
        model.addAttribute("list", service.getList(map));
        model.addAttribute("pu", pu);
        model.addAttribute("catlist", service.catList("subscriberboard"));
        String beforeparams = "?";

        if (CommonUtil.isNotEmpty((Object[]) field)) {
            for (String f : field) {
                beforeparams += "field=" + f + "&";
            }
        }
        beforeparams += "keyword=" + keyword + "&listSize=" + listSize + "&pageSize=" + pageSize;
        model.addAttribute("beforeparams", beforeparams);
        return ".board.subboard";
    }

    @GetMapping(value = { "/member/write/{cname}" })
    public String write(@PathVariable String cname, Model model) {
        model.addAttribute("cname", cname);
        model.addAttribute("boardName", "subboard");
        model.addAttribute("catlist", service.catList("subscriberboard"));
        return ".board.subboardwrite";
    }

    @PostMapping(value = { "/member/write" })
    public String write(@RequestParam String cname, @AuthenticationPrincipal Authentication authentication, Model model,
            @RequestParam String title, @RequestParam String content, @RequestParam String boardName,
            @RequestParam(required = false) String... fileName) throws UnsupportedEncodingException 
    {
        String id = authentication.getName();
        content = CommonUtil.changePath(sc, content, boardName, fileName);
        SubBoardVo vo = new SubBoardVo(0, id, title, content, null, 0, 0, cname);
        service.write(vo);
        return "redirect:/board/"+URLEncoder.encode(cname, "UTF-8")+"/list/";
    }

    @RequestMapping(value={"/board/{cname}/article/{num}"})
    public String getData(
        Model model, 
        @PathVariable Optional<Integer> page, 
        @PathVariable String cname, 
        @PathVariable int num, 
        @AuthenticationPrincipal Authentication authentication
    ){
        /* 조회수 ++ */
        if (authentication!=null) {
            try{
                service.addHit(new SbhitsVo(num, authentication.getName()));
            } catch (DuplicateKeyException|NullPointerException e) {logger.info("sameuser: "+e);}
        }
        model.addAttribute("cname", cname);
        model.addAttribute("vo", service.getData(num));
        model.addAttribute("catlist", service.catList("subscriberboard"));
        return ".board.subboardview";
    }

    

    /**
     * @param tglrecomm not null이면 추천/비추천이 토글 형식으로 전환된다.
     */
    @RequestMapping(value={"/board/{cname}/article/{num}/recomm"}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public SRecommVo recomm(
        @PathVariable String cname
        , @PathVariable int num
        , @RequestParam(required = false) String tglrecomm
        , @AuthenticationPrincipal Authentication authentication
    ){
        SRecommVo vo = new SRecommVo(num, null, 0, null);
        if (authentication!=null) {
            vo.setId(authentication.getName());
            vo.setValue(service.isRecommed(vo));
            if (CommonUtil.isNotEmpty(tglrecomm)) {
                vo.setValue((vo.getValue()==1)?-1:1);
                service.setRecomm(vo);
            }
        }
        return vo;
    }

    @RequestMapping(value={"/board/{cname}/article/{num}/comment"}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public List<SCommDetail> getComments(
        @PathVariable String cname
        , @PathVariable int num
    ){
        List<SCommDetail> commlist = service.getComment(num);
        return commlist;
    }
    
    @RequestMapping(value={"/board/{cname}/article/{num}/commentwrite"}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public HashMap<String, Object> addComment(
        @PathVariable String cname
        , @PathVariable int num
        , @AuthenticationPrincipal Authentication authentication
        , @RequestParam String content
    ) {
        HashMap<String, Object> map = new HashMap<>();
        // Optional<Integer> maxidx = Optional.ofNullable(service.getMax(num));
        // int idx = maxidx.orElse(0);
        int idx = service.getMax(num);
        int result=0;
        if (authentication!=null){
            result = service.addComment(new SCommentVo(idx, num, authentication.getName(), content, null));
        }

        map.put("idx", idx);
        map.put("success", (result==1)? true:false);
        return map;
    }

}
