package com.jhta.bonfire.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jhta.bonfire.service.QnaBoardService;
import com.jhta.bonfire.vo.PageUtil;
import com.jhta.bonfire.vo.QnaBoardVo;


@Controller
public class QnaListController {
		@Autowired private QnaBoardService service;

		@RequestMapping(value="/qna/main")
		public String list(@RequestParam(name="pageNum",defaultValue = "1")int pageNum,String field,String keyword,Model model) {
			HashMap<String, Object> map=new HashMap<String, Object>();
			map.put("field",field);
			map.put("keyword",keyword);
				   
			int totalRowCount=service.count(map);
			PageUtil pu=new PageUtil(pageNum, 10, 10, totalRowCount);
			int startRow=pu.getStartRow();
			int endRow=pu.getEndRow();
			map.put("startRow",startRow);
			map.put("endRow",endRow);
			      
			List<QnaBoardVo> list=service.list(map);
			model.addAttribute("list", list);
			model.addAttribute("pu", pu);
			model.addAttribute("field", field);
			model.addAttribute("keyword", keyword);
			return ".home.qna.main";
			
		/*
		  ModelAndView mv=new ModelAndView(".home.qna.main");
			mv.addObject("list", list); 
			mv.addObject("pu", pu); 
			mv.addObject("field", field);
		 mv.addObject("keyword", keyword); 
		 return mv;
		 */
	}
}			