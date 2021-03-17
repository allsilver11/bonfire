package com.jhta.bonfire.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CatsDao {
    @Autowired private SqlSession sqlSession;
    private static final String NAMESPACE="com.jhta.bonfire.mapper.CatsMapper";
    public List<String> catList(String cwhichboard) {return sqlSession.selectList(NAMESPACE+".catList", cwhichboard);}
}
