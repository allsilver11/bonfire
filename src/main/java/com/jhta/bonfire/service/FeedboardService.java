package com.jhta.bonfire.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhta.bonfire.dao.FeedboardDao;
import com.jhta.bonfire.vo.FbcommentVo;
import com.jhta.bonfire.vo.FbrecommVo;
import com.jhta.bonfire.vo.FeedboardJoinScrapboardVo;
import com.jhta.bonfire.vo.FeedboardVo;
import com.jhta.bonfire.vo.ScrapboardVo;

@Service
public class FeedboardService {
	@Autowired private FeedboardDao dao;
	
	public int insertPosting(FeedboardVo vo, int ispost) {
		if(ispost==-1) {
			return dao.addPosting(vo);
		}else{
			return dao.postPosting(vo);
		}
	}
	
//	public int updatePostingStatus(int num) {
//		return dao.updatePostingStatus(num);
//	}
	
	public int modify(FeedboardVo vo,int judge) {
		if(judge==-1) {
			return dao.modifyA(vo);
		}else{
			return dao.modifyB(vo);
		}
	}
	
	public int count(HashMap<String,Object> map) {
		return dao.count(map);
	}
	
	public List<FeedboardVo> selectAll(HashMap<String,Object> map){
		return dao.selectAll(map);
	}
	public int count2(HashMap<String,Object> map) {
		return dao.count2(map);
	}
	
	public List<FeedboardVo> selectByRegion(HashMap<String,Object> map){
		return dao.selectByRegion(map);
	}
	
	public int countbyId(HashMap<String,Object> map) {
		return dao.countbyId(map);
	}
	
	public List<FeedboardVo> selectAllbyId(HashMap<String,Object> map){
		return dao.selectAllbyId(map);
	}
	public int countbyId2(HashMap<String,Object> map) {
		return dao.countbyId2(map);
	}
	
	public List<FeedboardVo> selectAllbyId2(HashMap<String,Object> map){
		return dao.selectAllbyId2(map);
	}
	
	public int updatePostingStatus(int num) {
		return dao.updatePostingStatus(num);
	}
	
	public int deletePosting(int num) {
		dao.deletePostingA(num);
		dao.deletePostingB(num);
		dao.deletePostingC(num);
		dao.deletePostingD(num);
		dao.deletePostingE(num);
		int f=dao.deletePostingF(num);
		return f;
	}

	public FeedboardVo selectOne(int num) {
		return dao.selectOne(num);
	}
	
	public List<FbcommentVo> showComm(int num){
		return dao.showComm(num);
	}
	
	public int insertComm(FbcommentVo vo) {
		return dao.insertComm(vo);
	}
	
	public int deleteComm(int idx) {
		return dao.deleteComm(idx);
	}
	public int editComm(HashMap<String, Object> map) {
		return dao.editComm(map);
	}
	
	public int selectRecomm(int num,String id) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("num", num);
		map.put("id",id);
		return dao.selectRecomm(map);
	}
	
	public int selectRecommTot(int num) {
		return dao.selectRecommTot(num);
	}
	public int insertRecomm(FbrecommVo vo,int num) {
		int a=dao.insertRecomm(vo);
		int b=0;
		if(vo.getValue()==1) {
			System.out.println("서비스 추천 누름");
			b=dao.updateRecomm(num);
		}else {
			System.out.println("서비스 추천 취소");
			b=dao.cancelRecomm(num);
		}
		if(a>0 && b>0) {
			return b;
		}else {
			return -1;
		}
	}
	public int insertHits(String id, int num) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("num", num);
		HashMap<String, Object> map1=dao.selectHits(map);
		if(map1==null) {
			int a=dao.insertHits(map);
			int b=dao.updateHits(num);
			if(a>0 && b>0) { 
				return b;
			}else {
				return -1;
			}
		}else {
			return -1;
		}
	}
	
	public int selectScrap(int num) {
		return dao.selectScrap(num);
	}
	public int checkScrap(int num,String id) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("num", num);
		map.put("id", id);
		return dao.checkScrap(map);
	}
	public int updateScrap(int num) {
		return dao.updateScrap(num);
	}
	public int insertScrapboard(ScrapboardVo vo) {
		return dao.insertScrapboard(vo);
	}
	public int countScrap(HashMap<String, Object> map) {
		return dao.countScrap(map);
	}
	public List<FeedboardJoinScrapboardVo> showScrapList(HashMap<String, Object> map) {
		return dao.showScrapList(map);
	}
	public int deleteScrap(int num,String id) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("num", num);
		map.put("id", id);
		return dao.deleteScrap(map);
	}
	
}
