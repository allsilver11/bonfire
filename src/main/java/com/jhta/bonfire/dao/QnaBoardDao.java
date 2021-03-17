package com.jhta.bonfire.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jhta.bonfire.vo.QhitsVo;
import com.jhta.bonfire.vo.QnaBoardVo;

@Repository
public class QnaBoardDao {
	@Autowired private SqlSession sqlSession;
	
	private static final String NAMESPACE="com.jhta.bonfire.mapper.QnaBoardMapper";
	public int insert(QnaBoardVo vo) {
		return sqlSession.insert(NAMESPACE +".insert",vo);
	}
	public int count(HashMap<String, Object> map) {
		return sqlSession.selectOne(NAMESPACE+".count",map);
	}
	public List<QnaBoardVo> list(HashMap<String, Object> map){
		return sqlSession.selectList(NAMESPACE+".list",map);
	}
	public int delete(int num) {
		return sqlSession.delete(NAMESPACE+".delete",num);
	}
	public QnaBoardVo select(int num) {
		return sqlSession.selectOne(NAMESPACE+".select",num);
	}
	public int updatecomm(HashMap<String, Object> map) {
		return sqlSession.update(NAMESPACE+".updatecomm",map);
	}
	public HashMap<String, Object> selecthit(HashMap<String, Object> map) {
		return sqlSession.selectOne(NAMESPACE+".selecthit",map);
	}
	public int qhits(QhitsVo vo) {
		return sqlSession.insert(NAMESPACE+".qhits",vo);
	}
	public int addHit(int num) {
		return sqlSession.update(NAMESPACE+".addHit",num);
	}
	public int update(QnaBoardVo vo) {
		return sqlSession.update(NAMESPACE+".update",vo);
	}
}
