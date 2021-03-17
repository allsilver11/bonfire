package com.jhta.bonfire.dao;

import com.jhta.bonfire.vo.SRecommVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SRecommDao {
    @Autowired private SqlSession sqlSession;
    private static final String NAMESPACE = "com.jhta.bonfire.mapper.SRecommMapper";

    public int setRecomm(SRecommVo vo) {return sqlSession.insert(NAMESPACE+".setRecomm", vo); }
    // public int addRecomm(SRecommVo vo) {return sqlSession.insert(NAMESPACE+".addRecomm", vo); }
    // public int rmvRecomm(SRecommVo vo) {return sqlSession.insert(NAMESPACE+".rmvRecomm", vo); }
    public int isRecommed(SRecommVo vo) {return sqlSession.selectOne(NAMESPACE+".isRecommed", vo);}
}
