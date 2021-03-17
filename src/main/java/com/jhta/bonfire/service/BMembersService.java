package com.jhta.bonfire.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jhta.bonfire.dao.BMembersDao;
import com.jhta.bonfire.security.CustomUserDetail;
import com.jhta.bonfire.vo.AuthVo;
import com.jhta.bonfire.vo.BMembersVo;

@Service
public class BMembersService {
	@Autowired 
	private PasswordEncoder encoder;
	@Autowired
	private BMembersDao dao;
	public CustomUserDetail userDetail(String id) {
		return dao.getAuthsList(id);
	}
	public int insert(BMembersVo vo) {
		String pwd=vo.getPwd();
		vo.setPwd(encoder.encode(pwd));
		dao.insert(vo);//ȸ�����̺� �߰�
		AuthVo avo=new AuthVo();
		avo.setId(vo.getId());
		if(vo.getId().equals("admin")) {
			avo.setAuthority("ROLE_ADMIN");
		}else {
			avo.setAuthority("ROLE_MEMBER");
		}
		dao.insert(avo);
		return 1;
	}
	public HashMap<String, Object> idCheck(String id) {
		return dao.idCheck(id);
	}
	public HashMap<String, Object> isMember(HashMap<String, String> map) {
		return dao.isMember(map);
	}
	public String searchId(HashMap<String, String> map) {
		return dao.searchId(map);
	}
	public String searchPwd(HashMap<String, String> map) {
		return dao.searchPwd(map);
	}
	
	public int delete(String id) {
		dao.a_delete(id);
		dao.b_delete(id);
		return 1;
	}
	public BMembersVo userInfo(String id) {
		return dao.userInfo(id);
	}
	public int update(BMembersVo vo) {
		return dao.update(vo);
	}
	
	public boolean pwdMatch(String id,String rawPwd) {
		String encodedPwd=dao.pwdMatch(id);
		return encoder.matches(rawPwd, encodedPwd);
	}
	public int updatePwd(String id,String pwd) {
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("id", id);
		map.put("pwd", encoder.encode(pwd));
		return dao.updatePwd(map);
	}
	public List<BMembersVo> selectAll(){
		return dao.selectAll();
	}
	
	public String getRole(String id) {
		return dao.getRole(id);
	}
}
