package com.ict.interviewdotboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.interviewdotboot.mapper.SuccessMapper;
import com.ict.interviewdotboot.vo.SuccessVO;

@Service
public class SuccessService {
   @Autowired
    private SuccessMapper successMapper;

    public List<SuccessVO> getSuccessList(){
        return successMapper.getSuccessList();
    }

    public List<SuccessVO> getSuccessDetail(){
        return successMapper.getSuccessDetail();
    }

    public boolean insertSuccess(SuccessVO successVO){
        int res = successMapper.insertSuccess(successVO);
        return res > 0;
    }

    public int updateSuccess(SuccessVO successVO){
        return successMapper.updateSuccess(successVO);
    }

    public int deleteSuccess(SuccessVO successVO){
        return successMapper.deleteSuccess(successVO);
    } 
}
