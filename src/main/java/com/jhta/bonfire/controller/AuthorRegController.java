	package com.jhta.bonfire.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jhta.bonfire.service.AuthorRegService;
import com.jhta.bonfire.vo.PageUtil;
import com.jhta.bonfire.vo.AuthorReg_Auth_JoinVo;

@Controller
public class AuthorRegController {
	@Autowired private AuthorRegService service;
	
	@RequestMapping(value = "/authorRegList")
	public String list(@RequestParam(name = "pageNum", defaultValue = "1")int pageNum, String field, String keyword, Model model, HttpSession session) {
		String id = (String) session.getAttribute("id");
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("field", field);
		map.put("keyword", keyword);
		
		int totalRowCount = service.count(map);
		PageUtil pu = new PageUtil(pageNum, 10, 10, totalRowCount);
		int startRow = pu.getStartRow();
		int endRow = pu.getEndRow();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		
		List<AuthorReg_Auth_JoinVo> list = service.listAll(map);
		model.addAttribute("list", list);
		model.addAttribute("pu", pu);
		model.addAttribute("field", field);
		model.addAttribute("keyword", keyword);
		
		return ".feed.adminpage.adminside.authorRegList";
	}
}
