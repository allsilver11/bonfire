package com.jhta.bonfire.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jhta.bonfire.service.BMembersService;
import com.jhta.bonfire.vo.BMembersVo;

@Controller
public class BMembersController {
	@Autowired
	private BMembersService service;
	
	@GetMapping("/userModify")
	public String modifyForm(HttpSession session,Model model) {
		String id=(String)session.getAttribute("id");
		BMembersVo vo=service.userInfo(id);
		model.addAttribute("vo",vo);
		return ".home.login.modify";
	}
	@PostMapping("/userModify")
	public String modify(BMembersVo vo) {
		service.update(vo);
		return "redirect:/userInfo";
	}
	@GetMapping("/userInfo")
	public String info(HttpSession session,Model model) {
		String id=(String)session.getAttribute("id");
		BMembersVo vo=service.userInfo(id);
		model.addAttribute("vo",vo);
		return ".feed.mypage.info";
	}
	@GetMapping("/editPwd")
	public String editPwdForm(String searchId,Model model) {
		model.addAttribute("searchId",searchId);
		return ".home.login.editPwd";
	}
	@PostMapping("/editPwd")
	public String editPwd(String id,String rawPwd,String pwd,HttpSession session,Model model) {
		String sessionId=(String)session.getAttribute("id");
		boolean b=false;
		if(sessionId!=null) {
			b=service.pwdMatch(sessionId, rawPwd);
		}else {
			service.updatePwd(id, pwd);
			return ".home.login.login";
		}
		if(b) {
			service.updatePwd(sessionId, pwd);
			return "redirect:/logout";
		}else {
			model.addAttribute("error","1");
			return ".home.login.editPwd";
		}
	}
	@PostMapping("/manage_deleteList")
	public String delete(Model model, HttpServletRequest req) {
		String[] params=req.getParameterValues("checkk");
		int n=0;
		for(String i : params) {
			n=service.delete(i);
		}
		if(n>0) {
			return ".feed.adminpage.adminside.manageMembers";
		}else {
			return ".home.error";
		}
	}
}
