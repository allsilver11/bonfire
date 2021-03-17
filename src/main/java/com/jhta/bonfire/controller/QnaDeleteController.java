package com.jhta.bonfire.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jhta.bonfire.service.QnaBoardService;
import com.jhta.bonfire.vo.QnaBoardVo;

@Controller
public class QnaDeleteController {
	@Autowired QnaBoardService service;
	
	@GetMapping(value = "/qna/delete")
	public String deletech(int num,String pwd,Model model,HttpSession session) {
		System.out.println(num);
		
		model.addAttribute("num", num);//넘값받아오기
		//model.addAttribute("pwd",pwd);
		
		String id=(String)session.getAttribute("id");
		
		QnaBoardVo vo=service.select(num);
		if(vo.getPwd()==null || id.equals("admin")) {
			
			service.delete(num);
			
			return "redirect:/qna/main";
		}else {
			return ".home.qna.pwdch";
		}
	}
	
	@PostMapping(value = "qna/delete")
	public String delete(int num,String pwd,Model model) {
		System.out.println(num);
		QnaBoardVo vo=service.select(num);
		String pwd2=vo.getPwd();
		
		if(pwd.equals(pwd2)) {
			service.delete(num);
			return "redirect:/qna/main";
		}else {
			model.addAttribute("num",num);
			return ".home.qna.pwdch";
		}
	}
}

