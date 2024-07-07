package com.ict.interviewdotboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.interviewdotboot.mapper.OpenaiMapper;
import com.ict.interviewdotboot.vo.MyuserVO;
import com.ict.interviewdotboot.vo.ResumeVO;

@Service
public class OpenaiService {

    @Autowired
    private OpenaiMapper openaiMapper;

    public int introduceUpdate(ResumeVO resumeVO) {
        return openaiMapper.introduceUpdate(resumeVO);
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

    public List<ResumeVO> re_select(String u_idx) {
        return openaiMapper.re_select(u_idx);
    }
    
    public List<ResumeVO> re_select2(String resume_idx) {
        return openaiMapper.re_select2(resume_idx);
    }
    
    public int re_update(ResumeVO resumeVO) {
        return openaiMapper.re_update(resumeVO);
    }
    
    public int re_delete(ResumeVO resumeVO) {
        return openaiMapper.re_delete(resumeVO);
    }
    
}
