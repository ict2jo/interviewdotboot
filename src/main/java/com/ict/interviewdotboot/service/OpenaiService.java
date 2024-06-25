package com.ict.interviewdotboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.interviewdotboot.mapper.OpenaiMapper;
import com.ict.interviewdotboot.vo.UserVO;

@Service
public class OpenaiService {

    @Autowired
    private OpenaiMapper openaiMapper;

    public int introduceUpdate(UserVO uvo) {
        return openaiMapper.introduceUpdate(uvo);
    }
    
}
