package com.jhta.bonfire.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhta.bonfire.dao.DailyBoardDao;
import com.jhta.bonfire.vo.DailyBoardVo;
import com.jhta.bonfire.vo.DailyBoard_Dfile_JoinVo;

@Service
public class DailyBoardService {
	@Autowired private DailyBoardDao dao;
	
	public int insert(DailyBoard_Dfile_JoinVo vo) {
		return dao.insertPost(vo);
	}
	
	public int delete(int num, String id) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("num", num);
		map.put("id", id);
		return dao.deleteDaily(map);
	}
	
	public int count(HashMap<String, Object> map) {
		return dao.count(map);
	}
	
	public List<DailyBoardVo> listPost(HashMap<String, Object> map){
		return dao.listPost(map);
	}
	
	public DailyBoardVo listOne(int num) {
		return dao.listOne(num);
	}
	
	public int update(DailyBoardVo vo) {
		return dao.update(vo);
	}
}
