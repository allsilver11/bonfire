package com.jhta.bonfire.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhta.bonfire.dao.MProfileDao;
import com.jhta.bonfire.vo.MProfileVo;

@Service
public class MProfileService {
	@Autowired
	private MProfileDao dao;
	public int insert(MProfileVo vo) {
		return dao.insert(vo);
	}
	public MProfileVo select(String id) {
		return dao.select(id);
	}
	public int updateProfile(MProfileVo vo) {
		return dao.updateProfile(vo);
	}
}
