package com.jhta.bonfire.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jhta.bonfire.service.QnaBoardService;
import com.jhta.bonfire.vo.QhitsVo;
import com.jhta.bonfire.vo.QnaBoardVo;


@Controller
public class QnaDetailController {
	@Autowired
	private QnaBoardService service;
	
	@GetMapping("/qna/detail")
	public ModelAndView detail(int num, QhitsVo vo,HttpSession session) {
		ModelAndView mv=new ModelAndView(".home.qna.detail");
		System.out.println(num);
		System.out.println(vo.getId());
		String id=(String)session.getAttribute("id");
		
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put("id",id);
		hm.put("num",num);
		HashMap<String, Object> map=service.selecthit(hm);
		
		if(map==null) {
			service.qhits(vo, num);
		}
		QnaBoardVo vo1=service.select(num);

		String content=vo1.getContent();
		content=content.replaceAll("\n", "<br>");
		vo1.setContent(content);
		
		mv.addObject("vo", vo1);

		return mv;
	}
}
