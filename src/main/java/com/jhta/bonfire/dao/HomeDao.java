package com.jhta.bonfire.dao;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jhta.bonfire.vo.PopularArticleHitsVo;
import com.jhta.bonfire.vo.PopularArticleRecommVo;
import com.jhta.bonfire.vo.PopularPlaceVo;
import com.jhta.bonfire.vo.RecommAuthorVo;
import com.jhta.bonfire.vo.SearchAuthorVo;


@Repository
public class HomeDao {
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE="com.jhta.bonfire.mapper.HomeMapper";
	
	public int authorCnt() {
		return sqlSession.selectOne(NAMESPACE+".authorCnt");
	}
	public RecommAuthorVo select(int selectAutor){
		return sqlSession.selectOne(NAMESPACE+".select",selectAutor);
	}
	public List<PopularArticleHitsVo> popularArticle_hits(){
		return sqlSession.selectList(NAMESPACE+".popularArticle_hits");
	}
	public List<PopularArticleRecommVo> popularArticle_recomm(){
		return sqlSession.selectList(NAMESPACE+".popularArticle_recomm");
	}
	public List<PopularPlaceVo> popularPlace(){
		return sqlSession.selectList(NAMESPACE+".popularPlace");
	}
	public int searchAuthorCnt(HashMap<String, Object> map) {
		return sqlSession.selectOne(NAMESPACE+".searchAuthorCnt",map);
	}
	public SearchAuthorVo searchAuthor(HashMap<String, Object> map) {
		return sqlSession.selectOne(NAMESPACE+".searchAuthor",map);
	}
}
