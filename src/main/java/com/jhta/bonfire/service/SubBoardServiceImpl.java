package com.jhta.bonfire.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.jhta.bonfire.dao.CatsDao;
import com.jhta.bonfire.dao.SCommentDao;
import com.jhta.bonfire.dao.SRecommDao;
import com.jhta.bonfire.dao.SbhitsDao;
import com.jhta.bonfire.dao.SubBoardDao;
import com.jhta.bonfire.vo.SCommDetail;
import com.jhta.bonfire.vo.SCommentVo;
import com.jhta.bonfire.vo.SRecommVo;
import com.jhta.bonfire.vo.SbhitsVo;
import com.jhta.bonfire.vo.SubBoardVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubBoardServiceImpl implements SubBoardService {
	@Autowired private SubBoardDao subBoardDao;
	@Autowired private CatsDao catsDao;
	@Autowired private SbhitsDao sbhitsDao;
	@Autowired private SRecommDao srecommDao;
	@Autowired private SCommentDao sCommentDao;

	@Override
	public int count(HashMap<String, Object> map) {
		return subBoardDao.count(map);
	}

	@Override
	public List<SubBoardVo> getList(HashMap<String, Object> map) {
		return subBoardDao.getList(map);
	}

	@Override
	public List<String> catList(String cwhichboard) {
		return catsDao.catList(cwhichboard);
	}

	@Transactional
	@Override
	public int addHit(SbhitsVo vo) {
		sbhitsDao.addHit(vo);
		return subBoardDao.addHit(vo.getNum());
	}

	@Transactional
	@Override
	public int setRecomm(SRecommVo vo) {
		srecommDao.setRecomm(vo);
		return subBoardDao.recomm(vo);
	}

	@Override
	public SubBoardVo getData(int num) {
		return subBoardDao.getData(num);
	}

	@Override
	public int isRecommed(SRecommVo vo) {
		return srecommDao.isRecommed(vo);
	}

	@Override
	public int write(SubBoardVo vo) {
		return subBoardDao.write(vo);
	}

	@Override
	public List<SCommDetail> getComment(int num) {
		return sCommentDao.getComment(num);
	}

	@Override
	public int getMin(int num) {
		return Optional.ofNullable(sCommentDao.getMin(num)).orElse(0);
	}

	@Override
	public int getMax(int num) {
		return Optional.ofNullable(sCommentDao.getMax(num)).orElse(0);
	}

	@Override
	public int addComment(SCommentVo vo) {
		return sCommentDao.addComment(vo);
	}

	@Override
	public int editComment(SCommentVo vo) {
		return editComment(vo);
	}

	
	
}
