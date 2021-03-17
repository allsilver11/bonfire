package com.jhta.bonfire.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jhta.bonfire.service.FeedboardService;
import com.jhta.bonfire.util.PageUtil;
import com.jhta.bonfire.vo.FeedboardJoinScrapboardVo;
import com.jhta.bonfire.vo.FeedboardVo;

@Controller
public class ScrapboardController {
	@Autowired FeedboardService service;
	@Autowired private ServletContext sc;
	
	// 글목록
		@RequestMapping("/scrapboard_feed_scraplist")
		public String showScrapList(@RequestParam(value = "page",defaultValue = "1")int page,String field,String keyword,Model model,HttpSession session) {
				HashMap<String,Object> map=new HashMap<String, Object>();
				String feedId=(String)session.getAttribute("feedId");
				map.put("id", feedId);
				map.put("field", field);
				map.put("keyword",keyword);
				
				int listCount=service.countScrap(map);
				PageUtil pu=new PageUtil(page, 10, 10, listCount);
				int startRow=pu.getStartRow();
				int endRow=pu.getEndRow();
				map.put("startRow",startRow);
				map.put("endRow",endRow);
				List<FeedboardJoinScrapboardVo> list=service.showScrapList(map);
				model.addAttribute("list",list);
				model.addAttribute("pu",pu);
				model.addAttribute("field", field);
				model.addAttribute("keyword",keyword);
				return ".feed.scrapboard.scrap_list";
		}
		
	//글 상세페이지
		@GetMapping("/scrapboard_detail")
		public String selectOne(HttpSession session,String from,int num,Model model,String recentpage) {
			FeedboardVo vo=service.selectOne(num);
			model.addAttribute("vo",vo);
			model.addAttribute("from",from);
	        	return ".home.travelersboard.detail_feed";
		}
		
	//스크랩 글 삭제
		@PostMapping("/scrapboard_delete")
		public String deleteScrap(Model model, HttpServletRequest req,HttpSession session) {
			String[] params=req.getParameterValues("checkk");
			int n=0;
			for(String i : params) {
				String id=(String)session.getAttribute("id");
				int num= Integer.parseInt(i);
				n=service.deleteScrap(num,id);
			}
			if(n>0) {
				return "redirect:/scrapboard_feed_scraplist";
			}else {
				return ".home.error";
			}
		}

		
}
