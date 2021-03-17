package com.jhta.bonfire.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jhta.bonfire.service.BMembersService;
import com.jhta.bonfire.service.ManageMembersService;
import com.jhta.bonfire.util.PageUtil;
import com.jhta.bonfire.vo.ManageMembersVo;

@Controller
public class ManageMembersController {
	@Autowired
	private ManageMembersService service;
	@RequestMapping("/manageMembers_List_All")
	public String manameMembers_List_All(@RequestParam(value="pageNum",defaultValue="1") int pageNum,
			@RequestParam(value="field",defaultValue="") String field,
			@RequestParam(value="keyword",defaultValue="") String keyword,Model model) {
		HashMap<String,Object> map=new HashMap<String, Object>();
		if(field.equals("authority")) {
			keyword=keyword.toUpperCase();
		}
		map.put("field",field);
		map.put("keyword",keyword);
		int totalRowCount=service.count(map);
		PageUtil pu=new PageUtil(pageNum, 20, 10, totalRowCount);
		int startRow=pu.getStartRow();
		int endRow=pu.getEndRow();
		
		map.put("startRow", startRow);
		map.put("endRow",endRow);
		
		ArrayList<ManageMembersVo> list=(ArrayList<ManageMembersVo>)service.selectAll(map);		
		model.addAttribute("list",list);
		model.addAttribute("pu",pu);
		model.addAttribute("field",field);
		model.addAttribute("keyword",keyword);
		
		return ".feed.adminpage.adminside.manageMembers";
	}
	@GetMapping("/manageMembers_detail")
	public String manameMembers_List_All(String id,Model model) {
		ManageMembersVo vo=service.selectOne(id);
		model.addAttribute("vo",vo);
		return ".feed.adminpage.adminside.memberinfo";
	}
}
