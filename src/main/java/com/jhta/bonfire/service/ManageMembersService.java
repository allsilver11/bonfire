package com.jhta.bonfire.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhta.bonfire.dao.ManageMembersDao;
import com.jhta.bonfire.vo.ManageMembersVo;

@Service
public class ManageMembersService {
	@Autowired
	private ManageMembersDao dao;
	
	public List<ManageMembersVo> selectAll(HashMap<String , Object> map){
		return dao.selectAll(map);
	}
	public ManageMembersVo selectOne(String id){
		return dao.selectOne(id);
	}
	public int count(HashMap<String , Object> map) {
		return dao.count(map);
	}
}
