package com.jhta.bonfire.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jhta.bonfire.vo.ManageMembersVo;

@Repository
public class ManageMembersDao {
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE="com.jhta.bonfire.mapper.ManageMembersMapper";
	public List<ManageMembersVo> selectAll(HashMap<String , Object> map){
		return sqlSession.selectList(NAMESPACE+".selectAll",map);
	}
	public ManageMembersVo selectOne(String id){
		return sqlSession.selectOne(NAMESPACE+".selectOne",id);
	}
	public int count(HashMap<String , Object> map) {
		return sqlSession.selectOne(NAMESPACE+".count",map);
	}
}
