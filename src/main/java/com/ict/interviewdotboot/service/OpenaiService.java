package com.ict.interviewdotboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.interviewdotboot.mapper.OpenaiMapper;
import com.ict.interviewdotboot.vo.MyuserVO;
import com.ict.interviewdotboot.vo.ResumeVO;
import com.ict.interviewdotboot.vo.UserVO;

@Service
public class OpenaiService {

    @Autowired
    private OpenaiMapper openaiMapper;

    public int introduceUpdate(UserVO uvo) {
        return openaiMapper.introduceUpdate(uvo);
    }

    @Transactional
    public int update(MyuserVO myuserVO) {
        int result1 = openaiMapper.update1(myuserVO);
        int result2 = openaiMapper.update2(myuserVO);
        
        if (result1 > 0 && result2 > 0) {
            return 1;
        } else {
            throw new RuntimeException("데이터베이스 업데이트 실패"); 
        }
    }

    public int insert(ResumeVO resumeVO) {
        return openaiMapper.insert(resumeVO);
    }
    
}
