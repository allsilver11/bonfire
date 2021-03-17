package com.jhta.bonfire.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jhta.bonfire.security.CustomUserDetail;
import com.jhta.bonfire.vo.AuthVo;
import com.jhta.bonfire.vo.BMembersVo;

@Repository
public class BMembersDao {
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE="com.jhta.bonfire.mapper.BMembersMapper";
	public BMembersVo userInfo(String id) {
		return sqlSession.selectOne(NAMESPACE+".userInfo",id);
	}
	public int update(BMembersVo vo) {
		return sqlSession.update(NAMESPACE+".update",vo);
	}
	public int updatePwd(HashMap<String,String> map) {
		return sqlSession.update(NAMESPACE+".updatePwd",map);
	}
	public CustomUserDetail getAuthsList(String userid){
		return sqlSession.selectOne(NAMESPACE+".getAuths",userid);
	}
	public int insert(BMembersVo vo) {
		return sqlSession.insert(NAMESPACE+".b_insert",vo);
	}
	public int insert(AuthVo vo) {
		return sqlSession.insert(NAMESPACE+".a_insert",vo);
	}
	public int b_delete(String id) {
		return sqlSession.delete(NAMESPACE+".b_delete",id);
	}
	public int a_delete(String id) {
		return sqlSession.delete(NAMESPACE+".a_delete",id);
	}
	public HashMap<String, Object> idCheck(String id) {
		return sqlSession.selectOne(NAMESPACE+".idCheck",id);
	}
	public HashMap<String, Object> isMember(HashMap<String, String> map){
		return sqlSession.selectOne(NAMESPACE+".isMember", map);
	}
	public String searchId(HashMap<String, String> map) {
		return sqlSession.selectOne(NAMESPACE+".searchId",map);
	}
	public String searchPwd(HashMap<String, String> map) {
		return sqlSession.selectOne(NAMESPACE+".searchPwd",map);
	}
	public String pwdMatch(String id) {
		return sqlSession.selectOne(NAMESPACE+".pwdMatch",id);
	}
	public List<BMembersVo> selectAll(){
		return sqlSession.selectList(NAMESPACE+".selectAll");
	}
	
	public String getRole(String id) {
		return sqlSession.selectOne(NAMESPACE + ".getRole", id);
	}
	
}
