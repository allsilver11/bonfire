package com.jhta.bonfire.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jhta.bonfire.service.QnaBoardService;
import com.jhta.bonfire.util.CommonUtil;
import com.jhta.bonfire.vo.QnaBoardVo;

@Controller
public class QnaUpdateController {
	@Autowired
	private QnaBoardService service;
	@Autowired private ServletContext sc;
	
	@GetMapping(value = "/qna/update")
	public String updateForm(int num,Model model) {
		QnaBoardVo vo=service.select(num);
		model.addAttribute("vo", vo);
		return ".home.qna.update";
	}
	@PostMapping(value = "/qna/update")
	public String update(QnaBoardVo vo,
			@RequestParam(required = false) String... fileName) {
		
		vo.setContent(CommonUtil.changePath(sc, vo.getContent(), "qna", fileName));
		
		int n=service.update(vo);
		if(n>0) {
			return "redirect:/qna/main";
		}else {
			return ".home.qna.update";
		}

	}
}
