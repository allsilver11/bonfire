package com.jhta.bonfire.dao;

import com.jhta.bonfire.vo.LocalMapVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LocalMapDao {
    @Autowired private SqlSession sqlSession;
    private static final String NAMESPACE="com.jhta.bonfire.mapper.LocalMapMapper";

    public int addMap(LocalMapVo vo){return sqlSession.insert(NAMESPACE+".addMap", vo);}
    public LocalMapVo getById(String propertiesid){return sqlSession.selectOne(NAMESPACE+".getById", propertiesid);}
}
