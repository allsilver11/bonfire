package com.jhta.bonfire.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jhta.bonfire.vo.MProfileVo;

@Repository
public class MProfileDao {
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE="com.jhta.bonfire.mapper.MProfileMapper";
	public int insert(MProfileVo vo) {
		return sqlSession.insert(NAMESPACE+".insert",vo);
	}
	public MProfileVo select(String id) {
		return sqlSession.selectOne(NAMESPACE+".select",id);
	}
	public int updateProfile(MProfileVo vo) {
		return sqlSession.update(NAMESPACE+".updateProfile",vo);
	}
}
