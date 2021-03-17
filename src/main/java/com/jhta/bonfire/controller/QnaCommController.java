package com.jhta.bonfire.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jhta.bonfire.service.QnaBoardService;
import com.jhta.bonfire.vo.QnaBoardVo;


@RestController
public class QnaCommController {
	@Autowired
	private QnaBoardService service;
	
	@GetMapping(value = "/qna/comm")
	public String updateForm(int num,Model model) {
		QnaBoardVo vo=service.select(num);
		model.addAttribute("vo", vo);
		return ".home.qna.detail";
	}
	@GetMapping(value = "/qna/commm",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public HashMap<String, Object> update(int commnum,String comments) {
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put("num",commnum);
		hm.put("comments",comments);

		
		System.out.println(commnum);
		int n=service.updatecomm(hm);
		HashMap<String, Object> map =new HashMap<String, Object>();
        if(n>0) {
            map.put("code","success");
        }else {
            map.put("code","fail");
        }
        return map;
		
	}
	@RequestMapping(value = "/qna/commlist",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public QnaBoardVo selectComm(int commnum) {
		QnaBoardVo vo=service.select(commnum);
		
		//상세페이지에 줄바꿈하게하는거임!
		String comments=vo.getComments();
		comments=comments.replaceAll("\n", "<br>");
		vo.setComments(comments);
		
		return vo;
	}
}
