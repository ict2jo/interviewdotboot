package com.ict.interviewdotboot.service;

import com.ict.interviewdotboot.mapper.InterviewMapper;
import com.ict.interviewdotboot.vo.InterviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class InterviewService {

    @Autowired
    private InterviewMapper interviewMapper;

    public List<InterviewVO> getQuestionList(InterviewVO interviewVO) {
        return interviewMapper.getQuestionList(interviewVO);
    }

    public void insertTable(InterviewVO interviewVO) {
        interviewMapper.insertTable(interviewVO);
    }

    public void insertResult(InterviewVO interviewVO) {
        interviewMapper.insertResult(interviewVO);
    }

    public List<InterviewVO> getInterviewHistory(InterviewVO interviewVO) {
        return interviewMapper.getInterviewHistory(interviewVO);
    }

    public List<InterviewVO> getHistoryDetail(InterviewVO interviewVO) {
        return interviewMapper.getHistoryDetail(interviewVO);
    }
}
