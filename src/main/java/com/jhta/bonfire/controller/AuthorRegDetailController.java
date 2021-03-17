package com.jhta.bonfire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jhta.bonfire.service.AuthorRegService;
import com.jhta.bonfire.vo.AuthorReg_Auth_JoinVo;

@Controller
public class AuthorRegDetailController {
	@Autowired private AuthorRegService service;
	
	@GetMapping("/authorreg/detail")
	public ModelAndView detail(int num) {
		ModelAndView mv = new ModelAndView(".feed.adminpage.authorRegDetail");
		AuthorReg_Auth_JoinVo vo = service.listOne(num);
		mv.addObject("vo", vo);
		
		return mv;
	}
}
