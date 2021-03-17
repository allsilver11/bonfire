package com.jhta.bonfire.dao;

import java.util.List;

import com.jhta.bonfire.vo.LocalMapVo;
import com.jhta.bonfire.vo.TripPlanVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TripPlanDao {
    @Autowired private SqlSession sqlSession;
	private static final String NAMESPACE="com.jhta.bonfire.mapper.TripPlanMapper";
    
    public int addPlan(List<TripPlanVo> vo) {return sqlSession.insert(NAMESPACE+".addPlan", vo);}
    public int getIndex() {return sqlSession.selectOne(NAMESPACE+".getIndex");}
    public List<Integer> getIdxByUser(String id) {return sqlSession.selectList(NAMESPACE+".getIdxByUser", id);}
    public List<LocalMapVo> getPlanMapByUser(String id) {return sqlSession.selectList(NAMESPACE+".getPlanMapByUser", id);}
    public List<TripPlanVo> getPlanIdxByIdx(int idx) {return sqlSession.selectList(NAMESPACE+".getPlanIdxByIdx", idx);}
    public List<LocalMapVo> getPlanMapByIdx(int idx) {return sqlSession.selectList(NAMESPACE+".getPlanMapByIdx", idx);}
    public int removePlan(TripPlanVo vo) {return sqlSession.delete(NAMESPACE+".removePlan", vo);}
}
