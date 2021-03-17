package com.jhta.bonfire.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FeedDao {
	@Autowired private SqlSession sqlSession;
	private final String NAMESPACE="com.jhta.bonfire.mapper.FeedMapper";
	public HashMap<String, Object> feedCheck(String feedId) {
		return sqlSession.selectOne(NAMESPACE+".feedCheck",feedId);
	}
	public String roleCheck(String feedId) {
		return sqlSession.selectOne(NAMESPACE+".roleCheck",feedId);
	}
}
