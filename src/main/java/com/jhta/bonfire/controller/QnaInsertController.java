package com.jhta.bonfire.controller;


import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jhta.bonfire.service.QnaBoardService;
import com.jhta.bonfire.util.CommonUtil;
import com.jhta.bonfire.vo.QnaBoardVo;

@Controller
public class QnaInsertController {
	@Autowired private QnaBoardService service;
	@Autowired private ServletContext sc;
	
	public void setService(QnaBoardService service) {
		this.service = service;
	}
	@RequestMapping(value = "qna/insert",method = RequestMethod.GET)
	public String insertForm() {
		return ".home.qna.insert";
	}
	@RequestMapping(value = "qna/insert",method = RequestMethod.POST)
	public String insert(
			@RequestParam( value= "pin1", defaultValue = "off") String pin1,
			@RequestParam( value= "secret1", defaultValue = "off") String secret1,
			QnaBoardVo vo,
			@RequestParam(required = false) String... fileName
			) {

		vo.setContent(CommonUtil.changePath(sc, vo.getContent(), "qna", fileName));
		System.out.println(vo.getPin());

		if(pin1.equals("on")) { 
			vo.setPin(1);
					}
		if(secret1.equals("on")) { 
			vo.setSecret(1);
					}
		
		
		int n=service.insert(vo);
		if(n>0) {
			return "redirect:/qna/main";
		}else {
			return ".home.qna.insert";
		}
	}
	
}
