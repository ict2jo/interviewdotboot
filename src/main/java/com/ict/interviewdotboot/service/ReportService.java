package com.ict.interviewdotboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.interviewdotboot.mapper.ReportMapper;
import com.ict.interviewdotboot.vo.ReportVO;

@Service
public class ReportService {
    
    @Autowired
    private ReportMapper reportMapper;

    public int reportReview(ReportVO reportVO){
        return reportMapper.reportReview(reportVO);
    }
}
