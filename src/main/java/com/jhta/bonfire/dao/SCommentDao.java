package com.jhta.bonfire.dao;

import java.util.List;

import com.jhta.bonfire.vo.SCommDetail;
import com.jhta.bonfire.vo.SCommentVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SCommentDao {
    @Autowired private SqlSession sqlSession;
    private static final String NAMESPACE = "com.jhta.bonfire.mapper.SCommentMapper";
    public List<SCommDetail> getComment(int num) {return sqlSession.selectList(NAMESPACE+".getComment", num);}
    public Integer getMin(int num) {return sqlSession.selectOne(NAMESPACE+".getMin", num);}
    public Integer getMax(int num) {return sqlSession.selectOne(NAMESPACE+".getMax", num);}
    public int addComment(SCommentVo vo) {return sqlSession.insert(NAMESPACE+".addComment", vo);}
    public int editComment(SCommentVo vo) {return sqlSession.update(NAMESPACE+".editComment", vo);}
}
