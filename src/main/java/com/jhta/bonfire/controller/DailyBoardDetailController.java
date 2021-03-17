package com.jhta.bonfire.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jhta.bonfire.service.DailyBoardService;
import com.jhta.bonfire.vo.DailyBoardVo;

@Controller
public class DailyBoardDetailController {
	@Autowired private DailyBoardService service;
	
	@GetMapping("/daily/detail")
	public ModelAndView detail(int num, HttpSession session) {
		ModelAndView mv = new ModelAndView(".feed.daily.detail");
		String id = (String) session.getAttribute("id");
		DailyBoardVo vo = service.listOne(num);
		mv.addObject("vo", vo);
		
		return mv;
	}
}
