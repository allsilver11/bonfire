package com.jhta.bonfire.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jhta.bonfire.vo.AuthVo;


public class CustomUserDetail implements UserDetails{ //인증객체
	private String id;
	private String pwd;
	private String enabled;
	private List<AuthVo> authoList;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	//해당 회원의 권한들을 불러온다.
		ArrayList<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();	//collection 의 자식 arraylist로 return
		for(AuthVo vo:authoList) {										//상향 for문으로 권한들을 vo객체에서 가져와서 auths arraylist에 담아줌
			auths.add(new SimpleGrantedAuthority(vo.getAuthority()));
		}
		return auths;
	}

	@Override
	public String getPassword() {//password = pwd 우리들의 컬럼명
		return pwd;
	}

	@Override
	public String getUsername() {	//username = id 우리들의 컬럼명
		return id;
	}

	@Override
	public boolean isAccountNonExpired() { //계정이 만료되었는지 만료되지않았는지
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // 계정이 잠겨있는지
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	} 
	
}
