package com.jhta.bonfire.service;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhta.bonfire.dao.HomeDao;
import com.jhta.bonfire.vo.PopularArticleHitsVo;
import com.jhta.bonfire.vo.PopularArticleRecommVo;
import com.jhta.bonfire.vo.PopularPlaceVo;
import com.jhta.bonfire.vo.RecommAuthorVo;
import com.jhta.bonfire.vo.SearchAuthorVo;

@Service
public class HomeService {
	@Autowired
	private HomeDao dao;
	
	public int authorCnt() {
		return dao.authorCnt();
	}
	public RecommAuthorVo select(int selectAutor){
		return dao.select(selectAutor);
	}
	public List<PopularArticleHitsVo> popularArticle_hits(){
		return dao.popularArticle_hits();
	}
	public List<PopularArticleRecommVo> popularArticle_recomm(){
		return dao.popularArticle_recomm();
	}
	public List<PopularPlaceVo> popularPlace(){
		return dao.popularPlace();
	}
	public int searchAuthorCnt(HashMap<String, Object> map) {
		return dao.searchAuthorCnt(map);
	}
	public SearchAuthorVo searchAuthor(HashMap<String, Object> map) {
		return dao.searchAuthor(map);
	}
}
