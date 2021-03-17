package com.jhta.bonfire.controller;


import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jhta.bonfire.service.FeedService;
import com.jhta.bonfire.service.MProfileService;
import com.jhta.bonfire.util.CommonUtil;
import com.jhta.bonfire.vo.MProfileVo;
@Controller
public class FeedController {
	@Autowired FeedService service;
	@Autowired MProfileService proService;
	@GetMapping(value="/@{feedId}")
	public String feed(@PathVariable("feedId") String feedId,HttpSession session,Model model) {
		String id=(String)session.getAttribute("id");
		HashMap<String, Object> map=service.feedCheck(feedId);
		String feedRole=service.roleCheck(feedId);
		MProfileVo proVo=proService.select(feedId);
		Boolean pageBlock=CommonUtil.pageBlock(id, feedId, feedRole, map);
		if(pageBlock) {
			model.addAttribute("errorMsg","접근 불가");
			return ".home";
		}else {
			session.setAttribute("feedId", feedId);
			session.setAttribute("feedRole", feedRole);
//			model.addAttribute("feedId",feedId);
//			model.addAttribute("feedRole",feedRole);
			session.setAttribute("proVo",proVo);
			return "redirect:/feedboard_feed_selectAllbyId?id="+feedId;
		}
	}
}
