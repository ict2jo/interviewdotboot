package com.ict.interviewdotboot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ict.interviewdotboot.vo.ReportVO;

@Mapper
public interface ReportMapper {
    int reportReview(ReportVO reportVO);
}
