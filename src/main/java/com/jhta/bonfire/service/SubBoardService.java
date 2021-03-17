package com.jhta.bonfire.service;

import java.util.HashMap;
import java.util.List;

import com.jhta.bonfire.vo.SCommDetail;
import com.jhta.bonfire.vo.SCommentVo;
import com.jhta.bonfire.vo.SRecommVo;
import com.jhta.bonfire.vo.SbhitsVo;
import com.jhta.bonfire.vo.SubBoardVo;

public interface SubBoardService {
    public int count(HashMap<String, Object> map);
    public List<SubBoardVo> getList(HashMap<String, Object> map);
    public List<String> catList(String cwhichboard);
    public SubBoardVo getData(int num);
    public int write(SubBoardVo vo);
    //hits
    public int addHit(SbhitsVo vo);
    //recomms
    public int setRecomm(SRecommVo vo);
    public int isRecommed(SRecommVo vo);
    //comments
    public List<SCommDetail> getComment(int num);
    public int getMin(int num);
    public int getMax(int num);
    public int addComment(SCommentVo vo);
    public int editComment(SCommentVo vo);
}
