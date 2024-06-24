package com.ict.interviewdotboot.service;

import com.ict.interviewdotboot.mapper.InterviewMapper;
import com.ict.interviewdotboot.vo.InterviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {

    @Autowired
    private InterviewMapper interviewMapper;

    public List<InterviewVO> getQuestionList(InterviewVO interviewVO) {
        return interviewMapper.getQuestionList(interviewVO);
    }

}
