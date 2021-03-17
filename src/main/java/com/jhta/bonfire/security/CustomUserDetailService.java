package com.jhta.bonfire.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jhta.bonfire.service.BMembersService;
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
	private BMembersService service;
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		CustomUserDetail userDetail=service.userDetail(id);
		return userDetail;
	}
}
