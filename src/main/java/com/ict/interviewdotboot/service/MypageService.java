package com.ict.interviewdotboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.interviewdotboot.mapper.MypageMapper;
import com.ict.interviewdotboot.vo.MypageVO;

@Service
public class MypageService {
    @Autowired
    private MypageMapper mypageMapper;

    public List<MypageVO> getinquiry(){
        return mypageMapper.getinquiry();
    }
}
