package com.ict.interviewdotboot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ict.interviewdotboot.vo.ReportVO;

import java.util.List;

@Mapper
public interface ReportSuccMapper {
    List<ReportVO> getReportSuccList();
    int insertSuccReport(ReportVO reportVO);
    int updateSuccReport(ReportVO reportVO);
}
