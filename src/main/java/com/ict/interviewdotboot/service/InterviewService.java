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

    public int deleteHistory(List<String> r_idxList) {
        return interviewMapper.deleteHistory(r_idxList);
    }

    public List<InterviewVO> getRandchoose(InterviewVO interviewVO) {
        return interviewMapper.getRandchoose(interviewVO);
    }

    public int getPayCount(InterviewVO interviewVO) {
        return interviewMapper.getPayCount(interviewVO);
    }

    @Transactional
    public int minusPayCount(InterviewVO interviewVO) {
        int res1 = interviewMapper.minusPayCount(interviewVO);
        int res2 = interviewMapper.payUpdate(interviewVO);
        
        if (res1 > 0 && res2 > 0) {
            return 1;
        } else {
            throw new RuntimeException("데이터베이스 업데이트 실패");
        }
    }

    public List<InterviewVO> searchQuestion(InterviewVO interviewVO) {
        return interviewMapper.searchQuestion(interviewVO);
    }
}
