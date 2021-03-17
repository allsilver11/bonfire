package com.jhta.bonfire.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jhta.bonfire.vo.DailyBoardVo;
import com.jhta.bonfire.vo.DailyBoard_Dfile_JoinVo;

@Repository
public class DailyBoardDao {
	@Autowired private SqlSession session;
	private final String NAMESPACE = "com.jhta.bonfire.mapper.DailyBoardMapper";
	
	public List<DailyBoardVo> listPost(HashMap<String, Object> map){
		return session.selectList(NAMESPACE + ".selectPost", map);
	}
	
	public int count(HashMap<String, Object> map) {
		return session.selectOne(NAMESPACE + ".count", map);
	}
	
	public int insertPost(DailyBoard_Dfile_JoinVo vo) {
		return session.insert(NAMESPACE + ".insertDaily", vo);
	}
	
	public int deleteDaily(HashMap<String, Object> map) {
		return session.delete(NAMESPACE + ".delete", map);
	}
	
	public DailyBoardVo listOne(int num) {
		return session.selectOne(NAMESPACE + ".selectOne", num);
	}
	
	public int update(DailyBoardVo vo) {
		return session.update(NAMESPACE + ".updatePost", vo);
	}
}
