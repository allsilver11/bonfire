package com.jhta.bonfire.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jhta.bonfire.service.DailyBoardService;
import com.jhta.bonfire.util.CommonUtil;
import com.jhta.bonfire.vo.DailyBoardVo;
import com.jhta.bonfire.vo.DailyBoard_Dfile_JoinVo;

@Controller
public class DailyBoardInsertController {
	@Autowired private DailyBoardService service;
	@Autowired private ServletContext sc;
	
	public void setService(DailyBoardService service) {
		this.service = service;
	}
	
	@RequestMapping (value = "daily/insert", method = RequestMethod.GET)
	public String insertForm() {
		return ".feed.daily.write";
	}
	
	@RequestMapping(value = "daily/insert", method = RequestMethod.POST)
	public String insertPost(DailyBoard_Dfile_JoinVo vo, @RequestParam(required = false) String... fileName) {
		vo.setContent(CommonUtil.changePath(sc, vo.getContent(), "dailyboard", fileName));
		int n = service.insert(vo);
		
		if(n>0) {
			return "redirect:/daily_main";
		} else {
			return ".feed.daily.main";
		}
		
	}
}
