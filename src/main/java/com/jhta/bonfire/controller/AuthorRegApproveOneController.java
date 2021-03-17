package com.jhta.bonfire.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.jhta.bonfire.service.AuthorRegService;
import com.jhta.bonfire.vo.AuthorReg_Auth_JoinVo;

@Controller
public class AuthorRegApproveOneController {
	@Autowired AuthorRegService service;
	
	@PostMapping(value = "/author/approveOne")
	public String approveOne(Model model, HttpServletRequest req) {
		int num = Integer.parseInt(req.getParameter("num"));
		String id = service.listOne(num).getId();
		
		int n = service.approveOne(id);
		
		if(n>0) {
			return "redirect:/authorRegList";
		} else {
			return ".home.error";
		}
	}
}
