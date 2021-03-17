package com.jhta.bonfire.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jhta.bonfire.service.DailyBoardService;
import com.jhta.bonfire.vo.DailyBoardVo;
import com.jhta.bonfire.vo.PageUtil;

@Controller
public class DailyBoardController {
	@Autowired private DailyBoardService service;
	
	//일기장 목록
	@RequestMapping(value = "/daily_main")
	public String list(@RequestParam(name = "pageNum", defaultValue = "1")int pageNum, String field, String keyword, Model model, HttpSession session) {
		String id = (String)session.getAttribute("id");
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("field", field);
		map.put("keyword", keyword);
		map.put("id", id);
		
		int totalRowCount = service.count(map);
		PageUtil pu = new PageUtil(pageNum, 10, 10, totalRowCount);
		int startRow = pu.getStartRow();
		int endRow = pu.getEndRow();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		
		List<DailyBoardVo> list = service.listPost(map);
		model.addAttribute("list", list);
		model.addAttribute("pu", pu);
		model.addAttribute("field", field);
		model.addAttribute("keyword", keyword);
		model.addAttribute("id", id);
		return ".feed.daily.main";
	}
	
}
