package com.jhta.bonfire.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhta.bonfire.service.HomeService;
import com.jhta.bonfire.vo.PopularArticleHitsVo;
import com.jhta.bonfire.vo.PopularArticleRecommVo;
import com.jhta.bonfire.vo.PopularPlaceVo;
import com.jhta.bonfire.vo.RecommAuthorVo;
import com.jhta.bonfire.vo.SearchAuthorVo;

@Controller
public class HomeController {
	@Autowired
	private HomeService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest req) {
//		String cp=req.getContextPath();
//		req.getSession().getServletContext().setAttribute("cp", cp);
		return ".home";
	}
	@GetMapping(value="/searchAuthor",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public HashMap<String, Object> searchAuthor(
			@RequestParam(value="slideNum", defaultValue = "1") int slideNum,
			@RequestParam(value="keyword", defaultValue = "")String keyword){
		HashMap<String , Object> resultMap=new HashMap<String, Object>();
		List<SearchAuthorVo> list=new ArrayList<SearchAuthorVo>();
		HashMap<String , Object> map=new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("slideNum",slideNum); //현재 페이지번호
		int Cnt=service.searchAuthorCnt(map);
		if(Cnt==0) {
			resultMap.put("Cnt", Cnt);
			return resultMap;
		}else if(Cnt==1) {
			SearchAuthorVo vo=service.searchAuthor(map);
			resultMap.put("vo", vo);
			resultMap.put("Cnt", Cnt);
			return resultMap;
		}else {
			if(slideNum==1) {
				//num==1번 작가
				list.add(service.searchAuthor(map));
				//num==2번 작가
				map.put("slideNum",slideNum+1);
				list.add(service.searchAuthor(map));
				resultMap.put("list", list);
				resultMap.put("slideNum", slideNum);//몇번째 작가를 보고있는지 (paging의 현재페이지와 같음)
				resultMap.put("Cnt", Cnt);// 총 작가 수
				return resultMap;
			}else if(slideNum==Cnt) {
				//num==Cnt-1번 작가
				map.put("slideNum",slideNum-1);
				list.add(service.searchAuthor(map));
				//num==Cnt번 작가
				map.put("slideNum",slideNum);
				list.add(service.searchAuthor(map));
				resultMap.put("list", list);
				resultMap.put("slideNum", slideNum);//몇번째 작가를 보고있는지 (paging의 현재페이지와 같음)
				resultMap.put("Cnt", Cnt);// 총 작가 수
				return resultMap;
			}else {
				//num==num-1번 작가
				map.put("slideNum",slideNum-1);
				list.add(service.searchAuthor(map));
				//num==num번 작가
				map.put("slideNum",slideNum);
				list.add(service.searchAuthor(map));
				//num==num+1번 작가
				map.put("slideNum",slideNum+1);
				list.add(service.searchAuthor(map));
				resultMap.put("list", list);
				resultMap.put("slideNum", slideNum);//몇번째 작가를 보고있는지 (paging의 현재페이지와 같음)
				resultMap.put("Cnt", Cnt);// 총 작가 수
				return resultMap;
			}
		}
	}
	@GetMapping(value="/recommAuthor",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public List<RecommAuthorVo> recommAuthor(){
		List<RecommAuthorVo> list=new ArrayList<RecommAuthorVo>();
		int Cnt=service.authorCnt();
		int viewCnt=12;
		if(Cnt<12) {
			viewCnt=1;
		}
		boolean matchCnt=false;
		int[] selectAuthors=new int[viewCnt];
		for(int i=0;i<viewCnt;i++) {
			matchCnt=false;
			int num=(int)(Math.random()*Cnt)+1;
			for(int j=0;j<viewCnt;j++) {
				if(selectAuthors[j]==num) matchCnt=true;
			}
			if(matchCnt) {
				i--;
				continue;
			}
			selectAuthors[i]=num;
		}
		for(int selectAuthor:selectAuthors) {
			list.add(service.select(selectAuthor));
		}
		return list;
	}
	@GetMapping(value="/popularArticle_hits",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public List<PopularArticleHitsVo> popularArticle_hits(){
		List<PopularArticleHitsVo> list=service.popularArticle_hits();
		return list;
	}
	@GetMapping(value="/popularArticle_recomm",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public List<PopularArticleRecommVo> popularArticle_recomm(){
		List<PopularArticleRecommVo> list=service.popularArticle_recomm();
		return list;
	}
	@GetMapping(value="/popularPlace",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public List<PopularPlaceVo> popularPlace(){
		List<PopularPlaceVo> list=service.popularPlace();
		return list;
	}
}
