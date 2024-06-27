package com.ict.interviewdotboot.mapper;

import com.ict.interviewdotboot.vo.InterviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InterviewMapper {

    List<InterviewVO> getQuestionList(InterviewVO interviewVO);

    void insertResult(InterviewVO interviewVO);

    void insertTable(InterviewVO interviewVO);

}
