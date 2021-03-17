package com.jhta.bonfire.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jhta.bonfire.service.QnaBoardService;
import com.jhta.bonfire.vo.QhitsVo;
import com.jhta.bonfire.vo.QnaBoardVo;

@Controller
public class QnaSecretPwdController {
	@Autowired QnaBoardService service;
	
	@GetMapping(value = "/qna/secretpwd")
	public String secretpwdch(int num,Model model,HttpSession session) {
		model.addAttribute("num",num);

		
		String id=(String)session.getAttribute("id");
		
		 if(id.equals("admin")) { 
			// QhitsVo vo=new QhitsVo(num, id);
			 model.addAttribute("id",id);
			 return "redirect:/qna/detail"; 
			 }
		 
		
		return ".home.qna.secretpwd";
	}
	@PostMapping(value = "/qna/secretpwd")
	public String secretpwd(int num,String pwd,Model model,HttpSession session) {
		QnaBoardVo vo=service.select(num);
		String pwd2=vo.getPwd();
		String id=(String)session.getAttribute("id");
		if(pwd.equals(pwd2)) {
			model.addAttribute("num",num);
			 model.addAttribute("id",id);
			return "redirect:/qna/detail";
		}else {
			model.addAttribute("num",num);
			return ".home.qna.secretpwd";
		}
		
	}
}
