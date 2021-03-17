package com.jhta.bonfire.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhta.bonfire.dao.FeedDao;
@Service
public class FeedService {
	@Autowired
	private FeedDao dao;
	public HashMap<String, Object> feedCheck(String feedId) {
		return dao.feedCheck(feedId);
	}
	public String roleCheck(String feedId) {
		return dao.roleCheck(feedId);
	}
	
}
