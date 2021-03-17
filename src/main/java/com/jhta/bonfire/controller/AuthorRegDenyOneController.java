package com.jhta.bonfire.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.jhta.bonfire.service.AuthorRegService;

@Controller
public class AuthorRegDenyOneController {
	@Autowired AuthorRegService service;
	
	@PostMapping(value = "/author/denyOne")
	public String approveOne(Model model, HttpServletRequest req) {
		int num = Integer.parseInt(req.getParameter("num"));
		int n = service.denyOne(num);
		
		if(n>0) {
			return "redirect:/authorRegList";
		} else {
			return ".home.error";
		}
	}
}
