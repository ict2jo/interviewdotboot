package com.ict.interviewdotboot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ict.interviewdotboot.vo.ReportVO;
import java.util.List;


@Mapper
public interface ReportMapper {
    List<ReportVO> getReportList();
    int insertReport(ReportVO reportVO);
    int updateReport(ReportVO reportVO);
}
