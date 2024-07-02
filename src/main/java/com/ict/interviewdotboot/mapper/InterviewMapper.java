package com.ict.interviewdotboot.mapper;

import com.ict.interviewdotboot.vo.InterviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InterviewMapper {

    List<InterviewVO> getQuestionList(InterviewVO interviewVO);

    void insertTable(InterviewVO interviewVO);

    void insertResult(InterviewVO interviewVO);

    List<InterviewVO> getInterviewHistory(InterviewVO interviewVO);

    List<InterviewVO> getHistoryDetail(InterviewVO interviewVO);

    int deleteHistory(@Param("r_idxList") List<String> r_idxList);

    List<InterviewVO> getRandchoose(InterviewVO interviewVO);

    int getPayCount(InterviewVO interviewVO);

    int minusPayCount(InterviewVO interviewVO);

    int payUpdate(InterviewVO interviewVO);
}
