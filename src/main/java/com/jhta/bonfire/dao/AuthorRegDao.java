package com.jhta.bonfire.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jhta.bonfire.vo.AuthorRegVo;
import com.jhta.bonfire.vo.AuthorReg_Auth_JoinVo;

@Repository
public class AuthorRegDao {
	@Autowired private SqlSession session;
	private final String NAMESPACE = "com.jhta.bonfire.mapper.AuthorRegisterMapper";
	
	public List<AuthorReg_Auth_JoinVo> listAll(HashMap<String, Object> map){
		return session.selectList(NAMESPACE + ".selectAll", map);
	}
	
	public int count(HashMap<String, Object> map) {
		return session.selectOne(NAMESPACE + ".count", map);
	}
	
	public AuthorReg_Auth_JoinVo listOne(int num) {
		return session.selectOne(NAMESPACE + ".selectOne", num);
	}
	
	public int insert(AuthorRegVo vo) {
		return session.insert(NAMESPACE + ".insertAuthReg", vo);
	}
	
	public int delete(HashMap<String, Object> map) {
		return session.delete(NAMESPACE + ".delete", map);
	}
	
	public String exists(String idIn) {
		return session.selectOne(NAMESPACE + ".selectId", idIn);
	}
	
	public int approve(HashMap<String, Object> map) {
		return session.update(NAMESPACE + ".approve", map);
	}
	
	public int approveOne(String id) {
		return session.update(NAMESPACE + ".approveOne", id);
	}
	
	public int denyOne(int num) {
		return session.delete(NAMESPACE + ".deleteOne", num);
	}
}
