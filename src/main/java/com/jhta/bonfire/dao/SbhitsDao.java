package com.jhta.bonfire.dao;

import com.jhta.bonfire.vo.SbhitsVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SbhitsDao {
    @Autowired private SqlSession sqlSession;
    private static final String NAMESPACE = "com.jhta.bonfire.mapper.SbhitsMapper";
    public int count(int num) {return sqlSession.selectOne(NAMESPACE+".count", num);}
    public int addHit(SbhitsVo vo) {return sqlSession.insert(NAMESPACE+".addhit", vo);}

}