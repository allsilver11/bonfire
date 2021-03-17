package com.jhta.bonfire.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jhta.bonfire.service.DailyBoardService;
import com.jhta.bonfire.vo.DailyBoardVo;
import com.jhta.bonfire.vo.DailyBoard_Dfile_JoinVo;

@Controller
public class DailyDeleteController {
	@Autowired DailyBoardService service;
	
	@PostMapping(value = "/daily/delete")
	public String delete(Model model, HttpSession session, HttpServletRequest req) {
		String[] params = req.getParameterValues("checkk");
		int n = 0;
		for (String i : params) {
			String id = (String) session.getAttribute("id");
			int num = Integer.parseInt(i);
			n = service.delete(num, id);
		}
		
		if(n>0) {
			return "redirect:/daily_main";
		} else {
			return ".home.error";
		}
	}
}
