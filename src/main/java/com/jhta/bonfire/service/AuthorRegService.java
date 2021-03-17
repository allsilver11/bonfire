package com.jhta.bonfire.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhta.bonfire.dao.AuthorRegDao;
import com.jhta.bonfire.vo.AuthorRegVo;
import com.jhta.bonfire.vo.AuthorReg_Auth_JoinVo;

@Service
public class AuthorRegService {
	@Autowired private AuthorRegDao dao;
	
	public List<AuthorReg_Auth_JoinVo> listAll(HashMap<String, Object> map){
		return dao.listAll(map);
	}
	
	public int count(HashMap<String, Object> map) {
		return dao.count(map);
	}
	
	public AuthorReg_Auth_JoinVo listOne(int num) {
		return dao.listOne(num);
	}
	
	public int insert(AuthorRegVo vo) {
		return dao.insert(vo);
	}
	
	public int delete(int num) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		return dao.delete(map);
	}
	
	public String exists(String idIn) {
		return dao.exists(idIn);
	}
	
	public int approve(String id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return dao.approve(map);
	}
	
	public int approveOne(String id) {
		return dao.approveOne(id);
	}
	
	public int denyOne(int num) {
		return dao.denyOne(num);
	}
}
