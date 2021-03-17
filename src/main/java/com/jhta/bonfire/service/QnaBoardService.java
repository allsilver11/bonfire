package com.jhta.bonfire.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhta.bonfire.dao.QnaBoardDao;
import com.jhta.bonfire.vo.QhitsVo;
import com.jhta.bonfire.vo.QnaBoardVo;


@Service
public class QnaBoardService {
	@Autowired private QnaBoardDao dao;
	public int insert(QnaBoardVo vo) {
		return dao.insert(vo);
	}
	public int count(HashMap<String, Object> map) {
		return dao.count(map);
	}
	public List<QnaBoardVo> list(HashMap<String, Object> map){
		return dao.list(map);
	}
	public int delete(int num) {
		return dao.delete(num);
	}
	public QnaBoardVo select(int num) {
		return dao.select(num);
	}
	public HashMap<String, Object> selecthit(HashMap<String, Object> map) {
		return dao.selecthit(map);
	}
	public int updatecomm(HashMap<String, Object> map) {
		return dao.updatecomm(map);
	}
	public int qhits(QhitsVo vo, int num) {
		int a= dao.qhits(vo);
		int b= dao.addHit(num);
		if(a>0 && b>0) {
			return b;
		}else {
			return -1;
		}
	}
	public int update(QnaBoardVo vo) {
		return dao.update(vo);
	}
}
